package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findTop1ByEmail(String userName);

	List<User> findByIsActive(boolean isActive);

//	@Query(value = "SELECT * FROM ad_user where email=?1", nativeQuery = true)
//	User findByUserName(String userName);

}
