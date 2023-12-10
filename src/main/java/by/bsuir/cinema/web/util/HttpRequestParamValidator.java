package by.bsuir.cinema.web.util;

import static by.bsuir.cinema.web.util.ConstantDeclaration.*;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public final class HttpRequestParamValidator {
	private HttpRequestParamValidator() {

	}

	public static void validateRequestParamIdnotNull(int id) {
		if (id == 0) {
			throw new ValidateNullParamException("Empty param recieved");
		}
	}

	public static void validateRequestParamNotNull(String... str) {
		for (String s : str) {
			if (s == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static void validateRequestParamNotNull(String[]... str) {
		for (String[] s : str) {
			if (s == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static void validateRequestParamNotNull(Object... obj) {
		for (Object o : obj) {
			if (o == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static boolean isPost(HttpServletRequest req) {
		return req.getMethod().toUpperCase().equals("POST");
	}

	public static boolean checkEmailInput(String email) {
		return Pattern.matches(EMAIL_INPUT_VALIDATION_REGEX, email);
	}
}
