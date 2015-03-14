package it.univaq.mwt.fastmarket.presentation.users;

import it.univaq.mwt.fastmarket.business.model.User;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "username", "errors.maxlength", user.getUsername(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "username", "errors.maxlength", user.getPassword(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "firstName", "errors.maxlength", user.getFirstName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "lastName", "errors.maxlength", user.getLastName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "errors.required");
	}
	
}
