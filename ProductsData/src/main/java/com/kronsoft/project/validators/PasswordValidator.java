package com.kronsoft.project.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String>{

	private static final int MIN_LENGHT = 8;
	private static final int MAX_LENGHT = 20;
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		if (Objects.isNull(password))
			return false;
		
		if (password.length() < MIN_LENGHT || password.length() > MAX_LENGHT)
			return false;
		
		return true;
	}

}
