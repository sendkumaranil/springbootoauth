package com.springboot.oauth.example.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.oauth.example.app.model.User;
import com.springboot.oauth.example.app.table.UserTable;

/**
 * 
 * @author anilkumar
 *
 */
@Service
public class UserService {

	public List<User> getAllUsers() {
		return UserTable.users();
	}

	public User getUser(String username) {

		List<User> users=UserTable.users();
		
		User userDetail=users
		.stream() //convert to stream
		.filter(user -> user.getUsername().equalsIgnoreCase(username)) //filter the condition
		.findAny() //return matched value
		.orElse(null); //return null if not found
		
		return userDetail;
	}
	
	public static void main(String[] args) {
		String username="anilk";
		List<User> users=UserTable.users();
		
		System.out.println(
				
				users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findAny().orElse(null)
				
				);//users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username));
		
	}

}
