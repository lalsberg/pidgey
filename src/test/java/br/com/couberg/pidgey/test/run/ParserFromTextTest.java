package br.com.couberg.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.couberg.pidgey.exception.ParseException;
import br.com.couberg.pidgey.parser.IParser;
import br.com.couberg.pidgey.parser.Parser;
import br.com.couberg.pidgey.test.mock.Mocker;
import br.com.couberg.pidgey.test.model.ObjectWithListWithoutMany2;
import br.com.couberg.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.couberg.pidgey.test.model.ObjectWithStringAndListOfObjectsWithId;
import br.com.couberg.pidgey.test.model.ObjectWithUnaccessibleClass;
import br.com.couberg.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.couberg.pidgey.test.model.ObjectWithoutId;

public class ParserFromTextTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void testFromTextObjectWithoutId() throws ParseException {
		String response = Mocker.getStringObjectWithoutId();
		ObjectWithoutId objectWithoutId = 
				parser.fromText(ObjectWithoutId.class, response);
		ObjectWithoutId check = Mocker.getObjectWithoutId();
		assertEquals(objectWithoutId.getNomePerfil(), check.getNomePerfil());
	}
	
	@Test
	public void testFromTextObjectWithoutId2() throws ParseException {
		String response = Mocker.getStringObjectWithoutId();
		ObjectWithoutId objectWithoutId = 
				parser.fromText(ObjectWithoutId.class, response);
		ObjectWithoutId check = Mocker.getObjectWithoutId();
		assertEquals(objectWithoutId.getIdSistema(), check.getIdSistema());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getQuantidade(),check.getQuantidade());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId2() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(0).getDescricao(),
				check.getRoles().get(0).getDescricao());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId3() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(0).getIdPerfil(),
				check.getRoles().get(0).getIdPerfil());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId4() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(0).getIdSistema(),
				check.getRoles().get(0).getIdSistema());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId5() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(0).getNome(),
				check.getRoles().get(0).getNome());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId6() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(0).getNomeSistema(),
				check.getRoles().get(0).getNomeSistema());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId7() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(2).getDescricao(),
				check.getRoles().get(2).getDescricao());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId8() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(2).getIdPerfil(),
				check.getRoles().get(2).getIdPerfil());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId9() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(2).getIdSistema(),
				check.getRoles().get(2).getIdSistema());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId10() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(2).getNome(),
				check.getRoles().get(2).getNome());
	}
	
	@Test
	public void testFromTextObjectWithStringAndListOfObjectsWithId11() 
			throws ParseException {
		String responseStr = Mocker.
				getStringObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId check = 
				Mocker.getObjectObjectWithStringAndListOfObjectsWithId();
		ObjectWithStringAndListOfObjectsWithId response = 
				parser.fromText(ObjectWithStringAndListOfObjectsWithId.class, 
				responseStr);
		assertEquals(response.getRoles().get(2).getNomeSistema(),
				check.getRoles().get(2).getNomeSistema());
	}
	
	@Test(expected = ParseException.class)
	public void testFromTextListWithoutMany() throws ParseException {
		String responseStr = 
				Mocker.getStringObjectWithStringAndListOfObjectsWithId();
		parser.fromText(ObjectWithListWithoutMany2.class, responseStr);
	}
	
	@Test(expected = ParseException.class)
	public void testFromTextManyWithoutRepeated() throws ParseException {
		String responseStr = 
				Mocker.getStringObjectWithStringAndListOfObjectsWithId();
		parser.fromText(ObjectWithManyWithoutRepeated.class, responseStr);
	}
	
	@Test(expected = ParseException.class)
	public void testFromTextObjectWithoutDefaultConstructor() 
			throws ParseException {
		String responseStr = 
				Mocker.getStringObjectWithoutDefaultConstructor();
		parser.fromText(ObjectWithoutDefaultConstructor.class, responseStr);
	}
	
	@Test(expected = ParseException.class)
	public void testFromTextObjectWithUnaccessibleClass() 
			throws ParseException {
		String response = Mocker.getStringObjectWithoutId();
		parser.fromText(ObjectWithUnaccessibleClass.class, response);
	}

}