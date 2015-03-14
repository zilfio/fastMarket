package it.univaq.mwt.fastmarket.presentation.intoleranceCategories;

import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class IntoleranceCategoryValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return IntoleranceCategory.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		IntoleranceCategory intoleranceCategory = (IntoleranceCategory) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", intoleranceCategory.getName(), 255);		
	}

}
