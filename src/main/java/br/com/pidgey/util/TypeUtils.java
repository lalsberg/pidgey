package br.com.pidgey.util;

import java.util.Date;

public class TypeUtils {
	
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

}