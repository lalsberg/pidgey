package br.com.pidgey.test.model;

import java.util.List;

import br.com.pidgey.annotation.PField;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;

public class ObjectWithListWithoutMany {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 4, fillValue = FillValue.ZERO, fill = FillDirection.LEFT)
	private List<String> idSistemas;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public List<String> getIdSistema() {
		return idSistemas;
	}

	public void setIdSistema(List<String> idSistemas) {
		this.idSistemas = idSistemas;
	}

	@Override
	public String toString() {
		return "ObjectWithListWithoutMany [nomePerfil=" + nomePerfil
				+ ", idSistemas=" + idSistemas + "]";
	}
	
}