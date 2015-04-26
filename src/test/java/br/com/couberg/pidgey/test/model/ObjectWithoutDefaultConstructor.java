package br.com.couberg.pidgey.test.model;

import br.com.couberg.pidgey.annotation.PField;
import br.com.couberg.pidgey.enumeration.FillDirectionEnum;

public class ObjectWithoutDefaultConstructor {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4, fillValue = '0', fill = FillDirectionEnum.LEFT)
	private String idSistema;

	public ObjectWithoutDefaultConstructor(String nomePerfil, String idSistema) {
		super();
		this.nomePerfil = nomePerfil;
		this.idSistema = idSistema;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithoutId [nomePerfil=" + nomePerfil + ", idSistema="
				+ idSistema + "]";
	}
	
}
