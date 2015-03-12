package Model;

public class UsuarioBean {

	private String login;
	private String senha;
	private boolean resposta;
	
	public boolean getResposta(){
		return resposta;
	}
	public void setResposta(boolean resposta){
		this.resposta = resposta;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
