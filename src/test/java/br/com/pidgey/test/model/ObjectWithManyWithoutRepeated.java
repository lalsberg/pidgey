package br.com.pidgey.test.model;

import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;

public class ObjectWithManyWithoutRepeated {
	
	@PField(position = 1716, size = 4)
	private String quantidade;
	
	@Many()
	@PField(size = 773)
	private List<ObjectWithMandatory> roles;

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public List<ObjectWithMandatory> getRoles() {
		return roles;
	}

	public void setRoles(List<ObjectWithMandatory> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "ObjectWithManyWithoutRepeated [quantidade=" + quantidade
				+ ", roles=" + roles + "]";
	}
	
}