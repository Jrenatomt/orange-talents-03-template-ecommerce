package com.renato.mercadolivre.finalizaCompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class NotaFiscal implements EventoPedidoSucesso {

	@Override
	public void processa(Pedido pedido) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idPedido", pedido.getId(),
				"idComprador", pedido.getComprador().getId());		

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);
	}
}
