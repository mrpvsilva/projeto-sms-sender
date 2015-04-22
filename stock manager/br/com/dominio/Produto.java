package com.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.interfaces.Validate;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable, Validate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long produtoId;
	@Column
	private String nome;
	@Column
	private BigDecimal custo;
	@Column
	private BigDecimal valor;
	@Column
	private int quantidade;

	public long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(long produtoId) {
		this.produtoId = produtoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String valid() {
		if (this.nome.isEmpty())
			return "Nome é obrigatório";
		else if (this.custo == null)
			return "Custo é obrigatório";
		else if (this.valor == null)
			return "Valor é obrigatório";
		else if (this.quantidade == 0)
			return "Quantidade é obrigatório";

		return "";
	}

}
