package com.renato.mercadolivre.finalizaCompra;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroRequest implements RetornoGatewayPagamento {
	
	@NotBlank
	private String idTransacao;
	@Enumerated(EnumType.STRING) @NotNull
	private StatusRetornoPagseguro status;
	
	public PagseguroRequest(@NotBlank String idTransacao,
			StatusRetornoPagseguro status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}
    
	public String getIdTransacao() {
		return idTransacao;
	}


	public StatusRetornoPagseguro getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RetornoPagseguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Pedido pedido) {
		return new Transacao(status.normaliza(), idTransacao, pedido);
	}
}
