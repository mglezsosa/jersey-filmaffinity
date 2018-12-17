package tech.sosa.ingweb.domain.director;

import tech.sosa.ingweb.domain.shared.ResourceDoesNotExistException;

public class DirectorDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DirectorDoesNotExistException() {
		super();
	}

	public DirectorDoesNotExistException(String message) {
		super(message);
	}

	public DirectorDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public DirectorDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DirectorDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String defaultMessage() {
		return "Director was not found.";
	}

}
