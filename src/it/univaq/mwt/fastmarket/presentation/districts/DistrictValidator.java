package it.univaq.mwt.fastmarket.presentation.districts;

import it.univaq.mwt.fastmarket.business.model.District;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DistrictValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return District.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		District district = (District) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", district.getName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "province", "errors.required");
	}

}
