package br.com.pidgey.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pidgey.exception.ParseException;

public class ReflectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	public static <T> T newInstance(Class<T> clazz) throws ParseException {
		
		try {
			return clazz.newInstance();
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
	
	public static Object getInstanceFieldValue(Field field, Object instance) 
			throws ParseException {
		try {
			return field.get(instance);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new ParseException(e.getMessage());
		}
	}
	
	public static void setInstanceFieldValue(Object instance, Field field, Object value) 
			throws ParseException {
		try {
			field.setAccessible(true);
			field.set(instance, value);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new ParseException(e.getMessage());
		}
	}
	
	public static Class<?> getFirstGenericType(Field field) throws ParseException {
		try {
			ParameterizedType genericType = (ParameterizedType) field.getGenericType();
			return (Class<?>) genericType.getActualTypeArguments()[0];
		} catch(ClassCastException e) {
			throw new ParseException("The type of the field \"" + field.getName() + 
					"\" should be parameterized");
		}
	}
	
}