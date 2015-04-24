package Model;

import java.util.Date;

public class LembretesBean {

	private Date dtcontatolemb;
	private Date dtagendadalemb;
	private String assunto;
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getDtcontatolemb() {
		return dtcontatolemb;
	}
	public void setDtcontatolemb(Date dtcontatolemb) {
		this.dtcontatolemb = dtcontatolemb;
	}
	public Date getDtagendadalemb() {
		return dtagendadalemb;
	}
	public void setDtagendadalemb(Date dtagendadalemb) {
		this.dtagendadalemb = dtagendadalemb;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	
	
}
