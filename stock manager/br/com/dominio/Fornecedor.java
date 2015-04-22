package com.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.interfaces.Validate;

@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable, Validate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long fornecedorId;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String site;

	public long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String valid() {
		if (this.nome.isEmpty())
			return "campo nome é obrigatótio";

		return null;
	}

}
