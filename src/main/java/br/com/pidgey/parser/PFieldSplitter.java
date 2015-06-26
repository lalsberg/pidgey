package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.util.ReflectionUtils;
import br.com.pidgey.util.TypeUtils;
import br.com.pidgey.validation.Validator;


public class PFieldSplitter {

	public Iterator<PFieldElement> split(Object instance) {
		
		List<PFieldElement> elements = new ArrayList<PFieldElement>();
		
		
		createByClass(instance.getClass(), instance, 0);
		
		//TODO order by position
		return Collections.unmodifiableList(elements).iterator();
	}
	
	private void createByClass(Class<?> clazz, Object instance, 
			int listSizeSum) {
		
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			createByClass(superClass, instance, listSizeSum);
		}
		
		List<Field> fields = getPFieldFields(clazz);
		for(Field field : fields) {
			PField pField = field.getAnnotation(PField.class);
			
			field.setAccessible(true);
			Object value;
			if(instance == null) {
				value = null;
			} else {
				value = ReflectionUtils.getInstanceFieldValue(field, instance);
			}
			
			if(!List.class.isAssignableFrom(field.getType())) {
				if(TypeUtils.isJavaType(field.getType())) {
					int position = pField.position() + listSizeSum;
					int endPosition = position + pField.size();
					PFieldElement element = new PFieldElement(position, endPosition, value);
				} else {
					createByClass(field.getType(), value,listSizeSum);
				}
			} else {
				Class<?> listGenericType = ReflectionUtils.getFirstGenericType(field);
				
				int repeated = field.getAnnotation(Many.class).repeated();
				
				if(TypeUtils.isJavaType(listGenericType)) {
					ListOfJavaTypeSplitter listOfJavaTypeSplitter = new ListOfJavaTypeSplitter();
					List<PFieldElement> elements = listOfJavaTypeSplitter.split(
							value, listSizeSum, pField.size(), pField.position(), repeated);
					
					
				} else {
					@SuppressWarnings("unchecked")
					List<Object> values = (List<Object>) value;
					
					for(int i=0; i<values.size(); i++) {
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						int innerListSizeSum = listSizeSum + (pField.size() * i);
						createByClass(listGenericType, value2, innerListSizeSum);
					}
				}
			}
		}
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
