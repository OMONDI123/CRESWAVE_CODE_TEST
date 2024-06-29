/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Blogs;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.CommentModel;
import com.example.demo.repository.BlogsRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;

/**
 * @author austine
 *
 */
@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository repository;
	
	@Autowired
	private BlogsRepository blogRepo;

	@Override
	public Comment getCommentById(Long comment_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Optional<Comment> comment=repository.findById(comment_id);
			if(comment.isPresent()) {
			return comment.get();
			}
			else {
				throw new SetUpExceptions("CoursePaymentMatrix not found");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not fetch Comment by id");
		}
	}

	@Override
	public Comment createComment(CommentModel comment1) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Comment comment=new Comment();
			comment.setActive(true);
			comment.setMessage(comment1.getMessage());
			User user=userRepository.findById(comment1.getUser_id()).get();
			if(user==null) {
				throw new SetUpExceptions("User with ID "+comment1.getUser_id()+" not found");
			}
			comment.setUser(user);
			Blogs blog=blogRepo.findById(comment1.getBlog_id()).get();
			if(blog==null) {
				throw new SetUpExceptions("Blog with ID "+comment1.getBlog_id()+" not found");
			}
			comment.setBlog(blog);
			return repository.save(comment);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not create comment");
		}
		
	}

	@Override
	public Comment updateComment(CommentModel comment1) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Comment comment=repository.findById(comment1.getId()).get();
			if(comment==null) {
				throw new SetUpExceptions("Comment with ID "+comment1.getUser_id()+" not found");
			}
			comment.setActive(true);
			comment.setMessage(comment1.getMessage());
			comment.setUser(userRepository.findById(comment1.getUser_id()).get());
			comment.setBlog(blogRepo.findById(comment1.getBlog_id()).get());
			return repository.save(comment);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not create comment");
		}

	}

	@Override
	public Comment deleteById(Long comment_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			Comment existEntity = repository.findById(comment_id).orElse(null);

			if (existEntity == null) {
				throw new SetUpExceptions("Comment is not found");

			}
			existEntity.setActive(false);
			return repository.save(existEntity);
		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not find comment");
			// TODO: handle exception
		}
	}

	@Override
	public List<Comment> getAllComment() throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findAll();
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to Fetch all comments");
		}
	}

	@Override
	public List<Comment> findByIsActive(boolean isActive) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findByIsActive(isActive);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to Fetch  active comments");
		}
	}

	@Override
	public List<Comment> findCommentByUser(long user_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findCommentByUser(user_id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to Fetch comments made by user with id=====>>>"+user_id);
		}
	}

	@Override
	public List<Comment> findCommentByBlog(long blog_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findCommentByBlog(blog_id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to Fetch comments for the blog with id===="+blog_id);
		}
	}

	@Override
	public List<Comment> findCommentByUserAndBlog(long user_id, long blog_id) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return repository.findCommentByUserAndBlog(user_id,blog_id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Failed to Fetch comments for the blog with id===="+blog_id+" "+"made by user with id===>>"+user_id);
		}
	}

	@Override
	public List<Comment> getCommentByPageAndSize(int page, int size) throws SetUpExceptions {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		Page<Comment> patientPage = repository.findAll(pageable);
		return patientPage.getContent();
	}

}
