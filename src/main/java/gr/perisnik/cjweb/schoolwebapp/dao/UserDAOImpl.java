package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.DBUtil.DBUtil;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IUserDAO interface.
 * Provides methods for performing CRUD operations on User objects.
 *
 * @version 1.3
 * @author Peris Nik
 */
public class UserDAOImpl implements IUserDAO {

    @Override
    public void addUser(User user) throws UserNotFoundException {
        // Check if the username already exists
        if (isUserExists(user.getUsername()) != null) {
            throw new UserNotFoundException("Username " + user.getUsername() + " already exists");
        }

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        // Check if the username exists
        if (isUserExists(user.getUsername()) == null) {
            throw new UserNotFoundException("User with username " + user.getUsername() + " not found");
        }

        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User with username " + username + " not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int userId) throws UserNotFoundException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("username"),
                            rs.getString("password")
                    );
                } else {
                    throw new UserNotFoundException("User with ID " + userId + " not found");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getUserByUsername(String username) throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + username + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with username containing " + username);
        }
        return users;
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserNotFoundException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    throw new UserNotFoundException("User with username " + username + " and provided password not found");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User isUserExists(String username) throws UserNotFoundException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("username"),
                            rs.getString("password")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}