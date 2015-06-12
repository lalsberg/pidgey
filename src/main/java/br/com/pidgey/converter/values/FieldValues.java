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
	
	private final Object value;

	public FieldValues(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "FieldValues [value=" + value + "]";
	}
	
}