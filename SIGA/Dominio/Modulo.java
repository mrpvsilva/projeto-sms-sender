package Dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "modulos")
public class Modulo {

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nome;
	@OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL)
	private List<Permissao> permissoes;

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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

	public void addPermissao(Permissao permissao) {
		if (permissoes == null)
			permissoes = new ArrayList<Permissao>();

		permissoes.add(permissao);
	}

}
