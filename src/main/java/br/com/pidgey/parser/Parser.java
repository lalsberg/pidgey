package br.com.pidgey.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pidgey.annotation.Mandatory;
import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.converter.TypeDefinition;
import br.com.pidgey.converter.TypeDefinitions;
import br.com.pidgey.enumeration.FillDirectionEnum;
import br.com.pidgey.exception.ParseException;
import br.com.pidgey.formatter.Formatter;
import br.com.pidgey.formatter.FormatterUtil;

/**
 * The parser to convert between String and Object
 * 
 * @author Coutinho
 * @author lalsberg
 */
public class Parser implements IParser {
	
	private static Logger logger = LoggerFactory.getLogger(Parser.class);
	
	public <T> T fromText(Class<T> clazz, String text) 
			throws ParseException {
		try {
			T instance = clazz.newInstance();
			createByText(instance.getClass(), text, instance, 0);
			return instance;
		} catch(InstantiationException e) {
			String message = "The class " + clazz + 
					" must provide a default constructor in order to be "
					+ "created by using reflection.";
			logger.error(message, e);
			throw new ParseException(message);
		} catch(IllegalAccessException e) {
			String message = "Could not access the class " + clazz + 
					". Are you sure the class and/or the constructor is "
					+ "accessible?";
			logger.error(message);
			throw new ParseException(message);
		}
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
	private int createByClass(Class<?> clazz, StringBuilder sb, 
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
				try {
					value = field.get(instance);
				} catch (IllegalAccessException e) {
					throw new ParseException(e.getMessage());
				}
				
			}
			
			if(!List.class.isAssignableFrom(field.getType())) {
				if(FormatterUtil.isJavaType(field.getType())) {
					int position = pField.position() + listSizeSum;
					
					TypeDefinition typeDefinition = TypeDefinitions.getDefinition(field.getType());
					Formatter formatter = new Formatter(typeDefinition);
					
					String textValue = formatter.toText(pField, value);
					
					//TODO
					//manter isso abaixo ou lancar exception? acho que nao deveria ter nada > size
					//posso ver onde que seria lancada a exception. acho que no insertvalue. dai 
					//fazer um check antes do textValue.size > pfield.size, caso true, lancar ex.
					
					//CRIAR classe de test pra garantir essa exception.
					textValue = textValue.substring(0, pField.size());
					
					insertValue(sb, pField, textValue, position);
					
				} else {
					createByClass(field.getType(), sb, value, 
							listSizeSum);
				}
			} else {
				Class<?> listGenericType = null;
				try {
					ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					listGenericType = (Class<?>) genericType.getActualTypeArguments()[0];
				} catch(ClassCastException e) {
					throw new ParseException("The type of the field \"" + field.getName() + 
							"\" should be parameterized");
				}
				
				@SuppressWarnings("unchecked")
				List<Object> values = (List<Object>) value;
					
				Many many = field.getAnnotation(Many.class);
				if(many == null) {
					String message = "The collection " + field.getName() + 
							" must be annotated with Many.";
					logger.error(message);
					throw new ParseException(message);
				}
				
				int listLimit;
				if(many.repeated() != -1) {
					listLimit = many.repeated();
				} else {
					listLimit = values.size();
				}
				
				if(listGenericType == String.class) {
					for(int i=0; i<listLimit; i++) {
						int position = pField.position() + 
								(pField.size() * i) + listSizeSum;
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						insertString(sb, pField, value2, position);
					}
					return pField.size() * listLimit;
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
		return 0;
	}
	
	/**
	 * @param sb
	 * @param pField
	 * @param value
	 * @param position
	 * 
	 * Insert the String in the given position. 
	 */
	private void insertString(StringBuilder sb,
			PField pField, Object value, int position) {
		
		value = formatValue(pField, value);
		
		if(position >= sb.length()) {
			while (position > sb.length()) {
				sb.append(" ");
			}
			sb.append(value);
		} else {
			sb.replace(position, position + pField.size(), (String)value);
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
	 * Format the value using fillValue (or nullFillValue if it is null),  
	 * fillDirection and size.
	 * @param pField
	 * @param value
	 * @return
	 */
	private Object formatValue(PField pField, Object value) {
		char actualFillValue = pField.fillValue();
		if (value == null) {
			actualFillValue = pField.nullFillValue();
		}
		
		value = value != null ? value : "";

		if(pField.fill() == FillDirectionEnum.LEFT) {
			value = StringUtils.leftPad((String) value, pField.size(), 
					actualFillValue);
		} else if(pField.fill() == FillDirectionEnum.RIGHT) {
			value = StringUtils.rightPad((String) value, pField.size(), 
					actualFillValue);
		}
		
		value = ((String) value).substring(0, pField.size());
		return value;
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
			String value = "";
			
			if(!List.class.isAssignableFrom(field.getType())) {
				if(FormatterUtil.isJavaType(field.getType())) {
					
					TypeDefinition typeDefinition = TypeDefinitions.getDefinition(field.getType());
					Formatter formatter = new Formatter(typeDefinition);
					
					int position = pField.position() + listSizeSum;
					
					boolean endOfNode = isEndOfNode(field, text, position, typeDefinition);
					if(endOfNode) {
						return endOfNode;
					}
					value = text.substring(position, position + size);
					
					Object valueObject = formatter.fromText(pField, value);
					
					field.setAccessible(true);
					try {
						field.set(obj, valueObject);
					} catch (IllegalAccessException e) {
						logger.error("Could not access field " + field.getName());
						throw new ParseException(e.getMessage());
					}
				} else {
					try {
						Object instance = field.getType().newInstance();
						createByText(field.getType(), text, instance, listSizeSum);
						field.setAccessible(true);
						try {
							field.set(obj, instance);
						} catch (IllegalAccessException e) {
							logger.error("Could not access field " + field.getName());
							throw new ParseException(e.getMessage());
						}
					} catch(InstantiationException e) {
						String message = "The class " + field.getType() + 
								" must provide a default constructor in order to be "
								+ "created by using reflection.";
						logger.error(message, e);
						throw new ParseException(message);
					} catch(IllegalAccessException e) {
						String message = "Could not access the class " + field.getType() + 
								". Are you sure the class and/or the constructor is "
								+ "accessible?";
						logger.error(message);
						throw new ParseException(message);
					}
				}
			} else {
				Class<?> listGenericType = null;
				try {
					ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					listGenericType = (Class<?>) genericType.getActualTypeArguments()[0];
				} catch(ClassCastException e) {
					throw new ParseException("The type of the field " + field.getName() + 
							" should be parameterized");
				}
				field.setAccessible(true);
				List<Object> lista = new ArrayList<Object>();
				Many many = field.getAnnotation(Many.class);
				validateMany(field, many);
				for(int i = 0; i < many.repeated(); i++) {
					if(listGenericType == String.class) {
						
						TypeDefinition typeDefinition = TypeDefinitions.getDefinition(listGenericType);
						int position = pField.position() + (pField.size() * i) + listSizeSum;
						boolean endOfNode = isEndOfNodeStringList(field, text, position, typeDefinition);
						if(endOfNode) {
							break;
						}
						value = text.substring(position, position + size);
						if(pField.fill() == FillDirectionEnum.LEFT) {
							value = StringUtils.stripStart(value, String.valueOf(pField.fillValue()));
						} else {
							value = StringUtils.stripEnd(value, String.valueOf(pField.fillValue()));
						}
						lista.add(value);
					} else {
						try {
							Object instance = listGenericType.newInstance();
							int innerListSizeSum = listSizeSum + (pField.size() * i);
							boolean endOfNode = createByText(listGenericType, 
									text, instance, innerListSizeSum);
							if(endOfNode) {
								break;
							}
							lista.add(instance);
						} catch(InstantiationException e) {
							String message = "The class " + listGenericType + 
									" must provide a default constructor in order to be "
									+ "created by using reflection.";
							logger.error(message, e);
							throw new ParseException(message);
						} catch(IllegalAccessException e) {
							String message = "Could not access the class " + listGenericType + 
									". Are you sure the class and/or the constructor is "
									+ "accessible?";
							logger.error(message);
							throw new ParseException(message);
						}
					}
				}
				try {
					field.set(obj, lista);
				} catch (IllegalAccessException e) {
					logger.error("Could not access field " + field.getName());
					throw new ParseException(e.getMessage());
				}
			}
		}
		return false;
	}

	private void validateMany(Field field, Many many) throws ParseException {
		if(many == null) {
			String message = "The collection " + field.getName() + 
					" must be annotated with Many.";
			logger.error(message);
			throw new ParseException(message);
		} else if(many.repeated() == -1) {
			String message = "The Many annotation in the collection " + 
		field.getName() + " must specify the attribute repeated.";
			logger.error(message);
			throw new ParseException(message);
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
			
			char actualFillValue = FormatterUtil.obtainFillValue(
					pfield.fillValue(), typeDefinition.getDefaultFillValue());
			
			char actualNullFillValue = FormatterUtil.obtainNullFillValue(
					pfield.nullFillValue(), typeDefinition.getDefaultNullFillValue());
			
			FillDirectionEnum actualFillDirection = 
					FormatterUtil.obtainFillDirection(pfield.fill(), 
					typeDefinition.getDefaultFillDirection());
			
			if(actualFillDirection == FillDirectionEnum.LEFT) {
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
			
			char actualFillValue = FormatterUtil.obtainFillValue(
					pfield.fillValue(), typeDefinition.getDefaultFillValue());
			
			char actualNullFillValue = FormatterUtil.obtainNullFillValue(
					pfield.nullFillValue(), typeDefinition.getDefaultNullFillValue());
			
			FillDirectionEnum actualFillDirection = 
					FormatterUtil.obtainFillDirection(pfield.fill(), 
					typeDefinition.getDefaultFillDirection());
			
			if(actualFillDirection == FillDirectionEnum.LEFT) {
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