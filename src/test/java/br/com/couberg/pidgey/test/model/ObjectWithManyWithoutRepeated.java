package br.com.couberg.pidgey.test.model;

import java.util.List;

import br.com.couberg.pidgey.annotation.Many;
import br.com.couberg.pidgey.annotation.PField;

public class ObjectWithManyWithoutRepeated {
	
	@PField(position = 1716, size = 4)
	private String quantidade;
	
	@Many()
	@PField(size = 773, clazz = ObjectWithId.class)
	private List<ObjectWithId> roles;

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public List<ObjectWithId> getRoles() {
		return roles;
	}

	public void setRoles(List<ObjectWithId> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "ObjectWithManyWithoutRepeated [quantidade=" + quantidade
				+ ", roles=" + roles + "]";
	}
	
}