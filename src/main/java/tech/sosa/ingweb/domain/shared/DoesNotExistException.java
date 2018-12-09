package tech.sosa.ingweb.domain.shared;

public abstract class DoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoesNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DoesNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DoesNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DoesNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
