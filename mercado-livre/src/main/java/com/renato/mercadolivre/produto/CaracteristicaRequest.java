package com.renato.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public CaracteristicaRequest() {
	}
	
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicaRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
}
