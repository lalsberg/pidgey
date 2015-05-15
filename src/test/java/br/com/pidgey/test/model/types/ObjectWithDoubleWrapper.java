package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.annotation.PField;

public class ObjectWithDoubleWrapper {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@DoubleField(fractionDigits = 2)
	@PField(position = 1971, size = 4)
	private Double idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Double getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Double idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithDoubleWrapper [nomePerfil=" + nomePerfil
				+ ", idSistema=" + idSistema + "]";
	}
	
}