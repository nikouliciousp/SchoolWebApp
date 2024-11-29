package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.Teacher;
import java.util.List;

/**
 * Interface for Data Access Object (DAO) for the Teacher entity.
 * Provides methods for performing CRUD operations on Teacher objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public interface ITeacherDAO {

    /**
     * Adds a new teacher to the storage.
     *
     * @param teacher the teacher to be added
     */
    void addTeacher(Teacher teacher);

    /**
     * Updates the details of an existing teacher.
     *
     * @param teacher the teacher with updated details
     * @throws TeacherNotFoundException if the teacher with the specified ID is not found
     */
    void updateTeacher(Teacher teacher) throws TeacherNotFoundException;

    /**
     * Deletes a teacher from the storage.
     *
     * @param teacherId the ID of the teacher to be deleted
     * @throws TeacherNotFoundException if the teacher with the specified ID is not found
     */
    void deleteTeacher(int teacherId) throws TeacherNotFoundException;

    /**
     * Retrieves a teacher by their ID.
     *
     * @param teacherId the ID of the teacher to be retrieved
     * @return the teacher with the specified ID
     * @throws TeacherNotFoundException if the teacher with the specified ID is not found
     */
    Teacher getTeacherById(int teacherId) throws TeacherNotFoundException;

    /**
     * Retrieves all teachers from the storage.
     *
     * @return a list of all teachers
     */
    List<Teacher> getAllTeachers();

    /**
     * Retrieves a teacher by their lastname.
     *
     * @param lastname the lastname of the teacher to be retrieved
     * @return a list of all teachers
     * @throws TeacherNotFoundException if the teacher with the specified lastname is not found
     */
    List<Teacher> getTeacherByLastname(String lastname) throws TeacherNotFoundException;
}