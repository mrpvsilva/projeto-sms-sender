package Control;

import java.util.ArrayList;

import Model.ClientesModel;

public class ClientesControl {
	
	ClientesModel cliMod = new ClientesModel();
	
	/*Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros(){
		
		return cliMod.FiltroCli();
	}// final do método filtros
	
}
