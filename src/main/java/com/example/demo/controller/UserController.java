/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.HibernateProxyTypeAdapter;
import com.example.demo.util.ResponseEntity;
import com.google.gson.GsonBuilder;

/**
 * @author austine
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository repo;

	@GetMapping(value = "/getUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getUserById(@PathVariable Long id) {
		User user = service.getUserById(id);
		logger.debug("Called UserController.getUserById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String createUser(@RequestBody UserDetails user) {
		String message = "";
		User existingUser = repo.findTop1ByEmail(user.getEmail());
		if (existingUser != null) {
			message = "User with the same Username or email already exists. Please use a different email or username";
			throw new DuplicateKeyException(
					"User with the same Username or email already exists. Please use a different email or username");
		}

		User createdUser = service.createUser(user);
		logger.debug("Called UserController.createUser");
		message = "User Added Successfully.";
		ResponseEntity<?> response = new ResponseEntity<User>(message, 200, createdUser);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@PostMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String updateUser(@RequestBody UserDetails user) {
		User updatedUser = service.updateUser(user);
		logger.debug("Called UserController.updateUser");
		String message = "User Updated Successfully.";
		ResponseEntity<?> response = new ResponseEntity<User>(message, 200, updatedUser);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@PutMapping(value = "/deActivateUserByID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String deleteById(@PathVariable Long id) {
		User deletedUser = service.deleteById(id);
		logger.debug("Called UserController.deleteById");
		String message = "User Deleted Successfully.";
		ResponseEntity<?> response = new ResponseEntity<User>(message, 200, deletedUser);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@GetMapping(value = "/getAllUserList", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getAllUser() {
		List<User> user = service.getAllUsers();
		logger.debug("Called UserController.getAllUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

	@GetMapping(value = "/getActiveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getActiveUser() {
		List<User> user = service.findByIsActive(true);
		logger.debug("Called UserController.getActiveUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

//Typical Pagination implementation
	@GetMapping(value = "/getUserByPage/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getUserByPage(@PathVariable int page, @PathVariable int size) {
		List<User> User = service.getUserByPageAndSize(page, size);
		logger.debug("Called UserController.getUserByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(User);
	}

}
