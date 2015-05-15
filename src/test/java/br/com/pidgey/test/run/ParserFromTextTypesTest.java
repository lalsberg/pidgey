package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithBooleanPrimitive;
import br.com.pidgey.test.model.types.ObjectWithBooleanWrapper;
import br.com.pidgey.test.model.types.ObjectWithDate;
import br.com.pidgey.test.model.types.ObjectWithDoublePrimitive;
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
	
	// double
	
	@Test
	public void testFromTextObjectWithDoublePrimitive() throws ParseException {
		String response = Mocker.getStringObjectWithDoublePrimitiveOrWrapper();
		ObjectWithDoublePrimitive responseObj = 
				parser.fromText(ObjectWithDoublePrimitive.class, response);
		ObjectWithDoublePrimitive check = Mocker.getObjectWithDoublePrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema(), 0.00);
	}
	
	//boolean true
	
	@Test
	public void testFromTextObjectWithBooleanPrimitiveTrue() throws ParseException {
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		ObjectWithBooleanPrimitive responseObj = 
				parser.fromText(ObjectWithBooleanPrimitive.class, response);
		assertTrue(responseObj.isTheBoolean());
	}
	
	//boolean false
	
	@Test
	public void testFromTextObjectWithBooleanPrimitiveFalse() throws ParseException {
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		ObjectWithBooleanPrimitive responseObj = 
				parser.fromText(ObjectWithBooleanPrimitive.class, response);
		assertFalse(responseObj.isTheBoolean());
	}
	
	//Boolean true
	
	@Test
	public void testFromTextObjectWithBooleanWrapperTrue() throws ParseException {
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		ObjectWithBooleanWrapper responseObj = 
				parser.fromText(ObjectWithBooleanWrapper.class, response);
		assertTrue(responseObj.isTheBoolean());
	}
	
	//Boolean false
	
	@Test
	public void testFromTextObjectWithBooleanWrapperFalse() throws ParseException {
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		ObjectWithBooleanWrapper responseObj = 
				parser.fromText(ObjectWithBooleanWrapper.class, response);
		assertFalse(responseObj.isTheBoolean());
	}
	
	//Date
	
	@Test
	public void testFromTextObjectWithDate() throws ParseException, 
			java.text.ParseException {
		String response = Mocker.getStringObjectWithDate();
		ObjectWithDate responseObj = 
				parser.fromText(ObjectWithDate.class, response);
		
		String datePattern="yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String dateStr = "2015-05-12";
		Date check = dateFormat.parse(dateStr);
		
		assertEquals(check, responseObj.getDate());
	}
	

}