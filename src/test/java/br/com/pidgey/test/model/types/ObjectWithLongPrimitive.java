package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithLongPrimitive {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4)
	private long idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(long idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithLongPrimitive [nomePerfil=" + nomePerfil + ", idSistema="
				+ idSistema + "]";
	}
	
}
