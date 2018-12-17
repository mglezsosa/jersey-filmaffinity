package tech.sosa.ingweb.domain.actor;

import tech.sosa.ingweb.domain.shared.ResourceDoesNotExistException;

public class ActorDoesNotExistException extends ResourceDoesNotExistException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActorDoesNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActorDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ActorDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ActorDoesNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ActorDoesNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String defaultMessage() {
		return "Actor was not found.";
	}

}
