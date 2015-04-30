package br.com.pidgey.parser;

import br.com.pidgey.exception.ParseException;

/**
 * @author lalsberg
 * The Parser specification
 *
 */
public interface IParser {
	
	/**
	 * Uses the String to set this object values
	 * @param text to parse
	 */
	<T> T fromText(Class<T> clazz, String text) throws ParseException;
	
	/**
	 * Uses the object to create the String
	 * @return {@link String} parsed
	 */
	String toText(Object instance) throws ParseException;
	
}