package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithBooleanPrimitive;
import br.com.pidgey.test.model.types.ObjectWithBooleanWrapper;
import br.com.pidgey.test.model.types.ObjectWithDate;
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
	
	// long
	
	@Test
	public void testToTextObjectWithLongPrimitive() throws ParseException {
		ObjectWithLongPrimitive request = Mocker.getObjectWithLongPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// Long
	
	@Test
	public void testToTextObjectWithLongWrapper() throws ParseException {
		ObjectWithLongWrapper request = Mocker.getObjectWithLongWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// int
	
	@Test
	public void testToTextObjectWithIntegerPrimitive() throws ParseException {
		ObjectWithIntegerPrimitive request = Mocker.getObjectWithIntegerPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// Integer
	
	@Test
	public void testToTextObjectWithIntegerWrapper() throws ParseException {
		ObjectWithIntegerWrapper request = Mocker.getObjectWithIntegerWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// boolean true
	
	@Test
	public void testToTextObjectWithBooleanPrimitiveTrue() throws ParseException {
		ObjectWithBooleanPrimitive request = Mocker.getObjectWithBooleanPrimitiveTrue();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		assertEquals(check, requestStr);
	}
	
	// boolean false
	
	@Test
	public void testToTextObjectWithBooleanPrimitiveFalse() throws ParseException {
		ObjectWithBooleanPrimitive request = Mocker.getObjectWithBooleanPrimitiveFalse();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		assertEquals(check, requestStr);
	}
	
	// Boolean true
	
	@Test
	public void testToTextObjectWithBooleanWrapperTrue() throws ParseException {
		ObjectWithBooleanWrapper request = Mocker.getObjectWithBooleanWrapperTrue();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		assertEquals(check, requestStr);
	}
	
	// Boolean false
	
	@Test
	public void testToTextObjectWithBooleanWrapperFalse() throws ParseException {
		ObjectWithBooleanWrapper request = Mocker.getObjectWithBooleanWrapperFalse();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		assertEquals(check, requestStr);
	}
	
	// Date
	
	@Test
	public void testToTextObjectWithDate() throws ParseException {
		ObjectWithDate request = Mocker.getObjectWithDate();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithDate();
		assertEquals(check, requestStr);
	}

}