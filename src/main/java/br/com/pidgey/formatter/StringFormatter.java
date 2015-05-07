package br.com.pidgey.formatter;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;

public class StringFormatter implements ITypeFormatter {
	
	private static final char fillValue = ' ';
	private static final char nullFillValue = ' ';
	
	public String toText(PField pField, Object value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(pField, 
				value, fillValue, nullFillValue);
		
		value = value != null ? value : "";
		
		String valueStr = StringUtils.rightPad(String.valueOf(value), pField.size(), 
				actualFillValue);
		
		return valueStr;
	}

	public Long fromText(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}