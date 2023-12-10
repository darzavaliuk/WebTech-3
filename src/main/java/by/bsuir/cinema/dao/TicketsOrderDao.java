package by.bsuir.cinema.dao;

import java.util.List;

import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;
import by.bsuir.cinema.domain.User;

public interface TicketsOrderDao extends BaseDao<TicketsOrder> {

	List<TicketsOrder> readAll(String string, Object object);

	TicketsOrder read(Seat seat, FilmSession filmSession);

	TicketsOrder read(User user);

}
