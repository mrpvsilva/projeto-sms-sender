package Interfaces;

import Dominio.FormaPagamento;
import java.util.*;

public interface IFormaPagamento extends IRepositoryBase<FormaPagamento>{
	
	public List<FormaPagamento> allFormaPagamento(String coluna,String valor);

}
