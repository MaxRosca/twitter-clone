package com.example.twitterclone.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection factory is an implementation of the factory pattern. It is used
 * mainly to retrieve the database connection which is used to manipulate the
 * data from the database or the database itself. This implementation helps to
 * isolate the connection to the database from the data manipulating logic.
 *
 * @author Rosca Maxim
 */

public class ConnectionFactory {
    String driver = "org.postgresql.Driver";
    String fileName = "src/main/resources/db.config";
    String url;
    String user;
    String password;

    /**
     * Sets up the driver to start the connection and read the properties from the specified .config
     * file
     */
    ConnectionFactory() {
        // read the properties from the file
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Properties prop = new Properties();
            prop.load(fis);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the connection object for the database if successful, else returns null
     *
     * @return the connection object for the database or null
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
