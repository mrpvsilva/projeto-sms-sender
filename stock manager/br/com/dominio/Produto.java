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
	private BigDecimal custoUnitario;
	@Column
	private BigDecimal valorUnitario;
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
		return custoUnitario;
	}

	public void setCusto(BigDecimal custounitario) {
		this.custoUnitario = custounitario;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorunitario) {
		this.valorUnitario = valorunitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void EntrarEstoque(int quantidade) {
		this.quantidade = this.quantidade + quantidade;
	}

	@Override
	public String valid() {
		if (this.nome.isEmpty())
			return "Nome é obrigatório";
		else if (this.custoUnitario == null)
			return "Custo é obrigatório";
		else if (this.valorUnitario == null)
			return "Valor é obrigatório";
		else if (this.quantidade == 0)
			return "Quantidade é obrigatório";

		return "";
	}

}
