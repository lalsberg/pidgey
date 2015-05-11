package br.com.pidgey.converter;

import br.com.pidgey.enumeration.FillDirectionEnum;

public class TypeDefinitions {
	
	public static final TypeDefinition STRING = new TypeDefinition(
			' ', ' ', FillDirectionEnum.RIGHT) {
	    public String convert(String value) {
	    	return value;
	    }
	};
	
	public static final TypeDefinition LONG = new TypeDefinition(
			'0', ' ', FillDirectionEnum.LEFT) {
	    public Long convert(String value) {
	    	return Long.parseLong(value);
	    }
	};
	
	public static final TypeDefinition INTEGER = new TypeDefinition(
			'0', ' ', FillDirectionEnum.LEFT) {
	    public Integer convert(String value) {
	    	return Integer.parseInt(value);
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
		} else {
			//TODO throw exception
		}
		
		return TypeDefinition;
	}

}