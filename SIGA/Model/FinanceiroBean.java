package Model;

import java.util.Date;

public class FinanceiroBean {

	/*formas de pagamentos por cliente*/
	
	private String tipopagamentofin;
	private Double vlrpagamentofin;
	private String formapagamentofin;
	private Date dtvencimentofin;
	private Date dtemissaofin;
	private int qtdparcelas;
	private String numerodocumento;
	public String getTipopagamentofin() {
		return tipopagamentofin;
	}
	public void setTipopagamentofin(String tipopagamentofin) {
		this.tipopagamentofin = tipopagamentofin;
	}
	public Double getVlrpagamentofin() {
		return vlrpagamentofin;
	}
	public void setVlrpagamentofin(Double vlrpagamentofin) {
		this.vlrpagamentofin = vlrpagamentofin;
	}
	public String getFormapagamentofin() {
		return formapagamentofin;
	}
	public void setFormapagamentofin(String formapagamentofin) {
		this.formapagamentofin = formapagamentofin;
	}
	public Date getDtvencimentofin() {
		return dtvencimentofin;
	}
	public void setDtvencimentofin(Date dtvencimentofin) {
		this.dtvencimentofin = dtvencimentofin;
	}
	public Date getDtemissaofin() {
		return dtemissaofin;
	}
	public void setDtemissaofin(Date dtemissaofin) {
		this.dtemissaofin = dtemissaofin;
	}
	public int getQtdparcelas() {
		return qtdparcelas;
	}
	public void setQtdparcelas(int qtdparcelas) {
		this.qtdparcelas = qtdparcelas;
	}
	public String getNumerodocumento() {
		return numerodocumento;
	}
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
		
	/*sincronização de pagamentos*/
	
	
}
