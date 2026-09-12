package org.example;

import org.example.dao.MarksDAO;
import org.example.dao.StudentDAO;
import org.example.dao.SubjectDAO;
import org.example.entity.Marks;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.util.JPAUtil;

import java.util.List;

public class App {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();
        SubjectDAO subjectDAO = new SubjectDAO();
        MarksDAO marksDAO = new MarksDAO();


        // =====================================================
        // 1. ALL STUDENTS
        // =====================================================

        System.out.println();
        System.out.println("========== ALL STUDENTS ==========");

        List<Student> students =
                studentDAO.getAllStudents();

        for (Student student : students) {

            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());

            System.out.println("----------------------------");
        }


        // =====================================================
        // 2. ALL SUBJECTS
        // =====================================================

        System.out.println();
        System.out.println("========== ALL SUBJECTS ==========");

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        for (Subject subject : subjects) {

            System.out.println("ID: " + subject.getId());
            System.out.println("Subject: " + subject.getName());

            System.out.println("----------------------------");
        }


        // =====================================================
        // 3. ALL MARKS
        // =====================================================

        System.out.println();
        System.out.println("========== ALL MARKS ==========");

        List<Marks> marksList =
                marksDAO.getAllMarks();

        for (Marks mark : marksList) {

            System.out.println("ID: " + mark.getId());
            System.out.println(
                    "Student ID: " +
                            mark.getStudent().getId()
            );

            System.out.println(
                    "Subject ID: " +
                            mark.getSubject().getId()
            );

            System.out.println(
                    "Marks: " +
                            mark.getMarks()
            );

            System.out.println("----------------------------");
        }


        // =====================================================
        // 4. WHERE
        // =====================================================

        System.out.println();
        System.out.println("========== MARKS > 80 ==========");

        List<Marks> marksAbove80 =
                marksDAO.getMarksGreaterThan(80);

        for (Marks mark : marksAbove80) {

            System.out.println("ID: " + mark.getId());

            System.out.println(
                    "Student ID: " +
                            mark.getStudent().getId()
            );

            System.out.println(
                    "Subject ID: " +
                            mark.getSubject().getId()
            );

            System.out.println(
                    "Marks: " +
                            mark.getMarks()
            );

            System.out.println("----------------------------");
        }


        // =====================================================
        // 5. JOIN
        // =====================================================

        System.out.println();
        System.out.println("========== STUDENT + SUBJECT + MARKS ==========");

        List<Object[]> joinResult =
                marksDAO.getStudentSubjectMarks();

        for (Object[] row : joinResult) {

            System.out.println("Student: " + row[0]);
            System.out.println("Subject: " + row[1]);
            System.out.println("Marks: " + row[2]);

            System.out.println("----------------------------");
        }


        // =====================================================
        // 6. GROUP BY
        // =====================================================

        System.out.println();
        System.out.println("========== AVERAGE MARKS BY STUDENT ==========");

        List<Object[]> averageResult =
                marksDAO.getAverageMarksByStudent();

        for (Object[] row : averageResult) {

            System.out.println("Student: " + row[0]);
            System.out.println("Average Marks: " + row[1]);

            System.out.println("----------------------------");
        }


        // =====================================================
        // 7. HAVING
        // =====================================================

        System.out.println();
        System.out.println(
                "========== STUDENTS WITH AVERAGE > 75 =========="
        );

        List<Object[]> havingResult =
                marksDAO.getStudentsWithAverageGreaterThan(75);

        for (Object[] row : havingResult) {

            System.out.println("Student: " + row[0]);
            System.out.println("Average Marks: " + row[1]);

            System.out.println("----------------------------");
        }


        // =====================================================
        // 8. ORDER BY
        // =====================================================

        System.out.println();
        System.out.println("========== MARKS DESCENDING ==========");

        List<Marks> descendingMarks =
                marksDAO.getMarksDescending();

        for (Marks mark : descendingMarks) {

            System.out.println("ID: " + mark.getId());

            System.out.println(
                    "Student ID: " +
                            mark.getStudent().getId()
            );

            System.out.println(
                    "Subject ID: " +
                            mark.getSubject().getId()
            );

            System.out.println(
                    "Marks: " +
                            mark.getMarks()
            );

            System.out.println("----------------------------");
        }


        // =====================================================
        // 9. SUBQUERY
        // =====================================================

        System.out.println();
        System.out.println(
                "========== MARKS ABOVE OVERALL AVERAGE =========="
        );

        List<Marks> aboveAverage =
                marksDAO.getMarksAboveOverallAverage();

        for (Marks mark : aboveAverage) {

            System.out.println("ID: " + mark.getId());

            System.out.println(
                    "Student ID: " +
                            mark.getStudent().getId()
            );

            System.out.println(
                    "Subject ID: " +
                            mark.getSubject().getId()
            );

            System.out.println(
                    "Marks: " +
                            mark.getMarks()
            );

            System.out.println("----------------------------");
        }


        // =====================================================
        // 10. SUBQUERY + JAVA MARKS > 80
        // =====================================================

//        System.out.println();
//        System.out.println(
//                "========== STUDENTS WITH JAVA MARKS > 80 =========="
//        );
//
//        List<String> javaStudents =
//                marksDAO.getStudentsWithJavaMarksAbove80();
//
//        for (String name : javaStudents) {
//
//            System.out.println("Student: " + name);
//        }


        // =====================================================
        // CLOSE JPA
        // =====================================================

        JPAUtil.close();
    }
}