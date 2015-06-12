package br.com.pidgey.converter.values;


/**
 * Represents values that are used in doule and Double 
 * to format when parsing. 
 * 
 * @author lalsberg
 *
 */
public class DoubleValues extends FieldValues {
	
	private final int decimalPrecision;
	private final int size;
	
	public DoubleValues(Object value, int decimalPrecision, int size) {
		super(value);
		this.decimalPrecision = decimalPrecision;
		this.size = size;
	}

	public int getDecimalPrecision() {
		return decimalPrecision;
	}
	
	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "DoubleValues [decimalPrecision=" + decimalPrecision + ", size="
				+ size + "]";
	}
	
}