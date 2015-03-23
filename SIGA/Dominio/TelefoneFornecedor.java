package Dominio;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("FORNECEDOR")
public class TelefoneFornecedor extends Telefone {

	private static final long serialVersionUID = 1L;

	public TelefoneFornecedor() {

	}

	public TelefoneFornecedor(String ddd, String numero, String operadora) {
		setDdd(ddd);
		setNumero(numero);
		setOperadora(operadora);
	}

}
