package com.futoid.spring.mobile.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.futoid.spring.mobile.Utils.Utils;
import com.futoid.spring.mobile.model.response.UserDetailsRequestModel;
import com.futoid.spring.mobile.model.response.UserRef;

@Service
public class UserService implements UserServiceI {
	
	  Map<String, UserRef> users;
	  Utils utils;
	public UserService() {
	
	}
	
	@Autowired
	public UserService(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRef postUser(UserDetailsRequestModel userDetails) {
		UserRef user = new UserRef();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		
		String key = utils.generateId();
		if(users == null) users = new HashMap<>();
		user.setUserId(key);
		users.put(key, user);
		
		return user;
	}

	
}
