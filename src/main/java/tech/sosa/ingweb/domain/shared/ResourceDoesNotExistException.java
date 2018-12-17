package tech.sosa.ingweb.domain.shared;

public abstract class ResourceDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract String defaultMessage();

	public ResourceDoesNotExistException() {
		super();
	}

	public ResourceDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceDoesNotExistException(String message) {
		super(message);
	}

	public ResourceDoesNotExistException(Throwable cause) {
		super(cause);
	}

}
