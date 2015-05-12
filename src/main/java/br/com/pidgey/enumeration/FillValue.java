package br.com.pidgey.enumeration;


public enum FillValue {
	
	ZERO('0'),
	SPACE(' '),
	UNSPECIFIED();
	
	private FillValue() {
	}

	private FillValue(char fillValue) {
		this.fillValue = fillValue;
	}

	private char fillValue;

	public char getFillValue() {
		return fillValue;
	}
	
}
