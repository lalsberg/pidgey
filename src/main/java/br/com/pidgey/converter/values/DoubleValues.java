package br.com.pidgey.converter.values;


/**
 * Represents values that are used in doule and Double 
 * to format when parsing. 
 * 
 * @author lalsberg
 *
 */
public class DoubleValues extends FieldValues {
	
	private int decimalPrecision;
	private int size;
	
	public int getDecimalPrecision() {
		return decimalPrecision;
	}
	
	public void setDecimalPrecision(int decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "DoubleValues [decimalPrecision=" + decimalPrecision + ", size="
				+ size + "]";
	}
	
}