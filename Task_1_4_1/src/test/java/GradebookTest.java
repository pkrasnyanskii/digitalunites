import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class GradebookTest {
    Gradebook myBook;

    @BeforeEach
    void init() {
        myBook = new Gradebook(
                "Краснянский",
                "Пётр",
                "Михайлович");
        addGrade_test();
    }

    @Test
    void person_toString_test() {
        Assertions.assertEquals(
                "Краснянский Пётр Михайлович",
                myBook.getStudent().toString());
    }

    @Test
    void avgGrades_test() {
        Assertions.assertEquals(
                (5 + 3 + 4 + 4) / 4d,
                myBook.avgGradesAll());

        Gradebook emptyBook = new Gradebook("Ф", "И", "О");
        Assertions.assertEquals(
                0.0,
                emptyBook.avgGradesAll());
    }

    @Test
    void currentSemester_test() {
        Assertions.assertEquals(
                1,
                myBook.currentSemester());

        addGrade_test1();
        Assertions.assertEquals(
                2,
                myBook.currentSemester()
        );
    }

    @Test
    void addGrade_test() {
        myBook.addGrade(
                1,
                "Иностранный язык",
                new Grade(5,
                        LocalDate.of(2021, 12, 24),
                        Grade.FormType.credit,
                        new Person(
                                "Хоцкина",
                                "Ольга",
                                "Валерьевна")));
        myBook.addGrade(
                1,
                "Введение в алгебру и анализ",
                new Grade(3,
                        LocalDate.of(2022, 1, 11),
                        Grade.FormType.exam,
                        new Person(
                                "Васкевич",
                                "Владимир",
                                "Леонтьевич")));
        myBook.addGrade(
                1,
                "История",
                new Grade(4,
                        LocalDate.of(2021, 12, 23),
                        Grade.FormType.diffCredit,
                        new Person(
                                "Оплаканская",
                                "Рената",
                                "Валерьевна")));

        myBook.addGrade(
                1,
                "Введение в дискретную математику и математическую логику",
                new Grade(4,
                        LocalDate.of(2022, 1, 18),
                        Grade.FormType.exam,
                        new Person(
                                "Власов",
                                "Дмитрий",
                                "Юрьевич")));

        Assertions.assertEquals(5, myBook.getGrade(1, "Иностранный язык").getGrade());
    }

    @Test
    void isIncreasedScholarshipReady_test() {
        Gradebook trueBook = new Gradebook("test", "test", "test");
        trueBook.addGrade(1, "OOP", new Grade(5));
        Assertions.assertTrue(
                trueBook.isIncreasedScholarshipReady());

        addGrade_test1();
        Assertions.assertFalse(
                myBook.isIncreasedScholarshipReady());
    }

    @Test
    void isRedDiplomaReady_test() {
        myBook.setQualifyingWorkGrade(5);
        Assertions.assertFalse(
                myBook.isRedDiplomaReady());

        addGrade_test1();
        Assertions.assertFalse(
                myBook.isRedDiplomaReady());
    }

    @Test
    void addGrade_test1() {
        myBook.addGrade(1, "Декларативное программирование", new Grade(3));
        myBook.addGrade(1, "Физическая культура и спорт", new Grade(5));
        myBook.addGrade(1, "Основы культуры и речи", new Grade(4));
        myBook.addGrade(1, "Императивное программирование", new Grade(3));
        myBook.addGrade(1, "Цифровые платформы", new Grade(5));

        myBook.addGrade(2, "Введение в алгебру и анализ", new Grade(4));
        myBook.addGrade(2, "Императивное программирование", new Grade(3));
        myBook.addGrade(2, "Цифровые платформы", new Grade(4));
        myBook.addGrade(2, "Введение в дискретную математику и математическую логику", new Grade(3));
        myBook.addGrade(2, "Иностранный язык", new Grade(5));
        myBook.addGrade(2, "Декларативное программирование", new Grade(3));
        myBook.addGrade(2, "Физическая культура и спорт", new Grade(5));
        myBook.addGrade(2, "Измерительный практикум", new Grade(5));

        Assertions.assertEquals(
                4,
                myBook.getGrade(2, "Введение в алгебру и анализ").getGrade()
        );

        Assertions.assertEquals(
                3,
                myBook.getGrade(1, "Декларативное программирование").getGrade()
        );
    }

}