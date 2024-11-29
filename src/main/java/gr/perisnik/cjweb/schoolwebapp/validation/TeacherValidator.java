package gr.perisnik.cjweb.schoolwebapp.validation;

import gr.perisnik.cjweb.schoolwebapp.dto.TeacherDTO;

/**
 * Validator class for Teacher objects.
 * Provides methods for validating Teacher data.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class TeacherValidator {

    /**
     * Validates the data of a TeacherDTO.
     *
     * @param teacher the TeacherDTO to be validated
     * @return true if the TeacherDTO is valid, false otherwise
     */
    public static boolean isValid(TeacherDTO teacher) {
        if (teacher == null) {
            return false;
        }
        if (isNullOrEmpty(teacher.getFirstname()) || isNullOrEmpty(teacher.getLastname())) {
            return false;
        }
        // You can add more validation rules here
        return true;
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