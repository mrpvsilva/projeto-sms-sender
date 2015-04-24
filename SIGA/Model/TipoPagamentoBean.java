package Model;

public class TipoPagamentoBean {

	private long idtipopagamento;
	private String descricao;
	private boolean ativo;
	
	public long getIdtipopagamento() {
		return idtipopagamento;
	}
	public void setIdtipopagamento(long idtipopagamento) {
		this.idtipopagamento = idtipopagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
