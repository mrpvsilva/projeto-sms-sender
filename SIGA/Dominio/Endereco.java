package Dominio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Extra.Extras;

@Entity(name = "Endereco_SINGLE_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ENDERECO")
@Table(name = "enderecos")
public abstract class Endereco implements Validate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String endereco;
	@Column
	private String bairro;
	@Column
	private String cep;
	@Column
	private String complemento;
	@Column
	private String cidade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public ModelState modelState() {

		if (endereco == null || endereco.isEmpty())
			return new ModelState("O campo Endereço é obrigatório");
		if (bairro == null || bairro.isEmpty())
			return new ModelState("O campo Bairro é obrigatório");
		if (cep == null || cep.isEmpty())
			return new ModelState("O campo CEP é obrigatório");
		if (!Extras.isValidaCep(cep))
			return new ModelState("O campo CEP é inválido");
		if (cidade == null || cidade.isEmpty())
			return new ModelState("O campo Cidade é obrigatório");

		return new ModelState();
	}

}
