package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Enumerated(EnumType.STRING)
	private StatusEvento status;

	@Column
	@Enumerated(EnumType.STRING)
	private TiposEvento tipo;

	@Column
	private int numeroconvidadoscliente;

	@Column
	private int numeroparcelas;

	@Column
	private int numeroclientes;

	@Column
	private String nome;

	@Column
	@Temporal(value = TemporalType.DATE)
	private Date datacriacao;

	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataevento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "evento")
	private List<ClienteEvento> clientes;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EventoItem> itens;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EventoServico> servicos;

	public Evento() {
		setStatus(status.ORCAMENTO);
		setTipo(TiposEvento.SELECIONE);
		setNumeroClientes(1);
		setNumeroConvidadosCliente(0);
		setNumeroParcelas(1);
		setClientes(new ArrayList<ClienteEvento>());
		this.datacriacao = Calendar.getInstance().getTime();
		this.dataevento = Calendar.getInstance().getTime();
	}

	public Evento(StatusEvento status) {
		setStatus(status);
		setTipo(TiposEvento.SELECIONE);
		setNumeroClientes(1);
		setNumeroConvidadosCliente(0);
		setNumeroParcelas(1);
		setClientes(new ArrayList<ClienteEvento>());
		this.datacriacao = Calendar.getInstance().getTime();
		this.dataevento = Calendar.getInstance().getTime();
	}

	public void addServico(EventoServico eventoServico) {
		if (servicos == null)
			servicos = new ArrayList<EventoServico>();

		servicos.add(eventoServico);
	}

	public List<EventoServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<EventoServico> servicos) {
		this.servicos = servicos;
	}

	public void addCliente(Cliente cliente) {
		if (this.clientes == null)
			clientes = new ArrayList<ClienteEvento>();

		this.clientes.add(new ClienteEvento(this, cliente));
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

	public StatusEvento getStatus() {
		return status;
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public TiposEvento getTipo() {
		return tipo;
	}

	public void setTipo(TiposEvento tipo) {
		this.tipo = tipo;
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

	public List<ClienteEvento> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteEvento> clientes) {
		this.clientes = clientes;
	}

	public List<EventoItem> getItens() {
		return itens;
	}

	public void setItens(List<EventoItem> itens) {
		this.itens = itens;
	}

	public int getNumeroConvidadosCliente() {
		return numeroconvidadoscliente;
	}

	public void setNumeroConvidadosCliente(int numeroconvidadoscliente) {
		this.numeroconvidadoscliente = numeroconvidadoscliente;
	}

	public int getNumeroParcelas() {
		return numeroparcelas;
	}

	public void setNumeroParcelas(int numeroparcelas) {
		this.numeroparcelas = numeroparcelas;
	}

	public int getNumeroClientes() {
		return numeroclientes;
	}

	public void setNumeroClientes(int numeroclientes) {
		this.numeroclientes = numeroclientes;
	}

	public int getTotalConvidados() {
		return numeroclientes * numeroconvidadoscliente + numeroclientes;
	}

}
