package com.futoid.spring.mobile.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.futoid.spring.MobileAppWsApplication;
import com.futoid.spring.mobile.model.response.UserRef;

@RestController
@RequestMapping("/users")
public class UserController {

    private final MobileAppWsApplication mobileAppWsApplication;

    UserController(MobileAppWsApplication mobileAppWsApplication) {
        this.mobileAppWsApplication = mobileAppWsApplication;
    }
	
	//if required is false a request param is string it will pass null value, for int throws error
	@GetMapping
	public String getUser(@RequestParam(defaultValue="3") int page, @RequestParam(defaultValue="50", required = false) int limit, @RequestParam(required=false) String sort) {
		return "get user called with page no " + page + " limit " + limit + " sort " + sort;  
	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public UserRef getUser(@PathVariable String userId) {
		UserRef user = new UserRef();
		user.setFirstName("alexa");
		user.setLastName("tois");
		user.setEmail("alex@alex.com");
		
		return user;
	}
	
	@PostMapping
	public String postUser() {
		return "post user called";
	}
	
	@PutMapping
	public String updateUser() {
		return "udpate user called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}
	
}
