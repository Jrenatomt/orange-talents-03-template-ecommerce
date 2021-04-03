package com.renato.mercadolivre.detalheProduto;

import com.renato.mercadolivre.produto.CaracteristicaProduto;

public class CaracteristicasProdutoResponse {
	
	private String nome;
	private String descricao;
	
	public CaracteristicasProdutoResponse(CaracteristicaProduto obj) {
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
