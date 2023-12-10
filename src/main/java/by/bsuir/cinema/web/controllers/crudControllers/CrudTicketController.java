package by.bsuir.cinema.web.controllers.crudControllers;

import java.io.UnsupportedEncodingException;

import by.bsuir.cinema.domain.Ticket;
import by.bsuir.cinema.service.FilmSessionService;
import by.bsuir.cinema.service.SeatService;
import by.bsuir.cinema.service.TicketService;
import by.bsuir.cinema.service.TicketsOrderService;
import by.bsuir.cinema.web.util.ConstantDeclaration;
import by.bsuir.cinema.web.util.HttpRequestParamFormatter;
import by.bsuir.cinema.web.util.HttpRequestParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/newapp/admin/crud/ticket")
public class CrudTicketController {

	@Autowired
    TicketService ticketService;
	@Autowired
    FilmSessionService filmSessionService;
	@Autowired
    SeatService seatService;
	@Autowired
	@Qualifier("ticketOrderService")
    TicketsOrderService ticketsOrderService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_TICKET_LIST, ticketService.getTicketList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_SESSION_LIST, filmSessionService.getFilmSessionList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_SEAT_LIST, seatService.getSeatList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_TICKETS_ORDER_LIST, ticketsOrderService.getTicketsOrderList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET, new Ticket());
		mav.setViewName("springMvcPages/admin/crud_ticket");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticket.getFilmSession(), ticket.getSeat(), ticket.getOrder());
		ticketService.createTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		Ticket foundTicket = ticketService.readTicket(HttpRequestParamFormatter.getInt(id));
		return "{\"foundTicket\" : \"" + foundTicket + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticket.getId(), ticket.getFilmSession(), ticket.getSeat(), ticket.getOrder());
		ticketService.updateTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(ticket.getId());
		ticketService.deleteTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

}
