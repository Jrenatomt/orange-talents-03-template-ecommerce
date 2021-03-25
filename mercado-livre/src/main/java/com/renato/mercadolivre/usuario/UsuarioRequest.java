package com.renato.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		// a senha sera encodada nesse momento
		return new Usuario(login, new BCryptPasswordEncoder().encode(senha));
	}
}
