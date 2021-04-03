package com.renato.mercadolivre.detalheProduto;

import com.renato.mercadolivre.opniao.Opiniao;

public class OpiniaoResponse {
	
	private String titulo;
	private String descricao;
	
	public OpiniaoResponse(Opiniao obj) {
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
