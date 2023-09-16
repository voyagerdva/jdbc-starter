package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner1 {
    public static void main(String[] args) throws SQLException {

        String flightId = "2 OR 1 = 1";
        List<Long> result = getTicketsByFlightId(flightId);
        System.out.println(result);


    }


    private static List<Long> getTicketsByFlightId(String flightId) throws SQLException {
        String sql = """
                SELECT id
                FROM ticket
                WHERE flight_id = %s
                """.formatted(flightId);

        ArrayList<Long> result = new ArrayList<>();

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
//                result.add(resultSet.getLong("id"));
                result.add(resultSet.getObject("id", Long.class)); // NULL safe
                
            }

        }

        return result;
    }
}
