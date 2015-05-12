package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithBooleanWrapper {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 5)
	private Boolean theBoolean;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Boolean isTheBoolean() {
		return theBoolean;
	}

	public void setTheBoolean(Boolean theBoolean) {
		this.theBoolean = theBoolean;
	}

	@Override
	public String toString() {
		return "ObjectWithBooleanWrapper [nomePerfil=" + nomePerfil
				+ ", theBoolean=" + theBoolean + "]";
	}
	
}