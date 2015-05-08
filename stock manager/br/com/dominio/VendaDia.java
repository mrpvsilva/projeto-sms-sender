package com.dominio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class VendaDia {

	private Date data;
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
