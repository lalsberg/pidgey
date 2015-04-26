package br.com.couberg.pidgey.test.model;

import java.util.List;

import br.com.couberg.pidgey.annotation.PField;

public class ObjectWithListWithoutMany2 {
	
	@PField(position = 1716, size = 4)
	private String quantidade;
	
	@PField(size = 773, clazz = ObjectWithId.class)
	private List<ObjectWithId> objectsWithId;

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public List<ObjectWithId> getObjectsWithId() {
		return objectsWithId;
	}

	public void setRoles(List<ObjectWithId> objectsWithId) {
		this.objectsWithId = objectsWithId;
	}

	@Override
	public String toString() {
		return "ObjectWithListWithoutMany2 [quantidade=" + quantidade
				+ ", objectsWithId=" + objectsWithId + "]";
	}
	
}
