package by.itacademy.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection open() throws IOException, SQLException;
}
