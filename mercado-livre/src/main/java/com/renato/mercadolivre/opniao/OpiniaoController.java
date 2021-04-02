package com.renato.mercadolivre.opniao;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.produto.ProdutoRepository;
import com.renato.mercadolivre.security.AutenticacaoService;
import com.renato.mercadolivre.usuario.Usuario;
import com.renato.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos")
public class OpiniaoController {
	
	private final OpiniaoRepository opiniaoRepository;
	private final ProdutoRepository produtoRepository;
	private final AutenticacaoService autenticacaoService;

	public OpiniaoController(OpiniaoRepository opiniaoRepository,
			UsuarioRepository usuarioRepository,
			ProdutoRepository produtoRepository,
			AutenticacaoService autenticacaoService) {
		this.opiniaoRepository = opiniaoRepository;
		this.produtoRepository = produtoRepository;
		this.autenticacaoService = autenticacaoService;
	}

	@PostMapping(value = "/{idProduto}/opiniao")
	@Transactional
	public String criaOpiniao(@PathVariable("idProduto") Long idProduto, @RequestBody @Valid OpiniaoRequest request) {
		Usuario usuario = autenticacaoService.authenticated();
		Produto produto = produtoRepository.findById(idProduto).get();
		
		Opiniao novaOpiniao = request.toModel(usuario,produto);
		opiniaoRepository.save(novaOpiniao);
		return novaOpiniao.toString();
	}
}
