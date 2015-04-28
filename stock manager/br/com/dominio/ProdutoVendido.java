package com.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
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
	private long produtoVendidoId;
	@Column
	private int quantidade;
	@Column
	private BigDecimal subTotal;
	@ManyToOne
	@JoinColumn(name = "produtoId")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "vendaId")
	private Venda venda;

	public ProdutoVendido() {
		subTotal = new BigDecimal(0.00);
	}

	public ProdutoVendido(Venda venda, Produto produto, int quantidade) {

		setVenda(venda);
		setProduto(produto);
		setQuantidade(quantidade);
		BigDecimal qtd = new BigDecimal(this.quantidade);
		subTotal = produto.getValorUnitario().multiply(qtd);
		setSubTotal(subTotal);
	}

	public long getProdutovendidoId() {
		return produtoVendidoId;
	}

	public void setProdutovendidoId(long produtovendidoId) {
		this.produtoVendidoId = produtovendidoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subtotal) {
		this.subTotal = subtotal;
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
