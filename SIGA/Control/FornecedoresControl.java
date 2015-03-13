package Control;

import java.util.ArrayList;

import Model.FornecedoresModel;


public class FornecedoresControl {

	FornecedoresModel fornMod = new FornecedoresModel();
	
	/*Envia filtros para a JDTelaBuscarForn*/
	public ArrayList<String> Filtros(){

		return fornMod.FiltroForn();
		
	}// final do método filtros
	
}
