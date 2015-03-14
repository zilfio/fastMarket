package it.univaq.mwt.fastmarket.presentation.provinces;

import it.univaq.mwt.fastmarket.business.model.Province;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProvinceValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Province.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Province province = (Province) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "abbreviation", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "abbreviation", "errors.maxlength", province.getAbbreviation(), 2);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "region", "errors.required");
	}
	
}
