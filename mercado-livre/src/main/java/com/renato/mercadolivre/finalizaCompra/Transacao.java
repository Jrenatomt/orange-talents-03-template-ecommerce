package com.renato.mercadolivre.finalizaCompra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @Enumerated(EnumType.STRING)
	private StatusTransacao status;
	@NotBlank
	private  String idTransacaoGateway;
	@ManyToOne @NotNull @Valid
	private Pedido pedido;
	@NotNull
	private LocalDateTime instante;
	
	@Deprecated
	public Transacao() {
	}

	public Transacao(@NotNull StatusTransacao status,
			@NotBlank String idTransacaoGateway, @Valid Pedido pedido) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.pedido = pedido;
		this.instante = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public String getIdTransacaoGateway() {
		return idTransacaoGateway;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
	

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status + ", idTransacaoGateway=" + idTransacaoGateway
				+ ", instante=" + instante + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
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
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}	
}
