package it.univaq.mwt.fastmarket.presentation.cartLines;

import it.univaq.mwt.fastmarket.business.model.CartLine;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CartLineValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return CartLine.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "errors.integer");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cart", "errors.required");
	}
	
}
