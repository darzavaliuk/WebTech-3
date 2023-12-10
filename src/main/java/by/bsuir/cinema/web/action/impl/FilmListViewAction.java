package by.bsuir.cinema.web.action.impl;

import javax.servlet.http.HttpServletRequest;

import by.bsuir.cinema.domain.Film;
import by.bsuir.cinema.service.FilmService;
import by.bsuir.cinema.web.action.BaseAction;
import by.bsuir.cinema.web.util.ConstantDeclaration;

import java.util.List;

public class FilmListViewAction implements BaseAction {
	FilmService filmService;

	public FilmListViewAction() {
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<Film> films = filmService.getFilmList();
		req.setAttribute(ConstantDeclaration.REQUEST_PARAM_FILM_LIST, films);

		return ConstantDeclaration.PAGE_USER_MAIN;
	}
}
