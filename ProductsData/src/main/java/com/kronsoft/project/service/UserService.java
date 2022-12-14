package com.kronsoft.project.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.UserRepository;
import com.kronsoft.project.entities.User;
import com.kronsoft.project.exceptions.UserExistsByEmailException;
import com.kronsoft.project.exceptions.UserExistsByUsernameException;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(User user) throws UserExistsByEmailException, UserExistsByUsernameException {
		
		user.setCreationDate(LocalDateTime.now());
		
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserExistsByEmailException(user.getEmail());
		}
		
		else if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserExistsByUsernameException(user.getUsername());
		}
		
		String password = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(password));
		
		return userRepository.save(user);
		
		
	}
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username).get();
	}
	
	public User updateUserName(User user) throws UserExistsByUsernameException{
		
		String username = user.getUsername();
		
		if (userRepository.existsByUsername(username))
			throw new UserExistsByUsernameException(username);

		return userRepository.save(user);
		
	}
	
	public User updateUserPassword(User user) {
		
		String newPassword = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(newPassword));
		
		return userRepository.save(user);
		
	}

	public User updateUserEmail(User user) throws UserExistsByEmailException {
		
		String email = user.getEmail();

		if (userRepository.existsByEmail(email)) 
			throw new UserExistsByEmailException(email);
		
		return userRepository.save(user);
		
	}
	
	public void deleteUser(Long id) {
		
		userRepository.deleteById(id);
	}
	
	
}
