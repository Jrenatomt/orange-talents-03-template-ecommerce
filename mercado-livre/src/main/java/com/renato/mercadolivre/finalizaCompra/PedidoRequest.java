package com.renato.mercadolivre.finalizaCompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PedidoRequest {

	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	@Deprecated
	public PedidoRequest() {
	}

	public PedidoRequest(@Positive int quantidade, @NotNull Long idProduto, @NotNull GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
}
