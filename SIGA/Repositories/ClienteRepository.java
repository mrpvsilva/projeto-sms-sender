package Repositories;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import Dominio.Cliente;
import Interfaces.IClienteRepository;

public class ClienteRepository extends RepositoryBase<Cliente> implements
		IClienteRepository {

	@Override
	public List<Cliente> findAll() {

		try {
			String q = "select c from Cliente c order by c.nomecompleto";
			@SuppressWarnings("unchecked")
			List<Cliente> l = entityManager.createQuery(q).getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Cliente> findAll(String valor, String campo) {
		try {

			String q = "select c from Cliente c where c." + campo + " like '%"
					+ valor + "%' order by c.nomecompleto";
			return entityManager.createQuery(q, Cliente.class).getResultList();

		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Cliente find(String cpfcnpj) {
		try {
			String q = "select c from Cliente c where c.cpfcnpj= :cpfcnpj";
			Query query = entityManager.createQuery(q);
			query.setParameter("cpfcnpj", cpfcnpj);
			return (Cliente) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
