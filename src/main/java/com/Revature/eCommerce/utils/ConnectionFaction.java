package com.Revature.eCommerce.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class connects the program to a data base to manipulate data in the database
 */
public class ConnectionFaction {
    private static ConnectionFaction instance;
    private Connection connection;

    /**
     * Loads the driver manager with content from the app.prop file
     * @throws IOException - io exception
     * @throws SQLException - SQL Exception
     * @throws ClassNotFoundException - Class not found exception
     */
    private ConnectionFaction() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }

    /**
     * Creates an instance of the connection Faction
     * @return - instance
     * @throws ClassNotFoundException - Class not found exception
     * @throws IOException - io exception
     * @throws SQLException - SQL Exception
     */
    public static ConnectionFaction getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionFaction();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

// Help Methods to get read the contents of the application file to connect the database

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
