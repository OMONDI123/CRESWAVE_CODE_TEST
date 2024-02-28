package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Blogs;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.BlogsModel;


/**
 * @author austine
 *
 */
public interface BlogService {
	public Blogs getBlogsById(Long blog_id) throws SetUpExceptions;
	
	public Blogs createBlogs(BlogsModel blog,MultipartFile file) throws SetUpExceptions;
	
	public Blogs updateBlogs(BlogsModel blog,MultipartFile file) throws SetUpExceptions;
	
	public Blogs deleteById(Long blog_id) throws SetUpExceptions;
	
	public List<Blogs> getAllBlogs() throws SetUpExceptions;
	
	public List<Blogs> findByIsActive(boolean isActive) throws SetUpExceptions;
	
	public List<Blogs> findByName(String name) throws SetUpExceptions;
	
	public List<Blogs> findByDescription(String desc) throws SetUpExceptions;
	
	public List<Blogs> getBlogsByPageAndSize(int page, int size) throws SetUpExceptions;

}
