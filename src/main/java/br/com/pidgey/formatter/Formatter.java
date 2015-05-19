package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirection.RIGHT;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.converter.values.FieldValues;
import br.com.pidgey.converter.values.FieldValuesFactory;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.exception.ParseException;

public class Formatter {
	
	private final TypeDefinition typeDefinition;
	
	public Formatter(TypeDefinition typeDefinition) {
		this.typeDefinition = typeDefinition;
	}

	public String toText(Field field, Object value) throws ParseException {
		
		PField pField = field.getAnnotation(PField.class);
		
		char actualFillValue = OverridingRules.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		char actualNullFillValue = OverridingRules.obtainNullFillValue(
				pField.nullFillValue(), typeDefinition.getDefaultNullFillValue());
		
		char theFillValue = OverridingRules.checkFillValue(value, 
				actualFillValue, actualNullFillValue);
		
		FillDirection actualFillDirection = 
				OverridingRules.obtainFillDirection(pField.fill(), 
				typeDefinition.getDefaultFillDirection());
		
		value = value != null ? value : "";
		FieldValues fieldValues = FieldValuesFactory.getFieldValue(field, value);
		String stringValue = typeDefinition.convertToText(fieldValues);
		
		if(actualFillDirection == RIGHT) {
			stringValue = StringUtils.rightPad(stringValue, pField.size(), 
					theFillValue);
		} else {
			stringValue = StringUtils.leftPad(stringValue, pField.size(), 
					theFillValue);
		}
		
		return stringValue;
	}

	public Object fromText(Field field, String value) throws ParseException {
		
		PField pField = field.getAnnotation(PField.class);
		
		char actualFillValue = OverridingRules.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		FillDirection actualFillDirection = 
				OverridingRules.obtainFillDirection(pField.fill(), 
				typeDefinition.getDefaultFillDirection());
		
		if(actualFillDirection == RIGHT) {
			value = StringUtils.stripEnd(value, String.valueOf(actualFillValue));
		} else {
			value = StringUtils.stripStart(value, String.valueOf(actualFillValue));
		}
		
		char actualNullFillValue = OverridingRules.obtainNullFillValue(
				pField.nullFillValue(), typeDefinition.getDefaultNullFillValue());
		
		value = OverridingRules.checkFieldValue(value, actualNullFillValue, field.getType());
		
		FieldValues fieldValues = FieldValuesFactory.getFieldValue(field, value);
		return typeDefinition.convertFromText(fieldValues);
	}

}