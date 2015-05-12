package br.com.pidgey.converter;

import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;

public abstract class TypeDefinition {
	
	private final FillValue defaultFillValue;
	private final FillValue defaultNullFillValue;
	private final FillDirection defaultFillDirection;
	
	public TypeDefinition(FillValue defaultFillValue, FillValue 
			defaultNullFillValue, FillDirection defaultFillDirection) {
		super();
		this.defaultFillValue = defaultFillValue;
		this.defaultNullFillValue = defaultNullFillValue;
		this.defaultFillDirection = defaultFillDirection;
	}

	abstract public Object convert(String value);

	public FillValue getDefaultFillValue() {
		return defaultFillValue;
	}

	public FillValue getDefaultNullFillValue() {
		return defaultNullFillValue;
	}

	public FillDirection getDefaultFillDirection() {
		return defaultFillDirection;
	}
	
}