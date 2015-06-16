package Dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private long id;
	@Column
	private String texto;
	@Column
	@Temporal(TemporalType.DATE)
	private Date datahora;
	@Column
	private String assunto;
	@ManyToOne
	@JoinColumn(name = "idremetente", referencedColumnName = "id")
	private Usuario remetente;
	@ManyToOne
	@JoinColumn(name = "iddestinatario", referencedColumnName = "id")
	private Usuario destinatario;

	public Lembrete() {

	}

	public Lembrete(int id, String texto, Date datahora, String assunto,
			Usuario remetente, Usuario destinatario) {

		setId(id);
		setTexto(texto);
		setDatahora(datahora);
		setAssunto(assunto);
		setRemetente(remetente);
		setDestinatario(destinatario);

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

}
