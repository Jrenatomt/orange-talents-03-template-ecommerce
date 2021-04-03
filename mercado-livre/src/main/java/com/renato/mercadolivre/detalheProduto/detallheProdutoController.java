package com.renato.mercadolivre.detalheProduto;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.produto.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class detallheProdutoController {
	
	 private final ProdutoRepository produtoRepository;
	 
	public detallheProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping(value = "/{idProduto}")
	public ResponseEntity<DetelheProdutoResponse> detalhe(@PathVariable("idProduto") Long idProduto){
		Optional<Produto> produto = produtoRepository.findById(idProduto);
		if(produto.isPresent()) {
			return ResponseEntity.ok().body(new DetelheProdutoResponse(produto.get()));
		}
		return ResponseEntity.notFound().build();	
	}
}
