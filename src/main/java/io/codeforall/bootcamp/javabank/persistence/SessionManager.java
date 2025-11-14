package io.codeforall.bootcamp.javabank.persistence;

import javax.persistence.EntityManager;

public interface SessionManager<T> {
    /**
     * Starts the session
     */
    void startSession();

    /**
     * Stops the session
     */
    void stopSession();

    /**
     * Gets the current session
     *
     * @return the current session
     */
    EntityManager getCurrentSession();
}
