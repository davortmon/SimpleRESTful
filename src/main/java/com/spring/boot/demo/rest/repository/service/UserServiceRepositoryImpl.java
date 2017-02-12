package com.spring.boot.demo.rest.repository.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.demo.rest.repository.UserRepository;
import com.spring.boot.demo.rest.repository.model.User;
import com.spring.boot.demo.rest.util.DemoUtils;

/**
 * Class implementation of UserServiceRepository.
 * 
 * @author David
 *
 */
@Component
public class UserServiceRepositoryImpl implements UserServiceRepository {

	/**
	 * Object Repository for storing the user data.
	 */
	private final UserRepository userRepository;
	
	/**
	 * Builder with parameters.
	 * @param userRepository Repository for storing the user data.
	 */
	@Autowired
	public UserServiceRepositoryImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> getAll() {
		List<User> listUsers = new ArrayList<User>();
		Iterable<User> it= userRepository.findAll();
		it.forEach(listUsers::add);
		
		return listUsers;
	}
	
	@Override
	public User get(Integer id) {
		
		User user = userRepository.findOne(id);
		
		return user;
	}
	
	@Override
	public User create(String name, Date birthdate) {
		User user = userRepository.save(new User(name, birthdate));
		return user;
	}
	
	@Override
	public void remove(Integer id) {
		userRepository.delete(id);
	}
	
	@Override
	public User update(Integer id, String newName, Date newBirthdate) {
		
		User user = get(id);
		
		if (null != user) {
			
			if (!DemoUtils.isEmpty(newName))
				user.setName(newName);
			
			if (null != newBirthdate)
				user.setBirthdate(newBirthdate);
			
			userRepository.save(user);
		}
		
		return user;
	}
	
	@Override
	public boolean exists(Integer id) {
		return userRepository.exists(id);
	}
}
