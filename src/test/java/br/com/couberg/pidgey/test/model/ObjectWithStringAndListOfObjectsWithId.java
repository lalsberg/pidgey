package br.com.couberg.pidgey.test.model;

import java.util.List;

import br.com.couberg.pidgey.annotation.Many;
import br.com.couberg.pidgey.annotation.PField;

public class ObjectWithStringAndListOfObjectsWithId {
	
	@PField(position = 1716, size = 4)
	private String string1;
	
	@Many(repeated = 3)
	@PField(size = 773, clazz = ObjectWithId.class)
	private List<ObjectWithId> roles;

	public String getQuantidade() {
		return string1;
	}

	public void setQuantidade(String quantidade) {
		this.string1 = quantidade;
	}

	public List<ObjectWithId> getRoles() {
		return roles;
	}

	public void setRoles(List<ObjectWithId> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "ObjectWithStringAndListOfObjectsWithId [string1=" + string1
				+ ", roles=" + roles + "]";
	}
	
}