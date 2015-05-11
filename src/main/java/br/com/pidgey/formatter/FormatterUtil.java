package br.com.pidgey.formatter;

import static br.com.pidgey.enumeration.FillDirectionEnum.UNSPECIFIED;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.converter.TypeConverter;
import br.com.pidgey.converter.TypeConverters;
import br.com.pidgey.enumeration.FillDirectionEnum;

public class FormatterUtil {
	
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
				clazz == Double.class;
		
		return isJavaType;
	}
	
	public static TypeFormatter getFormatter(Class<?> clazz) {
		
		TypeFormatter typeFormatter = null;
		TypeConverter typeConverter = TypeConverters.getConverter(clazz);
		
		if(clazz == String.class) {
			typeFormatter = new StringFormatter();
		} else if(clazz == Long.TYPE || clazz == Long.class || 
				clazz == Integer.TYPE || clazz == Integer.class) {

			typeFormatter = new NumberFormatter(typeConverter);
		} else {
			//TODO throw exception
		}
		
		//TODO: double, java.util.Date, etc. 
		//(ver quais tratamentos especificos existem nos sistemas.)
		
		return typeFormatter;
	}
	
	public static char obtainFillValue(char fieldFillValue, 
			char formatterFillValue) {
		
		char actualFillValue;
		
		//TODO usar um enum. tb tem essa anotacao todo no PField. UNSPECIFIED, ZERO, EMPTYCHAR
		if(fieldFillValue != '#') { 
			actualFillValue = fieldFillValue;
		} else {
			actualFillValue = formatterFillValue;
		}
		
		return actualFillValue;
	}
	
	public static char obtainNullFillValue(char fieldNullFillValue, 
			char formatterNullFillValue) {
		
		char actualNullFillValue;
		
		//TODO usar enum tb. talvez usar a mesma do fillvalue?
		if(fieldNullFillValue != '#') {
			actualNullFillValue = fieldNullFillValue;
		} else {
			actualNullFillValue = formatterNullFillValue;
		}
		
		return actualNullFillValue;
	}
	
	public static FillDirectionEnum obtainFillDirection(
			FillDirectionEnum fieldFillDirection, FillDirectionEnum 
			formatterFillDirection) {
		
		FillDirectionEnum actualFillDirection;
		
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
			nullFillValue) {
		
		boolean isNull = StringUtils.containsOnly(value, 
				nullFillValue);
		
		if(isNull) {
			value = null;
		}
		
		return value;
	}

}