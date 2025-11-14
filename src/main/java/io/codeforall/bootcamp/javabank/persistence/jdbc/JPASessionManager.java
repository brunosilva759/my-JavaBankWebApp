package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPASessionManager implements SessionManager<EntityManager> {

    private EntityManagerFactory emf;
    private EntityManager em;

    public JPASessionManager() {
        this.emf = Persistence.createEntityManagerFactory("javabank");
    }

    @Override
    public void startSession() {
        em = emf.createEntityManager();
    }

    @Override
    public void stopSession() {
        em.close();
        emf.close();
    }

    @Override
    public EntityManager getCurrentSession() {
        startSession();
        return em;
    }
}
