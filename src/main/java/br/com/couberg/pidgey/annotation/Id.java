package br.com.couberg.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to determine if there is any actual value in that 
 * part of the text or if it's just filled with the default  
 * character. The default character must be provided by the 
 * parameter nullValue.
 * 
 * @author lalsberg
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	char nullValue();
}
