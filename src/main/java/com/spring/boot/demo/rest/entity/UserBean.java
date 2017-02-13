package com.spring.boot.demo.rest.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Class for params with user data.
 * 
 * @author David
 *
 */
public class UserBean {
	
	/**
	 * User name.
	 */
	private String name;
	
	/**
	 * Date of birth of the user.
	 */
	private String birthdate;
	
	/**
	 * Builder without parameters.
	 */
	public UserBean(){}
	
	/**
	 * Builder with parameters.
	 * @param id User ID.
	 * @param name User name.
	 * @param birthdate Date of birth of the user.
	 */
	public UserBean(String name, String birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}
	
	/**
	 * Get User name.
	 * @return User name.
	 */
	@ApiModelProperty(notes = "User name", required = true)
	public String getName() {
		return name;
	}

	/**
	 * Set User name.
	 * @param name User name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get Date of birth of the user.
	 * @return Date of birth of the user.
	 */
	@ApiModelProperty(notes = "Date of birth of the user", required = true)
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Set Date of birth of the user.
	 * @param birthdate Date of birth of the user.
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}
