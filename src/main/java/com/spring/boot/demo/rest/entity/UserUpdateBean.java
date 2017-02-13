package com.spring.boot.demo.rest.entity;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * Class for params with user data.
 * 
 * @author David
 *
 */
public class UserUpdateBean extends UserBean {
	
	/**
	 * User ID.
	 */
	@NotNull
	private Integer id;
	
	/**
	 * Builder without parameters.
	 */
	public UserUpdateBean(){
		super();
	}
	
	/**
	 * Builder with parameters.
	 * @param id User ID.
	 * @param name User name.
	 * @param birthdate Date of birth of the user.
	 */
	public UserUpdateBean(Integer id, String name, String birthdate) {
		super(name, birthdate);
		this.id = id;
	}
	
	/**
	 * Get User ID.
	 * @return User ID.
	 */
	@ApiModelProperty(notes = "Identifier of the user", required = true)
	public Integer getId() {
		return id;
	}

	/**
	 * Set User ID.
	 * @param id User ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * Get User name.
	 * @return User name.
	 */
	@Override
	@ApiModelProperty(notes = "User name", required = false)
	public String getName() {
		return super.getName();
	}
	
	
	/**
	 * Get Date of birth of the user.
	 * @return Date of birth of the user.
	 */
	@Override
	@ApiModelProperty(notes = "Date of birth of the user", required = false)
	public String getBirthdate() {
		return super.getBirthdate();
	}
	
}
