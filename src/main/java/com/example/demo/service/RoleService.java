/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Roles;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.RoleModel;

/**
 * @author austine
 *
 */
public interface RoleService {
	
public Roles getRolesById(Long role_id) throws SetUpExceptions;
	
	public Roles createRoles(RoleModel role) throws SetUpExceptions;
	
	public Roles updateRoles(RoleModel role) throws SetUpExceptions;
	
	public Roles deleteById(Long role_id) throws SetUpExceptions;
	
	public List<Roles> getAllRoles() throws SetUpExceptions;
	
	public List<Roles> findByIsActive(boolean isActive) throws SetUpExceptions;

}
