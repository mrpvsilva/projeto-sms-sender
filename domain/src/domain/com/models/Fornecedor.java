package domain.com.models;

import java.util.List;

public class Fornecedor {

	private long idfornecedor;
	private String nomefornecedor;
	private double cache;
	private String email;
	private String endereco;
	private List<Evento> eventos;

	public long getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(long idfornecedor) {
		this.idfornecedor = idfornecedor;
	}

	public String getNomefornecedor() {
		return nomefornecedor;
	}

	public void setNomefornecedor(String nomefornecedor) {
		this.nomefornecedor = nomefornecedor;
	}

	public double getCache() {
		return cache;
	}

	public void setCache(double cache) {
		this.cache = cache;
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

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
