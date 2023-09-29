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

public  class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(final String propertyFile) throws IOException, SQLException {
        final Properties properties = getProperties(propertyFile);

        return DriverManager.getConnection(
                properties.getProperty(URL),
                properties.getProperty(USER),
                properties.getProperty(PASSWORD)
        );
    }

    private Properties getProperties(final String propertyFile) throws IOException {
        try (final InputStream stream = JdbcApplication.class.getClassLoader().getResourceAsStream(propertyFile)) {
            final Properties properties = new Properties();
            properties.load(stream);

            return properties;
        }
    }
}
