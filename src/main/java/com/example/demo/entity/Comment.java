/**
 * 
 */
package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author austine
 *
 */
@Entity
@Table(name = "ad_comment")
@Audited
public class Comment extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	@Column(name = "ad_comment_id")
	private Long id;
	
	@Column(name = "blog_description",length = 30000,nullable = false)
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "ad_blog_id")
	@JsonIgnore
	private Blogs blog;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "ad_user_id")
	@JsonIgnore
	private User user;



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
	 * @return the blog
	 */
	public Blogs getBlog() {
		return blog;
	}



	/**
	 * @param blog the blog to set
	 */
	public void setBlog(Blogs blog) {
		this.blog = blog;
	}



	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
