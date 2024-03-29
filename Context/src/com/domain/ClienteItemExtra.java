package com.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientesitensextras")
public class ClienteItemExtra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "idcliente", referencedColumnName = "id")
	private Cliente cliente;
	@Id
	@ManyToOne
	@JoinColumn(name = "iditem", referencedColumnName = "id")
	private Item item;
	@Id
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id")
	private Evento evento;

	private int quantidade;

	public ClienteItemExtra() {
		// TODO Auto-generated constructor stub
	}

	public ClienteItemExtra(Cliente cliente, Evento evento, Item item,
			int quantidade) {
		setCliente(cliente);
		setItem(item);
		setEvento(evento);
		setQuantidade(quantidade);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
