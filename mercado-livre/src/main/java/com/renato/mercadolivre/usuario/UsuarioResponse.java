package com.renato.mercadolivre.usuario;

public class UsuarioResponse {
	
	private Long id;
	private String email;
	
	public UsuarioResponse(Usuario novoUsuario) {
		this.id = novoUsuario.getId();
		this.email = novoUsuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
}
