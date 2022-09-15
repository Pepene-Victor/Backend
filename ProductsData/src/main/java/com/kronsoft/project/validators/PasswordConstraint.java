package com.kronsoft.project.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Retention(RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { PasswordValidator.class })
public @interface PasswordConstraint {

	String message() default "Password must be between 8 and 20 characters long";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
