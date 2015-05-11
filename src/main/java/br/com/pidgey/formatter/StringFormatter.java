package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirectionEnum.RIGHT;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.enumeration.FillDirectionEnum;

public class StringFormatter implements TypeFormatter {
	
	private static final char defaultFillValue = ' ';
	private static final char defaultNullFillValue = ' ';
	private static final FillDirectionEnum defaultFillDirection = RIGHT;
	
	public String toText(PField pField, Object value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), defaultFillValue);
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), defaultNullFillValue);
		
		char theFillValue = FormatterUtil.checkFillValue(value, 
				actualFillValue, actualNullFillValue);
		
		value = value != null ? value : "";
		
		FillDirectionEnum actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				defaultFillDirection);
		
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

	public String fromText(PField pField, String value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), defaultFillValue);
		
		FillDirectionEnum actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				defaultFillDirection);
		
		if(actualFillDirection == RIGHT) {
			value = StringUtils.stripEnd(value, String.valueOf(actualFillValue));
		} else {
			value = StringUtils.stripStart(value, String.valueOf(actualFillValue));
		}
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), defaultNullFillValue);
		
		value = FormatterUtil.checkFieldValue(value, actualNullFillValue);
		
		return value;
	}

}