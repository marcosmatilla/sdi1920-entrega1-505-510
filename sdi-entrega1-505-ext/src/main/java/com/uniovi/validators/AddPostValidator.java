package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Post;

@Component
public class AddPostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Post.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Post post = (Post) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "Error.empty");
		if (post.getTitle().length() < 5 || post.getTitle().length() > 24) {
			errors.rejectValue("title", "Error.add.post.title.length");
		}

		if (post.getContent().length() < 5 || post.getContent().length() > 500) {
			errors.rejectValue("content", "Error.post.add.content.length");
		}

	}

}
