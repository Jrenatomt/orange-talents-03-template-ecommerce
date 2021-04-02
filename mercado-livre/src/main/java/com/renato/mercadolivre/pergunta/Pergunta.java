package com.renato.mercadolivre.pergunta;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.renato.mercadolivre.produto.Produto;
import com.renato.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	@ManyToOne @NotNull 
	private Usuario usuarioInteressado;
	@ManyToOne @NotNull 
	private Produto produto;
	private LocalDate instante;
	
	@Deprecated
	public Pergunta() {
	}

	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuarioInteressado, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuarioInteressado = usuarioInteressado;
		this.produto = produto;
		this.instante = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuarioInteressado() {
		return usuarioInteressado;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public LocalDate getInstante() {
		return instante;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuarioInteressado=" + usuarioInteressado + ", produto="
				+ produto + "]";
	}

	public Usuario getDonoProduto() {
		return produto.getUsuario();
	}
}
