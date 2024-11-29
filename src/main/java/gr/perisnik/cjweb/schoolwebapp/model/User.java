package gr.perisnik.cjweb.schoolwebapp.model;

/**
 * Represents a User with a username and password.
 * Provides constructors for creating a User object and
 * getter and setter methods for accessing and modifying the properties.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class User {
    private String username;
    private String password;

    /**
     * Default constructor.
     * Creates a new instance of User with default values.
     */
    public User() {
    }

    /**
     * Constructs a new instance of User with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}