package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.DBUtil.DBUtil;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IStudentDAO interface.
 * Provides methods for performing CRUD operations on Student objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (firstname, lastname, grade) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getGrade());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) throws StudentNotFoundException {
        String sql = "UPDATE students SET firstname = ?, lastname = ?, grade = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getGrade());
            ps.setInt(4, student.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new StudentNotFoundException("Student with ID " + student.getId() + " not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int studentId) throws StudentNotFoundException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new StudentNotFoundException("Student with ID " + studentId + " not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int studentId) throws StudentNotFoundException {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("grade")
                    );
                } else {
                    throw new StudentNotFoundException("Student with ID " + studentId + " not found");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getStudentByLastname(String lastname) throws StudentNotFoundException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE lastname LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + lastname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("grade")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found with lastname containing " + lastname);
        }
        return students;
    }
}