package com.spring.boot.demo.rest.repository.service;

import java.util.Date;
import java.util.List;

import com.spring.boot.demo.rest.repository.model.User;

/**
 * Service repository for storing the user data.
 * @author David
 *
 */
public interface UserServiceRepository {

	/**
	 * List all users in database.
	 * @return
	 */
	List<User> getAll();
	
	/**
	 * Get user by identifier.
	 * @param id User ID.
	 * @return User found.
	 */
	User get(Integer id);
	
	/**
	 * Create user in database.
	 * @param name User name.
	 * @param birthdate Date of birth of the user.
	 * @return Created user.
	 */
	User create(String name, Date birthdate);
	
	/**
	 * Update user data in database.
	 * @param id User ID to update.
	 * @param newName new User name.
	 * @param newBirthdate new Date of birth of the user.
	 * @return User with updated data.
	 */
	User update(Integer id, String newName, Date newBirthdate);
	
	/**
	 * Remove user by User ID.
	 * @param id User ID of the user to delete.
	 */
	void remove(Integer id);
	
	/**
	 * Checks the existence of a user in database by User ID.
	 * @param id User ID of user to search.
	 * @return true if user is found.
	 */
	boolean exists(Integer id);
}
