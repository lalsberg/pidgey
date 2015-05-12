package br.com.pidgey.test.model;

import br.com.pidgey.annotation.Mandatory;
import br.com.pidgey.annotation.PField;
import br.com.pidgey.enumeration.FillDirection;
import br.com.pidgey.enumeration.FillValue;

public class ObjectWithMandatory {
	
	@PField(position = 1720, size = 4, fillValue = FillValue.ZERO, fill = FillDirection.LEFT)
	private String idPerfil;
	
	@PField(position = 1724, size = 255)
	private String descricao;
	
	@Mandatory()
	@PField(position = 1979, size = 255)
	private String nome;
	
	@PField(position = 2234, size = 4, fillValue = FillValue.ZERO, fill = FillDirection.LEFT)
	private String idSistema;
	
	@PField(position = 2238, size = 255)
	private String nomeSistema;

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
	}

	@Override
	public String toString() {
		return "ObjectWithId [idPerfil=" + idPerfil + ", descricao="
				+ descricao + ", nome=" + nome + ", idSistema=" + idSistema
				+ ", nomeSistema=" + nomeSistema + "]";
	}
	
}
