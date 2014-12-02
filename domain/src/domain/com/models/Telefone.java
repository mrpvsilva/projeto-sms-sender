package domain.com.models;

public class Telefone {

	private long idtelefone;
	private int ddd;
	private String numero;
	private TipoTelefone tipo;
	private Cliente cliente;

	public long getIdtelefone() {
		return idtelefone;
	}

	public void setIdtelefone(long idtelefone) {
		this.idtelefone = idtelefone;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
