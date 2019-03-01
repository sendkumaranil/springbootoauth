package com.springboot.oauth.example.app.table;

import java.util.ArrayList;
import java.util.List;

import com.springboot.oauth.example.app.model.User;

public class UserTable {
	
	private static List<User> userTable=new ArrayList<>();
	
	static {
		User user1=new User("anil","anil@gmail.com","+91886655442");
		User user2=new User("ainesh","ainesh@gmail.com","+91806655442");
		
		userTable.add(user2);
		userTable.add(user1);
		
	}
	
	public static List<User> users(){
		return userTable;
	}
}
