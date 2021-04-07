package com.renato.mercadolivre.finalizaCompra;

public interface RetornoGatewayPagamento {

	Transacao toTransacao(Pedido pedido);
}
