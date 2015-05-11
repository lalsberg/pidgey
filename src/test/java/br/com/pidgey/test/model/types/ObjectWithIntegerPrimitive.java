package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithIntegerPrimitive {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4)
	private int idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public int getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithIntegerPrimitive [nomePerfil=" + nomePerfil + ", idSistema="
				+ idSistema + "]";
	}
	
}
