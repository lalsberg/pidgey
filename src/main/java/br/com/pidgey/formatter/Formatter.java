package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirection.RIGHT;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.exception.ParseException;

public class Formatter {
	
	private final TypeDefinition typeDefinition;
	
	public Formatter(TypeDefinition typeDefinition) {
		this.typeDefinition = typeDefinition;
	}

	public String toText(PField pField, Object value) throws ParseException {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), typeDefinition.getDefaultNullFillValue());
		
		char theFillValue = FormatterUtil.checkFillValue(value, 
				actualFillValue, actualNullFillValue);
		
		FillDirection actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				typeDefinition.getDefaultFillDirection());
		
		value = value != null ? value : "";
		String stringValue = typeDefinition.convertToText(value);
		
		if(actualFillDirection == RIGHT) {
			stringValue = StringUtils.rightPad(stringValue, pField.size(), 
					theFillValue);
		} else {
			stringValue = StringUtils.leftPad(stringValue, pField.size(), 
					theFillValue);
		}
		
		return stringValue;
	}

	public Object fromText(PField pField, String value) throws ParseException {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		FillDirection actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				typeDefinition.getDefaultFillDirection());
		
		if(actualFillDirection == RIGHT) {
			value = StringUtils.stripEnd(value, String.valueOf(actualFillValue));
		} else {
			value = StringUtils.stripStart(value, String.valueOf(actualFillValue));
		}
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), typeDefinition.getDefaultNullFillValue());
		
		value = FormatterUtil.checkFieldValue(value, actualNullFillValue);
		
		return typeDefinition.convertFromText(value);
	}

}