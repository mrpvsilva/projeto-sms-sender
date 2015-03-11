package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Column(name = "status")
	private String status;
	private String tipo;
	@Column(name = "numeroconvidados")
	private int numConvidados;
	private String Nome;
	@Column(name = "datacriacao")
	@Temporal(value = TemporalType.DATE)
	private Date dataCriacao;
	@Column(name = "dataevento")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataEvento;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "clienteseventos", joinColumns = { @JoinColumn(name = "idevento", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idcliente", referencedColumnName = "id") })
	private List<Cliente> clientes;
	@OneToMany(mappedBy = "evento",cascade=CascadeType.ALL)
	private List<EventoItem> itens;

	public Evento() {

	}

	public Evento(String status, String tipo, int numConvidados, String nome,
			Date dataCriacao, Date dataEvento) {
		setStatus(status);
		setTipo(tipo);
		setNumConvidados(numConvidados);
		setNome(nome);
		setDataCriacao(dataCriacao);
		setDataEvento(dataEvento);

	}

	public void addCliente(Cliente cliente) {
		if (this.clientes == null) {
			clientes = new ArrayList<Cliente>();
		}

		this.clientes.add(cliente);
	}

	public void addItem(Item item, int quantidade) {
		if (this.itens == null) {
			itens = new ArrayList<EventoItem>();
		}
		EventoItem evi = new EventoItem(this, item, quantidade);
		this.itens.add(evi);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNumConvidados() {
		return numConvidados;
	}

	public void setNumConvidados(int numConvidados) {
		this.numConvidados = numConvidados;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<EventoItem> getItens() {
		return itens;
	}

	public void setItens(List<EventoItem> itens) {
		this.itens = itens;
	}
}
