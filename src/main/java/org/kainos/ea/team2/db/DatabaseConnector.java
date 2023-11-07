package org.kainos.ea.team2.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public static Connection getConnection() throws SQLException, IllegalArgumentException {
        String user,password,host, name;

        if(conn != null && !conn.isClosed()) { return conn; }

        user = System.getenv("DB_USER");
        password = System.getenv("DB_PASSWORD");
        host = System.getenv("DB_HOST");
        name = System.getenv("DB_NAME");

        if(user == null || password == null || host == null || name == null){
            throw new IllegalArgumentException("Environment variables not set");
        }


        if(user == null || password == null || host == null) {
            throw new IllegalArgumentException("Properties file must exist and must contain user, password, name and host properties");
        }

        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false",user ,password);
        return conn;
    }
}
