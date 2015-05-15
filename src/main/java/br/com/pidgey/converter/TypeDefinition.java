package br.com.pidgey.converter;

import br.com.pidgey.converter.values.FieldValues;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;
import br.com.pidgey.exception.ParseException;

public abstract class TypeDefinition {
	
	private final FillValue defaultFillValue;
	private final FillValue defaultNullFillValue;
	private final FillDirection defaultFillDirection;
	private FieldValues fieldValues;
	
	public TypeDefinition(FillValue defaultFillValue, FillValue 
			defaultNullFillValue, FillDirection defaultFillDirection) {
		super();
		this.defaultFillValue = defaultFillValue;
		this.defaultNullFillValue = defaultNullFillValue;
		this.defaultFillDirection = defaultFillDirection;
	}

	abstract public Object convertFromText(FieldValues fieldValues) throws ParseException;
	abstract public String convertToText(FieldValues fieldValues) throws ParseException;

	public FillValue getDefaultFillValue() {
		return defaultFillValue;
	}

	public FillValue getDefaultNullFillValue() {
		return defaultNullFillValue;
	}

	public FillDirection getDefaultFillDirection() {
		return defaultFillDirection;
	}

	public FieldValues getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(FieldValues fieldValues) {
		this.fieldValues = fieldValues;
	}
	
}