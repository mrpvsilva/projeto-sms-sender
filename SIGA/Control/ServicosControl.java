package Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.ServicosModel;
import Model.UsuarioBean;
import Model.UsuarioModel;

public class ServicosControl {
	
	ServicosModel servMod = new ServicosModel();
	
	/*Envia filtros para view JDTelaBuscarServ */
	public ArrayList<String> Filtros(){
			
		return servMod.FiltroServ();
	
	}// final do método filtros
	
}
