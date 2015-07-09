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

@Entity
@Table(name = "eventosservicos")
public class EventoServico implements Serializable {

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

	@ManyToOne
	@JoinColumn(name = "idservico", referencedColumnName = "id")
	private Servico servico;
	@Column
	private BigDecimal subtotal;

	public EventoServico() {

	}

	public EventoServico(Evento evento, Servico servico) {
		setEvento(evento);
		setServico(servico);
		calcularSubTotal();
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public BigDecimal getSubTotal() {
		return subtotal;
	}

	public void setSubTotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void atualizarSubTotal(int numeroClientes, int convidadosDoCliente) {

		evento.setNumeroClientes(numeroClientes);
		evento.setNumeroConvidadosCliente(convidadosDoCliente);
		calcularSubTotal();

	}

	private void calcularSubTotal() {
		subtotal = new BigDecimal(0);

		if (servico.getTipoCobranca() == TipoCobranca.CLIENTE) {
			subtotal = servico.getValorServico().multiply(
					new BigDecimal(evento.getNumeroClientes()));
		} else if (servico.getTipoCobranca() == TipoCobranca.CONVIDADO) {
			subtotal = servico.getValorServico().multiply(
					new BigDecimal(evento.getTotalConvidados()));
		} else {
			subtotal = servico.getValorServico();
		}

	}

}
