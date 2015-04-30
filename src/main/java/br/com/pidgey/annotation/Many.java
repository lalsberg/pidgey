package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Determines a fixed size for the list. In classes used to 
 * parse from text, lists should always use Many, otherwise 
 * a ParseException will be thrown.
 * In classes used to parse to text, if the repeated attribute 
 * is missing, the size of the list will be used to create the 
 * text. This is a performance decision, since it uses just 
 * actual values of the list, evicting useless characters at the 
 * end of the list area. But, if there are more elements to be 
 * positioned after this list, the repeated atrribute should be 
 * specified, otherwise .. otherwise what? nothing. now we're working with positions. it wont affect the next element
 * child list also do not need, since the inner positions will be affected by the size of the out list. the size is the matter
 * @author lalsberg
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Many {
	int repeated() default -1;
}
