package Dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "eventositens")
public class EventoItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id")
	private Evento evento;
	@ManyToOne
	@JoinColumn(name = "iditem", referencedColumnName = "id")
	private Item item;
	@Column
	private int quantidade;
	@Column
	private BigDecimal subtotal;

	public EventoItem() {

	}

	public EventoItem(Evento evento, Item item, int quantidade) {
		setEvento(evento);
		setItem(item);
		setQuantidade(quantidade);

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
		subtotal = this.item.getValorComercial().multiply(new BigDecimal(getQuantidade()));

	}

	public boolean isIncluso() {
		if (getId() > 0)
			return true;

		return false;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

}
