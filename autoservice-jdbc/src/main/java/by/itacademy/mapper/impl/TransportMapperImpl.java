package by.itacademy.mapper.impl;

import by.itacademy.mapper.TransportMapper;
import by.itacademy.model.Client;
import by.itacademy.model.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportMapperImpl implements TransportMapper {
    @Override
    public List<Transport> getTransports(final ResultSet resultSet) throws SQLException {
        final List<Transport> transports = new ArrayList<>();

        while (resultSet.next()) {
            final Integer idTransport = resultSet.getInt("id");
            final String modelName = resultSet.getString("model");
            final String typeName = resultSet.getString("type");
            final String clientFirstName = resultSet.getString("owner name");
            final String clientLastName = resultSet.getString("owner family");

            final Client client = new Client(clientFirstName, clientLastName);
            final Transport transport = new Transport(idTransport, typeName, modelName, client);
            transports.add(transport);
        }
        return transports;
    }
}
