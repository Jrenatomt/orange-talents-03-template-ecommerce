package com.renato.mercadolivre.usuario;

public class UsuarioResponse {
	
	private Long id;
	private String login;
	
	public UsuarioResponse(Usuario novoUsuario) {
		this.id = novoUsuario.getId();
		this.login = novoUsuario.getLogin();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}
	
}
