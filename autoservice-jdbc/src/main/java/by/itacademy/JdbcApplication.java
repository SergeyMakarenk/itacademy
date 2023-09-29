package by.itacademy;

import by.itacademy.connection.ConnectionManager;
import by.itacademy.connection.impl.ConnectionManagerImpl;
import by.itacademy.model.Client;
import by.itacademy.model.Transport;

import java.io.IOException;
import java.sql.*;

import static by.itacademy.util.Constants.*;

public class JdbcApplication {

    public static void main(String[] args) {
        final ConnectionManager connectionManager = new ConnectionManagerImpl();

        try (final Connection connection = connectionManager.open(PROPERTY_FILE)) {
            final PreparedStatement preparedStatement = connection.prepareStatement("""
select mt."name" as model, tt."name" as "type", c.first_name as "owner name", c.last_name as "owner family"  from transport t\s
left join model_type mt on t.model_type_id = mt.id\s
left join transport_type tt on t.transport_type_id = tt.id\s
left join client c on t.client_id = c.id;
""");

            printResultSet(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
    }

    private static void printResultSet(final ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            final String modelName = resultSet.getString("model");
            final String typeName = resultSet.getString("type");
            final String clientFirstName = resultSet.getString("owner name");
            final String clientLastName = resultSet.getString("owner family");

            final Client client = new Client(clientFirstName, clientLastName);
            final Transport transport = new Transport(typeName, modelName, client);

            System.out.println(transport);
        }
    }
}
