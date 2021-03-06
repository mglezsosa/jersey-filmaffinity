package tech.sosa.ingweb.domain.movie;

import tech.sosa.ingweb.domain.shared.ResourceDoesNotExistException;

public class MovieDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieDoesNotExistException() {
	}

	public MovieDoesNotExistException(String message) {
		super(message);
	}

	public MovieDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public MovieDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovieDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String defaultMessage() {
		return "Movie was not found.";
	}

}
