package Model;

import java.util.ArrayList;

public class FornecedoresModel {

	/*Adiciona lista de poss�veis filtros */
	public ArrayList<String> FiltroForn (){
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("CNPJ");
		lista.add("NOME");
		lista.add("TELEFONE");
		
		return lista;
	}// final do m�todo filtros
	
}
