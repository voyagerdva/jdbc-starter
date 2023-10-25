package nn.ru.jdbc.starter;

import nn.ru.jdbc.starter.dao.TicketDao;
import nn.ru.jdbc.starter.dto.TicketFilter;
import nn.ru.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
import java.util.Optional;
public class DaoRunner {
    public static void main(String[] args) {
        var ticket = TicketDao.getInstance().findById(5L);
        System.out.println(ticket);
    }

    private static void filterTest() {
        var ticketFilter = new TicketFilter(3, 0, "Евгений Кудрявцев", "A1");
        var tickets = TicketDao.getInstance().findAll(ticketFilter);
//        System.out.println(tickets);
        tickets.forEach(System.out::println);
//        System.out.println();
//
//        ticketFilter = new TicketFilter(3, 0, null, "A1");
//        tickets = TicketDao.getInstance().findAll(ticketFilter);
////        System.out.println(tickets);
//        tickets.forEach(System.out::println);
//        System.out.println();

//        ticketFilter = new TicketFilter(3, 3, null, "A1");
//        tickets = TicketDao.getInstance().findAll(ticketFilter);
////        System.out.println(tickets);
//        tickets.forEach(System.out::println);
    }

    private static void updateTest() {
        var ticketDao = TicketDao.getInstance();
        var maybeTicket = ticketDao.findById(2L);
        System.out.println(maybeTicket);

        maybeTicket.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(188.88));
            ticketDao.update(ticket);
        });
    }

    private static void deleteTest() {
        var ticketDao = TicketDao.getInstance();
        var deleteResult = ticketDao.delete(59L);
        System.out.println(deleteResult);
    }

    private static void saveTest(TicketDao ticketDao) {
        var ticket = new Ticket();

        ticket.setPassengerNo("123456789");
        ticket.setPassengerName("Test2");
//        ticket.setFlight(5L);
        ticket.setSeatNo("B5");
        ticket.setCost(BigDecimal.TEN);

        var savedTicket = ticketDao.save(ticket);

        System.out.println(savedTicket);
    }
}
