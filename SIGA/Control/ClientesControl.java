package Control;

import javax.swing.JOptionPane;

import Model.UsuarioBean;
import Model.UsuarioModel;

public class ClientesControl {

	//fala arua kkkk
	public UsuarioBean Logar(UsuarioBean usuAut){
	
		UsuarioModel usuMod = new UsuarioModel();
		
		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));
		
		/*Validação da autenticação*/
		if(usuAut.getResposta()){
			return usuAut;
		}else{
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.","SIGA - Autenticação",JOptionPane.ERROR_MESSAGE);
			return usuAut;
		}
		
	}// final do método Logar
	
}
