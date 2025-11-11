package io.codeforall.bootcamp.javabank.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionManager {

    private String dbUrl = "jdbc:postgresql://localhost:5432/javabank";
    private String user = "postgres";
    private String pass = "postgres";
    private Connection connection;

    public void startSession() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
    }

    public void stopSession() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

    public Connection getCurrentSession() {
        startSession();
        return connection;
    }
}


