package it.univaq.mwt.fastmarket.presentation.groceries;

import it.univaq.mwt.fastmarket.business.model.Grocery;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GroceryValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return Grocery.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Grocery grocery = (Grocery) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", grocery.getName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "errors.float");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "errors.integer");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDate", "errors.date");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "errors.required");
	}
	
}
