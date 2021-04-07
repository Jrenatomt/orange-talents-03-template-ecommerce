package com.renato.mercadolivre.notaFiscalRanking;

import javax.validation.constraints.NotNull;

public class NFRequest {

	@NotNull
	private Long idPedido;
	@NotNull
	private Long idComprador;

	public NFRequest(Long idPedido, Long idComprador) {
		super();
		this.idPedido = idPedido;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "NFRequest [idPedido=" + idPedido + ", idComprador=" + idComprador + "]";
	}

	public Long getIdCompra() {
		return idPedido;
	}
	
	public Long getIdComprador() {
		return idComprador;
	}
}