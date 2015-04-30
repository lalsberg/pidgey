package br.com.pidgey.test.model;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.enumeration.FillDirectionEnum;

public class ObjectWithUnaccessibleClass {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4, fillValue = '0', fill = FillDirectionEnum.LEFT)
	private String idSistema;
	
	

	private ObjectWithUnaccessibleClass() {
		super();
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
