package gr.perisnik.cjweb.schoolwebapp.dao;

import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.model.User;
import java.util.List;

/**
 * Interface for Data Access Object (DAO) for the User entity.
 * Provides methods for performing CRUD operations on User objects.
 *
 * @version 1.2
 * @autor Peris Nik
 */
public interface IUserDAO {

    /**
     * Adds a new user to the storage.
     *
     * @param user the user to be added
     * @throws UserNotFoundException if the user with the specified username is not found
     */
    void addUser(User user) throws UserNotFoundException;

    /**
     * Updates the details of an existing user.
     *
     * @param user the user with updated details
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    void updateUser(User user) throws UserNotFoundException;

    /**
     * Deletes a user from the storage.
     *
     * @param username the username of the user to be deleted
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    void deleteUserByUsername(String username) throws UserNotFoundException;

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to be retrieved
     * @return the user with the specified ID
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    User getUserById(int userId) throws UserNotFoundException;

    /**
     * Retrieves all users from the storage.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Retrieves users by their username.
     *
     * @param username the username of the user to be retrieved
     * @return a list of all users
     * @throws UserNotFoundException if the user with the specified username is not found
     */
    List<User> getUserByUsername(String username) throws UserNotFoundException;

    /**
     * Checks if the user is valid based on username and password.
     *
     * @param username the username of the user to be checked
     * @param password the password of the user to be checked
     * @return true if the user is valid, false otherwise
     * @throws UserNotFoundException if the user with the specified username and password is not found
     */
    boolean isUserValid(String username, String password) throws UserNotFoundException;

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return a list of all users
     * @throws UserNotFoundException if the user with the specified username is not found
     */
    User isUserExists(String username) throws UserNotFoundException;
}