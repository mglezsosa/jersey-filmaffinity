package tech.sosa.ingweb.domain.director;

public class DirectorDoesNotExistException extends RuntimeException {

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

}
