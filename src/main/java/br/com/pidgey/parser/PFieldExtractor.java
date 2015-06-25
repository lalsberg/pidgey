package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.util.TypeUtils;

/**
 * Extract PField fields of the given class.
 * 
 * @author lalsberg
 *
 */
public class PFieldExtractor {

	/**
	 * Extracts all PField Fields of the given class 
	 * including all parent classes and references.
	 * @param clazz
	 * @return
	 */
	public List<Field> extract(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		return extractRecursively(clazz, fields);
	}

	private List<Field> extractRecursively(Class<?> clazz, List<Field> fields) {
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			extractRecursively(superClass, fields);
		}
		
		List<Field> baseFields = getPFieldFields(clazz);
		fields.addAll(baseFields);
		
		for (Field field : baseFields) {
			if(!TypeUtils.isJavaType(field.getType())) {
				extractRecursively(field.getType(), fields);
			}
		}
		
		return fields;
	}
	
	/**
	 * Retrieve the fields annotated with PField
	 * @param clazz
	 * @return the fields annotated with <code>PField</code> of the 
	 * given <code>clazz</code>.
	 */
	private List<Field> getPFieldFields(Class<?> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> declaredPFieldFields = new ArrayList<Field>();
		for(Field field : declaredFields) {
			PField pField = field.getAnnotation(PField.class);
			if(pField != null) {
				declaredPFieldFields.add(field);
			}
		}
		return declaredPFieldFields;
	}

}
