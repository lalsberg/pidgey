package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;
import br.com.pidgey.test.model.types.ObjectWithLongWrapper;

public class ParserFromTextTypesTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void testFromTextObjectWithLongPrimitive() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongPrimitive responseObj = 
				parser.fromText(ObjectWithLongPrimitive.class, response);
		ObjectWithLongPrimitive check = Mocker.getObjectWithLongPrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	@Test
	public void testFromTextObjectWithLongWrapper() throws ParseException {
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongWrapper responseObj = 
				parser.fromText(ObjectWithLongWrapper.class, response);
		ObjectWithLongWrapper check = Mocker.getObjectWithLongWrapper();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}

}