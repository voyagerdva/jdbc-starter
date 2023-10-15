package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.dao.TicketDao;
import nn.ru.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
public class DaoRunner {
    public static void main(String[] args) {
        var ticketDao = TicketDao.getInstance();
        var deleteResult = ticketDao.delete(59L);
        System.out.println(deleteResult);
    }

    private static void saveTest(TicketDao ticketDao) {
        var ticket = new Ticket();

        ticket.setPassengerNo("123456789");
        ticket.setPassengerName("Test2");
        ticket.setFlightId(5L);
        ticket.setSeatNo("B5");
        ticket.setCost(BigDecimal.TEN);

        var savedTicket = ticketDao.save(ticket);

        System.out.println(savedTicket);
    }
}
