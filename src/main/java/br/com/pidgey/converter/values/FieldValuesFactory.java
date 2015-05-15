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
			
			DoubleValues doubleValues = new DoubleValues();
			doubleValues.setValue(value);
			doubleValues.setDecimalPrecision(decimalPrecision);
			doubleValues.setSize(pfield.size());
			
			fieldValues = doubleValues;
		} else {
			fieldValues = new FieldValues();
			fieldValues.setValue(value);
		}
		
		return fieldValues;
	}

	private static void validateDoubleAnnotation(DoubleField doubleField) {
		if(doubleField == null) {
			//TODO throw exception
		}
	}

}
