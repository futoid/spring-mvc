package com.futoid.spring.mobile.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.futoid.spring.mobile.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessage = ex.getLocalizedMessage();
		
		if(errorMessage==null) {			
			errorMessage=ex.toString();
		}
		
		ErrorMessage err =  new ErrorMessage(new Date(), errorMessage);
		
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class, UserExceptionClass.class})
	public ResponseEntity<Object> handleNullException(Exception ex, WebRequest request){
		
		String errorMessage = ex.getLocalizedMessage();
		
		if(errorMessage==null) {			
			errorMessage=ex.toString();
		}
		
		ErrorMessage err =  new ErrorMessage(new Date(), errorMessage);
		
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
