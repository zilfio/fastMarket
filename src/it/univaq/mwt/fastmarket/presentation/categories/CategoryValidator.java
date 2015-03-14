package it.univaq.mwt.fastmarket.presentation.categories;

import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.common.spring.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return Category.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", category.getName(), 255);
	}
}
