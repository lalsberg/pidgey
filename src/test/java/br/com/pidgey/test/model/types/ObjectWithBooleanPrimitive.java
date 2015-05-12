package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithBooleanPrimitive {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 5)
	private boolean theBoolean;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public boolean isTheBoolean() {
		return theBoolean;
	}

	public void setTheBoolean(boolean theBoolean) {
		this.theBoolean = theBoolean;
	}

	@Override
	public String toString() {
		return "ObjectWithBooleanPrimitive [nomePerfil=" + nomePerfil
				+ ", theBoolean=" + theBoolean + "]";
	}
	
}