package br.com.pidgey.exception;

/**
 * Must be thrown as soon as a problem is identified 
 * during the parse process
 * @author lalsberg
 *
 */
public class ParseException extends Exception {

	private static final long serialVersionUID = -6405859181573182765L;
	
	String message;
	
	public ParseException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ParseException [message=" + message + "]";
	}

}