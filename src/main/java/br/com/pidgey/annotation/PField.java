package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.pidgey.enumeration.FillDirectionEnum;

/**
 * Determines that field will be parsed.
 * 
 * @author lalsberg
 * @author Coutinho
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PField {
	Class<?> clazz() default String.class;
	FillDirectionEnum fill() default FillDirectionEnum.RIGHT;
	char fillValue() default ' ';
	char nullFillValue() default ' ';
	int size() default 0;
	int position() default -1;
}
