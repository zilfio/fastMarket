package it.univaq.mwt.fastmarket.presentation.regions;

import it.univaq.mwt.fastmarket.business.model.Region;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegionValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Region.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Region region = (Region) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", region.getName(), 255);
	}

}
