package com.spring.boot.demo.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.demo.rest.repository.model.User;

/**
 * Class Repository for storing the user data.
 * 
 * @author David
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
