package Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.ClientesModel;
import Model.LembretesModel;
import Model.UsuarioBean;
import Model.UsuarioModel;

public class LembretesControl {

	LembretesModel lembMod = new LembretesModel();
	
	/*Envia filtros para view JDTelaBuscarLemb */
	public ArrayList<String> Filtros(){
		
		return lembMod.FiltroLemb();
	}// final do método filtros
	
}
