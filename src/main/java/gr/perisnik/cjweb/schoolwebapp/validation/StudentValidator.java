package gr.perisnik.cjweb.schoolwebapp.validation;

import gr.perisnik.cjweb.schoolwebapp.dto.StudentDTO;

/**
 * Validator class for Student objects.
 * Provides methods for validating Student data.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class StudentValidator {

    /**
     * Validates the data of a StudentDTO.
     *
     * @param student the StudentDTO to be validated
     * @return true if the StudentDTO is valid, false otherwise
     */
    public static boolean isValid(StudentDTO student) {
        if (student == null) {
            return false;
        }
        if (isNullOrEmpty(student.getFirstname()) || isNullOrEmpty(student.getLastname())) {
            return false;
        }
        if (isNullOrEmpty(student.getGrade())) {
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