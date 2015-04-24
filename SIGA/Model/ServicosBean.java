package Model;

import java.math.BigDecimal;

public class ServicosBean {

	private String nomeitem;
	private String descricaoitem;
	private BigDecimal vlrcustoitem;
	private BigDecimal vlrcomercialitem;
	private boolean ativoitem;
	
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
	public BigDecimal getVlrcustoitem() {
		return vlrcustoitem;
	}
	public void setVlrcustoitem(BigDecimal vlrcustoitem) {
		this.vlrcustoitem = vlrcustoitem;
	}
	public BigDecimal getVlrcomercialitem() {
		return vlrcomercialitem;
	}
	public void setVlrcomercialitem(BigDecimal vlrcomercialitem) {
		this.vlrcomercialitem = vlrcomercialitem;
	}
	public boolean isAtivoitem() {
		return ativoitem;
	}
	public void setAtivoitem(boolean ativoitem) {
		this.ativoitem = ativoitem;
	}
	
	
	
}
