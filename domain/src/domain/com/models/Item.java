package domain.com.models;

import java.util.List;

public class Item {

	private long iditem;
	private String nomeitem;
	private String descricaoitem;
	private double valoritem;
	private boolean ativo;
	private TipoItem tipoitem;
	private List<Evento> eventos;

	public long getIditem() {
		return iditem;
	}

	public void setIditem(long iditem) {
		this.iditem = iditem;
	}

	public String getNomeitem() {
		return nomeitem;
	}

	public void setNomeitem(String nomeitem) {
		this.nomeitem = nomeitem;
	}

	public String getDescricaoitem() {
		return descricaoitem;
	}

	public void setDescricaoitem(String descricaoitem) {
		this.descricaoitem = descricaoitem;
	}

	public double getValoritem() {
		return valoritem;
	}

	public void setValoritem(double valoritem) {
		this.valoritem = valoritem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoItem getTipoitem() {
		return tipoitem;
	}

	public void setTipoitem(TipoItem tipoitem) {
		this.tipoitem = tipoitem;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
