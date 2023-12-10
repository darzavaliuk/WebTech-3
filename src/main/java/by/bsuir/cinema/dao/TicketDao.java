package by.bsuir.cinema.dao;

import java.util.List;

import by.bsuir.cinema.domain.Ticket;

public interface TicketDao extends BaseDao<Ticket> {

	List<Ticket> readAll(String property, Object value);
}
