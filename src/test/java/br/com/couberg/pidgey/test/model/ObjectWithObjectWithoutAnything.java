package br.com.couberg.pidgey.test.model;

import br.com.couberg.pidgey.annotation.PField;

public class ObjectWithObjectWithoutAnything {
	
	@PField(clazz=ObjectWithoutAnything.class)
	private ObjectWithoutAnything objectWithoutAnything;

	public Object getObjectWithoutAnything() {
		return objectWithoutAnything;
	}

	public void setObjectWithoutAnything(ObjectWithoutAnything objectWithoutAnything) {
		this.objectWithoutAnything = objectWithoutAnything;
	}

	@Override
	public String toString() {
		return "ObjectWithObjectWithoutAnything [objectWithoutAnything="
				+ objectWithoutAnything + "]";
	}

}