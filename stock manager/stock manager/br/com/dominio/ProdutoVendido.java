package com.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.interfaces.Validate;

@Entity
@Table(name = "produtosvendidos")
public class ProdutoVendido implements Serializable, Validate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long produtovendidoId;
	@Column
	private int quantidade;
	@Column
	private BigDecimal total;
	@ManyToOne
	@JoinColumn(name = "produtoId")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "vendaId")
	private Venda venda;

	public ProdutoVendido() {
		total = new BigDecimal(0.00);
	}

	public ProdutoVendido(Venda venda, Produto produto, int quantidade) {

		setVenda(venda);
		setProduto(produto);
		setQuantidade(quantidade);
		BigDecimal qtd = new BigDecimal(this.quantidade);
		total = produto.getValor().multiply(qtd);
		setTotal(total);
	}

	public long getProdutovendidoId() {
		return produtovendidoId;
	}

	public void setProdutovendidoId(long produtovendidoId) {
		this.produtovendidoId = produtovendidoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String valid() {

		return "";
	}

}
