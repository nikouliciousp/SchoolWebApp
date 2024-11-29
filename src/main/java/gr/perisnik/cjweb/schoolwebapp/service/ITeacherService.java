package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dto.TeacherDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.TeacherServiceException;

import java.util.List;

/**
 * Interface for Teacher Service.
 * Provides methods for performing operations on Teacher objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public interface ITeacherService {

    /**
     * Adds a new teacher.
     *
     * @param teacher the teacher to be added
     * @throws TeacherServiceException if there is an error during the operation
     */
    void addTeacher(TeacherDTO teacher) throws TeacherServiceException;

    /**
     * Updates the details of an existing teacher.
     *
     * @param teacher the teacher with updated details
     * @throws TeacherServiceException if there is an error during the operation
     * @throws TeacherNotFoundException if the teacher is not found
     */
    void updateTeacher(TeacherDTO teacher) throws TeacherServiceException, TeacherNotFoundException;

    /**
     * Deletes a teacher by their ID.
     *
     * @param teacherId the ID of the teacher to be deleted
     * @throws TeacherServiceException if there is an error during the operation
     * @throws TeacherNotFoundException if the teacher is not found
     */
    void deleteTeacher(int teacherId) throws TeacherServiceException, TeacherNotFoundException;

    /**
     * Retrieves a teacher by their ID.
     *
     * @param teacherId the ID of the teacher to be retrieved
     * @return the teacher with the specified ID
     * @throws TeacherServiceException if there is an error during the operation
     * @throws TeacherNotFoundException if the teacher is not found
     */
    TeacherDTO getTeacherById(int teacherId) throws TeacherServiceException, TeacherNotFoundException;

    /**
     * Retrieves all teachers.
     *
     * @return a list of all teachers
     * @throws TeacherServiceException if there is an error during the operation
     */
    List<TeacherDTO> getAllTeachers() throws TeacherServiceException;

    /**
     * Retrieves teachers by their lastname.
     *
     * @param lastname the lastname of the teachers to be retrieved
     * @return a list of teachers with the specified lastname
     * @throws TeacherServiceException if there is an error during the operation
     * @throws TeacherNotFoundException if no teachers are found with the specified lastname
     */
    List<TeacherDTO> getTeacherByLastname(String lastname) throws TeacherServiceException, TeacherNotFoundException;
}