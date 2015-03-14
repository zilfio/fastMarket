package it.univaq.mwt.fastmarket.presentation.carts;

import it.univaq.mwt.fastmarket.business.model.Cart;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CartValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return Cart.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cart cart = (Cart) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", cart.getName(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "errors.required");
	}
	
}
