package com.spring.boot.demo.rest.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Class for response with the detail of the error in the request.
 * 
 * @author David
 *
 */
public class MessageBean {

	/**
	 * Detail of the error in the request.
	 */
	private String message;
	
	/**
	 * Builder with parameters.
	 * 
	 * @param message Detail of the error in the request.
	 */
	public MessageBean(String message) {
		super();
		this.message = message;
	}

	/**
	 *  Get detail of the error in the request.
	 * @return String Detail of the error in the request.
	 */
	@ApiModelProperty(notes = "Detail of the error in the request.", required = true)
	public String getMessage() {
		return message;
	}
}
