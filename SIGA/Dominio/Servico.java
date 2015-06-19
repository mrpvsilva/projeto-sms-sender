package Dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servicos")
public class Servico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String nome;
	@Column
	private BigDecimal valorservico;
	@Column
	private boolean ativo;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "fornecedoresselecionados", joinColumns = { @JoinColumn(name = "idservico", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") })
	private Fornecedor executor;

	public Servico() {
		setAtivo(true);

	}

	public Servico(String nome, boolean ativo, boolean selecionado) {
		setNome(nome);
		setAtivo(ativo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getValorservico() {
		return valorservico;
	}

	public void setValorservico(BigDecimal valorservico) {
		this.valorservico = valorservico;
	}

	public Fornecedor getExecutor() {
		return executor;
	}

	public void setExecutor(Fornecedor executor) {
		this.executor = executor;
	}

}
