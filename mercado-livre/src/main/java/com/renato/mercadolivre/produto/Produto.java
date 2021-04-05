package com.renato.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.renato.mercadolivre.categoria.Categoria;
import com.renato.mercadolivre.opniao.Opiniao;
import com.renato.mercadolivre.pergunta.Pergunta;
import com.renato.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal preco;
	@NotNull
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@ManyToOne
	@Valid
	private Categoria categoria;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	@PastOrPresent
	private LocalDateTime dataCadastro = LocalDateTime.now();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@OrderBy("titulo asc")
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
	private Set<Pergunta> perguntas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<Opiniao> opinioes = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal preco, @NotNull @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull Usuario usuario, @Size(min = 3) @Valid Collection<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		Assert.isTrue(this.caracteristicas.size() >= 3,
				"Produto precisa ter no mínimo 3 ou mais características únicas");
	}

	public Long getId() {
		return id;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", usuario=" + usuario.getEmail()
				+ ", dataCadastro=" + dataCadastro + ", caracteristicas=" + caracteristicas + ", imagens=" + imagens
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.usuario.equals(possivelDono);
	}

	public <T> Set<T> mapiarImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapiarPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public boolean abataEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque " + quantidade);

		if (quantidade <= this.quantidade) {
			this.quantidade -= quantidade;
			return true;
		}
		return false;
	}
}
