package com.renato.mercadolivre.usuario;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	private UsuarioRepository repository;

	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
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
