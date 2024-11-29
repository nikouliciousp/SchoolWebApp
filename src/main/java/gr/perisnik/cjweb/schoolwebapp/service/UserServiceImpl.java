package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dao.IUserDAO;
import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;
import gr.perisnik.cjweb.schoolwebapp.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the IUserService interface.
 * Provides methods for performing operations on User objects.
 *
 * @version 1.2
 * @autor Peris Nik
 */
public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(UserDTO user) throws UserServiceException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        try {
            if (userDAO.isUserExists(user.getUsername()) == null) {
                userDAO.addUser(new User(user.getUsername(), hashedPassword));
            } else {
                throw new UserServiceException("Username already exists");
            }
        } catch (Exception e) {
            throw new UserServiceException("Error adding user", e);
        }
    }

    @Override
    public void updateUser(UserDTO user) throws UserServiceException, UserNotFoundException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        try {
            userDAO.updateUser(new User(user.getUsername(), hashedPassword));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error updating user", e);
        }
    }

    @Override
    public void deleteUserByUsername(String username) throws UserServiceException, UserNotFoundException {
        try {
            userDAO.deleteUserByUsername(username);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error deleting user", e);
        }
    }

    @Override
    public UserDTO getUserById(int userId) throws UserServiceException, UserNotFoundException {
        try {
            User user = userDAO.getUserById(userId);
            return new UserDTO(user.getUsername(), user.getPassword());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error retrieving user", e);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() throws UserServiceException {
        try {
            List<User> users = userDAO.getAllUsers();
            return users.stream()
                    .map(user -> new UserDTO(user.getUsername(), user.getPassword()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserServiceException("Error retrieving users", e);
        }
    }

    @Override
    public List<UserDTO> getUserByUsername(String username) throws UserServiceException, UserNotFoundException {
        try {
            List<User> users = userDAO.getUserByUsername(username);
            if (users.isEmpty()) {
                throw new UserNotFoundException("No users found with username containing " + username);
            }
            return users.stream()
                    .map(user -> new UserDTO(user.getUsername(), user.getPassword()))
                    .collect(Collectors.toList());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error retrieving users", e);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserServiceException, UserNotFoundException {
        try {
            UserDTO userDTO;
            if ((userDTO = isUserExists(username)) == null) {
                throw new UserNotFoundException("User not found");
            }

            return BCrypt.checkpw(password, userDTO.getPassword());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error validating user", e);
        }
    }

    @Override
    public UserDTO isUserExists(String username) throws UserServiceException, UserNotFoundException {
        try {
            User user = userDAO.isUserExists(username);
            return new UserDTO(user.getUsername(), user.getPassword());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new UserServiceException("Error retrieving user", e);
        }
    }
}