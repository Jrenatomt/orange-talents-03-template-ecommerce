package com.renato.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.renato.mercadolivre.validation.ExistsId;
import com.renato.mercadolivre.validation.UniqueValue;

public class CategoriaRequest {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaMaeId;
	
	@Deprecated
	public CategoriaRequest() {
	}
	
	@JsonCreator
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}
	

	public Categoria toModel(CategoriaRepository repository) {
		Categoria categoria = new Categoria(nome);
		if(categoriaMaeId != null) {
			Categoria categoriaMae = repository.getOne(categoriaMaeId);
			Assert.notNull(categoriaMae, "O id da categoria Mãe não pode ser nulo");
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}
}
