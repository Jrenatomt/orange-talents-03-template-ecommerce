package com.renato.mercadolivre.produto;



import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.mercadolivre.categoria.CategoriaRepository;
import com.renato.mercadolivre.security.AutenticacaoService;
import com.renato.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	private final AutenticacaoService autenticacaoService;
	
	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, AutenticacaoService autenticacaoService) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.autenticacaoService = autenticacaoService;
	}

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid ProdutoRequest request) {
		Usuario usuario = autenticacaoService.authenticated();
		Produto novoProduto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(novoProduto);
		return novoProduto.toString();
	}
}
