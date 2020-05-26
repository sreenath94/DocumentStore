package simpledocumentstore.exception;

/**
 * Custom exception added to handle all Runtime exception
 * in the library
 *	 
 * @author Sreenath S
 *
 */
public class DocumentStoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message = null;

	public DocumentStoreException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
