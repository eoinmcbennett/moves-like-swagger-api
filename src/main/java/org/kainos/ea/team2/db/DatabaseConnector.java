package org.kainos.ea.team2.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

<<<<<<< HEAD
    public static Connection getConnection() throws SQLException {
=======
    public static Connection getConnection() throws SQLException, IllegalArgumentException {
>>>>>>> origin
        String user,password,host, name;

        if(conn != null && !conn.isClosed()) { return conn; }

<<<<<<< HEAD
        try(FileInputStream propsStream = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            if(user == null || password == null || host == null) {
                throw new IllegalArgumentException("Properties file must exist and must contain user, password, name and host properties");
            }

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false",user ,password);
            return conn;
        } catch(Exception e){
            System.err.println(e.getMessage());
        }

        return null;
=======
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
>>>>>>> origin
    }
}
