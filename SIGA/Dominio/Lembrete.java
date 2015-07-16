package Dominio;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lembretes")
public class Lembrete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String texto;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahora;
	@Column
	private String assunto;
	@ManyToOne
	@JoinColumn(name = "idremetente", referencedColumnName = "id")
	private Usuario remetente;
	@ManyToOne
	@JoinColumn(name = "iddestinatario", referencedColumnName = "id")
	private Usuario destinatario;
	@Column
	private boolean lido;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahoraleitura;

	public Lembrete() {
		lido = false;
	}

	public Lembrete(int id, String texto, Date datahora, String assunto,
			Usuario remetente, Usuario destinatario) {

		setId(id);
		setTexto(texto);
		setDatahora(datahora);
		setAssunto(assunto);
		setRemetente(remetente);
		setDestinatario(destinatario);
		setLido(false);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public boolean isLido() {
		return lido;
	}

	public void setLido(boolean lido) {
		this.lido = lido;
	}

	public Date getDatahoraleitura() {
		return datahoraleitura;
	}

	public void setDatahoraleitura(Date datahoraleitura) {
		this.datahoraleitura = datahoraleitura;
	}

	public void marcarComoLido() {
		if (!lido) {
			lido = true;
			datahoraleitura = Calendar.getInstance().getTime();
		}
	}

	public void marcarComoNaoLido() {
		if (lido) {
			lido = false;
			datahoraleitura = null;
		}
	}

	public void adiarLembrete(int minutos) {
		if (minutos <= 45) {
			Calendar d = Calendar.getInstance();
			d.setTime(datahora);
			d.add(Calendar.MINUTE, minutos);			
			datahora = d.getTime();
		}
	}

}
