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

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique = true, nullable = false)
	@NotBlank 
	private String email;
	@Column( nullable = false)
	@NotBlank @NotNull @Size(min = 6)
	private String senha;
	@PastOrPresent @NotNull
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime dataCadastro;
	
	@Deprecated
	public Usuario() {
	}
	
	public Usuario(@Email @NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	@PrePersist
	public void prePersist() {
		dataCadastro = LocalDateTime.now();
	}
}
