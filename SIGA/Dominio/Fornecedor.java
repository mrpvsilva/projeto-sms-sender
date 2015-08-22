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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Extra.Extras;

@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable, Validate {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idservico", referencedColumnName = "id")
	private Servico servico;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Telefone.class)
	@JoinTable(name = "telefonesfornecedores", joinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idtelefone", referencedColumnName = "id") })
	private List<Telefone> telefones;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "enderecosfornecedores", joinColumns = { @JoinColumn(name = "idfornecedor", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idendereco", referencedColumnName = "id") })
	private Endereco endereco;

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public Fornecedor(String nome, BigDecimal valorServico, String email,
			Endereco endereco, String cpfcnpj, String rg, String site,
			Servico tipoServico) {
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

	public Servico getTipoServico() {
		return servico;
	}

	public void setTipoServico(Servico servico) {
		this.servico = servico;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> list) {
		this.telefones = list;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addTelefone(TelefoneFornecedor telefone) {
		if (this.telefones == null) {
			this.telefones = new ArrayList<Telefone>();
		}

		this.telefones.add(telefone);
	}

	@Override
	public ModelState modelState() {

		if (nome == null || nome.isEmpty())
			return new ModelState("O campo Nome é obrigatório");
		if (cpfcnpj != null && !cpfcnpj.isEmpty()
				&& Extras.validarCPFCNPJ(cpfcnpj))
			return new ModelState("O campo CPF/CNPJ é inválido");
		if (servico == null)
			return new ModelState("O campo Serviço é obrigatório");
		if (valorservico == null || valorservico.equals(0))
			return new ModelState("O campo Valor do serviço é obrigatório");
		if (!endereco.modelState().isValid())
			return endereco.modelState();
		if (telefones.size() == 0)
			return new ModelState(
					"Fornecedor deve possuir ao menos um telefone");

		return new ModelState();
	}

}
