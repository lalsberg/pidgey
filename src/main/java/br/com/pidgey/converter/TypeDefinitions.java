package br.com.pidgey.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.pidgey.converter.values.DoubleValues;
import br.com.pidgey.converter.values.FieldValues;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;
import br.com.pidgey.exception.ParseException;

public class TypeDefinitions {
	
	public static void main(String[] args) {
		Object obj = null;
		String str = String.valueOf(obj);
		System.out.println(Long.parseLong(str));
	}
	
	public static final TypeDefinition STRING = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
				
		@Override
	    public String convertFromText(FieldValues fieldValues) {
			String value;
			if(fieldValues.getValue() == null) {
				value = null;
			} else {
				value = String.valueOf(fieldValues.getValue());
			}
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(FieldValues fieldValues) {
	    	return String.valueOf(fieldValues.getValue());
	    }
	};
	
	public static final TypeDefinition LONG = new TypeDefinition(
			FillValue.ZERO, FillValue.SPACE, FillDirection.LEFT) {
		
		@Override
	    public Long convertFromText(FieldValues fieldValues) {
			Long value;
			if(fieldValues.getValue() == null) {
				value = null;
			} else {
				String valueStr = String.valueOf(fieldValues.getValue());
				value = Long.parseLong(valueStr);
			}
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(FieldValues fieldValues) {
	    	return String.valueOf(fieldValues.getValue());
	    }
	    
	};
	
	public static final TypeDefinition INTEGER = new TypeDefinition(
			FillValue.ZERO, FillValue.SPACE, FillDirection.LEFT) {
		
		@Override
	    public Integer convertFromText(FieldValues fieldValues) {
			Integer value;
			if(fieldValues.getValue() == null) {
				value = null;
			} else {
				String valueStr = String.valueOf(fieldValues.getValue());
				value = Integer.parseInt(valueStr);
			}
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(FieldValues fieldValues) {
	    	return String.valueOf(fieldValues.getValue());
	    }
	    
	};
	
	public static final TypeDefinition DOUBLE = new TypeDefinition(
			FillValue.ZERO, FillValue.SPACE, FillDirection.LEFT) {
		
		@Override
	    public Double convertFromText(FieldValues fieldValues) {
			Double value;
			if(fieldValues.getValue() == null) {
				value = null;
			} else {
				DoubleValues doubleValues = (DoubleValues) fieldValues;
				String valueStr = String.valueOf(fieldValues.getValue());
				int decimalPrecision = doubleValues.getDecimalPrecision();
				int dividend = (int) Math.pow(10, decimalPrecision);
				value = Double.parseDouble(valueStr) / dividend;
			}
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(FieldValues fieldValues) {
	    	DoubleValues doubleValues = (DoubleValues) fieldValues;
	    	int decimalPrecision = doubleValues.getDecimalPrecision();
	    	
	    	DecimalFormat decimalFormat = new DecimalFormat();
	    	decimalFormat.setMaximumFractionDigits(decimalPrecision);
	    	decimalFormat.setMinimumFractionDigits(decimalPrecision);
	    	
	    	int maximumIntegerDigits = doubleValues.getSize() - decimalPrecision;
	    	decimalFormat.setMaximumIntegerDigits(maximumIntegerDigits);
	    	
	    	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	    	symbols.setDecimalSeparator('.');
	    	decimalFormat.setDecimalFormatSymbols(symbols);
	    	
	    	String value = decimalFormat.format(fieldValues.getValue()).replace(".", "");
	    	
	    	return value;
	    }
	    
	};
	
	public static final TypeDefinition BOOLEAN = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
		
		@Override
	    public Boolean convertFromText(FieldValues fieldValues) throws ParseException {
			Boolean value;
			if(fieldValues.getValue() == null) {
				value = null;
			} else {
				String valueStr = String.valueOf(fieldValues.getValue());
		    	value = Boolean.parseBoolean(valueStr);
			}
	    	return value;
	    }
	    
	    @Override
	    public String convertToText(FieldValues fieldValues) {
	    	return String.valueOf(fieldValues.getValue());
	    }
	    
	};
	
	//TODO annotation que permite alterar o dateFormat padrao. @DateFormat("yyyy.mm.dddd")
	public static final TypeDefinition DATE = new TypeDefinition(
			FillValue.SPACE, FillValue.SPACE, FillDirection.RIGHT) {
		
		private final static String datePattern="yyyy-MM-dd";
		private SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		
		@Override
	    public Date convertFromText(FieldValues fieldValues) throws ParseException {
	    	try {
	    		
	    		Date value;
				if(fieldValues.getValue() == null) {
					value = null;
				} else {
					String valueStr = String.valueOf(fieldValues.getValue());
		    		value = dateFormat.parse(valueStr);
				}
		    	return value;
	    		
	    	} catch(java.text.ParseException e) {
	    		//TODO log error. em todos os typedefinitions
	    		throw new ParseException(e.getMessage());
	    	}
	    }

		@Override
		public String convertToText(FieldValues fieldValues) throws ParseException {
			return dateFormat.format(fieldValues.getValue());
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
			TypeDefinition = BOOLEAN;
		} else if(clazz == Double.TYPE || clazz == Double.class) {
			TypeDefinition = DOUBLE; //TODO testes
		} else if(clazz == Date.class) {
			TypeDefinition = DATE;
		} else {
			//TODO throw exception (invalid argument)
		}
		
		return TypeDefinition;
	}

}