package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.List;

import br.com.pidgey.validation.Validator2;

public class Processor {
	
	public String parse(Object instance) {
		
		//1- Obter uma lista de Fields anotados com PField
		PFieldExtractor extractor = new PFieldExtractor();
		List<Field> fields = extractor.extract(instance.getClass());
		
		//2- Validar cada Field da lista
		Validator2 validator2 = new Validator2();
		for (Field field : fields) {
			validator2.validate(field);
		}
		
		//3- Obter uma lista de PFieldElement através da lista de Field.
		//4- Para cada item da lista
		//	5- Formatar
		//	6- Appendar
		//7- retornar
		return null;
	}

}
