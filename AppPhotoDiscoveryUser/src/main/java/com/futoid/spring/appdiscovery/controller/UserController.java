package com.futoid.spring.appdiscovery.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futoid.spring.appdiscovery.model.CreateUserRequestModel;
import com.futoid.spring.appdiscovery.model.CreateUserResponseModel;
import com.futoid.spring.appdiscovery.service.UserService;
import com.futoid.spring.appdiscovery.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/status/check")
	public String checkStatus() {
		return env.getProperty("local.server.port");
	}
	
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser (@RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto newUser = userService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(newUser, CreateUserResponseModel.class); 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
