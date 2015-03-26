package Repositories;

import static org.junit.Assert.*;

import org.junit.Test;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Fornecedor;
import Interfaces.IFornecedorRepository;

public class FornecedorTest {

	@Test
	public void GetById() {

		int id = 30;
		IFornecedorRepository fr = new FornecedorRepository(
				PersistenceManagerFactory.getPersistanceManager());
		int esperado = 1;
		Fornecedor retorno = fr.find(id);
		assertEquals(esperado, retorno.getId());

	}

}
