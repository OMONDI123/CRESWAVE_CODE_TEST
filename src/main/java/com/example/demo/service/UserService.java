/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.UserDetails;

/**
 * @author austine
 *
 */
public interface UserService {
	
	public User getUserById(Long user_id) throws SetUpExceptions;

	public User createUser(UserDetails userModel) throws SetUpExceptions;

	public User updateUser(UserDetails userModel) throws SetUpExceptions;

	public User deleteById(Long user_id) throws SetUpExceptions;

	public List<User> getAllUsers() throws SetUpExceptions;

	public List<User> findByIsActive(boolean isActive) throws SetUpExceptions;

	public List<User> getUserByPageAndSize(int page, int size) throws SetUpExceptions;

}
