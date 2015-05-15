package br.com.pidgey.test.model.types;

import br.com.pidgey.annotation.DoubleField;
import br.com.pidgey.annotation.PField;

public class ObjectWithDoublePrimitive {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@DoubleField(fractionDigits = 2)
	@PField(position = 1971, size = 4)
	private double idSistema;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public double getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(double idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithDoublePrimitive [nomePerfil=" + nomePerfil
				+ ", idSistema=" + idSistema + "]";
	}
	
}
