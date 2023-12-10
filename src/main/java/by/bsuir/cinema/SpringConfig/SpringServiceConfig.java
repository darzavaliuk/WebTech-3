package by.bsuir.cinema.SpringConfig;

import by.bsuir.cinema.service.impl.GenreServiceImpl;
import by.bsuir.cinema.service.impl.RoleServiceImpl;
import by.bsuir.cinema.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import by.bsuir.cinema.dao.FilmDao;
import by.bsuir.cinema.dao.FilmSessionDao;
import by.bsuir.cinema.dao.GenreDao;
import by.bsuir.cinema.dao.RoleDao;
import by.bsuir.cinema.dao.SeatDao;
import by.bsuir.cinema.dao.TicketDao;
import by.bsuir.cinema.dao.TicketsOrderDao;
import by.bsuir.cinema.dao.UserDao;
import by.bsuir.cinema.service.FilmService;
import by.bsuir.cinema.service.FilmSessionService;
import by.bsuir.cinema.service.GenreService;
import by.bsuir.cinema.service.RoleService;
import by.bsuir.cinema.service.SeatService;
import by.bsuir.cinema.service.TicketService;
import by.bsuir.cinema.service.TicketsOrderService;
import by.bsuir.cinema.service.UserService;
import by.bsuir.cinema.service.impl.CustomUserDetailsServiceImpl;
import by.bsuir.cinema.service.impl.FilmServiceImpl;
import by.bsuir.cinema.service.impl.FilmSessionServiceImpl;
import by.bsuir.cinema.service.impl.SeatServiceImpl;
import by.bsuir.cinema.service.impl.TicketServiceImpl;
import by.bsuir.cinema.service.impl.TicketsOrderServiceImpl;

@Configuration
public class SpringServiceConfig {
	{
		System.out.println("SpringServiceConfig");
	}

	@Bean
	protected UserDetailsService userDetailsService(UserDao userDao) {
		return new CustomUserDetailsServiceImpl(userDao);
	}

	@Bean
	FilmService filmService(FilmDao filmDao) {
		return new FilmServiceImpl(filmDao);
	}

	@Bean
	FilmSessionService filmSessionService(FilmSessionDao filmSessionDao) {
		return new FilmSessionServiceImpl(filmSessionDao);
	}

	@Bean
	GenreService genreService(GenreDao genreDao, FilmDao filmDao) {
		return new GenreServiceImpl(genreDao, filmDao);
	}

	@Bean
	RoleService roleService(RoleDao roleDao, UserDao userDao) {
		return new RoleServiceImpl(roleDao, userDao);
	}

	@Bean
	SeatService seatService(SeatDao seatDao, TicketsOrderDao ticketsOrderDao) {
		return new SeatServiceImpl(seatDao, ticketsOrderDao);
	}

	@Bean
	TicketService ticketService(TicketDao ticketDao) {
		return new TicketServiceImpl(ticketDao);
	}

	@Bean
	TicketsOrderService ticketOrderService(TicketsOrderDao ticketsOrderDao) {
		return new TicketsOrderServiceImpl(ticketsOrderDao);
	}

	@Bean
	UserService userService(UserDao userDao) {
		return new UserServiceImpl(userDao);
	}
}
