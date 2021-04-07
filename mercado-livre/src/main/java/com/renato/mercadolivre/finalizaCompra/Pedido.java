package com.renato.mercadolivre.finalizaCompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.usuario.Usuario;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING) @NotNull
	private StatusTransacao status = StatusTransacao.iniciada;
	@ManyToOne @NotNull @Valid
	private Produto produtoEscolhido;
	@PositiveOrZero
	private int quantidade;
	@ManyToOne @NotNull @Valid
	private Usuario comprador;
	@Enumerated(EnumType.STRING) @NotNull
	private GatewayPagamento gatewayPagamento;
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Pedido() {
	}

	public Pedido(@NotNull @Valid Produto produtoASerComprado, @PositiveOrZero int quantidade,
			@NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public StatusTransacao getStatus() {
		return status;
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

	public Usuario getDonoProduto() {
		return produtoEscolhido.getUsuario();
	}
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", status=" + status + ", produtoEscolhido=" + produtoEscolhido + ", quantidade="
				+ quantidade + ", comprador=" + comprador + ", gatewayPagamento=" + gatewayPagamento + ", transacoes="
				+ transacoes + "]";
	}

	public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);
		
		Assert.state(!this.transacoes.contains(novaTransacao),
				"Já existe uma transacao igual a essa processada "
						+ novaTransacao);

		Assert.state(transacoesConcluidasComSucesso().isEmpty(),
				"Esse compra já foi concluída com sucesso");
		this.transacoes.add(novaTransacao);
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
				.filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
				"tem mais de uma transacao concluida com sucesso nessa compra "+this.id);
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

}