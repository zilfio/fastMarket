package it.univaq.mwt.fastmarket.presentation.addresses;

import it.univaq.mwt.fastmarket.business.model.Address;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Address.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Address address = (Address) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "street", "errors.maxlength", address.getStreet(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetNumber", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetNumber", "errors.integer");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "country", "errors.maxlength", address.getCountry(), 255);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "district", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "errors.integer");
	}

}
