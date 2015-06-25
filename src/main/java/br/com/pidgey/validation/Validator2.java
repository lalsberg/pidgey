package br.com.pidgey.validation;

import java.lang.reflect.Field;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.exception.ParseException;

public class Validator2 {
	
	public void validate(Field field) {
		ValidationStatus status = validateDoubleHasDoubleFieldAnnotation(field);
		///TODO other validations
		if(status.isError()) {
			throw new ParseException(status.getMessage());
		}
	}

	private ValidationStatus validateDoubleHasDoubleFieldAnnotation(Field field) {
		boolean error = false;
		String message = null;
		DoubleField doubleField = field.getAnnotation(DoubleField.class);
		if(doubleField == null) {
			error = true;
			message = "Double field '" + field.getName() + "' must be annotated with DoubleField.";
		}
		return new ValidationStatus(error, message);
	}

}
