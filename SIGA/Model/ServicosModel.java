package Model;

import java.util.ArrayList;

public class ServicosModel {

	/* Adiciona lista de poss�veis filtros */
	public ArrayList<String> FiltroServ() {
		ArrayList<String> lista = new ArrayList<String>();

		lista.add("ITEM");
		lista.add("DESCRICAO");
		// lista.add("ATIVO");
		return lista;
	}// final do m�todo filtros

}
