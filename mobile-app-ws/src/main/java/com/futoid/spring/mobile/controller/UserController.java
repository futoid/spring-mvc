package com.futoid.spring.mobile.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.futoid.spring.MobileAppWsApplication;
import com.futoid.spring.mobile.model.response.UserDetailsRequestModel;
import com.futoid.spring.mobile.model.response.UserRef;
import com.futoid.spring.mobile.model.response.UserUpdateModel;

@RestController
@RequestMapping("/users")
public class UserController {

    private final MobileAppWsApplication mobileAppWsApplication;
    Map<String, UserRef> users;

    UserController(MobileAppWsApplication mobileAppWsApplication) {
        this.mobileAppWsApplication = mobileAppWsApplication;
    }
	
	//if required is false a request param is string it will pass null value, for int throws error
	@GetMapping
	public ResponseEntity<List<UserRef>> getUser() {
		if(users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<UserRef> userList = new ArrayList<>(users.values());
	    return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRef> getUser(@PathVariable String userId) {
		
		String firstName = null;
		int lengthofName = firstName.length();
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<UserRef>(users.get(userId), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRef> postUser(@Validated @RequestBody UserDetailsRequestModel userDetails) {
		UserRef user = new UserRef();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		
		String key = UUID.randomUUID().toString();
		if(users == null) users = new HashMap<>();
		user.setUserId(key);
		users.put(key, user);
		
		return new ResponseEntity<UserRef>(user, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}")
	public ResponseEntity<UserRef> updateUser(@PathVariable String userId, @RequestBody UserUpdateModel userDetail) {
		if(users.containsKey(userId)) {
			UserRef user = users.get(userId);
			user.setFirstName(userDetail.getFirstName());
			user.setLastName(userDetail.getLastName());
			return new ResponseEntity<UserRef>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteUser(@PathVariable String id) {
		if(users.containsKey(id)) {
			users.remove(id);
		}
		return "deleted the user";
	}
	
}
