package com.kronsoft.project.controllers.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kronsoft.project.exceptions.ProductExistsByIdException;
import com.kronsoft.project.exceptions.ProductIdNotExistException;
import com.kronsoft.project.exceptions.UserExistsByEmailException;
import com.kronsoft.project.exceptions.UserExistsByUsernameException;

@ControllerAdvice
public class ControllerExceptionsHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityException(DataIntegrityViolationException e) {
		
		return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e){
		
		StringBuilder sb = new StringBuilder();
		
		e.getAllErrors().stream().forEach(exception -> {
			sb.append(exception.getDefaultMessage() + "\n");
		});
		
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserExistsByUsernameException.class)
	public ResponseEntity<String> UserExistsByUsernameException( UserExistsByUsernameException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserExistsByEmailException.class)
	public ResponseEntity<String> userExistsByEmailException( UserExistsByEmailException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductIdNotExistException.class)
	public ResponseEntity<String> productNotExistsByIdException( ProductIdNotExistException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ProductExistsByIdException.class)
	public ResponseEntity<String> productExistsByIdException( ProductIdNotExistException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
