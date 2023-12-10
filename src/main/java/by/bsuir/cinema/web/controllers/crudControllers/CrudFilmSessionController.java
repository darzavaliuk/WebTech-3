package by.bsuir.cinema.web.controllers.crudControllers;

import static by.bsuir.cinema.web.util.HttpRequestParamValidator.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.bsuir.cinema.service.FilmService;
import by.bsuir.cinema.service.FilmSessionService;
import by.bsuir.cinema.web.util.ConstantDeclaration;
import by.bsuir.cinema.web.util.HttpRequestParamFormatter;
import by.bsuir.cinema.web.util.HttpRequestParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.bsuir.cinema.domain.FilmSession;

@Controller
@RequestMapping(value = "/newapp/admin/crud/session")
public class CrudFilmSessionController {

	@Autowired
    FilmService filmService;
	@Autowired
    FilmSessionService filmSessionService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_LIST, filmService.getFilmList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_SESSION_LIST, filmSessionService.getFilmSessionList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM_SESSION, new FilmSession());
		mav.setViewName("springMvcPages/admin/crud_film_session");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM_SESSION) FilmSession filmSession) {
		validateRequestParamNotNull(filmSession.getDate(), filmSession.getTime(), filmSession.getTicketPrice(),
				filmSession.getFilm());
		filmSessionService.createFilmSession(filmSession);
		return "redirect:/newapp/admin/crud/session/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		FilmSession foundFilmSession = filmSessionService.readFilmSession(HttpRequestParamFormatter.getInt(id));
		return "{\"foundFilmSession\" : \"" + foundFilmSession + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM_SESSION) FilmSession filmSession) {
		validateRequestParamNotNull(filmSession.getId(), filmSession.getDate(), filmSession.getTime(),
				filmSession.getTicketPrice(), filmSession.getFilm());
		filmSessionService.updateFilmSession(filmSession);
		return "redirect:/newapp/admin/crud/session/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_FILM_SESSION) FilmSession filmSession) {
		validateRequestParamNotNull(filmSession.getId(), filmSession.getDate());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (new Date().before(format.parse(filmSession.getDate()))
					&& filmSessionService.isAnyTicketRelatedToFilmSession(filmSession.getId())) {
				return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
						"Sorry. You can't delete filmSession");
			} else
				filmSessionService.deleteFilmSession(filmSession);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/newapp/admin/crud/session/");
	}
}
