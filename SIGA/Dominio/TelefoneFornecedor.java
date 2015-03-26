package Dominio;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("FORNECEDOR")
@OnDelete(action=OnDeleteAction.CASCADE)
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
