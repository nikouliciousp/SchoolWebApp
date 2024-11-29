package gr.perisnik.cjweb.schoolwebapp.validation;

import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import java.util.regex.Pattern;

/**
 * Validator class for User objects.
 * Provides methods for validating User data.
 * Password criteria
 * - At least 8 characters
 * - At least one uppercase letter
 * - At least one lowercase letter
 * - At least one number
 * - At least one special character
 *
 * @version 1.1
 * @autor Peris Nik
 */
public class UserValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Validates the data of a UserDTO.
     *
     * @param user the UserDTO to be validated
     * @return true if the UserDTO is valid, false otherwise
     */
    public static boolean isValid(UserDTO user) {
        if (user == null) {
            return false;
        }
        if (isNullOrEmpty(user.getUsername()) || isNullOrEmpty(user.getPassword())) {
            return false;
        }
        if (!isPasswordValid(user.getPassword())) {
            return false;
        }
        // You can add more validation rules here
        return true;
    }

    /**
     * Checks if a password is valid based on the defined pattern.
     *
     * @param password the password to be checked
     * @return true if the password is valid, false otherwise
     */
    private static boolean isPasswordValid(String password) {
        return pattern.matcher(password).matches();
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str the string to be checked
     * @return true if the string is null or empty, false otherwise
     */
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}