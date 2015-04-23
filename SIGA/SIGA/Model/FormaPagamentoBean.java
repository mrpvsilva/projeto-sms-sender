package Model;

public class FormaPagamentoBean {

	private long idformapagamento;
	private String forma;
	private boolean ativo;
	
	public long getIdformapagamento() {
		return idformapagamento;
	}
	public void setIdformapagamento(long idformapagamento) {
		this.idformapagamento = idformapagamento;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
