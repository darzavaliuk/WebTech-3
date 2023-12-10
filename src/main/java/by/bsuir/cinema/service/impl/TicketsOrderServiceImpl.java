package by.bsuir.cinema.service.impl;

import java.util.List;

import by.bsuir.cinema.dao.TicketsOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;
import by.bsuir.cinema.domain.User;
import by.bsuir.cinema.service.TicketsOrderService;

@Service
public class TicketsOrderServiceImpl implements TicketsOrderService {

	@Autowired
    TicketsOrderDao ticketsOrderDao;

	public TicketsOrderServiceImpl() {
	}

	public TicketsOrderServiceImpl(TicketsOrderDao ticketsOrderDao) {
		this.ticketsOrderDao = ticketsOrderDao;
	}

	public TicketsOrderDao getTicketsOrderDao() {
		return ticketsOrderDao;
	}

	public void setTicketsOrderDao(TicketsOrderDao ticketsOrderDao) {
		this.ticketsOrderDao = ticketsOrderDao;
	}

	@Override
	public List<TicketsOrder> getTicketsOrderList() {
		return ticketsOrderDao.readAll("id");
	}

	@Override
	public void createTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.create(ticketsOrder);
	}

	@Override
	public TicketsOrder readTicketsOrder(int id) {
		return ticketsOrderDao.read(id);
	}

	@Override
	public void updateTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.update(ticketsOrder);
	}

	@Override
	public void deleteTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.delete(ticketsOrder);
	}

	@Override
	public TicketsOrder readUserNonPaidOrder(User user) {
		for (TicketsOrder order : ticketsOrderDao.readAll("user", user))
			if (!order.getIsPaid())
				return order;
		return null;
	}

	@Override
	public TicketsOrder readOrderWhereSeatPresent(Seat seat, FilmSession filmSession) {
		return ticketsOrderDao.read(seat, filmSession);
	}

	@Override
	public TicketsOrder readCurrentUserNonPaidOrder(User user) {
		return ticketsOrderDao.read(user);
	}
}
