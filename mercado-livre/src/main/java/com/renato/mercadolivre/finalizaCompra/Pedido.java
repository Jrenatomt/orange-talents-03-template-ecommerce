package com.renato.mercadolivre.finalizaCompra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.usuario.Usuario;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne @NotNull @Valid
	private Produto produtoEscolhido;
	@Positive
	private int quantidade;
	@ManyToOne @NotNull @Valid
	private Usuario comprador;
	@Enumerated @NotNull
	private GatewayPagamento gatewayPagamento;

	@Deprecated
	public Pedido() {

	}

	public Pedido(@NotNull @Valid Produto produtoASerComprado,
			@Positive int quantidade, @NotNull @Valid Usuario comprador,
			GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}

	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
	}
}
