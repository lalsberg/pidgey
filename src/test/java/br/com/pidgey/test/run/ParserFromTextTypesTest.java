package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithBooleanPrimitive;
import br.com.pidgey.test.model.types.ObjectWithBooleanWrapper;
import br.com.pidgey.test.model.types.ObjectWithDate;
import br.com.pidgey.test.model.types.ObjectWithDoublePrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;
import br.com.pidgey.test.model.types.ObjectWithListOfIntegers;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;
import br.com.pidgey.test.model.types.ObjectWithLongWrapper;

public class ParserFromTextTypesTest {
	
	// long
	
	@Test
	public void testFromTextObjectWithLongPrimitive() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongPrimitive responseObj = 
				parser.fromText(ObjectWithLongPrimitive.class, response);
		ObjectWithLongPrimitive check = Mocker.getObjectWithLongPrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	// Long
	
	@Test
	public void testFromTextObjectWithLongWrapper() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithLongWrapper responseObj = 
				parser.fromText(ObjectWithLongWrapper.class, response);
		ObjectWithLongWrapper check = Mocker.getObjectWithLongWrapper();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	// int
	
	@Test
	public void testFromTextObjectWithIntegerPrimitive() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerPrimitive responseObj = 
				parser.fromText(ObjectWithIntegerPrimitive.class, response);
		ObjectWithIntegerPrimitive check = Mocker.getObjectWithIntegerPrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	//Integer
	
	@Test
	public void testFromTextObjectWithIntegerWrapper() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerWrapper responseObj = 
				parser.fromText(ObjectWithIntegerWrapper.class, response);
		ObjectWithIntegerWrapper check = Mocker.getObjectWithIntegerWrapper();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema());
	}
	
	// double
	
	@Test
	public void testFromTextObjectWithDoublePrimitive() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithDoublePrimitiveOrWrapper();
		ObjectWithDoublePrimitive responseObj = 
				parser.fromText(ObjectWithDoublePrimitive.class, response);
		ObjectWithDoublePrimitive check = Mocker.getObjectWithDoublePrimitive();
		assertEquals(check.getIdSistema(), responseObj.getIdSistema(), 0.00);
	}
	
	//boolean true
	
	@Test
	public void testFromTextObjectWithBooleanPrimitiveTrue() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		ObjectWithBooleanPrimitive responseObj = 
				parser.fromText(ObjectWithBooleanPrimitive.class, response);
		assertTrue(responseObj.isTheBoolean());
	}
	
	//boolean false
	
	@Test
	public void testFromTextObjectWithBooleanPrimitiveFalse() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		ObjectWithBooleanPrimitive responseObj = 
				parser.fromText(ObjectWithBooleanPrimitive.class, response);
		assertFalse(responseObj.isTheBoolean());
	}
	
	//Boolean true
	
	@Test
	public void testFromTextObjectWithBooleanWrapperTrue() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperTrue();
		ObjectWithBooleanWrapper responseObj = 
				parser.fromText(ObjectWithBooleanWrapper.class, response);
		assertTrue(responseObj.isTheBoolean());
	}
	
	//Boolean false
	
	@Test
	public void testFromTextObjectWithBooleanWrapperFalse() throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithBooleanPrimitiveOrWrapperFalse();
		ObjectWithBooleanWrapper responseObj = 
				parser.fromText(ObjectWithBooleanWrapper.class, response);
		assertFalse(responseObj.isTheBoolean());
	}
	
	//Date
	
	@Test
	public void testFromTextObjectWithDate() throws ParseException, 
			java.text.ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithDate();
		ObjectWithDate responseObj = 
				parser.fromText(ObjectWithDate.class, response);
		
		String datePattern="yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String dateStr = "2015-05-12";
		Date check = dateFormat.parse(dateStr);
		
		assertEquals(check, responseObj.getDate());
	}
	
	//List<String>
	
	@Test
	public void testFromTextObjectWithListOfStringsFirstElementsShouldMatch() 
			throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithListOfString();
		ObjectWithListOfStrings responseObj = 
				parser.fromText(ObjectWithListOfStrings.class, response);
		
		ObjectWithListOfStrings check = Mocker.getObjectWithListOfString();
		assertEquals(check.getTheList().get(0), responseObj.getTheList().get(0));
	}
	
	//List<String> second element
	
	@Test
	public void testFromTextObjectWithListOfStringsSecondElementsShouldMatch() 
			throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithListOfString();
		ObjectWithListOfStrings responseObj = 
				parser.fromText(ObjectWithListOfStrings.class, response);
		
		ObjectWithListOfStrings check = Mocker.getObjectWithListOfString();
		assertEquals(check.getTheList().get(1), responseObj.getTheList().get(1));
	}
	
	//List<Integer>
	
	@Test
	public void testFromTextObjectWithListOfIntegersFirstElementsShouldMatch() 
			throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithListOfIntegers();
		ObjectWithListOfIntegers responseObj = 
				parser.fromText(ObjectWithListOfIntegers.class, response);
		
		ObjectWithListOfIntegers check = Mocker.getObjectWithListOfIntegers();
		assertEquals(check.getList().get(0), responseObj.getList().get(0));
	}
	
	//List<Integer> second element
	
	@Test
	public void testFromTextObjectWithListOfIntegersSecondElementsShouldMatch() 
			throws ParseException {
		Parser parser = new Parser();
		String response = Mocker.getStringObjectWithListOfIntegers();
		ObjectWithListOfIntegers responseObj = 
				parser.fromText(ObjectWithListOfIntegers.class, response);
		
		ObjectWithListOfIntegers check = Mocker.getObjectWithListOfIntegers();
		assertEquals(check.getList().get(1), responseObj.getList().get(1));
	}
	

}