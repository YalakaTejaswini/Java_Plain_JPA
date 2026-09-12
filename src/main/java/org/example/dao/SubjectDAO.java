package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Subject;
import org.example.util.JPAUtil;

import java.util.List;

public class SubjectDAO {

    // CREATE
    public void addSubject(Subject subject) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(subject);

            em.getTransaction().commit();

            System.out.println("Subject added successfully");

        } finally {
            em.close();
        }
    }

    // READ BY ID
    public Subject getSubjectById(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.find(Subject.class, id);

        } finally {
            em.close();
        }
    }

    // READ ALL
    public List<Subject> getAllSubjects() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT s FROM Subject s";

            return em.createQuery(jpql, Subject.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // UPDATE
    public void updateSubject(int id, String name) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Subject subject =
                    em.find(Subject.class, id);

            if (subject != null) {

                subject.setName(name);

                System.out.println("Subject updated successfully");

            } else {

                System.out.println("Subject not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // DELETE
    public void deleteSubject(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Subject subject =
                    em.find(Subject.class, id);

            if (subject != null) {

                em.remove(subject);

                System.out.println("Subject deleted successfully");

            } else {

                System.out.println("Subject not found");
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}