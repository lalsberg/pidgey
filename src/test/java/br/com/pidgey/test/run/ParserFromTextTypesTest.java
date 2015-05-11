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

public class ParserFromTextTypesTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	// long
	
	@Test
	public void testFromTextObjectWithLongPrimitive() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongPrimitive responseObj = 
				parser.fromText(ObjectWithLongPrimitive.class, response);
		ObjectWithLongPrimitive check = Mocker.getObjectWithLongPrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	// Long
	
	@Test
	public void testFromTextObjectWithLongWrapper() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongWrapper responseObj = 
				parser.fromText(ObjectWithLongWrapper.class, response);
		ObjectWithLongWrapper check = Mocker.getObjectWithLongWrapper();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	// int
	
	@Test
	public void testFromTextObjectWithIntegerPrimitive() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerPrimitive responseObj = 
				parser.fromText(ObjectWithIntegerPrimitive.class, response);
		ObjectWithIntegerPrimitive check = Mocker.getObjectWithIntegerPrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	//Integer
	
	@Test
	public void testFromTextObjectWithIntegerWrapper() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerWrapper responseObj = 
				parser.fromText(ObjectWithIntegerWrapper.class, response);
		ObjectWithIntegerWrapper check = Mocker.getObjectWithIntegerWrapper();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}

}