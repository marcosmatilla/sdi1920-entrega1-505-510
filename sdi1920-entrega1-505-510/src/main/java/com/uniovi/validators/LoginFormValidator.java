package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;

@Component
public class LoginFormValidator implements Validator {

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");

//		if (usersService.getUserByEmail(user.getEmail()) == null) {
//			errors.rejectValue("email", "Error.login.mail");
//		}
	}
	
		
	
}
