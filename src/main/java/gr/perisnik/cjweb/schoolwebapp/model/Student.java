package gr.perisnik.cjweb.schoolwebapp.model;

/**
 * Represents a Student with an id, firstname, lastname, and grade.
 * Provides constructors for creating a Student object and
 * getter and setter methods for accessing and modifying the properties.
 *
 * @version 1.0
 * @autor Peris Nik
 */
public class Student {
    private int id;
    private String firstname;
    private String lastname;
    private String grade;

    /**
     * Default constructor.
     * Creates a new instance of Student with default values.
     */
    public Student() {
    }

    /**
     * Constructs a new instance of Student with the specified id, firstname, lastname, and grade.
     *
     * @param id        the unique identifier of the student
     * @param firstname the first name of the student
     * @param lastname  the last name of the student
     * @param grade     the grade of the student
     */
    public Student(int id, String firstname, String lastname, String grade) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return the id of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the student.
     *
     * @param id the new id of the student
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the student.
     *
     * @return the firstname of the student
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstname the new first name of the student
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns the last name of the student.
     *
     * @return the lastname of the student
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastname the new last name of the student
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns the grade of the student.
     *
     * @return the grade of the student
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the student.
     *
     * @param grade the new grade of the student
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
}