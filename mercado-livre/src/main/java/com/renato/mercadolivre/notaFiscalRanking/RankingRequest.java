package com.renato.mercadolivre.notaFiscalRanking;

import javax.validation.constraints.NotNull;

public class RankingRequest {

	@NotNull
	private Long idPedido;
	@NotNull
	private Long idDonoProduto;

	public RankingRequest(Long idPedido, Long idDonoProduto) {
		super();
		this.idPedido = idPedido;
		this.idDonoProduto = idDonoProduto;
	}

	@Override
	public String toString() {
		return "RankingRequest [idPedido=" + idPedido + ", idDonoProduto=" + idDonoProduto + "]";
	}
}
