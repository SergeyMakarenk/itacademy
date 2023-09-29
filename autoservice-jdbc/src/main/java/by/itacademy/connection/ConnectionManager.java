package by.itacademy.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface ConnectionManager {
    Connection open(String propertyFile) throws IOException, SQLException;
}
