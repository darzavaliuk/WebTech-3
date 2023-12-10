package by.bsuir.cinema.web.controllers.crudControllers;

import by.bsuir.cinema.domain.TicketsOrder;
import by.bsuir.cinema.service.TicketsOrderService;
import by.bsuir.cinema.service.UserService;
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
@RequestMapping(value = "/newapp/admin/crud/order")
public class CrudTicketsOrderController {

	@Autowired
	@Qualifier("ticketOrderService")
    TicketsOrderService ticketsOrderService;
	@Autowired
    UserService userService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_TICKETS_ORDER_LIST, ticketsOrderService.getTicketsOrderList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_USER_LIST, userService.getUserList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKETS_ORDER, new TicketsOrder());
		mav.setViewName("springMvcPages/admin/crud_tickets_order");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKETS_ORDER) TicketsOrder ticketsOrder) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticketsOrder.getUser(), ticketsOrder.getIsPaid());
		ticketsOrderService.createTicketsOrder(ticketsOrder);
		return "redirect:/newapp/admin/crud/order/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		System.out.println(id);
		TicketsOrder foundTicketsOrder = ticketsOrderService.readTicketsOrder(HttpRequestParamFormatter.getInt(id));
		return "{\"foundTicketsOrder\" : \"" + foundTicketsOrder + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKETS_ORDER) TicketsOrder ticketsOrder) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticketsOrder.getId(), ticketsOrder.getOrderNumber(), ticketsOrder.getUser(),
				ticketsOrder.getIsPaid());
		ticketsOrderService.updateTicketsOrder(ticketsOrder);
		return "redirect:/newapp/admin/crud/order/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKETS_ORDER) TicketsOrder ticketsOrder) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(ticketsOrder.getId());
		ticketsOrderService.deleteTicketsOrder(ticketsOrder);
		return "redirect:/newapp/admin/crud/order/";
	}
}
