package br.com.pidgey.converter;


public class TypeConverters {
	
	public static final TypeConverter LONG = new TypeConverter() {
	    public Long convert(String value) {
	    	return Long.parseLong(value);
	    }
	};
	
	public static final TypeConverter INTEGER = new TypeConverter() {
	    public Integer convert(String value) {
	    	return Integer.parseInt(value);
	    }
	};
	
	public static TypeConverter getConverter(Class<?> clazz) {
		
		//No conversion needed for Strings
		if(clazz == String.class) {
			return null;
		}
		
		TypeConverter typeConverter = null;
		
		if(clazz == Long.TYPE || clazz == Long.class) {
			typeConverter = LONG;
		} else if(clazz == Integer.TYPE || clazz == Integer.class) {
			typeConverter = INTEGER;
		} else {
			//TODO throw exception
		}
		
		return typeConverter;
	}

}
