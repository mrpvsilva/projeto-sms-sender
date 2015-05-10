package com.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.interfaces.Validate;

@Entity
@Table(name = "vendas")
public class Venda implements Validate {

	@Id
	@GeneratedValue
	private long vendaId;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datahoravenda;
	@Column
	private BigDecimal valortotal;
	@Column
	private BigDecimal desconto;
	private BigDecimal valorRecebido;
	private BigDecimal troco;
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProdutoVendido> produtosvendidos;

	public Venda() {
		produtosvendidos = new ArrayList<ProdutoVendido>();
		this.valortotal = new BigDecimal(0.00);
		this.desconto = new BigDecimal(0.00);
		datahoravenda = Calendar.getInstance();
	}

	public long getVendaId() {
		return vendaId;
	}

	public void setVendaId(long vendaId) {
		this.vendaId = vendaId;
	}

	public Calendar getDatahoravenda() {
		return datahoravenda;
	}

	public void setDatahoravenda(Calendar datahoravenda) {
		this.datahoravenda = datahoravenda;
	}

	public BigDecimal getValortotal() {

		return this.valortotal.subtract(this.desconto);
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public List<ProdutoVendido> getProdutosvendidos() {
		return produtosvendidos;
	}

	public void setProdutosvendidos(List<ProdutoVendido> produtosvendidos) {
		this.produtosvendidos = produtosvendidos;
	}

	public void addProduto(Produto produto, int quantidade) {
		ProdutoVendido pv = new ProdutoVendido(this, produto, quantidade);
		produtosvendidos.add(pv);
		this.valortotal = this.valortotal.add(pv.getSubTotal());

	}

	public void removerProduto(int index) {
		this.valortotal = new BigDecimal(0);
		produtosvendidos.remove(index);

		for (int i = 0; i < produtosvendidos.size(); i++) {
			this.valortotal = this.valortotal.add(produtosvendidos.get(i)
					.getSubTotal());
		}
	}

	@Override
	public String valid() {
		return "";
	}

}
