package br.com.pidgey.test.run;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.parser.IParser;
import br.com.pidgey.parser.Parser;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.ObjectWithListWithoutMany;
import br.com.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;
import br.com.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.pidgey.test.model.ObjectWithoutId;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;

public class ParserToTextTest {
	
	private IParser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	public static void main(String[] args) {
//		Gson gson = new Gson();
		ObjectWithLongPrimitive obj = new ObjectWithLongPrimitive();
		obj.setIdSistema(3);
		obj.setNomePerfil("teste");
		
//		String json = gson.toJson(obj);
		Gson gson = new Gson();
		String json = "{\"nomePerfil\":\"teste\",\"idSistema\":3}";
		System.out.println(json);
		
		ObjectWithLongPrimitive obj2 = gson.fromJson(json, ObjectWithLongPrimitive.class);
		System.out.println(obj2);
		
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