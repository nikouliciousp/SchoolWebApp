package gr.perisnik.cjweb.schoolwebapp.dao.exceptions;

/**
 * Custom exception thrown when a user is not found in the storage.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFoundException with {@code null} as its
     * detail message. The cause is not initialized.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *                (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UserNotFoundException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}