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
	private BigDecimal valorServico;
	@Column
	private String email;
	@Column
	private String endereco;
	@Column
	private String cpfcnpj;
	@Column
	private String rg;
	@Column
	private String site;
	@ManyToOne
	@JoinColumn(name = "idtiposervico", referencedColumnName = "id")
	private TipoServico tipoServico;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "telefonesfornecedores", joinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idtelefone", referencedColumnName = "id") })
	private List<Telefone> telefones;

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public Fornecedor(String nome, BigDecimal valorServico, String email,
			String endereco, String cpfcnpj, String rg, String site,
			TipoServico tipoServico) {
		setNome(nome);
		setValorservico(valorServico);
		setEmail(email);
		setEndereco(endereco);
		setCpfcnpj(cpfcnpj);
		setRg(rg);
		setSite(site);
		setTiposervico(tipoServico);
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

	public BigDecimal getValorservico() {
		return valorServico;
	}

	public void setValorservico(BigDecimal valorservico) {
		this.valorServico = valorservico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public TipoServico getTiposervico() {
		return tipoServico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tipoServico = tiposervico;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void addTelefoneFornecedor(Telefone telefone) {
		if (this.telefones == null) {
			this.telefones = new ArrayList<Telefone>();
		}

		this.telefones.add(telefone);
	}

}
