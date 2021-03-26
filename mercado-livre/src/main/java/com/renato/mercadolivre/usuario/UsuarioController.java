package com.renato.mercadolivre.usuario;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.mercadolivre.validation.ProibeUsuarioComEmailDuplicado;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	private UsuarioRepository repository;
	private ProibeUsuarioComEmailDuplicado proibeUsuarioComEmailDuplicado;

	public UsuarioController(UsuarioRepository repository, ProibeUsuarioComEmailDuplicado proibeUsuarioComEmailDuplicado) {
		this.proibeUsuarioComEmailDuplicado = proibeUsuarioComEmailDuplicado;
		this.repository = repository;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeUsuarioComEmailDuplicado);
	}

	@PostMapping
	public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest request) {
		Usuario novoUsuario = request.toModel();
		repository.save(novoUsuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoUsuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioResponse(novoUsuario));
	}
}
