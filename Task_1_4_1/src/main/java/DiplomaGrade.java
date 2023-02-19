import java.time.LocalDate;

public class DiplomaGrade extends Grade {
    private String thesisTopic;

    public DiplomaGrade(int grade, LocalDate date, Person teacher, String thesisTopic) {
        super(grade, date, FormType.qualifyingWork, teacher);
        this.thesisTopic = thesisTopic;
    }

    public DiplomaGrade(int grade, String thesisTopic) {
        super(grade);
        this.thesisTopic = thesisTopic;
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }
}
