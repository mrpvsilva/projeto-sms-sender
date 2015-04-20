package Dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissoes")
public class Permissao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Id
	@ManyToOne
	@JoinColumn(name = "idperfil", referencedColumnName = "id")
	private Perfil perfil;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idformulario", referencedColumnName = "id")
	private Formulario formulario;
	@Column
	private boolean visualizar;
	@Column
	private boolean cadastrar;
	@Column
	private boolean alterar;
	@Column
	private boolean excluir;
	@Column
	private boolean imprimir;

	public Permissao() {
		setVisualizar(false);
		setCadastrar(false);
		setAlterar(false);
		setExcluir(false);
		setImprimir(false);
	}

	public Permissao(Perfil perfil) {
		setPerfil(perfil);
	}

	public Permissao(Formulario formulario) {
		setFormulario(formulario);
	}

	public Permissao(Formulario formulario, Perfil perfil) {
		setFormulario(formulario);
		setPerfil(perfil);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public boolean isImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

}
