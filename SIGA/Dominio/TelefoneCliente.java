package Dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("CLIENTE")
public class TelefoneCliente extends Telefone {
	private static final long serialVersionUID = 1L;

	public TelefoneCliente() {

	}

	public TelefoneCliente(String ddd, String numero, String operadora) {
		setDdd(ddd);
		setNumero(numero);
		setOperadora(operadora);
	}

}
