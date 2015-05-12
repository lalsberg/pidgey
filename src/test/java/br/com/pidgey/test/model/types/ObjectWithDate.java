package br.com.pidgey.test.model.types;

import java.util.Date;

import br.com.pidgey.annotation.PField;

public class ObjectWithDate {
	
	@PField(position = 1716, size = 255)
	private String nomePerfil;
	
	@PField(position = 1971, size = 10)
	private Date date;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ObjectWithDate [nomePerfil=" + nomePerfil + ", date=" + date
				+ "]";
	}
	
}