package com.springboot.oauth.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.oauth.example.app.model.User;
import com.springboot.oauth.example.app.service.UserService;

/**
 * 
 * @author anilkumar
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//-------------------Retrieve All Users------
	
	@GetMapping(value="/user/",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> getUsers() {
		
		List<User> users=userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}

	//-------------------Retrieve Single User------
	
	@GetMapping(value="/user/{username}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUser(@PathVariable String username) {
		User user=userService.getUser(username);
		if(user==null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(user);
	}
}
