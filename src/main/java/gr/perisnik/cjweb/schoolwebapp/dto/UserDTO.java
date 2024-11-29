package gr.perisnik.cjweb.schoolwebapp.dto;

/**
 * Data Transfer Object (DTO) for User.
 * Represents a User with a username and password.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class UserDTO {
    private String username;
    private String password;

    /**
     * Default constructor.
     * Creates a new instance of UserDTO with default values.
     */
    public UserDTO() {
    }

    /**
     * Constructs a new instance of UserDTO with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public UserDTO(String username, String password) {
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