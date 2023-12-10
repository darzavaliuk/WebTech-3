package by.bsuir.cinema.web.controllers.crudControllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import by.bsuir.cinema.domain.Role;
import by.bsuir.cinema.domain.User;
import by.bsuir.cinema.service.RoleService;
import by.bsuir.cinema.service.UserService;
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
@RequestMapping(value = "/newapp/admin/crud/user")
public class CrudUserController {

	@Autowired
    UserService userService;
	@Autowired
    RoleService roleService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		List<User> users = userService.getUserList();
		List<Role> roles = roleService.getRoleList();

		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_USER_LIST, users);
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_ROLE_LIST, roles);
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_USER, new User());
		mav.setViewName("springMvcPages/admin/crud_user");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_USER) User user) {
		HttpRequestParamValidator.validateRequestParamNotNull(user.getLogin(), user.getEmail(), user.getPassword());
		HttpRequestParamValidator.validateRequestParamNotNull(user.getRole());
		userService.createUser(user);
		return "redirect:/newapp/admin/crud/user/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		User foundUser = userService.readUser(HttpRequestParamFormatter.getInt(id));
		return "{\"foundUser\" : \"" + foundUser + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_USER) User user) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(user.getId());
		HttpRequestParamValidator.validateRequestParamNotNull(user.getLogin(), user.getEmail(), user.getPassword());
		HttpRequestParamValidator.validateRequestParamNotNull(user.getRole());
		userService.updateUser(user);
		return "redirect:/newapp/admin/crud/user/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_USER) User user) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(user.getId());
		userService.deleteUser(user);
		return "redirect:/newapp/admin/crud/user/";
	}
}
