package by.bsuir.cinema.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceManagerContext {

	public ServiceManagerContext() {
	}

	public static Service getService(HttpServletRequest req, String service) {
		ServletContext servletContext = req.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		return (Service) webApplicationContext.getBean(service);
	}

}
