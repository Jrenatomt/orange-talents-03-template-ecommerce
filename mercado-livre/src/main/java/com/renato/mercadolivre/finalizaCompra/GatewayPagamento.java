package com.renato.mercadolivre.finalizaCompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
	
		pagseguro {
			@Override
			String criaUrlRetorno(Pedido pedido,
					UriComponentsBuilder uriComponentsBuilder) {
				String urlRetornoPagseguro = uriComponentsBuilder
						.path("/retorno-pagseguro/{id}")
						.buildAndExpand(pedido.getId()).toString();

				return "pagseguro.com/" + pedido.getId() + "?redirectUrl="
						+ urlRetornoPagseguro;
			}
		},
		paypal {
			@Override
			String criaUrlRetorno(Pedido pedido,
					UriComponentsBuilder uriComponentsBuilder) {
				String urlRetornoPaypal = uriComponentsBuilder
						.path("/retorno-paypal/{id}").buildAndExpand(pedido.getId())
						.toString();

				return "paypal.com/" + pedido.getId() + "?redirectUrl=" + urlRetornoPaypal;
			}
		};

		abstract String criaUrlRetorno(Pedido pedido,
				UriComponentsBuilder uriComponentsBuilder);
	}