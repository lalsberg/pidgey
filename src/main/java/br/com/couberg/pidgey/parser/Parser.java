package br.com.couberg.pidgey.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.couberg.pidgey.annotation.Id;
import br.com.couberg.pidgey.annotation.Many;
import br.com.couberg.pidgey.annotation.PField;
import br.com.couberg.pidgey.enumeration.FillDirectionEnum;
import br.com.couberg.pidgey.exception.ParseException;

/**
 * Represents a Book. This class must be extended in order to  
 * implement a book that can be converted to a formatted String 
 * that can be sent to the mainframe. 
 * 
 * @author Coutinho
 * @author lalsberg
 */
public class Parser implements IParser {
	
	private static Logger logger = LoggerFactory.getLogger(Parser.class);
	
	/**
	 * Uses the String to set this object values
	 * @param text
	 */
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
	
	/**
	 * Uses the object to create the String
	 * @return {@link String} that can be sent to the mainframe
	 */
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
		
		List<Field> fields = getBookfieldFields(clazz);
		for(Field field : fields) {
			PField bookfield = field.getAnnotation(PField.class);
			
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
				if(bookfield.clazz() == String.class) {
					int position = bookfield.position() + listSizeSum;
					insertString(sb, bookfield, value, position);
				} else {
					createByClass(bookfield.clazz(), sb, value, 
							listSizeSum);
				}
			} else {
				
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
				
				if(bookfield.clazz() == String.class) {
					for(int i=0; i<listLimit; i++) {
						int position = bookfield.position() + 
								(bookfield.size() * i) + listSizeSum;
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						insertString(sb, bookfield, value2, position);
					}
					return bookfield.size() * listLimit;
				} else {
					for(int i=0; i<listLimit; i++) {
						Object value2 = (values == null || i >= values.size()) 
								? null : values.get(i);
						int innerListSizeSum = listSizeSum + 
								(bookfield.size() * i);
						createByClass(bookfield.clazz(), sb, value2, 
								innerListSizeSum);
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * @param sb
	 * @param bookfield
	 * @param value
	 * @param position
	 * 
	 * Overloaded method. Realizes the same, but specifying the 
	 * position to insert the value. Used in lists, where the 
	 * position of each element is dynamic, not the position 
	 * specified in the Bookfield annotation.
	 */
	private void insertString(StringBuilder sb,
			PField bookfield, Object value, int position) {
		
		value = value != null ? value : "";

		if(bookfield.fill() == FillDirectionEnum.LEFT) {
			value = StringUtils.leftPad((String) value, bookfield.size(), 
					bookfield.fillValue());
		} else if(bookfield.fill() == FillDirectionEnum.RIGHT) {
			value = StringUtils.rightPad((String) value, bookfield.size(), 
					bookfield.fillValue());
		}
		
		value = ((String) value).substring(0, bookfield.size());
		
		if(position >= sb.length()) {
			while (position > sb.length()) {
				sb.append(" ");
			}
			sb.append(value);
		} else {
			sb.replace(position, position + bookfield.size(), (String)value);
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
//			createByTextNoIntervalBySize(superClass, text, this, listSizeSum);
			createByText(superClass, text, obj, listSizeSum);
		}
		
		List<Field> fields = getBookfieldFields(clazz);
		for(Field field : fields) {
			PField bookfield = field.getAnnotation(PField.class);
			int size = bookfield.size();
			String value = "";
			
			if(!List.class.isAssignableFrom(field.getType())) {
				if(bookfield.clazz() == String.class) {
					int position = bookfield.position() + listSizeSum;
					boolean endOfNode = isEndOfNode(field, text, position);
					if(endOfNode) {
						return endOfNode;
					}
//					value = text.substring(position, position + size).trim();
					value = text.substring(position, position + size);
					if(bookfield.fill() == FillDirectionEnum.LEFT) {
						value = StringUtils.stripStart(value, String.valueOf(bookfield.fillValue()));
					} else {
						value = StringUtils.stripEnd(value, String.valueOf(bookfield.fillValue()));
					}
					field.setAccessible(true);
					try {
						field.set(obj, value);
					} catch (IllegalAccessException e) {
						logger.error("Could not access field " + field.getName());
						throw new ParseException(e.getMessage());
					}
				} else {
					try {
						Object instance = bookfield.clazz().newInstance();
						createByText(bookfield.clazz(), text, instance, listSizeSum);
						field.setAccessible(true);
						try {
							field.set(obj, instance);
						} catch (IllegalAccessException e) {
							logger.error("Could not access field " + field.getName());
							throw new ParseException(e.getMessage());
						}
					} catch(InstantiationException e) {
						String message = "The class " + bookfield.clazz() + 
								" must provide a default constructor in order to be "
								+ "created by using reflection.";
						logger.error(message, e);
						throw new ParseException(message);
					} catch(IllegalAccessException e) {
						String message = "Could not access the class " + bookfield.clazz() + 
								". Are you sure the class and/or the constructor is "
								+ "accessible?";
						logger.error(message);
						throw new ParseException(message);
					}
				}
			} else {
				field.setAccessible(true);
				List<Object> lista = new ArrayList<Object>();
				Many many = field.getAnnotation(Many.class);
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
				for(int i = 0; i < many.repeated(); i++) {
					if(bookfield.clazz() == String.class) {
						int position = bookfield.position() + 
								(bookfield.size() * i) + listSizeSum;
						boolean endOfNode = isEndOfNode(field, text, position);
						if(endOfNode) {
							break;
						}
//						value = text.substring(position, position + size).trim();
						value = text.substring(position, position + size);
						if(bookfield.fill() == FillDirectionEnum.LEFT) {
							value = StringUtils.stripStart(value, String.valueOf(bookfield.fillValue()));
						} else {
							value = StringUtils.stripEnd(value, String.valueOf(bookfield.fillValue()));
						}
						lista.add(value);
					} else {
						try {
							Object instance = bookfield.clazz().newInstance();
							int innerListSizeSum = listSizeSum + (bookfield.size() * i);
							boolean endOfNode = createByText(bookfield.clazz(), 
									text, instance, innerListSizeSum);
							if(endOfNode) {
								break;
							}
							lista.add(instance);
						} catch(InstantiationException e) {
							String message = "The class " + bookfield.clazz() + 
									" must provide a default constructor in order to be "
									+ "created by using reflection.";
							logger.error(message, e);
							throw new ParseException(message);
						} catch(IllegalAccessException e) {
							String message = "Could not access the class " + bookfield.clazz() + 
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

	/**
	 * Retrieve the fields annotated with Bookfield
	 * @param clazz
	 * @return the fields annotated with <code>Bookfield</code> of the 
	 * given <code>clazz</code>.
	 */
	private List<Field> getBookfieldFields(Class<?> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> declaredBookfieldsFields = new ArrayList<Field>();
		for(Field field : declaredFields) {
			PField bookfield = field.getAnnotation(PField.class);
			if(bookfield != null) {
				declaredBookfieldsFields.add(field);
			}
		}
		return declaredBookfieldsFields;
	}

	/**
	 * Indicate if this value was not returned from the mainframe.
	 * The returned value will be used to indicate that the list 
	 * that involves the element should stop adding elements. 
	 * 
	 * @param field
	 * @param value
	 * @return <code>true</code> if the value of the element is all 
	 * composed by the specified nullValue parameter of the Id 
	 * annotation;
	 * 		   <code>false</code> otherwise.
	 */
	private boolean isEndOfNode(Field field, String text, int position) {
		boolean endOfNode = false;
		
		PField pfield = field.getAnnotation(PField.class);
		int size = pfield.size();
		
		//Check if the text ended
		//TODO trocar por =, e antes fazer if. se >, entao throw parseException(unexpectedEndOfString).
		if(position + size > text.length()) {
			endOfNode = true;
			
		//Check if the there is an Id not returned
		} else {
//			String value = text.substring(position, position + size).trim();
			String value = text.substring(position, position + size);
			if(pfield.fill() == FillDirectionEnum.LEFT) {
				value = StringUtils.stripStart(value, String.valueOf(pfield.fillValue()));
			} else {
				value = StringUtils.stripEnd(value, String.valueOf(pfield.fillValue()));
			}
			
			Id id = field.getAnnotation(Id.class);
			if(id != null) {
				char mainframeFillValue = id.nullValue();
				boolean allDefault = true;
				for(char c : value.toCharArray()) {
					allDefault = allDefault && (c == mainframeFillValue);
					if(!allDefault) {
						break;
					}
				}
				if(allDefault) {
					endOfNode = true;
				}
			}
		}
		
		return endOfNode;
	}

}