package by.bsuir.cinema.service;

import java.util.List;

import by.bsuir.cinema.domain.Film;
import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;

public interface FilmSessionService extends Service {

	List<FilmSession> getFilmSessionList();

	List<FilmSession> getFilmSessionListWhereSeatNotFree(Seat seat);

	List<FilmSession> getChosenFilmFilmSessionList(Film film);

	void createFilmSession(FilmSession filmSession);

	FilmSession readFilmSession(int id);

	void updateFilmSession(FilmSession filmSession);

	void deleteFilmSession(FilmSession filmSession);

	boolean isAnyTicketRelatedToFilmSession(int filmSessionId);

}
