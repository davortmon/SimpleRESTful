package com.spring.boot.demo.rest.repository.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 * Class Entity for storing the user data.
 * 
 * @author David
 *
 */
@Entity
@Table(name = "users_table")
@Transactional
public class User implements Serializable {

	private static final long serialVersionUID = 8648047811712270818L;

	/**
	 * User ID.
	 */
	@Id
	@GeneratedValue
	@Column(nullable = false)
	private Integer id;

	/**
	 * User name.
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * Date of birth of the user.
	 */
	@Column(nullable = false)
	private Date birthdate;
	
	/**
	 * Builder without parameters.
	 */
	public User() {
	}	
	
	/**
	 * Builder with parameters.
	 * @param name User name.
	 * @param birthdate Date of birth of the user.
	 */
	public User(String name, Date birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}
	
	/**
	 * Get User ID.
	 * @return User ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Get User name.
	 * @return User name.
	 */
	@ApiModelProperty(notes = "The name of the user", required = true)
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
	@ApiModelProperty(notes = "The birthdate of the user", required = true)
	public String getBirthdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(birthdate);
	}

	/**
	 * Set Date of birth of the user.
	 * @param birthdate Date of birth of the user.
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", birthdate=" + birthdate + "]";
	}
}
