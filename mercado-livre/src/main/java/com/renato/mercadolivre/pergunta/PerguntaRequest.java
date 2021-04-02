package com.renato.mercadolivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	public PerguntaRequest() {
	}

	@JsonCreator
	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario usuarioInteressado, @NotNull @Valid Produto produto) {
		return new Pergunta(titulo,usuarioInteressado,produto);
	}
}
