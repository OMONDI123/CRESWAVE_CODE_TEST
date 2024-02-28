/**
 * 
 */
package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * @author austine
 *
 */
@Entity
@Table(name = "ad_blog")
@Audited
public class Blogs extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930010500613989139L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "ad_blog_id")
	private Long id;
	
	@Column(name = "blog_name",nullable = false,length = 255)
	private String name;
	
	@Column(name = "blog_description")
	private String description;
	
	@Column(name = "filepath",nullable = true)
	private String filepath;
	
	@Column(name = "fileName",nullable = true)
	private String fileName;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the serialversionuid
	 */
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	

}
