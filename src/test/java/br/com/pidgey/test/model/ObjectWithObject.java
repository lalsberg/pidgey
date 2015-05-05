package br.com.pidgey.test.model;

import br.com.pidgey.annotation.PField;

public class ObjectWithObject {
	
	@PField()
	private ObjectWithoutId objectWithoutId;

	public ObjectWithoutId getObjectWithoutId() {
		return objectWithoutId;
	}

	public void setObjectWithoutId(ObjectWithoutId objectWithoutId) {
		this.objectWithoutId = objectWithoutId;
	}

	@Override
	public String toString() {
		return "ObjectWithObject [objectWithoutId=" + objectWithoutId + "]";
	}
	
}