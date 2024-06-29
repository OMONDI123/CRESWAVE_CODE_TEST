package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
	
	List<Roles> findByIsActive(boolean isactive);

}
