package br.com.couberg.pidgey.test.run;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.couberg.pidgey.exception.ParseException;
import br.com.couberg.pidgey.parser.IParser;
import br.com.couberg.pidgey.parser.Parser;
import br.com.couberg.pidgey.test.mock.Mocker;
import br.com.couberg.pidgey.test.model.ObjectWithListOfStrings;
import br.com.couberg.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;

public class ParserFromTextNullsTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void testFromTextObjectWithStringBindingNullvalueTextMustEvaluateNull() 
			throws ParseException {
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
	
	@Test
	public void testFromTextObjectWithStringWithMandatoryBindingNullvalueTextMustBreakTheList() 
			throws ParseException {
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
		String responseStr = Mocker.
				getStringObjectWithListOfStringBindingNullvalueOnSecondElement();
		ObjectWithListOfStrings response = 
				parser.fromText(ObjectWithListOfStrings.class, responseStr);
		assertEquals(1, response.getTheList().size());
	}
	
}