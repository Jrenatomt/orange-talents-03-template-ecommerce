package com.renato.mercadolivre.finalizaCompra;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PedidoRequest {

	@NotNull @PositiveOrZero
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull @Enumerated(EnumType.STRING)
	private GatewayPagamento gateway;
	
	@Deprecated
	public PedidoRequest() {
	}

	public PedidoRequest(@NotNull @PositiveOrZero int quantidade, @NotNull Long idProduto, @NotNull GatewayPagamento gateway) {
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
