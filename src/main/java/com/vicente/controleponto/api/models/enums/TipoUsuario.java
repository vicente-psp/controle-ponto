package com.vicente.controleponto.api.models.enums;

public enum TipoUsuario {
	
	ADM("Administrador"), COL("Colaborador"), COM("Comum");
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}

}
