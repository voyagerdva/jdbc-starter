package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;

        String sql = """
                SELECT *
                FROM ticket
                """;

        try (Connection connection = ConnectionManager.get();
             Statement statement = connection.createStatement();
        ) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());

            ResultSet executeResult = statement.executeQuery(sql);
            System.out.println(executeResult);
        }


    }
}
