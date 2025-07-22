package com.futoid.spring.mobile.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
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
	public String getUser(@RequestParam(defaultValue="3") int page, @RequestParam(defaultValue="50", required = false) int limit, @RequestParam(required=false) String sort) {
		return "get user called with page no " + page + " limit " + limit + " sort " + sort;  
	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRef> getUser(@PathVariable String userId) {
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
