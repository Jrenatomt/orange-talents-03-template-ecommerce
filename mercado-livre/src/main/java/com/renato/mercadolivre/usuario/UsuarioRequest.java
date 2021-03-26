package com.renato.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.renato.mercadolivre.validation.UniqueValue;
import com.sun.istack.NotNull;

public class UsuarioRequest {
	
	@Email
	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank @NotNull @Size(min = 6)
	private String senha;
	
	@Deprecated
	public UsuarioRequest() {
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toModel() {
		// a senha sera encodada nesse momento
		return new Usuario(email, new BCryptPasswordEncoder().encode(senha));
	}
}
