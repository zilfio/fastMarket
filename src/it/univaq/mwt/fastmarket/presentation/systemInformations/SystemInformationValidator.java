package it.univaq.mwt.fastmarket.presentation.systemInformations;

import it.univaq.mwt.fastmarket.business.model.SystemInformation;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SystemInformationValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return SystemInformation.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SystemInformation systemInformation = (SystemInformation) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", systemInformation.getName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "errors.required");
	}

}
