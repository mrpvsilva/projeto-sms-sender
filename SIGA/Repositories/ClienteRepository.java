package Repositories;

import java.util.List;

import Dominio.Cliente;
import Interfaces.IClienteRepository;

public class ClienteRepository extends RepositoryBase<Cliente> implements
		IClienteRepository {

	@Override
	public List<Cliente> findAll() {

		try {
			open();
			String q = "from Cliente order by nomecompleto";
			@SuppressWarnings("unchecked")
			List<Cliente> l = entityManager.createQuery(q).getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public List<Cliente> findAll(String valor, String campo) {
		try {
			open();
			String q = "from Cliente where " + campo + " like '%" + valor
					+ "%' order by nomecompleto";
			@SuppressWarnings("unchecked")
			List<Cliente> l = entityManager.createQuery(q).getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

}
