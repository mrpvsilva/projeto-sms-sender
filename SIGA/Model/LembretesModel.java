package Model;

import java.util.ArrayList;

public class LembretesModel {
	
	/*Adiciona lista de possíveis filtros */
	public ArrayList<String> FiltroLemb (){
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("DT AGENDADA");
		lista.add("ASSUNTO");
		lista.add("USUÁRIO");
		
		return lista;
	}// final do método filtros
	
}
