package Control;

import javax.swing.JOptionPane;

import Model.UsuarioBean;
import Model.UsuarioModel;

public class ClientesControl {

	//fala arua kkkk
	public UsuarioBean Logar(UsuarioBean usuAut){
	
		UsuarioModel usuMod = new UsuarioModel();
		
		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));
		
		/*Valida��o da autentica��o*/
		if(usuAut.getResposta()){
			return usuAut;
		}else{
			JOptionPane.showMessageDialog(null, "Usu�rio ou senha incorretos.","SIGA - Autentica��o",JOptionPane.ERROR_MESSAGE);
			return usuAut;
		}
		
	}// final do m�todo Logar
	
}
