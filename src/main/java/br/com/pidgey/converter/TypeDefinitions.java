package br.com.pidgey.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;
import br.com.pidgey.exception.ParseException;

public class TypeDefinitions {
	
	public static final TypeDefinition STRING = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
		
		@Override
	    public String convertFromText(String value) {
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(Object value) {
	    	return String.valueOf(value);
	    }
	};
	
	public static final TypeDefinition LONG = new TypeDefinition(
			FillValue.ZERO, FillValue.SPACE, FillDirection.LEFT) {
		
		@Override
	    public Long convertFromText(String value) {
	    	return Long.parseLong(value);
	    }
	    
	    @Override
	    public String convertToText(Object value) {
	    	return String.valueOf(value);
	    }
	    
	};
	
	public static final TypeDefinition INTEGER = new TypeDefinition(
			FillValue.ZERO, FillValue.SPACE, FillDirection.LEFT) {
		
		@Override
	    public Integer convertFromText(String value) {
	    	return Integer.parseInt(value);
	    }
	    
	    @Override
	    public String convertToText(Object value) {
	    	return String.valueOf(value);
	    }
	    
	};
	
	public static final TypeDefinition BOOLEAN = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
		
		@Override
	    public Boolean convertFromText(String value) throws ParseException {
	    	return Boolean.parseBoolean(value);
	    }
	    
	    @Override
	    public String convertToText(Object value) {
	    	return String.valueOf(value);
	    }
	    
	};
	
	public static final TypeDefinition DATE = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
		
		private final static String datePattern="yyyy-MM-dd";
		private SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		
		@Override
	    public Date convertFromText(String value) throws ParseException {
	    	try {
	    		return dateFormat.parse(value);
	    	} catch(java.text.ParseException e) {
	    		//TODO log error. em todos os typedefinitions
	    		throw new ParseException(e.getMessage());
	    	}
	    }

		@Override
		public String convertToText(Object value) throws ParseException {
			return dateFormat.format(value);
		}
	    
	};
	
	
	public static TypeDefinition getDefinition(Class<?> clazz) {
		
		TypeDefinition TypeDefinition = null;
		
		if(clazz == String.class) {
			TypeDefinition = STRING;
		} else if(clazz == Long.TYPE || clazz == Long.class) {
			TypeDefinition = LONG;
		} else if(clazz == Integer.TYPE || clazz == Integer.class) {
			TypeDefinition = INTEGER;
		} else if(clazz == Boolean.TYPE || clazz == Boolean.class) {
			TypeDefinition = BOOLEAN;//TODO TESTES
		} else if(clazz == Date.class) {
			TypeDefinition = DATE;//TODO TESTES
		} else {
			//TODO throw exception (invalid argument)
		}
		
		return TypeDefinition;
	}

}