package Repositories;

import java.util.List;

import javax.persistence.Query;

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

	@Override
	public Cliente find(String cpfcnpj) {
		try {
			open();
			String q = "from Cliente where cpfcnpj= :cpfcnpj";
			Query query = entityManager.createQuery(q);
			query.setParameter("cpfcnpj", cpfcnpj);
			return (Cliente) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

}
