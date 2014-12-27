package com.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eventositens")
public class EventoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "idevento",referencedColumnName="id")
	private Evento evento;
	@Id
	@ManyToOne
	@JoinColumn(name = "iditem",referencedColumnName="id")
	private Item item;
	private int quantidade;

	public EventoItem() {
		
	}

	public EventoItem(Evento evento, Item item, int quantidade) {
		setEvento(evento);
		setItem(item);
		setQuantidade(quantidade);

	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
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

}
