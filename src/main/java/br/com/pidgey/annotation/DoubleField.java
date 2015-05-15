package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to specify the number of fraction digits 
 * after the dot. <br> 
 * The parsed/parsing String contains no separators, 
 * therefore this information is necessary to generate 
 * the double value when parsing from String.
 * 
 * @author lalsberg
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoubleField {
	int fractionDigits();
}
