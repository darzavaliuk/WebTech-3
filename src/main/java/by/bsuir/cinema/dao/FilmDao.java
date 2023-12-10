package by.bsuir.cinema.dao;

import java.util.List;
import java.util.Map;

import by.bsuir.cinema.domain.Film;

public interface FilmDao extends BaseDao<Film> {

	List<Film> readAll(Map<String, Object> map);

	List<Film> readAllFilmsWhereGenreIdPresent(int genreId);
}
