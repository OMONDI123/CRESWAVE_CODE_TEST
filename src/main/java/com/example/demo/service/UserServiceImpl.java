/**
 * 
 */
package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

/**
 * @author austine
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Override
	public User getUserById(Long user_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Optional<User> user=userRepository.findById(user_id);
			if(user.isPresent()) {
				return user.get();
			}else {
				throw new SetUpExceptions("Failed to fetch user with ID");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to fetch user with ID");
		}
	}

	@Override
	public User createUser(UserDetails userModel) throws SetUpExceptions {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFullName(userModel.getFirstName() + " " + userModel.getLastName());
		user.setEmail(userModel.getEmail());
		String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        user.setPassword(encodedPassword);
		user.setApprovalStage("Approved");
		user.setApproved(true);
		user.setDateOfBirth(userModel.getDateOfBirth());
		Set<Roles> roles=new HashSet<>();
		for (int i=0;i< userModel.getUserRoleIds().size();i++) {
			Roles role=roleRepo.findById(userModel.getUserRoleIds().get(i)).get();
			roles.add(role);
		}
		user.setRoles(roles);
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(UserDetails userModel) throws SetUpExceptions {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userModel.getId()).get();
		if(user==null) {
			throw new SetUpExceptions("User with Id "+userModel.getId()+" "+"Not found");
		}
		user.setFullName(userModel.getFirstName() + " " + userModel.getLastName());
		user.setEmail(userModel.getEmail());
		String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        user.setPassword(encodedPassword);
		user.setApprovalStage("Approved");
		user.setApproved(true);
		user.setDateOfBirth(userModel.getDateOfBirth());
		Set<Roles> roles=new HashSet<>();
		for (int i=0;i< userModel.getUserRoleIds().size();i++) {
			Roles role=roleRepo.findById(userModel.getUserRoleIds().get(i)).get();
			roles.add(role);
		}
		user.setRoles(roles);
		
		return userRepository.save(user);
	}

	@Override
	public User deleteById(Long user_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			User existEntity = userRepository.findById(user_id).orElse(null);

			if (existEntity == null) {
				throw new SetUpExceptions("User is not found");

			}
			existEntity.setActive(false);
			return userRepository.save(existEntity);
		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not find User by Id");
			// TODO: handle exception
		}
	}

	@Override
	public List<User> getAllUsers() throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return userRepository.findAll();
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not fetch all users");
		}
	}

	@Override
	public List<User> findByIsActive(boolean isActive) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return userRepository.findByIsActive(isActive);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not fetch all users");
		}
	}

	@Override
	public List<User> getUserByPageAndSize(int page, int size) throws SetUpExceptions {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		Page<User> userpage =userRepository.findAll(pageable);
		return userpage.getContent();
	}

}
