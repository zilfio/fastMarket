package it.univaq.mwt.fastmarket.presentation.bookings;

import it.univaq.mwt.fastmarket.business.model.Booking;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookingValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Booking.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "errors.date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalPrice", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalPrice", "errors.float");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cart", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDate", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDate", "errors.date");
	}

}
