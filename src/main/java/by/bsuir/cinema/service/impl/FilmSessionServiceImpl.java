package by.bsuir.cinema.service.impl;

import java.util.List;

import by.bsuir.cinema.dao.FilmSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.cinema.domain.Film;
import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.service.FilmSessionService;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

	@Autowired
    FilmSessionDao filmSessionDao;

	public FilmSessionServiceImpl() {
	}

	public FilmSessionServiceImpl(FilmSessionDao filmSessionDao) {
		this.filmSessionDao = filmSessionDao;
	}

	public FilmSessionDao getFilmSessionDao() {
		return filmSessionDao;
	}

	public void setFilmSessionDao(FilmSessionDao filmSessionDao) {
		this.filmSessionDao = filmSessionDao;
	}

	@Override
	public List<FilmSession> getFilmSessionList() {
		return filmSessionDao.readAll("id");
	}

	@Override
	public List<FilmSession> getChosenFilmFilmSessionList(Film film) {
		return filmSessionDao.readAll("film", film);
	}

	@Override
	public List<FilmSession> getFilmSessionListWhereSeatNotFree(Seat seat) {
		return filmSessionDao.readAllWhereSeatNotFree(seat);
	}

	@Override
	public void createFilmSession(FilmSession filmSession) {
		filmSessionDao.create(filmSession);
	}

	@Override
	public FilmSession readFilmSession(int id) {
		return filmSessionDao.read(id);
	}

	@Override
	public void updateFilmSession(FilmSession filmSession) {
		filmSessionDao.update(filmSession);
	}

	@Override
	public void deleteFilmSession(FilmSession filmSession) {
		filmSessionDao.delete(filmSession);
	}

	@Override
	public boolean isAnyTicketRelatedToFilmSession(int filmSessionId) {
		FilmSession filmSession = filmSessionDao.read(filmSessionId);
		return !filmSession.getTickets().isEmpty();
	}
}
