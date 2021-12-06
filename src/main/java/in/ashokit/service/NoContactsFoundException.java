package in.ashokit.service;

public class NoContactsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoContactsFoundException() {
	}
	
	public NoContactsFoundException(String msg) {
		super(msg);
	}
}
