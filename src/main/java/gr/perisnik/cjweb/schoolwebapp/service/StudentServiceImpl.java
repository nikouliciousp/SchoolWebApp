package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dao.IStudentDAO;
import gr.perisnik.cjweb.schoolwebapp.dto.StudentDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.StudentServiceException;
import gr.perisnik.cjweb.schoolwebapp.model.Student;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the IStudentService interface.
 * Provides methods for performing operations on Student objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public class StudentServiceImpl implements IStudentService {

    private IStudentDAO studentDAO;

    public StudentServiceImpl(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public void addStudent(StudentDTO student) throws StudentServiceException {
        try {
            studentDAO.addStudent(new Student(student.getId(), student.getFirstname(), student.getLastname(), student.getGrade()));
        } catch (Exception e) {
            throw new StudentServiceException("Error adding student", e);
        }
    }

    @Override
    public void updateStudent(StudentDTO student) throws StudentServiceException, StudentNotFoundException {
        try {
            studentDAO.updateStudent(new Student(student.getId(), student.getFirstname(), student.getLastname(), student.getGrade()));
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException("Student not found", e);
        } catch (Exception e) {
            throw new StudentServiceException("Error updating student", e);
        }
    }

    @Override
    public void deleteStudent(int studentId) throws StudentServiceException, StudentNotFoundException {
        try {
            studentDAO.deleteStudent(studentId);
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException("Student not found", e);
        } catch (Exception e) {
            throw new StudentServiceException("Error deleting student", e);
        }
    }

    @Override
    public StudentDTO getStudentById(int studentId) throws StudentServiceException, StudentNotFoundException {
        try {
            Student student = studentDAO.getStudentById(studentId);
            return new StudentDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getGrade());
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException("Student not found", e);
        } catch (Exception e) {
            throw new StudentServiceException("Error retrieving student", e);
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() throws StudentServiceException {
        try {
            List<Student> students = studentDAO.getAllStudents();
            return students.stream()
                    .map(student -> new StudentDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getGrade()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new StudentServiceException("Error retrieving students", e);
        }
    }

    @Override
    public List<StudentDTO> getStudentByLastname(String lastname) throws StudentServiceException, StudentNotFoundException {
        try {
            List<Student> students = studentDAO.getStudentByLastname(lastname);
            if (students.isEmpty()) {
                throw new StudentNotFoundException("No students found with lastname containing " + lastname);
            }
            return students.stream()
                    .map(student -> new StudentDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getGrade()))
                    .collect(Collectors.toList());
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException("Student not found", e);
        } catch (Exception e) {
            throw new StudentServiceException("Error retrieving students", e);
        }
    }
}