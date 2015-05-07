package br.com.pidgey.formatter;

import br.com.pidgey.annotation.PField;

public class FormatterUtil {
	
	public static boolean isWrapperOrString(Class<?> clazz) {
		
		boolean isWrapperOrString = 
				clazz == String.class || 
				clazz == Boolean.TYPE || 
				clazz == Character.TYPE || 
				clazz == Byte.TYPE || 
				clazz == Short.TYPE || 
				clazz == Integer.TYPE || 
				clazz == Long.TYPE || 
				clazz == Float.TYPE || 
				clazz == Double.TYPE;
		
		return isWrapperOrString;
	}
	
	public static ITypeFormatter getFormatter(Class<?> clazz) {
		
		ITypeFormatter typeFormatter = null;
		
		if(clazz == String.class) {
			typeFormatter = new StringFormatter();
		} else if(clazz == Long.TYPE || clazz == Integer.TYPE) {
			typeFormatter = new NumberFormatter();
		}
		
		//TODO: double, java.util.Date, etc. 
		//(ver quais tratamentos especificos existem nos sistemas.)
		
		return typeFormatter;
	}
	
	/**
	 * Return the fill value considering if the value is null 
	 * and considering the priority of specified fill-value and 
	 * null-fill-value of pField over the formatter specified 
	 * fill-value and null-fill-value.
	 * @param pField
	 * @param value
	 * @param formatterFillValue
	 * @param formatterNullFillValue
	 * @return
	 */
	public static char obtainFillValue(PField pField, Object value, 
			char formatterFillValue, char formatterNullFillValue) {

		char actualFillValue;
		
		if(value != null) {
			//TODO usar um enum. tb tem essa anotacao todo no PField
			if(pField.fillValue() != '#') { 
				actualFillValue = pField.fillValue();
			} else {
				actualFillValue = formatterFillValue;
			}
		} else {
			if(pField.nullFillValue() != '#') {
				actualFillValue = pField.nullFillValue();
			} else {
				actualFillValue = formatterNullFillValue;
			}
		}
		
		return actualFillValue;
	}

}
