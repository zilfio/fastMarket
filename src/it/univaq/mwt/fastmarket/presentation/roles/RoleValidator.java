package it.univaq.mwt.fastmarket.presentation.roles;

import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return Role.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Role role = (Role) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", role.getName(), 255);
	}

}
