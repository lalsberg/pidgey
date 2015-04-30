package br.com.couberg.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.couberg.pidgey.exception.ParseException;
import br.com.couberg.pidgey.parser.IParser;
import br.com.couberg.pidgey.parser.Parser;
import br.com.couberg.pidgey.test.mock.Mocker;
import br.com.couberg.pidgey.test.model.ObjectWithListWithoutMany;
import br.com.couberg.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.couberg.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;
import br.com.couberg.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.couberg.pidgey.test.model.ObjectWithoutId;

public class ParserToTextTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void testToTextObjectWithoutId() 
			throws ParseException {
		ObjectWithoutId request = Mocker.getObjectWithoutId();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithoutId();
		assertEquals(check, requestStr);
	}
	
	@Test
	public void testToTextObjectWithStringAndListOfObjectsWithId() 
			throws ParseException {
		ObjectWithStringAndListOfObjectsWithMandatory request = Mocker.
				getObjectObjectWithStringAndListOfObjectsWithMandatory();
		String requestStr = parser.toText(request);
		String check = Mocker.getStringObjectWithStringAndListOfObjectsWithMandatory();
		assertEquals(check, requestStr);
	}
	
	@Test(expected = ParseException.class)
	public void testToTextListWithoutMany() throws ParseException {
		ObjectWithListWithoutMany request = 
				Mocker.getObjectWithListWithoutMany();
		parser.toText(request);
	}
	
	@Test
	public void testToTextManyWithoutRepeated() throws ParseException {
		ObjectWithManyWithoutRepeated request = 
				Mocker.getObjectWithManyWithoutRepeated();
		parser.toText(request);
	}
	
	@Test
	public void testToTextObjectWithoutDefaultConstructor() 
			throws ParseException {
		ObjectWithoutDefaultConstructor request = 
				Mocker.getObjectWithoutDefaultConstructor();
		parser.toText(request);
	}

}