package Dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private BigDecimal valorcusto;
	@Column
	private BigDecimal valorcomercial;
	@Column
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name = "idtipoitem", referencedColumnName = "id")
	private TipoItem tipoItem;

	public Item() {
		this.ativo = true;
	}

	public Item(String nome, String descricao, BigDecimal valorCusto,
			BigDecimal valorComercial, boolean ativo, TipoItem tipoitem) {

		setNome(nome);
		setDescricao(descricao);
		setValorCusto(valorCusto);
		setValorComercial(valorComercial);
		setAtivo(ativo);
		setTipoitem(tipoitem);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public BigDecimal getValorCusto() {
		return valorcusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorcusto = valorCusto;
	}

	public BigDecimal getValorComercial() {
		return valorcomercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorcomercial = valorComercial;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoItem getTipoitem() {
		return tipoItem;
	}

	public void setTipoitem(TipoItem tipoitem) {
		this.tipoItem = tipoitem;
	}

}
