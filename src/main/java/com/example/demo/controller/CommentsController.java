/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.entity.Comment;
import com.example.demo.model.CommentModel;
import com.example.demo.service.CommentService;
import com.example.demo.util.HibernateProxyTypeAdapter;
import com.example.demo.util.ResponseEntity;
import com.google.gson.GsonBuilder;

/**
 * @author austine
 *
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentsController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommentService service;
	
	@GetMapping(value="/getCommentById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getCommentById(@PathVariable Long id) {
		Comment comment = service.getCommentById(id);
		logger.debug("Called CommentController.getCommentById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(comment);
	}

	@PostMapping(value="/createComment",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String createComment(@RequestBody CommentModel comment) {
		Comment createdComment = service.createComment(comment);
		logger.debug("Called CommentController.createComment");
		String message="Comment Added Successfully.";
		ResponseEntity<?> response=new ResponseEntity<Comment>(message, 200, createdComment);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@PostMapping(value="/updateComment",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String updateComment(@RequestBody CommentModel comment) {
		Comment updatedComment = service.updateComment(comment);
		logger.debug("Called CommentController.updateComment");
		String message="Comment Updated Successfully.";
		ResponseEntity<?> response=new ResponseEntity<Comment>(message, 200, updatedComment);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@PutMapping(value="/deActivateCommentByID/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String deleteById(@PathVariable Long id) {
		Comment deletedComment = service.deleteById(id);
		logger.debug("Called CommentController.deleteById");
		String message="Comment Deleted Successfully.";
		ResponseEntity<?> response=new ResponseEntity<Comment>(message, 200, deletedComment);
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(response);
	}

	@GetMapping(value="/getAllCommentList",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String getAllComment() {
		List<Comment> Comment = service.getAllComment();
		logger.debug("Called CommentController.getAllComment");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(Comment);
	}

	@GetMapping(value="/getActiveComment",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String getActiveComment() {
		List<Comment> Comment = service.findByIsActive(true);
		logger.debug("Called CommentController.getActiveComment");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(Comment);
	}

//Typical Pagination implementation
	@GetMapping(value="/getCommentByPage/{page}/{size}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String getCommentByPage(@PathVariable int page, @PathVariable int size) {
		List<Comment> comment = service.getCommentByPageAndSize(page, size);
		logger.debug("Called CommentController.getCommentByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(comment);
	}
	
	@GetMapping(value="/getCommentByUserAndBlog/{user_id}/{blog_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getCommentByUserAndBlog(@PathVariable int user_id, @PathVariable int blog_id) {
		List<Comment> comment = service.findCommentByUserAndBlog(user_id, blog_id);
		logger.debug("Called CommentController.getCommentByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(comment);
	}
	
	@GetMapping(value="/getCommentByUser/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getCommentByUser(@PathVariable int user_id) {
		List<Comment> comment = service.findCommentByUser(user_id);
		logger.debug("Called CommentController.getCommentByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(comment);
	}
	
	@GetMapping(value="/getCommentByBlog/{blog_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getCommentByBlog( @PathVariable int blog_id) {
		List<Comment> comment = service.findCommentByBlog( blog_id);
		logger.debug("Called CommentController.getCommentByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(comment);
	}

}
