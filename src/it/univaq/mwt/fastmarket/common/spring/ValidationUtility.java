package it.univaq.mwt.fastmarket.common.spring;

import org.springframework.validation.Errors;

public class ValidationUtility {

	public static void rejectIfMaxLength(Errors errors, String fieldName, String errorMessage, String fieldValue, int maxlength) {
		if (fieldValue != null && fieldValue.length() > maxlength) {
			Object[] args = {maxlength};
			errors.rejectValue(fieldName, errorMessage, args, "error.maxlength");
		}
	}
	
	public static void invalidField(Errors errors, String fieldName, String errorMessage) {
		errors.rejectValue(fieldName, errorMessage);
	}
}
