package com.renato.mercadolivre.finalizaCompra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.mercadolivre.email.Emails;
import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.produto.ProdutoRepository;
import com.renato.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping(value = "/pedidos")
public class FinalizaCompraController {
	
	private PedidoRepository pedidoRepository;
	private ProdutoRepository produtoRepository;
	private Emails emails;

	public FinalizaCompraController(ProdutoRepository produtoRepository, 
			PedidoRepository pedidoRepository, Emails emails) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.emails = emails;
	}

	@PostMapping
	@Transactional
	public String criaPedido(@RequestBody @Valid PedidoRequest request, @AuthenticationPrincipal Usuario comprador,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

		Produto produtoComprado = produtoRepository.findById(request.getIdProduto()).get();

		int quantidade = request.getQuantidade();
		boolean abateu = produtoComprado.abataEstoque(quantidade);

		if (abateu) {
			GatewayPagamento gateway = request.getGateway();

			Pedido novoPedido = new Pedido(produtoComprado, quantidade, comprador, gateway);
			pedidoRepository.save(novoPedido);
			emails.novaPedido(novoPedido);
			return novoPedido.urlRedirecionamento(uriComponentsBuilder);
		}

		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null,
				"Não foi possível realizar a compra por conta do estoque");
		throw problemaComEstoque;
	}
}
