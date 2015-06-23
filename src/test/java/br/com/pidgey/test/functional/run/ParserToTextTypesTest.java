package br.com.pidgey.test.functional.run;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithBooleanPrimitive;
import br.com.pidgey.test.model.types.ObjectWithBooleanWrapper;
import br.com.pidgey.test.model.types.ObjectWithDate;
import br.com.pidgey.test.model.types.ObjectWithDoublePrimitive;
import br.com.pidgey.test.model.types.ObjectWithDoubleWrapper;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;
import br.com.pidgey.test.model.types.ObjectWithLongWrapper;

public class ParserToTextTypesTest {
	
	// long
	
	@Test
	public void testToTextObjectWithLongPrimitive() throws ParseException {
		Parser parser = new Parser();
		ObjectWithLongPrimitive request = Mocker.getObjectWithLongPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// Long
	
	@Test
	public void testToTextObjectWithLongWrapper() throws ParseException {
		Parser parser = new Parser();
		ObjectWithLongWrapper request = Mocker.getObjectWithLongWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// int
	
	@Test
	public void testToTextObjectWithIntegerPrimitive() throws ParseException {
		Parser parser = new Parser();
		ObjectWithIntegerPrimitive request = Mocker.getObjectWithIntegerPrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// Integer
	
	@Test
	public void testToTextObjectWithIntegerWrapper() throws ParseException {
		Parser parser = new Parser();
		ObjectWithIntegerWrapper request = Mocker.getObjectWithIntegerWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// double
	
	@Test
	public void testToTextObjectWithDoublePrimitive() throws ParseException {
		Parser parser = new Parser();
		ObjectWithDoublePrimitive request = Mocker.getObjectWithDoublePrimitive();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithDoublePrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// Double
	
	@Test
	public void testToTextObjectWithDoubleWrapper() throws ParseException {
		Parser parser = new Parser();
		ObjectWithDoubleWrapper request = Mocker.getObjectWithDoubleWrapper();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithDoublePrimitiveOrWrapper();
		assertEquals(check, requestStr);
	}
	
	// boolean true
	
	@Test
	public void testToTextObjectWithBooleanPrimitiveTrue() throws ParseException {
		Parser parser = new Parser();
		ObjectWithBooleanPrimitive request = Mocker.getObjectWithBooleanPrimitiveTrue();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		assertEquals(check, requestStr);
	}
	
	// boolean false
	
	@Test
	public void testToTextObjectWithBooleanPrimitiveFalse() throws ParseException {
		Parser parser = new Parser();
		ObjectWithBooleanPrimitive request = Mocker.getObjectWithBooleanPrimitiveFalse();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		assertEquals(check, requestStr);
	}
	
	// Boolean true
	
	@Test
	public void testToTextObjectWithBooleanWrapperTrue() throws ParseException {
		Parser parser = new Parser();
		ObjectWithBooleanWrapper request = Mocker.getObjectWithBooleanWrapperTrue();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		assertEquals(check, requestStr);
	}
	
	// Boolean false
	
	@Test
	public void testToTextObjectWithBooleanWrapperFalse() throws ParseException {
		Parser parser = new Parser();
		ObjectWithBooleanWrapper request = Mocker.getObjectWithBooleanWrapperFalse();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		assertEquals(check, requestStr);
	}
	
	// Date
	
	@Test
	public void testToTextObjectWithDate() throws ParseException {
		Parser parser = new Parser();
		ObjectWithDate request = Mocker.getObjectWithDate();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithDate();
		assertEquals(check, requestStr);
	}

}