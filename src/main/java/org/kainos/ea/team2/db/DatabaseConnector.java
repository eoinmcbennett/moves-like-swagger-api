package org.kainos.ea.team2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnector {
    /**
     * The currently active connection to the database.
     */
    private static Connection conn;

    private DatabaseConnector() { }

    /**
     * Gets the current connection to the database.
     * If no connection exists it will attempt to connect.
     * @return Connection
     * @throws SQLException Thrown on connection attempt error.
     * @throws IllegalArgumentException Thrown if environment vars not set.
     */
    public static Connection getConnection()
            throws SQLException, IllegalArgumentException {
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

        String connStr = "jdbc:mysql://" + host + "/" + name + "?useSSL=false";
        conn = DriverManager.getConnection(connStr, user, password);
        return conn;
    }
}
