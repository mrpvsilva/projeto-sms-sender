package Dominio;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nome;
	@Column
	private BigDecimal valorservico;
	@Column
	private String email;

	@Column
	private String cpfcnpj;
	@Column
	private String rg;
	@Column
	private String site;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idtiposervico", referencedColumnName = "id")
	private TipoServico tipoServico;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "telefonesfornecedores", joinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idtelefone", referencedColumnName = "id") })
	private List<TelefoneFornecedor> telefones;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "enderecosfornecedores", joinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idendereco", referencedColumnName = "id") })
	private Endereco endereco;

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public Fornecedor(String nome, BigDecimal valorServico, String email,
			Endereco endereco, String cpfcnpj, String rg, String site,
			TipoServico tipoServico) {
		setNome(nome);
		setValorServico(valorServico);
		setEmail(email);
		setEndereco(endereco);
		setCpfcnpj(cpfcnpj);
		setRg(rg);
		setSite(site);
		setTipoServico(tipoServico);
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

	public BigDecimal getValorServico() {
		return valorservico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorservico = valorServico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public List<TelefoneFornecedor> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneFornecedor> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addTelefone(TelefoneFornecedor telefone) {
		if (this.telefones == null) {
			this.telefones = new ArrayList<TelefoneFornecedor>();
		}

		this.telefones.add(telefone);
	}

}
