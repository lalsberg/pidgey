package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.ObjectWithListOfObjects;
import br.com.pidgey.test.model.ObjectWithObject;
import br.com.pidgey.test.model.ObjectWithObjectWithoutAnything;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;

public class ParserToTextNullsTest {
	
	@Test
	public void testToTextObjectWithNullObject() throws ParseException {
		Parser parser = new Parser();
		ObjectWithObject request = Mocker.getObjectWithNullObject();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullObject();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithNullObjectWithoutAnything() 
			throws ParseException {
		Parser parser = new Parser();
		ObjectWithObjectWithoutAnything request = 
				Mocker.getObjectWithNullObjectWithoutAnything();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullObjectWithoutAnything();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithNullListOfObjects() throws ParseException {
		Parser parser = new Parser();
		ObjectWithListOfObjects request = Mocker.getObjectWithNullListOfObjects();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithNullListOfObjects();
		assertEquals(check, requestStr);
	}
	
	// int
	
	@Test
	public void testToTextObjectWithIntegerPrimitiveDefaultValue() 
			throws ParseException {
		Parser parser = new Parser();
		ObjectWithIntegerPrimitive request = Mocker.getObjectWithNullIntegerPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getRequestStringObjectWithNullLongOrIntPrimitive();
		assertEquals(check, requestStr);
	}
	
	// Integer

	@Test
	public void testToTextObjectWithIntegerWrapperNullValue() 
			throws ParseException {
		Parser parser = new Parser();
		ObjectWithIntegerWrapper request = Mocker.getObjectWithNullIntegerWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getResponseStringObjectWithNullLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
}