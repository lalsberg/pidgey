package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithLongWrapper {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4)
	private Long idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithLongPrimitive [nomePerfil=" + nomePerfil + ", idSistema="
				+ idSistema + "]";
	}
	
}
