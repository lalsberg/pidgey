package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.util.ReflectionUtils;
import br.com.pidgey.util.TypeUtils;


public class PFieldSplitter {

	public Iterator<PFieldElement> split(Object instance) {
		
		List<PFieldElement> elements = new ArrayList<PFieldElement>();
		
		//todo order first
		createByClass(instance.getClass(), instance, 0);
		
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
					
				} else {
					createByClass(field.getType(), value,listSizeSum);
				}
			} else {
				Class<?> listGenericType = ReflectionUtils.getFirstGenericType(field);
				
				@SuppressWarnings("unchecked")
				List<Object> values = (List<Object>) value;
				
				if(TypeUtils.isJavaType(listGenericType)) {
					for(int i=0; i<values.size(); i++) {
						int position = pField.position() + (pField.size() * i) + listSizeSum;
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						
					}
				} else {
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
