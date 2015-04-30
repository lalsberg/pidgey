package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Determines a fixed size for the list. In classes used in 
 * fromText method, lists should always use Many, otherwise 
 * a ParseException will be thrown.
 * 
 * @author lalsberg
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Many {
	int repeated() default -1;
}
