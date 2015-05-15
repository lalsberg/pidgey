package br.com.pidgey.converter.values;


/**
 * Represents values of the field that are relevant 
 * when formatting. This class has values relevant 
 * for every field and can be extended to provide more.
 * 
 * @author lalsberg
 *
 */
public class FieldValues {
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FieldValues [value=" + value + "]";
	}
	
}