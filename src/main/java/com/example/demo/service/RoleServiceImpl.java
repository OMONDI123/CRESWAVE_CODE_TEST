/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;

/**
 * @author austine
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired private RoleRepository repository;

	@Override
	public Roles getRolesById(Long role_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Optional<Roles> user=repository.findById(role_id);
			if(user.isPresent()) {
				return user.get();
			}else {
				throw new SetUpExceptions("Failed to fetch role with ID");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to fetch role with ID");
		}
	}

	@Override
	public Roles createRoles(RoleModel role) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Roles roles=new Roles();
			roles.setActive(true);
			roles.setName(role.getRoleName());
			roles.setDescription(role.getRoleDescription());
			roles.setApprovalStage("Approved");
			roles.setApproved(true);
			return repository.save(roles);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not create role");
		}
	}

	@Override
	public Roles updateRoles(RoleModel role) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Roles roles=repository.findById(role.getId()).get();
			if(roles==null) {
				throw new SetUpExceptions("Role with ID==="+role.getId()+" "+"not found");
			}
			roles.setActive(true);
			roles.setName(role.getRoleName());
			roles.setDescription(role.getRoleDescription());
			roles.setApprovalStage("Approved");
			roles.setApproved(true);
			return repository.save(roles);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not create role");
		}
	}

	@Override
	public Roles deleteById(Long role_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Roles existEntity = repository.findById(role_id).orElse(null);

			if (existEntity == null) {
				throw new SetUpExceptions("Role is not found");

			}
			existEntity.setActive(false);
			return repository.save(existEntity);
		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not find Role by Id");
			// TODO: handle exception
		}
	}

	@Override
	public List<Roles> getAllRoles() throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findAll();
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to fetch all the roles");
		}
	}

	@Override
	public List<Roles> findByIsActive(boolean isActive) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findByIsActive(isActive);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not fetch active roles");
		}
	}

}
