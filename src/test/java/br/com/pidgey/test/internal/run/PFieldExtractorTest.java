package br.com.pidgey.test.internal.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.parser.PFieldExtractor;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;

public class PFieldExtractorTest {
	
	@Test
	public void testExtract() {
		PFieldExtractor extractor = new PFieldExtractor();
		List<Field> fields = extractor.extract(ObjectWithIntegerPrimitive.class);
		
		assertEquals(2, fields.size());
		
		PField pField = fields.get(0).getAnnotation(PField.class);
		assertNotNull(pField);
		
		PField pField2 = fields.get(1).getAnnotation(PField.class);
		assertNotNull(pField2);
	}
	
	//TODO write test
//	@Test
//	public void testExtractInnerFields() {
//	}
	
	//TODO write test
//	@Test
//	public void testExtractParentFields() {
//	}
	
	//TODO write test
//	@Test
//	public void testExtractParentWithInnerFields() {
//	}
	
	//TODO write test
//	@Test
//	public void testExtractInnerWithParentFields() {
//	}
	
	//TODO write test
//	@Test
//	public void testExtractNonAnnotatedFieldsShouldNotBeExtracted() {
//	}

}
