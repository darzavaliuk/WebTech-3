package by.bsuir.cinema.web.controllers.crudControllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.service.SeatService;
import by.bsuir.cinema.service.TicketService;
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

@Controller
@RequestMapping(value = "/newapp/admin/crud/seat")
public class CrudSeatController {

	@Autowired
    SeatService seatService;

	@Autowired
    TicketService ticketService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("springMvcPages/admin/crud_seat").addAllObjects(new HashMap<String, Object>() {
			{
				put(ConstantDeclaration.REQUEST_PARAM_SEAT_LIST, seatService.getSeatList());
				put(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT, new Seat());
			}
		});
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView mainUpdateDelete() {
		return new ModelAndView("springMvcPages/admin/crud_seat").addAllObjects(new HashMap<String, Object>() {
			{
				put(ConstantDeclaration.REQUEST_PARAM_SEAT_LIST, seatService.getSeatList());
				put(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT, new Seat());
			}
		});
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT) Seat seat) {
		int row = seat.getRow();
		int number = seat.getNumber();
		HttpRequestParamValidator.validateRequestParamNotNull(row, number);
		if (row <= 0 || number <= 0 || row > ConstantDeclaration.MAX_COUNT_SEAT_ROW_IN_HALL || number > ConstantDeclaration.MAX_COUNT_SEAT_NUMBER_IN_ROW) {
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
					"Sorry. You can't create seat.<br> The seat can't be out of the hall.");
		} else if (!seatService.isSeatExist(seat)) {
			seatService.createSeat(seat);
			return new ModelAndView("redirect:/newapp/admin/crud/seat/");
		} else
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
					"Sorry. You can't create seat.<br> The seat is already exist.");
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		Seat foundSeat = seatService.readSeat(HttpRequestParamFormatter.getInt(id));
		return "{\"foundSeat\" : \"" + foundSeat + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT) Seat seat) {
		if (!ticketService.isAnyTicketContainsSeat(seat)) {
			int row = seat.getRow();
			int number = seat.getNumber();
			HttpRequestParamValidator.validateRequestParamNotNull(row, number);
			if (row <= 0 || number <= 0 || row > ConstantDeclaration.MAX_COUNT_SEAT_ROW_IN_HALL || number > ConstantDeclaration.MAX_COUNT_SEAT_NUMBER_IN_ROW) {
				return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
						"Sorry. The seat can't be out of the hall.");
			} else if (seatService.isSeatExist(seat.getId())) {
				seatService.updateSeat(seat);
				return new ModelAndView("redirect:/newapp/admin/crud/seat/");
			} else
				return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
						"Sorry. You can't update seat.<br> The seat isn't exist.");
		} else
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
					"Sorry. You can't update seat.<br> Some tickets contain this seat");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT) Seat seat) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(seat.getId());
		if (!ticketService.isAnyTicketContainsSeat(seat)) {
			seatService.deleteSeat(seat);
			return new ModelAndView("redirect:/newapp/admin/crud/seat/");
		} else
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE,
					"Sorry. You can't delete seat.<br> Some tickets contain this seat");
	}
}
