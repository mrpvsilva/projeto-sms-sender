package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import Util.StatusEvento;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String status;

	@Column
	private String tipo;

	@Column
	private int numeroconvidados;

	@Column
	private String nome;

	@Column
	@Temporal(value = TemporalType.DATE)
	private Date datacriacao;

	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataevento;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "clienteseventos", joinColumns = { @JoinColumn(name = "idevento", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idcliente", referencedColumnName = "id") })
	private List<Cliente> clientes;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EventoItem> itens;

	public Evento() {
		setStatus(StatusEvento.ORCAMENTO.toString());
		this.datacriacao = Calendar.getInstance().getTime();
		
	}

	public void addCliente(Cliente cliente) {
		if (this.clientes == null)
			clientes = new ArrayList<Cliente>();
		this.clientes.add(cliente);
	}

	public void addItem(EventoItem eventoItem) {
		if (this.itens == null) {
			itens = new ArrayList<EventoItem>();
		}

		this.itens.add(eventoItem);
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
		return numeroconvidados;
	}

	public void setNumConvidados(int numConvidados) {
		this.numeroconvidados = numConvidados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return datacriacao;
	}

	public Date getDataEvento() {
		return dataevento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataevento = dataEvento;
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
