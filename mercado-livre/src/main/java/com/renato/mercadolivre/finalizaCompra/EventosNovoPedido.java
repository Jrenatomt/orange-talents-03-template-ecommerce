package com.renato.mercadolivre.finalizaCompra;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.mercadolivre.email.Emails;

@Service
public class EventosNovoPedido {

	@Autowired
	private Set<EventoPedidoSucesso> eventosPedidoSucesso;
	@Autowired
	Emails email;

	public void processa(Pedido pedido) {

		if (pedido.processadaComSucesso()) {
			eventosPedidoSucesso.forEach(evento -> evento.processa(pedido));
			email.novaPedido(pedido);
		}
		else {
			email.erroCompra(pedido);
		}
	}
}
