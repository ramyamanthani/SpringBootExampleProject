package com.newproject.exceptions;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.newproject.entity.ErrorMessage;

@RestControllerAdvice
public class GlobalException {
	
	@Autowired
	ErrorMessage errorMessage;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> notFoundException(ResourceNotFoundException e) {
		
		errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setErrorMessage(e.getMessage());
		errorMessage.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> ResourceAlreadyExistsException(AlreadyExistsException e){
		
		errorMessage.setErrorCode(HttpStatus.FOUND.value());
		errorMessage.setErrorMessage(e.getMessage());
		errorMessage.setLocalDateTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FOUND);
	}
}
