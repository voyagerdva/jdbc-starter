
package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRunner1 {
    public static void main(String[] args) throws SQLException {
        long flightId = 9;

        String deleteFlightSql = "DELETE FROM flight WHERE id = " + flightId;
        String deleteTicketSql = "DELETE FROM ticket WHERE flight_id = " + flightId;

        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionManager.get();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            statement.addBatch(deleteTicketSql);
            statement.addBatch(deleteFlightSql);

            var ints = statement.executeBatch();

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

    }
}
