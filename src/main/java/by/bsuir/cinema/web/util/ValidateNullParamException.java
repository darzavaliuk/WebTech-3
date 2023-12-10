package by.bsuir.cinema.web.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidateNullParamException extends RuntimeException {

	private static final long serialVersionUID = 2138070776965102949L;

	private static final Logger logger = LogManager.getLogger();

	public ValidateNullParamException() {
	}

	public ValidateNullParamException(String message, Throwable cause) {
		logger.error(message + " " + cause);
	}

	public ValidateNullParamException(String message) {
		logger.error(message);
	}

	public ValidateNullParamException(Throwable cause) {
		logger.error(cause);
	}

}
