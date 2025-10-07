package com.futoid.spring.mobile.services;

import com.futoid.spring.mobile.model.response.UserDetailsRequestModel;
import com.futoid.spring.mobile.model.response.UserRef;

public interface UserServiceI {
	UserRef postUser(UserDetailsRequestModel userDetails);
}
