package by.bsuir.cinema;

import by.bsuir.cinema.dao.TicketsOrderDao;
import by.bsuir.cinema.dao.impl.TicketsOrderDaoHibernateImpl;
import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;

public class Runner {

	public static void main(String[] args) {
		/*
		 * RoleDao roleDao=new RoleDaoHibernateImpl(); Role role=roleDao.read(2);
		 * for(User user:role.getUsers()) System.out.println(user);
		 */

		/*
		 * FilmDao filmDao = new FilmDaoHibernateImpl(); List<Film> films =
		 * filmDao.readAllFilmsWhereGenreIdPresent(14); for (Film f : films)
		 * System.out.println(f);
		 */

		/*
		 * UserDao userDao = new UserDaoHibernateImpl(); List<User> users =
		 * userDao.readAllUsersWhereRoleIdPresent(3); for (User u : users)
		 * System.out.println(u);
		 */

		TicketsOrderDao ticketsOrderDao = new TicketsOrderDaoHibernateImpl();
//		TicketsOrder order = ticketsOrderDao.read(1);
//		System.out.println(order + " " + order.getSeats() + order.getFilmSessions());
//		order = ticketsOrderDao.read(2);
//		System.out.println(order + " " + order.getSeats() + order.getFilmSessions());
//		order = ticketsOrderDao.read(3);
//		System.out.println(order + " " + order.getSeats() + order.getFilmSessions());
//		order = ticketsOrderDao.read(4);
//		System.out.println(order + " " + order.getSeats() + order.getFilmSessions());
		Seat seat = new Seat();
		seat.setId(5);
		FilmSession filmSession = new FilmSession();
		filmSession.setId(6);
		TicketsOrder order = ticketsOrderDao.read(seat, filmSession);
		System.out.println(order);

		/*
		 * FilmSessionDao filmSessionDao = new FilmSessionDaoHibernateImpl(); Seat seat
		 * = new Seat(); seat.setId(4);
		 * System.out.println(filmSessionDao.readAllWhereSeatNotFree(seat));
		 * seat.setId(5);
		 * System.out.println(filmSessionDao.readAllWhereSeatNotFree(seat));
		 * seat.setId(6);
		 * System.out.println(filmSessionDao.readAllWhereSeatNotFree(seat));
		 */
	}
}
