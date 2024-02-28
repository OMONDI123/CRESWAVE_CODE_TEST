/**
 * 
 */
package com.example.demo.model;

/**
 * @author austine
 *
 */
public class CommentModel {
	private Long id;
	
	private String message;
	
	private long blog_id;
	
	private long user_id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the blog_id
	 */
	public long getBlog_id() {
		return blog_id;
	}

	/**
	 * @param blog_id the blog_id to set
	 */
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}

	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	
	
	

}
