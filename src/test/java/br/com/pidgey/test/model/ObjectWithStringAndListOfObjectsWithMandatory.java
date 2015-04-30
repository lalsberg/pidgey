package br.com.pidgey.test.model;

import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;

public class ObjectWithStringAndListOfObjectsWithMandatory {
	
	@PField(position = 1716, size = 4)
	private String string1;
	
	@Many(repeated = 3)
	@PField(size = 773, clazz = ObjectWithMandatory.class)
	private List<ObjectWithMandatory> roles;

	public String getQuantidade() {
		return string1;
	}

	public void setQuantidade(String quantidade) {
		this.string1 = quantidade;
	}

	public List<ObjectWithMandatory> getRoles() {
		return roles;
	}

	public void setRoles(List<ObjectWithMandatory> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "ObjectWithStringAndListOfObjectsWithId [string1=" + string1
				+ ", roles=" + roles + "]";
	}
	
}