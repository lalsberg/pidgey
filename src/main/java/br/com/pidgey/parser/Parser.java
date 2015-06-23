package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.pidgey.annotation.Mandatory;
import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.converter.TypeDefinitions;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.exception.ParseException;
import br.com.pidgey.formatter.Formatter;
import br.com.pidgey.formatter.OverridingRules;
import br.com.pidgey.util.ReflectionUtils;
import br.com.pidgey.util.TypeUtils;
import br.com.pidgey.validation.Validator;

/**
 * The parser to convert between String and Object
 * 
 * @author Coutinho
 * @author lalsberg
 */
public class Parser {
	
	public <T> T fromText(Class<T> clazz, String text) 
			throws ParseException {
			T instance = ReflectionUtils.newInstance(clazz);
			createByText(instance.getClass(), text, instance, 0);
			return instance;
	}
	
	public String toText(Object instance) throws ParseException{
		StringBuilder sb = new StringBuilder();
		createByClass(instance.getClass(), sb, instance, 0);
		return sb.toString();
	}
	
	/**
	 * Converts the object to a String depending on it's class
	 * @param clazz the class of the object
	 * @param sb {@link StringBuilder} to create the String
	 */
	private void createByClass(Class<?> clazz, StringBuilder sb, 
			Object instance, int listSizeSum) throws ParseException {
		
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			createByClass(superClass, sb, instance, listSizeSum);
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
	
	private void insertValue(StringBuilder sb, 
			PField pField, String value, int position) {
		
		if(position >= sb.length()) {
			while (position > sb.length()) {
				sb.append(" ");
			}
			sb.append(value);
		} else {
			sb.replace(position, position + pField.size(), value);
		}
	}

	/**
	 * Converts part of the text into an object
	 * @param clazz class of the object
	 * @param text the text to be converted
	 * @param obj object to be created
	 * @return {@link String} the remainder part of the text
	 */
	private boolean createByText(Class<?> clazz, String text, Object obj, 
			int listSizeSum) throws ParseException {
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			createByText(superClass, text, obj, listSizeSum);
		}
		
		List<Field> fields = getPFieldFields(clazz);
		for(Field field : fields) {
			PField pField = field.getAnnotation(PField.class);
			int size = pField.size();
			String valueStr = "";
			
			if(!List.class.isAssignableFrom(field.getType())) {
				Object value;
				
				if(TypeUtils.isJavaType(field.getType())) {
					
					TypeDefinition typeDefinition = TypeDefinitions.getDefinition(field.getType());
					Formatter formatter = new Formatter(typeDefinition);
					
					int position = pField.position() + listSizeSum;
					
					boolean endOfNode = isEndOfNode(field, text, position, typeDefinition);
					if(endOfNode) {
						return endOfNode;
					}
					valueStr = text.substring(position, position + size);
					value = formatter.fromText(field, valueStr);
					
				} else {
					value = ReflectionUtils.newInstance(field.getType());
					createByText(field.getType(), text, value, listSizeSum);
				}
				
				ReflectionUtils.setInstanceFieldValue(obj, field, value);
				
			} else {
				Class<?> listGenericType = ReflectionUtils.getFirstGenericType(field);
				List<Object> list = new ArrayList<Object>();
				Many many = field.getAnnotation(Many.class);
				Validator.validateManyNotNull(field, many);
				Validator.validateRepeatedIsSpecified(field, many);
				for(int i = 0; i < many.repeated(); i++) {
					if(TypeUtils.isJavaType(listGenericType)) {
						
						TypeDefinition typeDefinition = TypeDefinitions.getDefinition(listGenericType);
						Formatter formatter = new Formatter(typeDefinition);
						int position = pField.position() + (pField.size() * i) + listSizeSum;
						boolean endOfNode = isEndOfNodeStringList(field, text, position, typeDefinition);
						if(endOfNode) {
							break;
						}
						valueStr = text.substring(position, position + size);
						
						Object valueObject = formatter.fromText(field, valueStr);
						list.add(valueObject);
					} else {
						Object instance = ReflectionUtils.newInstance(listGenericType);
						int innerListSizeSum = listSizeSum + (pField.size() * i);
						boolean endOfNode = createByText(listGenericType, 
								text, instance, innerListSizeSum);
						if(endOfNode) {
							break;
						}
						list.add(instance);
					}
				}
				ReflectionUtils.setInstanceFieldValue(obj, field, list);
			}
		}
		return false;
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

	/**
	 * If the text is over or have found a Mandatory field null
	 * 
	 * @param field
	 * @param typeDefinition 
	 * @param value
	 * @return <code>true</code> if the value of the element is all 
	 * composed by the specified nullfillValue parameter of the PField 
	 * annotation, or if the text is over;
	 * 		   <code>false</code> otherwise.
	 */
	private boolean isEndOfNode(Field field, String text, int position, 
			TypeDefinition typeDefinition) {
		boolean endOfNode = false;
		
		PField pfield = field.getAnnotation(PField.class);
		int size = pfield.size();
		
		if(position + size > text.length()) {
			endOfNode = true;
		} else {
			String value = text.substring(position, position + size);
			
			char actualFillValue = OverridingRules.obtainFillValue(
					pfield.fillValue(), typeDefinition.getDefaultFillValue());
			
			char actualNullFillValue = OverridingRules.obtainNullFillValue(
					pfield.nullFillValue(), typeDefinition.getDefaultNullFillValue());
			
			FillDirection actualFillDirection = 
					OverridingRules.obtainFillDirection(pfield.fill(), 
					typeDefinition.getDefaultFillDirection());
			
			if(actualFillDirection == FillDirection.LEFT) {
				value = StringUtils.stripStart(value, String.valueOf(actualFillValue));
			} else {
				value = StringUtils.stripEnd(value, String.valueOf(actualFillValue));
			}
			
			Mandatory id = field.getAnnotation(Mandatory.class);
			if(id != null) {
				boolean allDefault = StringUtils.containsOnly(value, actualNullFillValue);
				endOfNode = allDefault;
			}
		}
		return endOfNode;
	}
	
	/**
	 * The same as isEndOfNode, but instead of checking if 
	 * is Mandatory, this methods will always return true 
	 * when the value is all made of nullFillValue
	 * @param field
	 * @param text
	 * @param position
	 * @param typeDefinition 
	 * @return
	 */
	private boolean isEndOfNodeStringList(Field field, String text, int position, TypeDefinition typeDefinition) {
		boolean endOfNode = false;
		
		PField pfield = field.getAnnotation(PField.class);
		int size = pfield.size();
		
		if(position + size > text.length()) {
			endOfNode = true;
		} else {
			String value = text.substring(position, position + size);
			
			char actualFillValue = OverridingRules.obtainFillValue(
					pfield.fillValue(), typeDefinition.getDefaultFillValue());
			
			char actualNullFillValue = OverridingRules.obtainNullFillValue(
					pfield.nullFillValue(), typeDefinition.getDefaultNullFillValue());
			
			FillDirection actualFillDirection = 
					OverridingRules.obtainFillDirection(pfield.fill(), 
					typeDefinition.getDefaultFillDirection());
			
			if(actualFillDirection == FillDirection.LEFT) {
				value = StringUtils.stripStart(value, String.valueOf(actualFillValue));
			} else {
				value = StringUtils.stripEnd(value, String.valueOf(actualFillValue));
			}
			
			boolean allDefault = StringUtils.containsOnly(value, actualNullFillValue);
			endOfNode = allDefault;
		}
		return endOfNode;
	}

}