package br.com.pidgey.test.internal.run;

import java.lang.reflect.Field;

import org.junit.Test;

import br.com.pidgey.exception.ParseException;
import br.com.pidgey.test.model.invalid.ObjectWithDoubleWithoutDoubleFieldAnnotation;
import br.com.pidgey.validation.Validator2;

public class Validator2Test {

	@Test(expected = ParseException.class)
	public void testDoubleWithoutDoubleFieldAnnotation() {
		
		try {
			Field field = ObjectWithDoubleWithoutDoubleFieldAnnotation
					.class.getDeclaredField("idSistema");
			
			Validator2 validator = new Validator2();
			validator.validate(field);
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
