package Dominio;

public class ModelState {

	private boolean state;
	private String msg;

	/**
	 * Usado em caso de sucesso.
	 */

	public ModelState() {
		this.state = true;
		this.msg = "";
	}

	/**
	 * Usado em caso de erro.
	 * 
	 * @param msg
	 */
	public ModelState(String msg) {
		this.state = false;
		this.msg = msg;
	}

	public boolean isValid() {
		return state;
	}

	public String MensagemErro() {
		return msg;
	}

}
