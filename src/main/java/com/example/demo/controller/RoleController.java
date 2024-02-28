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

import com.example.demo.entity.Roles;
import com.example.demo.model.RoleModel;
import com.example.demo.service.RoleService;
import com.example.demo.util.HibernateProxyTypeAdapter;
import com.google.gson.GsonBuilder;

/**
 * @author austine
 *
 */
@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService service;

	@GetMapping(value = "/getRoleById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getRoleById(@PathVariable Long id) {
		Roles role = service.getRolesById(id);
		logger.debug("Called RoleController.getRoleById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(role);
	}

	@PostMapping(value = "/createRole", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public String createRole(@RequestBody RoleModel role) {
		Roles createdRole = service.createRoles(role);
		logger.debug("Called RoleController.createRole");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(createdRole);
	}

	@PostMapping(value = "/updateRole", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateRole(@Valid RoleModel role) {
		Roles updatedRole = service.updateRoles(role);
		logger.debug("Called RoleController.updateRole");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(updatedRole);
	}

	@PutMapping(value = "/deActivateRoleByID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public String deleteById(@PathVariable Long id) {
		Roles deletedRole = service.deleteById(id);
		logger.debug("Called RoleController.deleteById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(deletedRole);
	}

	@GetMapping(value = "/getAllRoleList", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getAllRole() {
		List<Roles> roles = service.getAllRoles();
		logger.debug("Called RoleController.getAllRole");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(roles);
	}

	@GetMapping(value = "/getActiveRole", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getActiveRole() {
		List<Roles> roles = service.findByIsActive(true);
		logger.debug("Called RoleController.getActiveRole");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(roles);
	}

}
