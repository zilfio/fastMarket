package it.univaq.mwt.fastmarket.presentation.deliveries;

import it.univaq.mwt.fastmarket.business.model.Delivery;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DeliveryValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Delivery.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalPrice", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalPrice", "errors.float");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cart", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dispatchDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dispatchDate", "errors.date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryCosts", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryCosts", "errors.float");
	}

}
