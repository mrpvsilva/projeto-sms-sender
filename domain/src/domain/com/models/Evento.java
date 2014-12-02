package domain.com.models;

import java.util.Date;
import java.util.List;

public class Evento {
	private long idevento;
	private Date dataevento;
	private Date datacriacao;
	private FaseEvento fase;
	private List<Item> itens;
	private List<Cliente> clientes;
	private List<Fornecedor> fornecedores;

	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	public Date getDataevento() {
		return dataevento;
	}

	public void setDataevento(Date dataevento) {
		this.dataevento = dataevento;
	}

	public Date getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	public FaseEvento getFase() {
		return fase;
	}

	public void setFase(FaseEvento fase) {
		this.fase = fase;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
