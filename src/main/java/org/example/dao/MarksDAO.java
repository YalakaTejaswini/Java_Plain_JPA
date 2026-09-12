package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Marks;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.util.JPAUtil;

import java.util.List;

public class MarksDAO {

    // CREATE
    public void addMarks(int studentId,
                         int subjectId,
                         int marks) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Student student =
                    em.find(Student.class, studentId);

            Subject subject =
                    em.find(Subject.class, subjectId);

            if (student == null) {

                System.out.println("Student not found");

                em.getTransaction().rollback();

                return;
            }

            if (subject == null) {

                System.out.println("Subject not found");

                em.getTransaction().rollback();

                return;
            }

            Marks mark =
                    new Marks(student, subject, marks);

            em.persist(mark);

            em.getTransaction().commit();

            System.out.println("Marks added successfully");

        } finally {
            em.close();
        }
    }

    // READ ALL MARKS
    public List<Marks> getAllMarks() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT m FROM Marks m";

            return em.createQuery(jpql, Marks.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // READ MARKS BY ID
    public Marks getMarksById(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.find(Marks.class, id);

        } finally {
            em.close();
        }
    }

    // UPDATE
    public void updateMarks(int id, int marks) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Marks mark =
                    em.find(Marks.class, id);

            if (mark != null) {

                mark.setMarks(marks);

                System.out.println("Marks updated successfully");

            } else {

                System.out.println("Marks record not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // DELETE
    public void deleteMarks(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Marks mark =
                    em.find(Marks.class, id);

            if (mark != null) {

                em.remove(mark);

                System.out.println("Marks deleted successfully");

            } else {

                System.out.println("Marks record not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // WHERE
    // Get marks greater than given value
    public List<Marks> getMarksGreaterThan(int value) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT m FROM Marks m " +
                            "WHERE m.marks > :value";

            return em.createQuery(jpql, Marks.class)
                    .setParameter("value", value)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // JOIN
    // Student + Subject + Marks
    public List<Object[]> getStudentSubjectMarks() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s.name, sub.name, m.marks " +
                            "FROM Marks m " +
                            "JOIN m.student s " +
                            "JOIN m.subject sub";

            return em.createQuery(jpql, Object[].class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // GROUP BY
    // Average marks of every student
    public List<Object[]> getAverageMarksByStudent() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s.name, AVG(m.marks) " +
                            "FROM Marks m " +
                            "JOIN m.student s " +
                            "GROUP BY s.id, s.name";

            return em.createQuery(jpql, Object[].class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // GROUP BY + HAVING
    // Students whose average is greater than given value
    public List<Object[]> getStudentsWithAverageGreaterThan(
            double value) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s.name, AVG(m.marks) " +
                            "FROM Marks m " +
                            "JOIN m.student s " +
                            "GROUP BY s.id, s.name " +
                            "HAVING AVG(m.marks) > :value";

            return em.createQuery(jpql, Object[].class)
                    .setParameter("value", value)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // ORDER BY
    // Highest marks first
    public List<Marks> getMarksDescending() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT m FROM Marks m " +
                            "ORDER BY m.marks DESC";

            return em.createQuery(jpql, Marks.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // SUBQUERY
    // Marks greater than overall average
    public List<Marks> getMarksAboveOverallAverage() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT m FROM Marks m " +
                            "WHERE m.marks > " +
                            "(SELECT AVG(m2.marks) FROM Marks m2)";

            return em.createQuery(jpql, Marks.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // SUBQUERY
    // Students who scored more than 80 in Java
    public List<String> getStudentsWithJavaMarksAbove80() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s.name " +
                            "FROM Student s " +
                            "WHERE s.id IN " +
                            "(SELECT m.student.id " +
                            "FROM Marks m " +
                            "JOIN m.subject sub " +
                            "WHERE sub.name = 'Java' " +
                            "AND m.marks > 80)";

            return em.createQuery(jpql, String.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }
}