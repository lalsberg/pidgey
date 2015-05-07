package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.pidgey.enumeration.FillDirectionEnum;

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
	FillDirectionEnum fill() default FillDirectionEnum.RIGHT;
	char fillValue() default '#'; //TODO passar pra um enum
	char nullFillValue() default '#'; //TODO passar pra um enum
	int size() default 0;
	int position() default -1;
}
