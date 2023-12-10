package by.bsuir.cinema.service.impl;

import java.util.List;

import by.bsuir.cinema.dao.SeatDao;
import by.bsuir.cinema.dao.TicketsOrderDao;
import by.bsuir.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
    SeatDao seatDao;

	@Autowired
    TicketsOrderDao ticketsOrderDao;

	public SeatServiceImpl() {
	}

	public SeatServiceImpl(SeatDao seatDao, TicketsOrderDao ticketsOrderDao) {
		this.seatDao = seatDao;
		this.ticketsOrderDao = ticketsOrderDao;
	}

	public SeatDao getSeatDao() {
		return seatDao;
	}

	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}

	public TicketsOrderDao getTicketsOrderDao() {
		return ticketsOrderDao;
	}

	public void setTicketsOrderDao(TicketsOrderDao ticketsOrderDao) {
		this.ticketsOrderDao = ticketsOrderDao;
	}

	@Override
	public List<Seat> getSeatList() {
		return seatDao.readAll("id");
	}

	@Override
	public void createSeat(Seat seat) {
		seatDao.create(seat);
	}

	@Override
	public Seat readSeat(int id) {
		return seatDao.read(id);
	}

	@Override
	public Seat readSeat(int row, int number) {
		return seatDao.read(row, number);
	}

	@Override
	public void updateSeat(Seat seat) {
		seatDao.update(seat);
	}

	@Override
	public void deleteSeat(Seat seat) {
		seatDao.delete(seat);
	}

	@Override
	public Seat setSeatState(Seat seat, FilmSession filmSession) {
		TicketsOrder ticketsOrder = ticketsOrderDao.read(seat, filmSession);
		if (ticketsOrder == null)
			seat.setState(Seat.State.FREE);
		else if (ticketsOrder.getIsPaid())
			seat.setState(Seat.State.OCCUPIED);
		else
			seat.setState(Seat.State.BOOKED);
		return seat;
	}

	@Override
	public boolean isSeatExist(Seat seat) {
		return seatDao.read(seat.getRow(), seat.getNumber()) != null;
	}

	@Override
	public boolean isSeatExist(int id) {
		return seatDao.read(id) != null;
	}

	@Override
	public boolean isSeatFree(Seat seat, FilmSession filmSession) {
		return setSeatState(seat, filmSession).getState().equals(Seat.State.FREE);
	}
}
