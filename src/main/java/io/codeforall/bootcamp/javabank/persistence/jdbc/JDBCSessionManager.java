package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSessionManager implements SessionManager<Connection> {

    private static final String DEFAULT_USER = "codecadet";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    public static final Integer DEFAULT_PORT = 5432;
    private static final String DEFAULT_DB = "javabank";

    private static final String CONNECTOR = "jdbc:postgresql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    public JDBCSessionManager(String user, String pass, String host, Integer port, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + ":" + port + "/" + database;
    }

    public JDBCSessionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DB);
    }


    @Override
    public void startSession() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
    }

    @Override
    public void stopSession() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

    @Override
    public Connection getCurrentSession() {
        startSession();
        return connection;
    }
}
