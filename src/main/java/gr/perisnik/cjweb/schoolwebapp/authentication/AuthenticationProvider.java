package gr.perisnik.cjweb.schoolwebapp.authentication;

import gr.perisnik.cjweb.schoolwebapp.service.IUserService;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;

/**
 * Provides authentication services for users.
 * Uses the IUserService to validate users.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class AuthenticationProvider {

    private static AuthenticationProvider instance;
    private IUserService userService;

    // Private constructor prevents instantiation from other classes
    private AuthenticationProvider(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the singleton instance of AuthenticationProvider.
     *
     * @param userService the IUserService instance to be used
     * @return the singleton instance of AuthenticationProvider
     */
    public static AuthenticationProvider getInstance(IUserService userService) {
        if (instance == null) {
            instance = new AuthenticationProvider(userService);
        }
        return instance;
    }

    /**
     * Authenticates a user based on username and password.
     *
     * @param username the username of the user to be authenticated
     * @param password the password of the user to be authenticated
     * @return true if the user is authenticated, false otherwise
     * @throws UserServiceException if there is an error during the operation
     */
    public boolean authenticate(String username, String password) throws UserServiceException {
        try {
            return userService.isUserValid(username, password);
        } catch (UserNotFoundException e) {
            throw new UserServiceException("Authentication failed", e);
        }
    }

    /**
     * Checks if a user exists in the system based on username.
     *
     * @param username the username of the user to be checked
     * @return true if the user exists, false otherwise
     * @throws UserServiceException if there is an error during the operation
     */
    public boolean doesUserExist(String username) throws UserServiceException {
        try {
            userService.isUserExists(username);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        } catch (Exception e) {
            throw new UserServiceException("Error checking if user exists", e);
        }
    }
}