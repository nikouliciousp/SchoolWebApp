package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.DBUtil.DBUtil;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ITeacherDAO interface.
 * Provides methods for performing CRUD operations on Teacher objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws TeacherNotFoundException {
        String sql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setInt(3, teacher.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new TeacherNotFoundException("Teacher with ID " + teacher.getId() + " not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws TeacherNotFoundException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new TeacherNotFoundException("Teacher with ID " + teacherId + " not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws TeacherNotFoundException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Teacher(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname")
                    );
                } else {
                    throw new TeacherNotFoundException("Teacher with ID " + teacherId + " not found");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Teacher> getTeacherByLastname(String lastname) throws TeacherNotFoundException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers WHERE lastname LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + lastname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    teachers.add(new Teacher(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (teachers.isEmpty()) {
            throw new TeacherNotFoundException("No teachers found with lastname containing " + lastname);
        }
        return teachers;
    }
}