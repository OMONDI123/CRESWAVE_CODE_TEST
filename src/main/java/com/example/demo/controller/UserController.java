/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.example.demo.util.HibernateProxyTypeAdapter;
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
	
	@Autowired private UserService service;
	
	@GetMapping(value="/getUserById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getUserById(@PathVariable Long id) {
		User user = service.getUserById(id);
		logger.debug("Called UserController.getUserById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

	@PostMapping(value="/createUser",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String createUser(@RequestBody UserDetails user) {
		User createdUser = service.createUser(user);
		logger.debug("Called UserController.createUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(createdUser);
	}

	@PostMapping(value="/updateUser",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String updateUser(@RequestBody UserDetails user) {
		User updatedUser = service.updateUser(user);
		logger.debug("Called UserController.updateUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(updatedUser);
	}

	@PutMapping(value="/deActivateUserByID/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String deleteById(@PathVariable Long id) {
		User deletedUser = service.deleteById(id);
		logger.debug("Called UserController.deleteById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(deletedUser);
	}

	@GetMapping(value="/getAllUserList",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getAllUser() {
		List<User> user = service.getAllUsers();
		logger.debug("Called UserController.getAllUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

	@GetMapping(value="/getActiveUser",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getActiveUser() {
		List<User> user = service.findByIsActive(true);
		logger.debug("Called UserController.getActiveUser");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(user);
	}

//Typical Pagination implementation
	@GetMapping(value="/getUserByPage/{page}/{size}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getUserByPage(@PathVariable int page, @PathVariable int size) {
		List<User> User = service.getUserByPageAndSize(page, size);
		logger.debug("Called UserController.getUserByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(User);
	}

}
