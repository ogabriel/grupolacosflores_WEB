package br.com.lacosflores.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("floricultura")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numero;

	// @Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern="dd-MM-yyyy")
	// private Date pedidoData;
	private String pedidoData;
	private String status;
	private String observacao;
	private Integer quantidade;
	private Double valorTotal;

	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Item> itens;

	@ManyToOne
	@JoinColumn(name = "floricultura_id")
	private Floricultura floricultura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPedidoData() {
		return pedidoData;
	}

	public void setPedidoData(String pedidoData) {
		this.pedidoData = pedidoData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> items) {
		this.itens = items;
	}

	public Floricultura getFloricultura() {
		return floricultura;
	}

	public void setFloricultura(Floricultura floricultura) {
		this.floricultura = floricultura;
	}

}
