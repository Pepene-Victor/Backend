package com.kronsoft.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.entities.User;
import com.kronsoft.project.exceptions.UserExistsByEmailException;
import com.kronsoft.project.exceptions.UserExistsByUsernameException;
import com.kronsoft.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/account-details", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByUsername(@RequestParam String username) {
		
		return userService.getUserByUsername(username);
		
	}
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@Valid @RequestBody User user) throws UserExistsByEmailException, UserExistsByUsernameException {
		
		return userService.createUser(user);
	}
	
	@PutMapping(value = "/update-account-username", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUserName(@RequestBody User user) throws UserExistsByUsernameException {
		
		return userService.updateUserName(user);
		
	}
	
	@PutMapping(value = "/update-account-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUserPassword(@RequestBody User user) {
		
		return userService.updateUserPassword(user);
		
	}
	@PutMapping(value = "/update-account-email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUserEmail(@RequestBody User user) throws UserExistsByEmailException {
		
		return userService.updateUserEmail(user);
		
	}
	
	
}
