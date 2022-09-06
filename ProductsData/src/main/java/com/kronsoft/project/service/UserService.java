package com.kronsoft.project.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.UserRepository;
import com.kronsoft.project.entities.User;
import com.kronsoft.project.exceptions.UserExistsByEmailException;
import com.kronsoft.project.exceptions.UserExistsByUsernameException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(User user) throws UserExistsByEmailException, UserExistsByUsernameException {
		
		user.setCreationDate(LocalDateTime.now());
		
		final String email = user.getEmail();
		final String username = user.getUsername();
		if (userRepository.existsByEmail(email)) {
			throw new UserExistsByEmailException(email);
		}
		
		if (userRepository.existsByUsername(username)) {
			throw new UserExistsByUsernameException(username);
		}
		
		String password = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(password));
		
		return userRepository.save(user);
		
		
	}

	public User updateUserName(User user) throws UserExistsByUsernameException {
			
		final String username = user.getUsername();
		
		if (userRepository.existsByUsername(username)) {
			throw new UserExistsByUsernameException(username);
		}
		
		return userRepository.save(user);
		
	}
	
	public User updateUserPassword(User user) {
		
		String newPassword = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(newPassword));
		
		return userRepository.save(user);
		
	}
	
}
