package Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.UsuarioBean;
import Model.UsuarioModel;

public class UsuarioControl {

	UsuarioModel usuMod = new UsuarioModel();
	
	public UsuarioBean Logar(UsuarioBean usuAut){
			
		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));
		
		/*Valida��o da autentica��o*/
		if(usuAut.getResposta()){
			return usuAut;
		}else{
			JOptionPane.showMessageDialog(null, "Usu�rio ou senha incorretos.","SIGA - Autentica��o",JOptionPane.ERROR_MESSAGE);
			return usuAut;
		}
		
	}// final do m�todo Logar
	
	/*Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros(){
		
		return usuMod.FiltroUsu();
		
	}// final do m�todo filtros
	
}
