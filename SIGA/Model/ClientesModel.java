package Model;

import java.util.ArrayList;

public class ClientesModel {

	/*Adiciona lista de possíveis filtros */
	public ArrayList<String> FiltroCli (){
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("NOME");
		lista.add("CPF");
		lista.add("TIPO EVENTO");
		
		return lista;
	}// final do método filtros
	
}
