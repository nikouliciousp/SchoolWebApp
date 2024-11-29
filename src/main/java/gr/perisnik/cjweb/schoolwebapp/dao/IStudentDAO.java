package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.Student;
import java.util.List;

/**
 * Interface for Data Access Object (DAO) for the Student entity.
 * Provides methods for performing CRUD operations on Student objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public interface IStudentDAO {

    /**
     * Adds a new student to the storage.
     *
     * @param student the student to be added
     */
    void addStudent(Student student);

    /**
     * Updates the details of an existing student.
     *
     * @param student the student with updated details
     * @throws StudentNotFoundException if the student with the specified ID is not found
     */
    void updateStudent(Student student) throws StudentNotFoundException;

    /**
     * Deletes a student from the storage.
     *
     * @param studentId the ID of the student to be deleted
     * @throws StudentNotFoundException if the student with the specified ID is not found
     */
    void deleteStudent(int studentId) throws StudentNotFoundException;

    /**
     * Retrieves a student by their ID.
     *
     * @param studentId the ID of the student to be retrieved
     * @return the student with the specified ID
     * @throws StudentNotFoundException if the student with the specified ID is not found
     */
    Student getStudentById(int studentId) throws StudentNotFoundException;

    /**
     * Retrieves all students from the storage.
     *
     * @return a list of all students
     */
    List<Student> getAllStudents();

    /**
     * Retrieves a student by their lastname.
     *
     * @param lastname the lastname of the student to be retrieved
     * @return a list of all students
     * @throws StudentNotFoundException if the student with the specified lastname is not found
     */
    List<Student> getStudentByLastname(String lastname) throws StudentNotFoundException;
}