package by.itacademy.connection.impl;

import by.itacademy.JdbcApplication;
import by.itacademy.connection.ConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static by.itacademy.util.Constants.*;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open() throws IOException, SQLException {
        final Properties properties = getProperties();

        return DriverManager.getConnection(
                properties.getProperty(URL),
                properties.getProperty(USER),
                properties.getProperty(PASSWORD)
        );
    }

    private static Properties getProperties() throws IOException {
        try (final InputStream stream = JdbcApplication.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            final Properties properties = new Properties();
            properties.load(stream);

            return properties;
        }
    }
}
