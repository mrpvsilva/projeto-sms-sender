package Dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="formapagamento")
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idformapagamento;
	@Column
	private String forma;
	@Column
	private boolean ativo;
	
	public FormaPagamento(){
		ativo = true;
	}

	public FormaPagamento(long idformapagamento, String forma, boolean ativo) {
		super();
		setIdformapagamento(idformapagamento);
		setForma(forma);
		setAtivo(ativo);
	}

	public long getIdformapagamento() {
		return idformapagamento;
	}

	public void setIdformapagamento(long idformapagamento) {
		this.idformapagamento = idformapagamento;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
