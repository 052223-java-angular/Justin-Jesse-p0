package com.Revature.eCommerce.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionFaction {
    private static connectionFaction instance;
    private Connection connection;

    private connectionFaction() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }

//Method
    public static connectionFaction getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new connectionFaction();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

// Help Methods

    private Properties getProperties() throws IOException {
        Properties props = new Properties();

        try (InputStream iStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (iStream == null) {
                throw new IOException("Unable to find application.properties");
            }
            props.load(iStream);
        }

        return props;
    }
}