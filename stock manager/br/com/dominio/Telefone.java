package com.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.interfaces.Validate;

@Entity
@Table(name = "telefones")
public class Telefone implements Serializable, Validate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long telefoneId;
	@Column
	private String ddd;
	@Column
	private String numero;
	@Column
	private String operadora;

	@ManyToOne
	@JoinColumn(name = "fornecedorId")
	private Fornecedor fornecedor;

	public long getTelefoneId() {
		return telefoneId;
	}

	public void setTelefoneId(long telefoneId) {
		this.telefoneId = telefoneId;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return this.operadora + " (" + this.ddd + ")" + this.numero;
	}

	@Override
	public String valid() {
		if (this.ddd.isEmpty())
			return "DDD é obrigatório";
		else if (this.ddd.length() < 2)
			return "DDD está incorreto.";
		else if (this.numero.isEmpty())
			return "Número é obrigatório";
		else if (this.numero.length() < 8)
			return "Número está incorreto.";
		return "";
	}

}
