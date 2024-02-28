/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;

/**
 * @author austine
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query(value = "SELECT * FROM ad_comment WHERE ad_user_id=?1",nativeQuery = true)
	List<Comment> findCommentByUser(Long user_id);
	
	@Query(value = "SELECT * FROM ad_comment WHERE ad_user_id=?1 AND ad_blog_id=?2",nativeQuery = true)
	List<Comment> findCommentByUserAndBlog(Long yser_id,Long blog_id);
	
	@Query(value = "SELECT * FROM ad_comment WHERE ad_blog_id=?1",nativeQuery = true)
	List<Comment> findCommentByBlog(Long blog_id);
	
	List<Comment> findByIsActive(boolean isactive);

}
