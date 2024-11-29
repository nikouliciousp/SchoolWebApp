package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dao.ITeacherDAO;
import gr.perisnik.cjweb.schoolwebapp.dto.TeacherDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.TeacherServiceException;
import gr.perisnik.cjweb.schoolwebapp.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the ITeacherService interface.
 * Provides methods for performing operations on Teacher objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public class TeacherServiceImpl implements ITeacherService {

    private ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public void addTeacher(TeacherDTO teacher) throws TeacherServiceException {
        if (teacher == null) {
            return;
        }

        try {
            teacherDAO.addTeacher(new Teacher(teacher.getId(), teacher.getFirstname(), teacher.getLastname()));
        } catch (Exception e) {
            throw new TeacherServiceException("Error adding teacher", e);
        }
    }

    @Override
    public void updateTeacher(TeacherDTO teacher) throws TeacherServiceException, TeacherNotFoundException {
        if (teacher == null) {
            return;
        }

        try {
            teacherDAO.updateTeacher(new Teacher(teacher.getId(), teacher.getFirstname(), teacher.getLastname()));
        } catch (TeacherNotFoundException e) {
            throw new TeacherNotFoundException("Teacher not found", e);
        } catch (Exception e) {
            throw new TeacherServiceException("Error updating teacher", e);
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws TeacherServiceException, TeacherNotFoundException {
        try {
            teacherDAO.deleteTeacher(teacherId);
        } catch (TeacherNotFoundException e) {
            throw new TeacherNotFoundException("Teacher not found", e);
        } catch (Exception e) {
            throw new TeacherServiceException("Error deleting teacher", e);
        }
    }

    @Override
    public TeacherDTO getTeacherById(int teacherId) throws TeacherServiceException, TeacherNotFoundException {
        try {
            Teacher teacher = teacherDAO.getTeacherById(teacherId);
            return new TeacherDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
        } catch (TeacherNotFoundException e) {
            throw new TeacherNotFoundException("Teacher not found", e);
        } catch (Exception e) {
            throw new TeacherServiceException("Error retrieving teacher", e);
        }
    }

    @Override
    public List<TeacherDTO> getAllTeachers() throws TeacherServiceException {
        try {
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            return teachers.stream()
                    .map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new TeacherServiceException("Error retrieving teachers", e);
        }
    }

    @Override
    public List<TeacherDTO> getTeacherByLastname(String lastname) throws TeacherServiceException, TeacherNotFoundException {
        try {
            List<Teacher> teachers = teacherDAO.getTeacherByLastname(lastname);
            if (teachers.isEmpty()) {
                throw new TeacherNotFoundException("No teachers found with lastname containing " + lastname);
            }
            return teachers.stream()
                    .map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname()))
                    .collect(Collectors.toList());
        } catch (TeacherNotFoundException e) {
            throw new TeacherNotFoundException("Teacher not found", e);
        } catch (Exception e) {
            throw new TeacherServiceException("Error retrieving teachers", e);
        }
    }
}