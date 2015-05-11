package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.PField;

public class ObjectWithIntegerWrapper {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4)
	private Integer idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Integer getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithIntegerWrapper [nomePerfil=" + nomePerfil + ", idSistema="
				+ idSistema + "]";
	}
	
}
