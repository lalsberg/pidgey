package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.ObjectWithListOfObjects;
import br.com.pidgey.test.model.ObjectWithObject;
import br.com.pidgey.test.model.ObjectWithObjectWithoutAnything;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;

public class ParserToTextNullsTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void testToTextObjectWithNullObject() throws ParseException {
		ObjectWithObject request = Mocker.getObjectWithNullObject();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullObject();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithNullObjectWithoutAnything() throws ParseException {
		ObjectWithObjectWithoutAnything request = 
				Mocker.getObjectWithNullObjectWithoutAnything();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullObjectWithoutAnything();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithNullListOfObjects() throws ParseException {
		ObjectWithListOfObjects request = Mocker.getObjectWithNullListOfObjects();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullListOfObjects();
		assertEquals(check, requestStr);
	}
	
	// int
	
	@Test
	public void testToTextObjectWithIntegerPrimitiveDefaultValue() throws ParseException {
		ObjectWithIntegerPrimitive request = Mocker.getObjectWithNullIntegerPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getRequestStringObjectWithNullLongOrIntPrimitive();
		assertEquals(check, requestStr);
	}
	
	// Integer

	@Test
	public void testToTextObjectWithIntegerWrapperNullValue() throws ParseException {
		ObjectWithIntegerWrapper request = Mocker.getObjectWithNullIntegerWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getResponseStringObjectWithNullLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
}