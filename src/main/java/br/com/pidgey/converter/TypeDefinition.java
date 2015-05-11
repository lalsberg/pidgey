package br.com.pidgey.converter;

import br.com.pidgey.enumeration.FillDirectionEnum;

public abstract class TypeDefinition {
	
	private final char defaultFillValue;
	private final char defaultNullFillValue;
	private final FillDirectionEnum defaultFillDirection;
	
	public TypeDefinition(char defaultFillValue, char defaultNullFillValue,
			FillDirectionEnum defaultFillDirection) {
		super();
		this.defaultFillValue = defaultFillValue;
		this.defaultNullFillValue = defaultNullFillValue;
		this.defaultFillDirection = defaultFillDirection;
	}

	abstract public Object convert(String value);

	public char getDefaultFillValue() {
		return defaultFillValue;
	}

	public char getDefaultNullFillValue() {
		return defaultNullFillValue;
	}

	public FillDirectionEnum getDefaultFillDirection() {
		return defaultFillDirection;
	}
	
}