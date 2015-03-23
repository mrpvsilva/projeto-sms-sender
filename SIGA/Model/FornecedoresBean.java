package Model;

import java.util.ArrayList;
import java.util.List;

import Dominio.Telefone;

public class FornecedoresBean {

	private String nomeforn;
	private String cnpjforn;
	private String rgforn;

	private String emailforn;
	private String endforn;
	private String siteforn;
	private String tiposervprestadoforn;
	private String forn;
	private String bairro;
	private String cep;
	private List<Telefone> telefones;

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNomeforn() {
		return nomeforn;
	}

	public void setNomeforn(String nomeforn) {
		this.nomeforn = nomeforn;
	}

	public String getCnpjforn() {
		return cnpjforn;
	}

	public void setCnpjforn(String cnpjforn) {
		this.cnpjforn = cnpjforn;
	}

	public String getRgforn() {
		return rgforn;
	}

	public void setRgforn(String rgforn) {
		this.rgforn = rgforn;
	}

	public String getEmailforn() {
		return emailforn;
	}

	public void setEmailforn(String emailforn) {
		this.emailforn = emailforn;
	}

	public String getEndforn() {
		return endforn;
	}

	public void setEndforn(String endforn) {
		this.endforn = endforn;
	}

	public String getSiteforn() {
		return siteforn;
	}

	public void setSiteforn(String siteforn) {
		this.siteforn = siteforn;
	}

	public String getTiposervprestadoforn() {
		return tiposervprestadoforn;
	}

	public void setTiposervprestadoforn(String tiposervprestadoforn) {
		this.tiposervprestadoforn = tiposervprestadoforn;
	}

	public String getForn() {
		return forn;
	}

	public void setForn(String forn) {
		this.forn = forn;
	}

	public void AddTelefone(Telefone telefone) {
		if (telefones == null) {
			telefones = new ArrayList<Telefone>();
		}

		telefones.add(telefone);
	}

}
