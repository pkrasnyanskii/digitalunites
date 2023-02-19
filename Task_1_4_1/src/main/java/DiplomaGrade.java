import java.time.LocalDate;

/**
 * Represents a diploma grade, which is a type of grade that includes a thesis topic.
 * Inherits properties and behavior from the Grade superclass.
 */
public class DiplomaGrade extends Grade {

    /**
     * The thesis topic for the diploma.
     */
    private String thesisTopic;

    /**
     * Constructs a new instance of the DiplomaGrade class with the specified grade, date, teacher, and thesis topic.
     *
     * @param grade The grade for the diploma.
     * @param date The date the diploma was issued.
     * @param teacher The teacher who issued the diploma.
     * @param thesisTopic The topic of the thesis associated with the diploma.
     */
    public DiplomaGrade(int grade, LocalDate date, Person teacher, String thesisTopic) {
        super(grade, date, FormType.qualifyingWork, teacher);
        this.thesisTopic = thesisTopic;
    }

    /**
     * Constructs a new instance of the DiplomaGrade class with the specified grade and thesis topic.
     *
     * @param grade The grade for the diploma.
     * @param thesisTopic The topic of the thesis associated with the diploma.
     */
    public DiplomaGrade(int grade, String thesisTopic) {
        super(grade);
        this.thesisTopic = thesisTopic;
    }

    /**
     * Gets the thesis topic for the diploma.
     *
     * @return The thesis topic for the diploma.
     */
    public String getThesisTopic() {
        return thesisTopic;
    }

    /**
     * Sets the thesis topic for the diploma.
     *
     * @param thesisTopic The thesis topic for the diploma.
     */
    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }
}