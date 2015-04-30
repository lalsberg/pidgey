package br.com.pidgey.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Where to use:
 * Processed only during fromText method. 
 * Should be used only on String fields, which are directly or 
 * indirectly inside Lists.
 * How it works: 
 * Used to determine if the List has ended and can stop adding 
 * elements. This evaluation will be true if that part of text 
 * being parsed to the annotated String is actually a null value.
 * Normally null values just set null to the field, but if the 
 * field is annotated with Mandatory, a null value will mean the 
 * end of the list.
 * 
 * @author lalsberg
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mandatory { }
