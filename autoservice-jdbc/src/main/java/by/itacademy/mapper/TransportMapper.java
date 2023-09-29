package by.itacademy.mapper;

import by.itacademy.model.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TransportMapper {
    List<Transport> getTransports(ResultSet resultSet) throws SQLException;
}
