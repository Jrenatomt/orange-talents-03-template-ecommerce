package com.renato.mercadolivre.produto;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.renato.mercadolivre.categoria.CategoriaRepository;
import com.renato.mercadolivre.security.AutenticacaoService;
import com.renato.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	private final AutenticacaoService autenticacaoService;
	private final UploaderFake uploaderFake;

	public ProdutoController(ProdutoRepository produtoRepository, 
			CategoriaRepository categoriaRepository,
			AutenticacaoService autenticacaoService, 
			UploaderFake uploaderFake) {

		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.autenticacaoService = autenticacaoService;
		this.uploaderFake = uploaderFake;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request) {
		Usuario usuario = autenticacaoService.authenticated();
		Produto novoProduto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(novoProduto);
		return ResponseEntity.ok().build();
	} 

	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> criaImagem(@PathVariable("id") Long id, @Valid ImagemRequest request) {

		Usuario dono = autenticacaoService.authenticated();
		Produto produto = produtoRepository.findById(id).get();

		if (!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);

		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}
}
