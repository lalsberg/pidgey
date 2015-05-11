package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirectionEnum.RIGHT;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.enumeration.FillDirectionEnum;

public class Formatter {
	
	private final TypeDefinition typeDefinition;
	
	public Formatter(TypeDefinition typeDefinition) {
		this.typeDefinition = typeDefinition;
	}

	public String toText(PField pField, Object value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), typeDefinition.getDefaultNullFillValue());
		
		char theFillValue = FormatterUtil.checkFillValue(value, 
				actualFillValue, actualNullFillValue);
		
		FillDirectionEnum actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				typeDefinition.getDefaultFillDirection());
		
		value = value != null ? value : "";
		String valueStr = String.valueOf(value);
		
		if(actualFillDirection == RIGHT) {
			valueStr = StringUtils.rightPad(valueStr, pField.size(), 
					theFillValue);
		} else {
			valueStr = StringUtils.leftPad(valueStr, pField.size(), 
					theFillValue);
		}
		
		return valueStr;
	}

	public Object fromText(PField pField, String value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), typeDefinition.getDefaultFillValue());
		
		FillDirectionEnum actualFillDirection = 
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
		
		return typeDefinition.convert(value);
	}

}