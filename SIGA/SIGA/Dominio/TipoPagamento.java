package Dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipopagamento")
public class TipoPagamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idtipopagamento;
	@Column
	private String descricao;
	@Column
	private boolean ativo;
	
	public TipoPagamento() {

		this.ativo =true;
	}
	
	public TipoPagamento(String descricao,boolean ativo){
		setDescricao(descricao);
		setAtivo(ativo);
	}

	public long getIdtipopagamento() {
		return idtipopagamento;
	}

	public void setIdtipopagamento(long idtipopagamento) {
		this.idtipopagamento = idtipopagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
