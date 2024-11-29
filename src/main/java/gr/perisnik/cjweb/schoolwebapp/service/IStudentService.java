package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dto.StudentDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.StudentServiceException;

import java.util.List;

/**
 * Interface for Student Service.
 * Provides methods for performing operations on Student objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public interface IStudentService {

    /**
     * Adds a new student.
     *
     * @param student the student to be added
     * @throws StudentServiceException if there is an error during the operation
     */
    void addStudent(StudentDTO student) throws StudentServiceException;

    /**
     * Updates the details of an existing student.
     *
     * @param student the student with updated details
     * @throws StudentServiceException if there is an error during the operation
     * @throws StudentNotFoundException if the student is not found
     */
    void updateStudent(StudentDTO student) throws StudentServiceException, StudentNotFoundException;

    /**
     * Deletes a student by their ID.
     *
     * @param studentId the ID of the student to be deleted
     * @throws StudentServiceException if there is an error during the operation
     * @throws StudentNotFoundException if the student is not found
     */
    void deleteStudent(int studentId) throws StudentServiceException, StudentNotFoundException;

    /**
     * Retrieves a student by their ID.
     *
     * @param studentId the ID of the student to be retrieved
     * @return the student with the specified ID
     * @throws StudentServiceException if there is an error during the operation
     * @throws StudentNotFoundException if the student is not found
     */
    StudentDTO getStudentById(int studentId) throws StudentServiceException, StudentNotFoundException;

    /**
     * Retrieves all students.
     *
     * @return a list of all students
     * @throws StudentServiceException if there is an error during the operation
     */
    List<StudentDTO> getAllStudents() throws StudentServiceException;

    /**
     * Retrieves students by their lastname.
     *
     * @param lastname the lastname of the students to be retrieved
     * @return a list of students with the specified lastname
     * @throws StudentServiceException if there is an error during the operation
     * @throws StudentNotFoundException if no students are found with the specified lastname
     */
    List<StudentDTO> getStudentByLastname(String lastname) throws StudentServiceException, StudentNotFoundException;
}