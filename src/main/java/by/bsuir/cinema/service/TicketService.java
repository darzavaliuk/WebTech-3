package by.bsuir.cinema.service;

import java.util.List;

import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.Ticket;

public interface TicketService extends Service {

	List<Ticket> getTicketList();

	void createTicket(Ticket ticket);

	Ticket readTicket(int id);

	void updateTicket(Ticket ticket);

	void deleteTicket(Ticket ticket);

	boolean isAnyTicketContainsSeat(Seat seat);
}
