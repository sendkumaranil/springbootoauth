package com.springboot.oauth.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	//-------------------Create a User------
	
	@PostMapping(value="/user/",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder uriBuilder) {
		boolean isUserExist=userService.isUserExists(user);
		if(isUserExist) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		userService.saveUser(user);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(uriBuilder.path("/user/{username}").buildAndExpand(user.getUsername()).toUri());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	//-------------------Update existing User------
	
	@PutMapping(value="/user/",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> updateUser(@RequestBody User user){
		boolean isUserExist=userService.isUserExists(user);
		if(!isUserExist) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.updateUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//-------------------Delete existing User------
	
	@DeleteMapping(value="/user/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable String username){
		boolean isUserExist=userService.isUserExists(username);
		if(!isUserExist) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUser(username);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
