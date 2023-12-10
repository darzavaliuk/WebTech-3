package by.bsuir.cinema.SpringConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.bsuir.cinema.dao.FilmDao;
import by.bsuir.cinema.dao.FilmSessionDao;
import by.bsuir.cinema.dao.GenreDao;
import by.bsuir.cinema.dao.RoleDao;
import by.bsuir.cinema.dao.SeatDao;
import by.bsuir.cinema.dao.TicketDao;
import by.bsuir.cinema.dao.TicketsOrderDao;
import by.bsuir.cinema.dao.UserDao;
import by.bsuir.cinema.dao.impl.FilmDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.FilmSessionDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.GenreDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.RoleDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.SeatDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.TicketDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.TicketsOrderDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.UserDaoHibernateImpl;

@Configuration
public class SpringDaoConfig {
	{
		System.out.println("SpringDaoConfig");
	}

	@Bean
	FilmDao filmDao() {
		return new FilmDaoHibernateImpl();
	}

	@Bean
	FilmSessionDao filmSessionDao() {
		return new FilmSessionDaoHibernateImpl();
	}

	@Bean
	GenreDao genreDao() {
		return new GenreDaoHibernateImpl();
	}

	@Bean
	RoleDao roleDao() {
		return new RoleDaoHibernateImpl();
	}

	@Bean
	SeatDao seatDao() {
		return new SeatDaoHibernateImpl();
	}

	@Bean
	TicketDao ticketDao() {
		return new TicketDaoHibernateImpl();
	}

	@Bean
	TicketsOrderDao ticketsOrderDao() {
		return new TicketsOrderDaoHibernateImpl();
	}

	@Bean
	UserDao userDao() {
		return new UserDaoHibernateImpl();
	}

}
