package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;
import br.com.pidgey.test.model.types.ObjectWithLongWrapper;

public class ParserToTextTypesTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	// long & Long
	
	@Test
	public void testToTextObjectWithLongPrimitive() throws ParseException {
		ObjectWithLongPrimitive request = Mocker.getObjectWithLongPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithLongWrapper() throws ParseException {
		ObjectWithLongWrapper request = Mocker.getObjectWithLongWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// int & Integer
	
	@Test
	public void testToTextObjectWithIntegerPrimitive() throws ParseException {
		ObjectWithIntegerPrimitive request = Mocker.getObjectWithIntegerPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithIntegerWrapper() throws ParseException {
		ObjectWithIntegerWrapper request = Mocker.getObjectWithIntegerWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}

}