package br.com.pidgey.validation;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.annotation.Many;
import br.com.pidgey.exception.ParseException;

public class Validator {
	
	private static Logger logger = LoggerFactory.getLogger(Validator.class);
	
	public static void validateManyNotNull(Field field, Many many) 
			throws ParseException {
		if(many == null) {
			String message = "The collection " + field.getName() + 
					" must be annotated with Many.";
			logger.error(message);
			throw new ParseException(message);
		}
	}
	
	public static void validateRepeatedIsSpecified(Field field, Many many) 
			throws ParseException {
		if(many.repeated() == -1) {
			String message = "The Many annotation in the collection " + 
					field.getName() + " must specify the attribute repeated.";
			logger.error(message);
			throw new ParseException(message);
		}
	}
	
	public static void validateStringIsNoLongerThanSize(String value, int size, 
			Field field) throws ParseException {
		
		if(value.length() > size) {
			String message = "The value of the field '" + 
					field.getName() + "' is longer than it's specified size. " + 
					"Specified size: " + size + ", actual size: " + value.length() + 
					", value: " + value;
			logger.error(message);
			throw new ParseException(message);
		}
	}
	
	public static void validateDoubleHasDoubleFieldAnnotation(Field field) {
		DoubleField doubleField = field.getAnnotation(DoubleField.class);
		if(doubleField == null) {
			//TODO throw exception
		}
	}

}