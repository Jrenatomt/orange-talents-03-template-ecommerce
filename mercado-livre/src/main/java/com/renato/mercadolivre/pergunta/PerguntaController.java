package com.renato.mercadolivre.pergunta;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.mercadolivre.email.Emails;
import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.produto.ProdutoRepository;
import com.renato.mercadolivre.security.AutenticacaoService;
import com.renato.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping(value = "/produtos")
public class PerguntaController {
	
	private final ProdutoRepository produtoRepository;
	private final AutenticacaoService autenticacaoService;
	private final PerguntaRepository perguntaRepository;
	private final Emails emails;
	
	public PerguntaController(ProdutoRepository produtoRepository,
			AutenticacaoService autenticacaoService,
			PerguntaRepository perguntaRepository, Emails emails) {
		this.produtoRepository = produtoRepository;
		this.autenticacaoService = autenticacaoService;
		this.perguntaRepository = perguntaRepository;
		this.emails = emails;
	}

	@PostMapping(value = "/{idProduto}/perguntas")
	@Transactional
	public ResponseEntity<?> criaPergunta(@PathVariable("idProduto") Long idProduto
			, @RequestBody @Valid PerguntaRequest request) {
		
		Usuario usuarioInteressado = autenticacaoService.authenticated();
		Produto produto = produtoRepository.findById(idProduto).get();
		
		Pergunta novaPergunta = request.toModel(usuarioInteressado, produto);
		perguntaRepository.save(novaPergunta);
		emails.novaPergunta(novaPergunta);
		return ResponseEntity.ok().build();
	}
}
