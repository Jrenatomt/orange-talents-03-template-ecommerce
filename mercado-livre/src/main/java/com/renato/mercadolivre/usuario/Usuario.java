package com.renato.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique = true, nullable = false)
	@NotBlank @NotNull
	private String login;
	@Column( nullable = false)
	@NotBlank @NotNull @Size(min = 6)
	private String senha;
	@PastOrPresent @NotNull
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime dataCadastro;
	
	@Deprecated
	public Usuario() {
	}
	
	public Usuario(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	@PrePersist
	public void prePersist() {
		dataCadastro = LocalDateTime.now();
	}
}
