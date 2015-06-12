package br.com.pidgey.converter.values;

import java.lang.reflect.Field;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.annotation.PField;

public class FieldValuesFactory {
	
	public static FieldValues getFieldValue(Field field, Object value) {
		
		FieldValues fieldValues;
		Class<?> type = field.getType();
		if(type == Double.TYPE || type == Double.class) {
			
			PField pfield = field.getAnnotation(PField.class);
			DoubleField doubleField = field.getAnnotation(DoubleField.class);
			validateDoubleAnnotation(doubleField);
			int decimalPrecision = doubleField.fractionDigits();
			
			DoubleValues doubleValues = new DoubleValues(value, 
					decimalPrecision, pfield.size());
			
			fieldValues = doubleValues;
		} else {
			fieldValues = new FieldValues(value);
		}
		
		return fieldValues;
	}
	
	//TODO move to validator
	private static void validateDoubleAnnotation(DoubleField doubleField) {
		if(doubleField == null) {
			//TODO throw exception
		}
	}

}
