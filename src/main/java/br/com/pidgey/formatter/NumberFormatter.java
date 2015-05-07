package br.com.pidgey.formatter;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.PField;

public class NumberFormatter implements ITypeFormatter {
	
	private static final char fillValue = '0';
	private static final char nullFillValue = ' ';
	
	public String toText(PField pField, Object value) {
		
		char actualFillValue = FormatterUtil.obtainFillValue(pField, 
				value, fillValue, nullFillValue);
		
		String valueStr = value != null ? String.valueOf(value) : "";
		
		valueStr = StringUtils.leftPad(valueStr, pField.size(), 
				actualFillValue);
		
		return valueStr;
		
	}

	public Long fromText(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}