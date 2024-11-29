package gr.perisnik.cjweb.schoolwebapp.dto;

/**
 * Data Transfer Object (DTO) for Teacher.
 * Represents a Teacher with an id, firstname, and lastname.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class TeacherDTO {
    private int id;
    private String firstname;
    private String lastname;

    /**
     * Default constructor.
     * Creates a new instance of TeacherDTO with default values.
     */
    public TeacherDTO() {
    }

    /**
     * Constructs a new instance of TeacherDTO with the specified id, firstname, and lastname.
     *
     * @param id        the unique identifier of the teacher
     * @param firstname the first name of the teacher
     * @param lastname  the last name of the teacher
     */
    public TeacherDTO(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Returns the unique identifier of the teacher.
     *
     * @return the id of the teacher
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the teacher.
     *
     * @param id the new id of the teacher
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the teacher.
     *
     * @return the firstname of the teacher
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the first name of the teacher.
     *
     * @param firstname the new first name of the teacher
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns the last name of the teacher.
     *
     * @return the lastname of the teacher
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name of the teacher.
     *
     * @param lastname the new last name of the teacher
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}