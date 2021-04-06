package com.renato.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.renato.mercadolivre.categoria.Categoria;
import com.renato.mercadolivre.categoria.CategoriaRepository;
import com.renato.mercadolivre.usuario.Usuario;
import com.renato.mercadolivre.validation.ExistsId;

public class ProdutoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal preco;
	@NotNull @PositiveOrZero
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
    @Size(min = 3) @Valid
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
    
	@Deprecated
	public ProdutoRequest() {
	}

	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @PositiveOrZero int quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Long idCategoria, @Size(min = 3) @Valid List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario dono) {
		Categoria categoria = categoriaRepository.getOne(idCategoria);
		return new Produto(nome, preco, quantidade, descricao, categoria, dono, caracteristicas) ;
	}
}
