package com.renato.mercadolivre.finalizaCompra;

import javax.validation.Valid;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.produto.ProdutoRepository;
import com.renato.mercadolivre.usuario.Usuario;
import com.renato.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping(value = "/pedidos")
public class FinalizaCompraController {
	
	private PedidoRepository pedidoRepository;
	private ProdutoRepository produtoRepository;
	private UsuarioRepository usuarioRepository;
	
	public FinalizaCompraController(UsuarioRepository usuarioRepository
			, ProdutoRepository produtoRepository, 
			PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public String criaPedido(@RequestBody @Valid PedidoRequest request,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

		Produto produtoComprado = produtoRepository.findById(request.getIdProduto()).get();

		int quantidade = request.getQuantidade();
		boolean abateu = produtoComprado.abataEstoque(quantidade);

		if (abateu) {
			Usuario comprador = usuarioRepository.findByEmail("visitante1@email.com");
			GatewayPagamento gateway = request.getGateway();

			Pedido novoPedido = new Pedido(produtoComprado, quantidade,comprador, gateway);
			pedidoRepository.save(novoPedido);
			return novoPedido.urlRedirecionamento(uriComponentsBuilder);
		}

		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null,
				"Não foi possível realizar a compra por conta do estoque");
		throw problemaComEstoque;
	}
}
