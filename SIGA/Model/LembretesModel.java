package Model;

import java.util.ArrayList;

public class LembretesModel {
	
	/*Adiciona lista de poss�veis filtros */
	public ArrayList<String> FiltroLemb (){
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("DT AGENDADA");
		lista.add("ASSUNTO");
		lista.add("USU�RIO");
		
		return lista;
	}// final do m�todo filtros
	
}
