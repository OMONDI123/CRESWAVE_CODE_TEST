package com.example.demo.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Blogs;
import com.example.demo.model.BlogsModel;
import com.example.demo.service.BlogService;
import com.example.demo.util.HibernateProxyTypeAdapter;
import com.google.gson.GsonBuilder;
/**
 * @author austine
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogsController {

	@Autowired
	private BlogService blogservice;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value="/getBlogsById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getBlogsById(@PathVariable Long id) {
		Blogs blogs = blogservice.getBlogsById(id);
		logger.debug("Called BlogsController.getBlogsById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(blogs);
	}

	@PostMapping(value="/createBlogs",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String createBlogs(@Valid BlogsModel Blogs,@RequestParam("file") MultipartFile file) {
		Blogs createdBlogs = blogservice.createBlogs(Blogs,file);
		logger.debug("Called BlogsController.createBlogs");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(createdBlogs);
	}

	@PostMapping(value="/updateBlogs",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateBlogs(@Valid BlogsModel Blogs,@RequestParam("file") MultipartFile file) {
		Blogs updatedBlogs = blogservice.updateBlogs(Blogs,file);
		logger.debug("Called BlogsController.updateBlogs");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(updatedBlogs);
	}

	@PutMapping(value="/deActivateBlogsByID/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String deleteById(@PathVariable Long id) {
		Blogs deletedBlogs = blogservice.deleteById(id);
		logger.debug("Called BlogsController.deleteById");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create()
				.toJson(deletedBlogs);
	}

	@GetMapping(value="/getAllblogsList",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getAllblogs() {
		List<Blogs> blogs = blogservice.getAllBlogs();
		logger.debug("Called BlogsController.getAllblogs");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(blogs);
	}

	@GetMapping(value="/getActiveblogs",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getActiveblogs() {
		List<Blogs> blogs = blogservice.findByIsActive(true);
		logger.debug("Called BlogsController.getActiveblogs");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(blogs);
	}

//Typical Pagination implementation
	@GetMapping(value="/getblogsByPage/{page}/{size}",produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@ResponseBody
	public String getblogsByPage(@PathVariable int page, @PathVariable int size) {
		List<Blogs> blogs = blogservice.getBlogsByPageAndSize(page, size);
		logger.debug("Called BlogsController.getblogsByPage");
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(blogs);
	}

}
