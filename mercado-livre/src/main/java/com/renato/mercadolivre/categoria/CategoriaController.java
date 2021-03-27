package com.renato.mercadolivre.categoria;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	private final CategoriaRepository repository;
	
	public CategoriaController(CategoriaRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public Long cadastrar(@RequestBody @Valid CategoriaRequest request) {
		Categoria novaCategoria = request.toModel(repository);
		repository.save(novaCategoria);
		return novaCategoria.getId();
	}
}
