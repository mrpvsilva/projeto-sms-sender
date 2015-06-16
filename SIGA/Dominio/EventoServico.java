package Dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventosservicos")
public class EventoServico {

	@Id
	private Evento evento;
	@Id
	private Servico servico;
	@Column
	private BigDecimal valorservico;
	
	public EventoServico( Evento evento, Servico servico) {
		setEvento(evento);
		setServico(servico);
		setValorservico(servico.getValorservico());
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public BigDecimal getValorservico() {
		return valorservico;
	}

	public void setValorservico(BigDecimal valorservico) {
		this.valorservico = valorservico;
	}

}
