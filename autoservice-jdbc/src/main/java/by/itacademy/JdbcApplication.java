package by.itacademy;

import by.itacademy.connection.ConnectionManager;
import by.itacademy.connection.impl.ConnectionManagerImpl;
import by.itacademy.mapper.TransportMapper;
import by.itacademy.mapper.impl.TransportMapperImpl;
import by.itacademy.model.Transport;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class JdbcApplication {

    public static void main(String[] args) {
        final ConnectionManager connectionManager = new ConnectionManagerImpl();
        final TransportMapper mapper = new TransportMapperImpl();

        try (final Connection connection = connectionManager.open()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("""
                    select t.id, mt."name" as model, tt."name" as "type", c.first_name as "owner name", c.last_name as "owner family"  from transport t\s
                    left join model_type mt on t.model_type_id = mt.id\s
                    left join transport_type tt on t.transport_type_id = tt.id\s
                    left join client c on t.client_id = c.id;
                    """);

            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Transport> transports = mapper.getTransports(resultSet);

            printTransports(transports);

        } catch (final SQLException e) {
            throw new RuntimeException("Can't create connection", e);
        } catch (final IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private static void printTransports(final List<Transport> transports) {
        for (Transport transport : transports) {
            System.out.println(transport);
        }
    }
}
