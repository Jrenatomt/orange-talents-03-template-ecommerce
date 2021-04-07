package com.renato.mercadolivre.finalizaCompra;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessaRetornoCompraController {
	
	private final PedidoRepository pedidoRepository;
	private final EventosNovoPedido eventosNovoPedido;
	
	public ProcessaRetornoCompraController(PedidoRepository pedidoRepository,
			EventosNovoPedido eventosNovoPedido) {
		this.pedidoRepository = pedidoRepository;
		this.eventosNovoPedido = eventosNovoPedido;
	}

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idPedido, @Valid  PagseguroRequest request) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		pedido.adicionaTransacao(request);		
		pedidoRepository.save(pedido);
		eventosNovoPedido.processa(pedido);
		return pedido.toString();
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idPedido, @Valid PaypalRequest request) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		pedido.adicionaTransacao(request);		
		pedidoRepository.save(pedido);
		eventosNovoPedido.processa(pedido);
		return pedido.toString();		
	}
}
