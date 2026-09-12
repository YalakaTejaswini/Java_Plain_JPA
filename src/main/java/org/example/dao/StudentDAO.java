package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Student;
import org.example.util.JPAUtil;

import java.util.List;

public class StudentDAO {

    // CREATE
    public void addStudent(Student student) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(student);

            em.getTransaction().commit();

            System.out.println("Student added successfully");

        } finally {
            em.close();
        }
    }

    // READ BY ID
    public Student getStudentById(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Student.class, id);

        } finally {
            em.close();
        }
    }

    // READ ALL
    public List<Student> getAllStudents() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s FROM Student s";

            return em.createQuery(jpql, Student.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // UPDATE
    public void updateStudent(int id, String name, String email) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Student student =
                    em.find(Student.class, id);

            if (student != null) {

                student.setName(name);
                student.setEmail(email);

                System.out.println("Student updated successfully");

            } else {

                System.out.println("Student not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Student student =
                    em.find(Student.class, id);

            if (student != null) {

                em.remove(student);

                System.out.println("Student deleted successfully");

            } else {

                System.out.println("Student not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}