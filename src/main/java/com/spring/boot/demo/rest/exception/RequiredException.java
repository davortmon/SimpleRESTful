package com.spring.boot.demo.rest.exception;

/**
 * Class Exception for the validation of fields required for operations.
 * 
 * @author David
 *
 */
public class RequiredException extends Exception {

	private static final long serialVersionUID = 3768501984551069137L;

	/**
	 * Message of the parameter required.
	 */
	private String message;
	
	/**
	 * Builder with parameters.
	 * @param message Message of the parameter required.
	 */
	public RequiredException(String message){
		this.message = message;
	}

	/**
	 * Get message of the parameter required.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message of the parameter required.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
