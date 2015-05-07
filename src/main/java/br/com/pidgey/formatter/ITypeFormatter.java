package br.com.pidgey.formatter;

import br.com.pidgey.annotation.PField;

public interface ITypeFormatter {
	
	String toText(PField pField, Object value);
	Object fromText(String value);

}
