package br.com.pidgey.converter.values;

import java.lang.reflect.Field;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.validation.Validator;

public class FieldValuesFactory {
	
	public static FieldValues getFieldValue(Field field, Object value) {
		
		FieldValues fieldValues;
		Class<?> type = field.getType();
		if(type == Double.TYPE || type == Double.class) {
			
			PField pfield = field.getAnnotation(PField.class);
			DoubleField doubleField = field.getAnnotation(DoubleField.class);
			Validator.validateDoubleHasDoubleFieldAnnotation(field);
			int decimalPrecision = doubleField.fractionDigits();
			
			DoubleValues doubleValues = new DoubleValues(value, 
					decimalPrecision, pfield.size());
			
			fieldValues = doubleValues;
		} else {
			fieldValues = new FieldValues(value);
		}
		
		return fieldValues;
	}
	
}