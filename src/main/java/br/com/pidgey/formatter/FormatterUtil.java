package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirection.UNSPECIFIED;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;

public class FormatterUtil {
	
	static char a;
	static boolean b;
	static byte c;
	static float d;
	static double e;
	
	public static void main(String[] args) {
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		char thechar = '\u0000';
	}
	 
	//TODO passar pra classe TypeUtils
	public static boolean isJavaType(Class<?> clazz) {
		
		boolean isJavaType = 
				clazz == String.class ||
				clazz == Integer.TYPE || 
				clazz == Long.TYPE || 
				clazz == Boolean.TYPE || 
				clazz == Character.TYPE || 
				clazz == Byte.TYPE || 
				clazz == Short.TYPE || 
				clazz == Float.TYPE || 
				clazz == Double.TYPE || 
				clazz == Boolean.class || 
				clazz == Character.class || 
				clazz == Byte.class || 
				clazz == Short.class || 
				clazz == Integer.class || 
				clazz == Long.class || 
				clazz == Float.class || 
				clazz == Double.class || 
				clazz == Date.class;
		
		return isJavaType;
	}
	
	//TODO passar pra classe TypeUtils, trocar por map?
	public static String getJavaTypeDefaultValue(Class<?> clazz) {
		
		String defaultValue = null;
		
		if(clazz == String.class || 
			clazz == Boolean.class || 
			clazz == Character.class || 
			clazz == Byte.class || 
			clazz == Short.class || 
			clazz == Integer.class || 
			clazz == Long.class || 
			clazz == Float.class || 
			clazz == Double.class || 
			clazz == Date.class) {
			
			defaultValue = null;
			
		} else if (clazz == Long.TYPE || 
				clazz == Short.TYPE || 
				clazz == Integer.TYPE || 
				clazz == Byte.TYPE) {
			
			defaultValue = "0";
			
		} else if (clazz == Boolean.TYPE) {
			
			defaultValue = "false";
			
		} else if (clazz == Float.TYPE || 
				clazz == Double.TYPE) {
			
			defaultValue = "0.0";
		
		} else if (clazz == Character.TYPE) {
			
			defaultValue = "\u0000";

		} else {
			//TODO throw exception
		}
		
		return defaultValue;
	}
	
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
	
	public static String checkFieldValue(String value, char 
			nullFillValue, Class<?> clazz) {
		
		boolean isNull = StringUtils.containsOnly(value, 
				nullFillValue);
		
		if(isNull) {
//			value = null;
			value = getJavaTypeDefaultValue(clazz);
		}
		
		return value;
	}

}