package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String nomecompleto;
	@Column
	private String email;
	@Column
	private String rg;
	@Column
	private String cpfcnpj;
	@Column
	private String nomeresponsavel;
	@Column
	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	@Column
	private String nomeGuerraMilitar;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Telefone.class)
	@JoinTable(name = "telefonesclientes", joinColumns = { @JoinColumn(name = "idcliente", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idtelefone", referencedColumnName = "id") })
	private List<Telefone> telefones;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "enderecosclientes", joinColumns = { @JoinColumn(name = "idcliente", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idendereco", referencedColumnName = "id") })
	private Endereco endereco;

	@OneToMany(mappedBy = "cliente")	
	private List<ClienteEvento> eventos;

	// @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch =
	// FetchType.EAGER)
	// private List<ClienteItemExtra> itensExtras;

	public Cliente() {

		setEndereco(new EnderecoCliente());

	}

	public Cliente(String nomeCompleto, String email, String rg,
			String cpfCnpj, String responsavel, Endereco endereco,
			String nomeGuerraMilitar) {

		setNomeCompleto(nomeCompleto);
		setEmail(email);
		setRg(rg);
		setCpfCnpj(cpfCnpj);
		setResponsavel(responsavel);
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
		return nomecompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomecompleto = nomeCompleto;
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
		return cpfcnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfcnpj = cpfCnpj;
	}

	public String getResponsavel() {
		return nomeresponsavel;
	}

	public void setResponsavel(String responsavel) {
		this.nomeresponsavel = responsavel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
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

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public List<ClienteEvento> getEventos() {
		return eventos;
	}

	public void setEventos(List<ClienteEvento> eventos) {
		this.eventos = eventos;
	}

	// public void addEvento(Evento evento) {
	// if (eventos == null) {
	// eventos = new ArrayList<Evento>();
	// }
	//
	// eventos.add(evento);
	// }
	//
	// public List<Evento> getEventos() {
	// return eventos;
	// }
	//
	// public void setEventos(List<Evento> eventos) {
	// this.eventos = eventos;
	// }
	//
	// public void addItemExtra(Item ItemExtra, Evento evento, int quantidade) {
	// if (itensExtras == null) {
	// itensExtras = new ArrayList<ClienteItemExtra>();
	// }
	//
	// ClienteItemExtra cie = new ClienteItemExtra(this, evento, ItemExtra,
	// quantidade);
	// itensExtras.add(cie);
	// }

	// public List<ClienteItemExtra> getItensExtras() {
	// return itensExtras;
	// }
	//
	// public void setItensExtras(List<ClienteItemExtra> itensExtras) {
	// this.itensExtras = itensExtras;
	// }

}
