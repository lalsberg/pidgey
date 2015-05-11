package br.com.pidgey.formatter;

import br.com.pidgey.annotation.PField;

public interface TypeFormatter {
	
	String toText(PField pField, Object value);
	Object fromText(PField field, String value);

}
