package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nomeCompleto;
	@Column
	private String email;
	@Column
	private String rg;
	@Column
	private String cpfCnpj;
	@Column
	private String responsavel;
	@Column
	private String tipo;
	@Column
	private int convidadosExtras;
	@Column
	private String endereco;
	@Column
	private String nomeGuerraMilitar;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "telefonesclientes", joinColumns = { @JoinColumn(name = "idcliente", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idtelefone", referencedColumnName = "id") })
	private List<Telefone> telefones;

	@ManyToMany(mappedBy = "clientes")
	private List<Evento> eventos;

	@OneToMany(cascade = CascadeType.ALL)	
	private List<ClienteItemExtra> itensExtras;

	public Cliente() {

	}

	public Cliente(String nomeCompleto, String email, String rg,
			String cpfCnpj, String responsavel, String tipo,
			int convidadosExtras, String endereco, String nomeGuerraMilitar) {

		setNomeCompleto(nomeCompleto);
		setEmail(email);
		setRg(rg);
		setCpfCnpj(cpfCnpj);
		setResponsavel(responsavel);
		setTipo(tipo);
		setConvidadosExtras(convidadosExtras);
		setEndereco(endereco);
		setNomeGuerraMilitar(nomeGuerraMilitar);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getConvidadosExtras() {
		return convidadosExtras;
	}

	public void setConvidadosExtras(int convidadosExtras) {
		this.convidadosExtras = convidadosExtras;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeGuerraMilitar() {
		return nomeGuerraMilitar;
	}

	public void setNomeGuerraMilitar(String nomeGuerraMilitar) {
		this.nomeGuerraMilitar = nomeGuerraMilitar;
	}

	public void addTelefone(Telefone telefone) {
		if (telefones == null) {
			telefones = new ArrayList<Telefone>();
		}

		telefones.add(telefone);
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void addEvento(Evento evento) {
		if (eventos == null) {
			eventos = new ArrayList<Evento>();
		}

		eventos.add(evento);
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void addItemExtra(Item ItemExtra, int quantidade) {
		if (itensExtras == null) {
			itensExtras = new ArrayList<ClienteItemExtra>();
		}

		ClienteItemExtra cie = new ClienteItemExtra(this, ItemExtra, quantidade);
		itensExtras.add(cie);
	}

	public List<ClienteItemExtra> getItensExtras() {
		return itensExtras;
	}

	public void setItensExtras(List<ClienteItemExtra> itensExtras) {
		this.itensExtras = itensExtras;
	}

}
