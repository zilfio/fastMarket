package it.univaq.mwt.fastmarket.presentation.nonGroceries;

import it.univaq.mwt.fastmarket.business.model.NonGrocery;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NonGroceryValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return NonGrocery.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NonGrocery nonGrocery = (NonGrocery) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", nonGrocery.getName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "errors.float");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "errors.integer");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "errors.required");
	}

}
