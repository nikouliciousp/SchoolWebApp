package gr.perisnik.cjweb.schoolwebapp.service.exceptions;

/**
 * Custom exception thrown for errors in the UserService.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class UserServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserServiceException with {@code null} as its
     * detail message. The cause is not initialized.
     */
    public UserServiceException() {
        super();
    }

    /**
     * Constructs a new UserServiceException with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message
     */
    public UserServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserServiceException with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UserServiceException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UserServiceException(Throwable cause) {
        super(cause);
    }
}