package com.renato.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class UsuarioRequest {
	
	@Email
	@NotBlank @NotNull
	private String login;
	@NotBlank @NotNull @Size(min = 6)
	private String senha;
	
	@Deprecated
	public UsuarioRequest() {
	}
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toModel() {
		return new Usuario(login, senha);
	}
}
