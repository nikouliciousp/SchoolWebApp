package gr.perisnik.cjweb.schoolwebapp.service;

import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;

import java.util.List;

/**
 * Interface for User Service.
 * Provides methods for performing operations on User objects.
 *
 * @version 1.1
 * @autor Peris Nik
 */
public interface IUserService {

    /**
     * Adds a new user.
     *
     * @param user the user to be added
     * @throws UserServiceException if there is an error during the operation
     */
    void addUser(UserDTO user) throws UserServiceException;

    /**
     * Updates the details of an existing user.
     *
     * @param user the user with updated details
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if the user is not found
     */
    void updateUser(UserDTO user) throws UserServiceException, UserNotFoundException;

    /**
     * Deletes a user by their ID.
     *
     * @param username the username of the user to be deleted
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if the user is not found
     */
    void deleteUserByUsername(String username) throws UserServiceException, UserNotFoundException;

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to be retrieved
     * @return the user with the specified ID
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if the user is not found
     */
    UserDTO getUserById(int userId) throws UserServiceException, UserNotFoundException;

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     * @throws UserServiceException if there is an error during the operation
     */
    List<UserDTO> getAllUsers() throws UserServiceException;

    /**
     * Retrieves users by their username.
     *
     * @param username the username of the users to be retrieved
     * @return a list of users with the specified username
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if no users are found with the specified username
     */
    List<UserDTO> getUserByUsername(String username) throws UserServiceException, UserNotFoundException;

    /**
     * Validates a user based on username and password.
     * @param username the username of the user to be validated
     * @param password the password of the user to be validated
     * @return true if the user is valid, false otherwise
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if the user is not found
     */
    public boolean isUserValid(String username, String password) throws UserServiceException, UserNotFoundException;

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return the user with the specified ID
     * @throws UserServiceException if there is an error during the operation
     * @throws UserNotFoundException if the user is not found
     */
    UserDTO isUserExists(String username) throws UserServiceException, UserNotFoundException;
}