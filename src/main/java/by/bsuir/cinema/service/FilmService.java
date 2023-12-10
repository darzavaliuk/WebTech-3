package by.bsuir.cinema.service;

import java.util.List;

import by.bsuir.cinema.domain.Film;

public interface FilmService extends Service {

	List<Film> getFilmList();

	void createFilm(Film film);

	Film readFilm(int id);

	void updateFilm(Film film);

	void deleteFilm(Film film);
}
