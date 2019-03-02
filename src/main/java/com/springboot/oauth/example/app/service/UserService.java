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
	
	public boolean isUserExists(User user) {
	
		List<User> users=UserTable.users();
		User existUser=users
				.stream()
				.filter(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))
				.findAny()
				.orElse(null);
		
		if(existUser==null) {
			return false;
		}
		
		return true;
	}
	
	public boolean isUserExists(String username) {
		
		List<User> users=UserTable.users();
		User existUser=users
				.stream()
				.filter(u -> u.getUsername().equalsIgnoreCase(username))
				.findAny()
				.orElse(null);
		
		if(existUser==null) {
			return false;
		}
		
		return true;
	}
	
	public void saveUser(User user) {
		UserTable.users().add(user);
	}

	public void updateUser(User user) {
		List<User> users=UserTable.users();
		int i=0;
		for(User u:users) {
			if(u.getUsername().equalsIgnoreCase(user.getUsername())) {
				users.set(i, user);
			}
			i++;
		}
	}

	public void deleteUser(String username) {
		List<User> users=UserTable.users();
		int i=0;
		for(User u:users) {
			if(u.getUsername().equalsIgnoreCase(username)) {
				users.remove(i);
			}
			i++;
		}
	}

}
