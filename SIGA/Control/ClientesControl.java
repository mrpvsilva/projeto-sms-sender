package Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.ClientesModel;
import Model.UsuarioBean;
import Model.UsuarioModel;

public class ClientesControl {
	
	ClientesModel cliMod = new ClientesModel();
	
	/*Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros(){
		
		return cliMod.FiltroCli();
	}// final do método filtros
	
}
