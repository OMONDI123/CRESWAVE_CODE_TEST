/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Blogs;

/**
 * @author austine
 *
 */
@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long>{
	
	List<Blogs> findByIsActive(boolean isActive);
	
	@Query(value = "SELECT * FROM blog WHERE blog_name ILIKE %?1%",nativeQuery = true)
	List<Blogs> findByName(String name);
	
	@Query(value = "SELECT * FROM blog WHERE blog_description ILIKE %?1%",nativeQuery = true)
	List<Blogs> findByDescription(String desc);
	

}
