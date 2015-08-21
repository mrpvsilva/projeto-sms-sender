package Dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clienteseventos")
public class ClienteEvento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id")
	private Evento evento;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idcliente", referencedColumnName = "id")
	private Cliente cliente;
	@Column
	private int convidadosextras;

	public ClienteEvento() {

	}

	public ClienteEvento(Evento evento, Cliente cliente) {
		this.evento = evento;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getConvidadosextras() {
		return convidadosextras;
	}

	public void setConvidadosextras(int convidadosextras) {
		this.convidadosextras = convidadosextras;
	}

}
