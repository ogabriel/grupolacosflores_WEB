package br.com.lacosflores.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("floricultura")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numero;
	
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern="dd-MM-yyyy")
	//private Date pedidoData;
	private String pedidoData;
	private String status;
	private String observacao;
	private Integer quantidade;
	private Double valorTotal;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="pedido_item"
		, joinColumns={
			@JoinColumn(name="pedido_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="item_id")
			}
		)
	private List<Item> items;
	
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Floricultura getFloricultura() {
		return floricultura;
	}

	public void setFloricultura(Floricultura floricultura) {
		this.floricultura = floricultura;
	}
	
	

}
