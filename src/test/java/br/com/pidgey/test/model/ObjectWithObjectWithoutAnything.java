package br.com.pidgey.test.model;

import br.com.pidgey.annotation.PField;

public class ObjectWithObjectWithoutAnything {
	
	@PField()
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