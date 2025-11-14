package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.TransactionManager;

import javax.persistence.EntityManager;

public class JPATransactionManager implements TransactionManager {

    private EntityManager em;


    public void beginRead() {

        em.getTransaction().begin();

    }

    public void beginWrite() {

        em.getTransaction().begin();

    }

    public void commit() {

        em.getTransaction().commit();

    }

    public void rollback() {

        em.getTransaction().rollback();

    }
}
