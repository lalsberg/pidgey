package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;

public class ParserFromTextNullsTest {
	
	@Test
	public void testFromTextObjectWithStringBindingNullvalueTextMustEvaluateNull() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithMandatory();
		ObjectWithStringAndListOfObjectsWithMandatory check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithMandatory();
		ObjectWithStringAndListOfObjectsWithMandatory response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithMandatory.class, 
				responseStr);
		assertEquals(check.getRoles().get(0).getDescricao(), 
				response.getRoles().get(0).getDescricao());
	}
	
	@Test
	public void testFromTextObjectWithStringBindingNullvalueTextMustEvaluateNull2() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithMandatory();
		ObjectWithStringAndListOfObjectsWithMandatory check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithMandatory();
		ObjectWithStringAndListOfObjectsWithMandatory response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithMandatory.class, 
				responseStr);
		assertEquals(check.getRoles().get(1).getDescricao(), 
				response.getRoles().get(1).getDescricao());
	}
	
	// Integer
	
	@Test
	public void testFromTextObjectWithIntegerWrapperBindingNullvalueTextMustEvaluateNull() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.getResponseStringObjectWithNullLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerWrapper check = Mocker.getObjectWithNullIntegerWrapper();
		ObjectWithIntegerWrapper response = 
				parser.fromText(ObjectWithIntegerWrapper.class, responseStr);
		assertEquals(check.getIdSistema(), response.getIdSistema());
	}
	
	// int
	
	@Test
	public void testFromTextObjectWithIntegerPrimitiveBindingNullvalueTextMustEvaluateNull() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.getResponseStringObjectWithNullLongOrIntPrimitiveOrWrapper();
		ObjectWithIntegerPrimitive check = Mocker.getObjectWithNullIntegerPrimitive();
		ObjectWithIntegerPrimitive response = 
				parser.fromText(ObjectWithIntegerPrimitive.class, responseStr);
		assertEquals(check.getIdSistema(), response.getIdSistema());
	}
	
	@Test
	public void testFromTextObjectWithStringWithMandatoryBindingNullvalueTextMustBreakTheList() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithMandatoryBindingNullvalue();
		ObjectWithStringAndListOfObjectsWithMandatory response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithMandatory.class, 
				responseStr);
		assertTrue(response.getRoles().isEmpty());
	}
	
	@Test
	public void testFromTextObjectWithListOfStringMustBreakTheListOnFirstNullvalue() 
			throws ParseException {
		Parser parser = new Parser();
		String responseStr = Mocker.
				getStringObjectWithListOfStringBindingNullvalueOnSecondElement();
		ObjectWithListOfStrings response = 
				parser.fromText(ObjectWithListOfStrings.class, responseStr);
		assertEquals(1, response.getTheList().size());
	}
	
}