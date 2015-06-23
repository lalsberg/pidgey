package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.converter.TypeDefinitions;
import br.com.pidgey.exception.ParseException;
import br.com.pidgey.formatter.Formatter;
import br.com.pidgey.util.ReflectionUtils;
import br.com.pidgey.util.TypeUtils;
import br.com.pidgey.validation.Validator;


public class Exploder {

	public Appendables explode(Object instance) {
		
		List<AppendableItem> items = new ArrayList<AppendableItem>();
		createByClass(instance.getClass(), instance, 0);
		
		return null;
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
					
					TypeDefinition typeDefinition = TypeDefinitions.getDefinition(field.getType());
					Formatter formatter = new Formatter(typeDefinition);
					
					String textValue = formatter.toText(field, value);
					Validator.validateStringIsNoLongerThanSize(textValue, pField.size(), field);
					insertValue(sb, pField, textValue, position);
					
				} else {
					createByClass(field.getType(), sb, value, 
							listSizeSum);
				}
			} else {
				Class<?> listGenericType = ReflectionUtils.getFirstGenericType(field);
				
				@SuppressWarnings("unchecked")
				List<Object> values = (List<Object>) value;
					
				Many many = field.getAnnotation(Many.class);
				Validator.validateManyNotNull(field, many);
				
				int listLimit;
				if(many.repeated() != -1) {
					listLimit = many.repeated();
				} else {
					listLimit = values.size();
				}
				
				if(TypeUtils.isJavaType(listGenericType)) {
					for(int i=0; i<listLimit; i++) {
						int position = pField.position() + 
								(pField.size() * i) + listSizeSum;
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						
						TypeDefinition typeDefinition = TypeDefinitions.getDefinition(listGenericType);
						Formatter formatter = new Formatter(typeDefinition);
						
						String textValue = formatter.toText(field, value2);
						Validator.validateStringIsNoLongerThanSize(textValue, pField.size(), field);
						insertValue(sb, pField, textValue, position);
					}
				} else {
					for(int i=0; i<listLimit; i++) {
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						int innerListSizeSum = listSizeSum + (pField.size() * i);
						createByClass(listGenericType, sb, value2, innerListSizeSum);
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
