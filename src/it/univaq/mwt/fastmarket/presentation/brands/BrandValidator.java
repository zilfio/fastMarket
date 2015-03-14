package it.univaq.mwt.fastmarket.presentation.brands;

import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BrandValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return Brand.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Brand brand = (Brand) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", brand.getName(), 255);
	}
	
}
