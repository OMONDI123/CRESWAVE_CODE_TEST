package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/auth/")
public class AuthController {
	  @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private UserRepository userRepo;

	    @PostMapping("authenticate")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest authenticationRequest) throws Exception {

	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("Incorrect username or password", e);
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
	        User user=userRepo.findByUsername(authenticationRequest.getUserName());
	        final String jwt = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthResponse(authenticationRequest,jwt,user.getFullName(),user.getId(),user.getRoles()));
	    }
	}
