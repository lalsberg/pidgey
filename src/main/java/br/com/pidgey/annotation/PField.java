package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;

/**
 * Determines that the field will be parsed.
 * 
 * @author lalsberg
 * @author Coutinho
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PField {
	FillDirection fill() default FillDirection.UNSPECIFIED;
	FillValue fillValue() default FillValue.UNSPECIFIED;
	FillValue nullFillValue() default FillValue.UNSPECIFIED;
	int size() default 0;
	int position() default -1;
}
