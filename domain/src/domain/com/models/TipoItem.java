package domain.com.models;

public class TipoItem {

	private long idtipoitem;
	private String tipoitem;
	private boolean ativo;

	public long getIdtipoitem() {
		return idtipoitem;
	}

	public void setIdtipoitem(long idtipoitem) {
		this.idtipoitem = idtipoitem;
	}

	public String getTipoitem() {
		return tipoitem;
	}

	public void setTipoitem(String tipoitem) {
		this.tipoitem = tipoitem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
