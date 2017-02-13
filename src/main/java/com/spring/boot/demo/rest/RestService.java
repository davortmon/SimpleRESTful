package com.spring.boot.demo.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.demo.rest.entity.MessageBean;
import com.spring.boot.demo.rest.entity.UserBean;
import com.spring.boot.demo.rest.entity.UserUpdateBean;
import com.spring.boot.demo.rest.exception.RequiredException;
import com.spring.boot.demo.rest.repository.model.User;
import com.spring.boot.demo.rest.repository.service.UserServiceRepository;
import com.spring.boot.demo.rest.util.DemoUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Class RESTful Service Example.
 * 
 * @author David
 *
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "/api/user")
public class RestService {

	/**
	 * Object Simple Date Format for validation of date of birth.
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Service for storing the user data.
	 */
	private final UserServiceRepository userServiceRepository;
	
	@Autowired
	public RestService(UserServiceRepository userServiceRepository) {
		this.userServiceRepository = userServiceRepository;
	}
	
	@ApiOperation(value = "/getAll", nickname = "getAll", notes="Get all users")
	@RequestMapping(value = "/getAll", method=RequestMethod.GET)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = User.class),
	    @ApiResponse(code = 204, message = "Detail of the error in the request", response = MessageBean.class),
	    @ApiResponse(code = 500, message = "Failure", response = MessageBean.class)
	})
	public ResponseEntity<?> getAll() {
		
		try {
		
			List<User> listUsers = userServiceRepository.getAll();
			
			if (!listUsers.isEmpty()) {
				return new ResponseEntity<List<User>>(userServiceRepository.getAll(), HttpStatus.OK);
			} else {
				return new ResponseEntity<MessageBean>(new MessageBean("Not users were created."), HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "/get", nickname = "get", notes="Get user by identifier.")
	@RequestMapping(value = "/get/{id}", method=RequestMethod.GET)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = User.class),
	    @ApiResponse(code = 400, message = "Detail of the error in the request", response = MessageBean.class),
	    @ApiResponse(code = 500, message = "Failure", response = MessageBean.class)
	})
	public ResponseEntity<?> get(@ApiParam(value="Identifier of the user", allowableValues = "range[1," 
			+ Integer.MAX_VALUE + "]", required = true) @PathVariable("id") Integer id) {
		
		try {
			
			User user = userServiceRepository.get(id);
			
			if (null != user) {
				return new ResponseEntity<User>(userServiceRepository.get(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<MessageBean>(new MessageBean("Identifier not found."), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/create", nickname = "create", notes="Create user.")
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Created user", response = User.class),
	    @ApiResponse(code = 400, message = "Detail of the error in the request", response = MessageBean.class),
	    @ApiResponse(code = 500, message = "Failure", response = MessageBean.class)
	})
	public ResponseEntity<?> create(@ApiParam(value="JSON User Details", required = true) @RequestBody(required=true) UserBean userBean) {

		try {
			
			validationBean(userBean, true);
			
			Date date = sdf.parse(userBean.getBirthdate());
			User user = userServiceRepository.create(userBean.getName(), date);
			
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
			
		} catch (ParseException e) {
			return new ResponseEntity<MessageBean>(new MessageBean("The date must be in format 'dd/MM/yyyy'"), HttpStatus.BAD_REQUEST);
		} catch (RequiredException e) {
			return new ResponseEntity<MessageBean>(new MessageBean(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/update", nickname = "update", notes="Update user data.")	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Updated user", response = User.class),
	    @ApiResponse(code = 400, message = "Detail of the error in the request", response = MessageBean.class),
	    @ApiResponse(code = 500, message = "Failure", response = MessageBean.class)
	})
	public ResponseEntity<?> update(@ApiParam(value="JSON User data to update", required = true) @RequestBody(required=true) UserUpdateBean userBean) {

		try {
			
			validationBean(userBean, false);
			
			if (userServiceRepository.exists(userBean.getId())) {
					
				Date date = null;
				
				if (null != userBean.getBirthdate())
					date = sdf.parse(userBean.getBirthdate());
				
				User user = userServiceRepository.update(userBean.getId(), userBean.getName(), date);

				return new ResponseEntity<User>(user, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<MessageBean>(new MessageBean("Identifier not found."), HttpStatus.BAD_REQUEST);
			}

		} catch (ParseException e) {
			return new ResponseEntity<MessageBean>(new MessageBean("The date must be in format 'dd/MM/yyyy'"), HttpStatus.BAD_REQUEST);
		} catch (RequiredException e) {
			return new ResponseEntity<MessageBean>(new MessageBean(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (RuntimeException e) {
			return new ResponseEntity<MessageBean>(new MessageBean("The identifier can not be null."), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@ApiOperation(value = "/remove", nickname = "remove", notes="Remove user by identifier.")
	@RequestMapping(value = "/remove/{id}", method=RequestMethod.POST)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = MessageBean.class),
	    @ApiResponse(code = 400, message = "Detail of the error in the request", response = MessageBean.class),
	    @ApiResponse(code = 500, message = "Failure", response = MessageBean.class)
	})
	public ResponseEntity<?> remove(@ApiParam(value="Identifier of the user", allowableValues = "range[1," 
			+ Integer.MAX_VALUE + "]", required = true) @PathVariable("id") Integer id) {
		
		try {
			
			if (userServiceRepository.exists(id)) {
				userServiceRepository.remove(id);
				return new ResponseEntity<MessageBean>(new MessageBean("Delete success!!"), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<MessageBean>(new MessageBean("Identifier not found."), HttpStatus.BAD_REQUEST);
				
			}
			
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Validate if create and update operations are given the correct parameters.
	 * Operation create --> Name and Birthdate are required.
	 * Opertation update --> Name or Birthdate is required.
	 * 
	 * @param userBean User data.
	 * @param isCreation If it is a create or update operation.
	 * @throws RequiredException If the data required for the type of operation is not provided.
	 */
	private void validationBean(UserBean userBean, boolean isCreation) throws RequiredException {
		
		if (isCreation) {
			if (DemoUtils.isEmpty(userBean.getName()) || DemoUtils.isEmpty(userBean.getBirthdate())) {
				throw new RequiredException("Fields 'Name' and 'Birthdate' are required.");
			}
			
		} else {
			if (DemoUtils.isEmpty(userBean.getName()) && DemoUtils.isEmpty(userBean.getBirthdate())) {
				throw new RequiredException("Field 'Name' or field 'Birthdate' must be specified.");
			}
		}
	}
	
}
