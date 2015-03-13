package Model;

import java.util.ArrayList;

public class UsuarioModel {

	public boolean AutenticarUsuario(UsuarioBean usuario){
		
		
		return true;
	}
	
	/*Adiciona lista de possíveis filtros */
	public ArrayList<String> FiltroUsu (){
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("LOGIN");
		lista.add("NOME");
		lista.add("PERFIL");
		
		return lista;
	}// final do método filtros
	
}
