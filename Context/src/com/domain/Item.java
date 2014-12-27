package com.domain;

import java.io.Serializable;

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
	private String nome;
	private String descricao;
	private double valorCusto;
	private double valorComercial;
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name="idtipoitem",referencedColumnName="id")
	private TipoItem tipoItem;

	public Item() {
		this.ativo = true;
	}

	public Item(String nome, String descricao, double valorCusto,
			double valorComercial, boolean ativo, TipoItem tipoitem) {

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

	public double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public double getValorComercial() {
		return valorComercial;
	}

	public void setValorComercial(double valorComercial) {
		this.valorComercial = valorComercial;
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
