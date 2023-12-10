package by.bsuir.cinema.service;

import java.util.List;

import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;
import by.bsuir.cinema.domain.User;

public interface TicketsOrderService extends Service {

	List<TicketsOrder> getTicketsOrderList();

	void createTicketsOrder(TicketsOrder ticketsOrder);

	TicketsOrder readTicketsOrder(int id);

	void updateTicketsOrder(TicketsOrder ticketsOrder);

	void deleteTicketsOrder(TicketsOrder ticketsOrder);

	TicketsOrder readUserNonPaidOrder(User user);

	TicketsOrder readOrderWhereSeatPresent(Seat seat, FilmSession filmSession);

	TicketsOrder readCurrentUserNonPaidOrder(User user);
}
