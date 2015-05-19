package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirection.UNSPECIFIED;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;
import br.com.pidgey.util.TypeUtils;

public class OverridingRules {
	
	public static char obtainFillValue(FillValue fieldFillValue, 
			FillValue formatterFillValue) {
		
		char actualFillValue;
		
		if(fieldFillValue != FillValue.UNSPECIFIED) { 
			actualFillValue = fieldFillValue.getFillValue();
		} else {
			actualFillValue = formatterFillValue.getFillValue();
		}
		
		return actualFillValue;
	}
	
	public static char obtainNullFillValue(FillValue fieldNullFillValue, 
			FillValue formatterNullFillValue) {
		
		char actualNullFillValue;
		
		if(fieldNullFillValue != FillValue.UNSPECIFIED) {
			actualNullFillValue = fieldNullFillValue.getFillValue();
		} else {
			actualNullFillValue = formatterNullFillValue.getFillValue();
		}
		
		return actualNullFillValue;
	}
	
	public static FillDirection obtainFillDirection(
			FillDirection fieldFillDirection, FillDirection 
			formatterFillDirection) {
		
		FillDirection actualFillDirection;
		
		if(fieldFillDirection != UNSPECIFIED) { 
			actualFillDirection = fieldFillDirection;
		} else {
			actualFillDirection = formatterFillDirection;
		}
		
		return actualFillDirection;
	}

	/**
	 * Return the fillValue or nullFillValue depending if the 
	 * value is null.
	 * Note that primitive values will always use it's default value if 
	 * not set. The nullFillValue can be used only in objects.
	 * @param value
	 * @param fillValue
	 * @param nullFillValue
	 * @return the given fillValue if the object != null
	 * @return the given nullFillValue if the object == null
	 */
	public static char checkFillValue(Object value, char fillValue,
			char nullFillValue) {
		
		char theFillValue;
		
		if(value != null) {
			theFillValue = fillValue;
		} else {
			theFillValue = nullFillValue;
		}
		
		return theFillValue;
	}
	
	/**
	 * Check if the value contains only the character specified 
	 * in nullFillValue. If false, returns the value. If true, 
	 * then will check the appropriate 'null value' to return 
	 * depending on the given clazz type. If the clazz is an 
	 * object, return null, if the clazz type is primitive, 
	 * the return the java default value of this primitive type.
	 * @param value
	 * @param nullFillValue
	 * @param clazz
	 * @return
	 */
	public static String checkFieldValue(String value, char 
			nullFillValue, Class<?> clazz) {
		
		boolean isNull = StringUtils.containsOnly(value, 
				nullFillValue);
		
		if(isNull) {
			value = TypeUtils.getJavaTypeDefaultValue(clazz);
		}
		
		return value;
	}

}