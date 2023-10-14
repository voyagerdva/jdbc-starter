package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException, RuntimeException {
        long flightId = 9;

        String deleteFlightSql = "DELETE FROM flight WHERE id = ?";
        String deleteTicketSql = "DELETE FROM ticket WHERE flight_id = ?";

        Connection connection = null;
        PreparedStatement deleteFligthtStatement = null;
        PreparedStatement deleteTicketsStatement = null;


        try {
            connection = ConnectionManager.get();
            deleteFligthtStatement = connection.prepareStatement(deleteFlightSql);
            deleteTicketsStatement = connection.prepareStatement(deleteTicketSql);

            connection.setAutoCommit(false);
            deleteFligthtStatement.setLong(1, flightId);
            deleteTicketsStatement.setLong(1, flightId);

            deleteTicketsStatement.executeUpdate();
            System.out.println("ooops....");
//            throw new RuntimeException("Ooops!");

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteFligthtStatement != null) {
                deleteFligthtStatement.close();
            }
            if (deleteTicketsStatement != null) {
                deleteTicketsStatement.close();
            }
        }

    }
}
