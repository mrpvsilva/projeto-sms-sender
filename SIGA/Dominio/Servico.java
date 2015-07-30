package Dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicos")
public class Servico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private BigDecimal valorservico;	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoCobranca tipocobranca;
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

	public BigDecimal getValorServico() {
		return valorservico;
	}

	public void setValorServico(BigDecimal valorservico) {
		this.valorservico = valorservico;
	}

	public Fornecedor getExecutor() {
		return executor;
	}

	public void setExecutor(Fornecedor executor) {
		this.executor = executor;
	}

	public TipoCobranca getTipoCobranca() {
		return tipocobranca;
	}

	public void setTipoCobranca(TipoCobranca tipocobranca) {
		this.tipocobranca = tipocobranca;
	}
	
	

}
