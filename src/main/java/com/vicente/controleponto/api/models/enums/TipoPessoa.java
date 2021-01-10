package com.vicente.controleponto.api.models.enums;

public enum TipoPessoa {
	
	CONTRATANTE("Contratante"), CONTRATADO("Contratado"), AUTONOMO("Autônomo");
	
	private String descricao;
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}

}
