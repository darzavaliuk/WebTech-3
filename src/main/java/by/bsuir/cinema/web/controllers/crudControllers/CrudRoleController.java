package by.bsuir.cinema.web.controllers.crudControllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import by.bsuir.cinema.domain.Role;
import by.bsuir.cinema.service.RoleService;
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
@RequestMapping(value = "/newapp/admin/crud/role")
public class CrudRoleController {

	@Autowired
    RoleService roleService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		List<Role> roles = roleService.getRoleList();
		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_ROLE_LIST, roles);
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_ROLE, new Role());
		mav.setViewName("springMvcPages/admin/crud_role");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_ROLE) Role role) {
		HttpRequestParamValidator.validateRequestParamNotNull(role.getRoleName());
		roleService.createRole(role);
		return "redirect:/newapp/admin/crud/role/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdnotNull(HttpRequestParamFormatter.getInt(id));
		Role foundRole = roleService.readRole(HttpRequestParamFormatter.getInt(id));
		return "{\"foundRole\" : \"" + foundRole + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_ROLE) Role role) {
		HttpRequestParamValidator.validateRequestParamIdnotNull(role.getId());
		HttpRequestParamValidator.validateRequestParamNotNull(role.getRoleName());
		roleService.updateRole(role);
		return "redirect:/newapp/admin/crud/role/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_ROLE) Role role) {
		ModelAndView mav = new ModelAndView();
		HttpRequestParamValidator.validateRequestParamIdnotNull(role.getId());
		if (roleService.isAnyFilmContainGenre(role.getId())) {
			mav.addObject(ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "You can't delete role.<br>Some users are marked by this role");
			mav.setViewName("springMvcPages/error");
			return mav;
		}
		roleService.deleteRole(role);
		mav.setViewName("redirect:/newapp/admin/crud/role/");
		return mav;
	}
}
