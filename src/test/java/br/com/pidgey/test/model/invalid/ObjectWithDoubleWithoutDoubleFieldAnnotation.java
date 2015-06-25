package br.com.pidgey.test.model.invalid;

import br.com.pidgey.annotation.PField;

public class ObjectWithDoubleWithoutDoubleFieldAnnotation {
	
	@PField(position = 1971, size = 4)
	private double idSistema;

	public double getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(double idSistema) {
		this.idSistema = idSistema;
	}

}