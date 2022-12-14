package com.kronsoft.project.test;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import com.kronsoft.project.entities.User;
import com.kronsoft.project.exceptions.UserExistsByEmailException;
import com.kronsoft.project.exceptions.UserExistsByUsernameException;
import com.kronsoft.project.service.UserService;

@SpringBootTest
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
	

	public static final String USERNAME = "User188";
	public static final String EMAIL = "user45@gmail.com";
	public static final String PASSWORD = "victorA@2";
	
	public static final String USERNAME_2 = "User2000";
	public static final String EMAIL_2 = "2000@gmail.com";
	public static final String PASSWORD_2 = "victorA@2fffX";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User persistedUser;
	private String persistedUsername;
	private String persistedEmail;
	private String persistedPassword;
	private Long id;
	
	@BeforeAll
	public void createUser() throws UserExistsByEmailException, UserExistsByUsernameException {
		User user = new User();
		user.setUsername(USERNAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		
		persistedUser = userService.createUser(user);
		updateUser();
		Assertions.assertNotNull(persistedUser);
		
	}
	public void updateUser() {
		
		id = persistedUser.getId();
		persistedUsername = persistedUser.getUsername();
		persistedEmail = persistedUser.getEmail();
		persistedPassword = persistedUser.getPassword();
		
	}
	
	@Test
	public void getUserByUsernameTest() {
		User user = userService.getUserByUsername(persistedUser.getUsername());
		Assertions.assertEquals(persistedUsername, user.getUsername());
		Assertions.assertEquals(persistedEmail, user.getEmail());
		Assertions.assertEquals(persistedPassword, user.getPassword());
		
	}
	
	@Test
	public void updateUserNameTest() throws UserExistsByUsernameException {
		persistedUser.setUsername(USERNAME_2);
		persistedUser = userService.updateUserName(persistedUser);
		Assertions.assertEquals(USERNAME_2, persistedUser.getUsername());
		updateUser();
	}
	
	@Test
	public void updateUserPasswordTest() {
		persistedUser.setPassword(PASSWORD_2);
		persistedUser = userService.updateUserPassword(persistedUser);
		Assertions.assertTrue(passwordEncoder.matches(PASSWORD_2, persistedUser.getPassword()));
		updateUser();
	}
	
	@Test
	public void updateUserEmailTest() throws UserExistsByEmailException {
		persistedUser.setEmail(EMAIL_2);
		persistedUser = userService.updateUserEmail(persistedUser);
		Assertions.assertEquals(EMAIL_2, persistedUser.getEmail());
		updateUser();
	}
	
	@AfterAll
	public void deleteUserByIdTest() {
		
		userService.deleteUser(id);
		
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			userService.getUserByUsername(persistedUsername);
		});
	}
	
}
