package org.kainos.ea.team2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnector {
    private static Connection conn;

    private DatabaseConnector() { }

    public static Connection getConnection() throws SQLException, IllegalArgumentException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String host = System.getenv("DB_HOST");
        String name = System.getenv("DB_NAME");

        if (user == null || password == null || host == null || name == null) {
            throw new IllegalArgumentException("Environment variables not set");
        }

        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
        return conn;
    }
}
