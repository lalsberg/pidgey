package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirectionEnum.LEFT;
import static br.com.pidgey.enumeration.FillDirectionEnum.RIGHT;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeConverter;
import br.com.pidgey.enumeration.FillDirectionEnum;

public class NumberFormatter implements TypeFormatter {
	
	private static final char defaultFillValue = '0';
	private static final char defaultNullFillValue = ' ';
	private static final FillDirectionEnum defaultFillDirection = LEFT;
	private final TypeConverter typeConverter;
	
	public NumberFormatter(TypeConverter typeConverter) {
		this.typeConverter = typeConverter;
	}

	public String toText(PField pField, Object value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(
				pField.fillValue(), defaultFillValue);
		
		char actualNullFillValue = FormatterUtil.obtainNullFillValue(
				pField.nullFillValue(), defaultNullFillValue);
		
		char theFillValue = FormatterUtil.checkFillValue(
				value, actualFillValue, actualNullFillValue);
		
		FillDirectionEnum actualFillDirection = 
				FormatterUtil.obtainFillDirection(pField.fill(), 
				defaultFillDirection);
		
		String valueStr = value != null ? String.valueOf(value) : "";
		
		if(actualFillDirection == RIGHT) {
			valueStr = StringUtils.rightPad(String.valueOf(value), 
					pField.size(), theFillValue);
		} else {
			valueStr = StringUtils.leftPad(String.valueOf(value), 
					pField.size(), theFillValue);
		}
		
		return valueStr;
	}

	public Object fromText(PField pField, String value) {
		
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
		
		return typeConverter.convert(value);
	}
	
}