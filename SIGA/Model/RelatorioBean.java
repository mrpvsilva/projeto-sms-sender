package Model;

import java.util.Date;

public class RelatorioBean {

	/*Formas de pagamentos por cliente*/
	
	private String nomeclirel;
	private Date dtpagcli;
	private String tipopagamentorel;
	private String statustitulorel;
	private String nomeeventorel;


	public String getTipopagamentorel() {
		return tipopagamentorel;
	}

	public void setTipopagamentorel(String tipopagamentorel) {
		this.tipopagamentorel = tipopagamentorel;
	}

	public String getStatustitulorel() {
		return statustitulorel;
	}

	public void setStatustitulorel(String statustitulorel) {
		this.statustitulorel = statustitulorel;
	}

	public String getNomeeventorel() {
		return nomeeventorel;
	}

	public void setNomeeventorel(String nomeeventorel) {
		this.nomeeventorel = nomeeventorel;
	}
	
	public String getNomeclirel() {
		return nomeclirel;
	}

	public void setNomeclirel(String nomeclirel) {
		this.nomeclirel = nomeclirel;
	}

	public Date getDtpagcli() {
		return dtpagcli;
	}

	public void setDtpagcli(Date dtpagcli) {
		this.dtpagcli = dtpagcli;
	}
	
	/*Eventos a serem realizados*/
	
	private Date dteventserrealizadorel;

	public Date getDteventserrealizadorel() {
		return dteventserrealizadorel;
	}

	public void setDteventserrealizadorel(Date dteventserrealizadorel) {
		this.dteventserrealizadorel = dteventserrealizadorel;
	}
	
	/*Fornecedores por evento*/
		
	private Date dtforneventrel;
	
	public Date getDtforneventrel() {
		return dtforneventrel;
	}

	public void setDtforneventrel(Date dtforneventrel) {
		this.dtforneventrel = dtforneventrel;
	}	
	
	/*Impressão de orçamento*/
}
