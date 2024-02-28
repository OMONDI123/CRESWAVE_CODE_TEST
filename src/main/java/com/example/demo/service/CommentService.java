/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Comment;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.CommentModel;

/**
 * @author austine
 *
 */
public interface CommentService {
	
public Comment getCommentById(Long comment_id) throws SetUpExceptions;
	
	public Comment createComment(CommentModel comment) throws SetUpExceptions;
	
	public Comment updateComment(CommentModel comment) throws SetUpExceptions;
	
	public Comment deleteById(Long comment_id) throws SetUpExceptions;
	
	public List<Comment> getAllComment() throws SetUpExceptions;
	
	public List<Comment> findByIsActive(boolean isActive) throws SetUpExceptions;
	
	public List<Comment> findCommentByUser(long user_id) throws SetUpExceptions;
	
	public List<Comment> findCommentByBlog(long blod_id) throws SetUpExceptions; 
	
	public List<Comment> findCommentByUserAndBlog(long user_id,long blog_id) throws SetUpExceptions;
	
	public List<Comment> getCommentByPageAndSize(int page, int size) throws SetUpExceptions;

}
