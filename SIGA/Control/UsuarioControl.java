package Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.UsuarioBean;
import Model.UsuarioModel;

public class UsuarioControl {

	UsuarioModel usuMod = new UsuarioModel();
	
	public UsuarioBean Logar(UsuarioBean usuAut){
			
		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));
		
		/*Validação da autenticação*/
		if(usuAut.getResposta()){
			return usuAut;
		}else{
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.","SIGA - Autenticação",JOptionPane.ERROR_MESSAGE);
			return usuAut;
		}
		
	}// final do método Logar
	
	/*Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros(){
		
		return usuMod.FiltroUsu();
		
	}// final do método filtros
	
}
