package com.renato.mercadolivre.detalheProduto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.renato.mercadolivre.produto.Produto;

public class DetelheProdutoResponse {

	private String nome;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;
	private Set<CaracteristicasProdutoResponse> caracteristicas;
	private Set<String> linkImagens;
	private Set<String> perguntas;
	private Double mediaNotas;
	private Integer totalNotasDadas;
	private Set<OpiniaoResponse> opinioes;

	public DetelheProdutoResponse(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.quantidade = produto.getQuantidade();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicasProdutoResponse::new)
				.collect(Collectors.toSet());
		this.linkImagens = produto.mapiarImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapiarPerguntas(pergunta -> pergunta.getTitulo());
		Integer total = produto.getOpinioes().stream().map(opiniao -> opiniao.getNota()).reduce(0, Integer::sum);
		this.mediaNotas = (double) (total / produto.getOpinioes().size());
		this.totalNotasDadas = produto.getOpinioes().size();
		this.opinioes = produto.getOpinioes().stream().map(OpiniaoResponse::new).collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<CaracteristicasProdutoResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinkImagens() {
		return linkImagens;
	}

	public Set<String> getPerguntas() {
		return perguntas;
	}

	public Set<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotasDadas() {
		return totalNotasDadas;
	}
}
