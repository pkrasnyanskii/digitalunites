import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Gradebook is a digital grade book class.
 * It allows you control grades of students.
 */
public class Gradebook {

    private Person student;
    private final int MAX_SEMESTER = 8;
    private final List<Map<String, Grade>> semesterGrades = new ArrayList<>(MAX_SEMESTER);

    private DiplomaGrade diplomaGrade;


    /**
     * Initialises Gradebook with student's full name
     *
     * @param surname    student's surname
     * @param name       student's name
     * @param patronymic student's patronymic
     */
    public Gradebook(String surname, String name, String patronymic) {
        this.student = new Person(surname, name, patronymic);
        for (int k = 0; k < MAX_SEMESTER; k++) {
            semesterGrades.add(new HashMap<>());
        }
    }

    public void setDiplomaGrade(DiplomaGrade diplomaGrade) {
        this.diplomaGrade = diplomaGrade;
    }

    public DiplomaGrade getDiplomaGrade() {
        return diplomaGrade;
    }

    /**
     * Returns student as Person class object
     *
     * @return Person student
     */
    public Person getStudent() {
        return student;
    }

    /**
     * Sets student to Person class object that given as parameter
     *
     * @param student student
     */
    public void setStudent(Person student) {
        this.student = student;
    }

    /**
     * Adds grade in selected semester and subject, or for the qualifying work
     *
     * @param semester semester number (0-8) or -1 for qualifying work
     * @param subject  string name of subject or null for qualifying work
     * @param grade    grade to add
     */
    public void addGrade(int semester, String subject, Grade grade) {
        semesterGrades.get(semester - 1).put(subject, grade);
    }

    /**
     * Get grade in selected semester and subject, or for the qualifying work
     *
     * @param semester semester number (0-8) or -1 for qualifying work
     * @param subject  string name of subject or null for qualifying work
     * @return Grade object
     */
    public Grade getGrade(int semester, String subject) {
        return semesterGrades.get(semester - 1).get(subject);
    }

    /**
     * Returns grades in selected semester as list of integer values
     *
     * @param semester semester number (0-8)
     * @return just list of grades
     */
    public ArrayList<Integer> getJustGrades(int semester) {
        return new ArrayList<>(
                semesterGrades.get(semester - 1).values()
                        .stream().map(Grade::getGrade).collect(Collectors.toList()));
    }

    /**
     * Returns average of all the grades in all the semesters
     *
     * @return average of all grades
     */
    public double avgGradesAll() {
        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 0; i < MAX_SEMESTER; i++) {
            all.addAll(getJustGrades(i + 1));
        }

        return all.stream().mapToDouble(n -> n).average().orElse(0);
    }

    /**
     * Returns current semester number based on grades.
     *
     * @return current semester number (0-8)
     */
    public int currentSemester() {
        return (int) semesterGrades.stream()
                .takeWhile(s -> !s.isEmpty())
                .count();
    }

    /**
     * Tells if student have right to get increased scholarship
     *
     * @return true if student have right to get increased scholarship
     */
    public boolean isIncreasedScholarshipReady() {
        int badCnt = (int) getJustGrades(currentSemester())
                .stream()
                .filter(g -> g < 5)
                .count();

        return badCnt == 0;
    }

    /**
     * Tells if student have right to get red diploma. Based on current grades and qualifying work grade.
     *
     * @return true if student have right to get red diploma
     */
    public boolean isRedDiplomaReady() {
        for (int i = 0; i < currentSemester(); i++) {
            for (int grade : getJustGrades(i + 1)) {
                if (grade < 4) {
                    return false;
                }
            }
        }

        int excLastCnt = (int) getJustGrades(currentSemester())
                .stream()
                .filter(g -> g == 5)
                .count();

        int allLastCnt = getJustGrades(currentSemester()).size();

        if (excLastCnt / (double) allLastCnt < 0.75) {
            return false;
        }

        return diplomaGrade.getGrade() >= 5;
    }
}