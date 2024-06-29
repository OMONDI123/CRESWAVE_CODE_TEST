package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Roles;

public class AuthResponse {
	private LoginRequest loginRequest;
	private String jwtToken;
	private String fullName;
	private long user_id;

	private Set<Roles> roles=new HashSet<Roles>();
	public LoginRequest getLoginRequest() {
		return loginRequest;
	}
	public void setLoginRequest(LoginRequest loginRequest) {
		this.loginRequest = loginRequest;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public AuthResponse() {
		
	}
	
	
	
	public AuthResponse(LoginRequest loginRequest, String jwtToken, String fullName, long user_id,
			Set<Roles> roles) {
		super();
		this.loginRequest = loginRequest;
		this.jwtToken = jwtToken;
		this.fullName = fullName;
		this.user_id = user_id;
		this.roles = roles;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	

}
