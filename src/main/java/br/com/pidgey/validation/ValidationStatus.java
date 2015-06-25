package br.com.pidgey.validation;

public class ValidationStatus {
	
	private final boolean error;
	private final String message;
	
	public ValidationStatus(boolean error, String message) {
		super();
		this.error = error;
		this.message = message;
	}
	
	public boolean isError() {
		return error;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ValidationStatus [error=" + error + ", message=" + message
				+ "]";
	}
	
}