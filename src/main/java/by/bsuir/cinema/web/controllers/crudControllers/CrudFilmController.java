package by.bsuir.cinema.web.controllers.crudControllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import by.bsuir.cinema.domain.Film;
import by.bsuir.cinema.domain.Genre;
import by.bsuir.cinema.service.FilmService;
import by.bsuir.cinema.service.GenreService;
import by.bsuir.cinema.web.util.ConstantDeclaration;
import by.bsuir.cinema.web.util.HttpRequestParamFormatter;
import by.bsuir.cinema.web.util.HttpRequestParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/newapp/admin/crud/film")
public class CrudFilmController {

	@Autowired
    FilmService filmService;
	@Autowired
    GenreService genreService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		List<Film> films = filmService.getFilmList();
		List<Genre> genres = genreService.getGenreList();

		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_LIST, films);
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_GENRE_LIST, genres);
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM, new Film());
		mav.setViewName("springMvcPages/admin/crud_film");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM) Film film, BindingResult result) {
		HttpRequestParamValidator.validateRequestParamNotNull(film.getFilmName(), film.getDescription(), film.getPosterUrl());
		HttpRequestParamValidator.validateRequestParamNotNull(film.getGenres());
		filmService.createFilm(film);
		return "redirect:/newapp/admin/crud/film/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		Film foundFilm = filmService.readFilm(HttpRequestParamFormatter.getInt(id));
		return "{\"foundFilm\" : \"" + foundFilm + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM) Film film) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(film.getId());
		HttpRequestParamValidator.validateRequestParamNotNull(film.getFilmName(), film.getDescription(), film.getPosterUrl());
		HttpRequestParamValidator.validateRequestParamNotNull(film.getGenres());
		filmService.updateFilm(film);
		return "redirect:/newapp/admin/crud/film/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM) Film film) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(film.getId());
		filmService.deleteFilm(film);
		return "redirect:/newapp/admin/crud/film/";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "genres", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				String genreId = (String) element;
				return genreId != null ? genreService.readGenre(HttpRequestParamFormatter.getInt(genreId)) : null;
			}
		});
	}
}
