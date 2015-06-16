package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoServico;
import Interfaces.ITipoServicoRepository;

public class TipoServicoRepository extends RepositoryBase<TipoServico>
		implements ITipoServicoRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoServico> findAll() {
		try {
			open();
			Query query = entityManager
					.createQuery("select t from TipoServico t order by t.nome");
			List<TipoServico> l = query.getResultList();

			return l;

		} catch (Exception ex) {
			return null;
		} finally {
		//	entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<TipoServico> findAll(boolean ativo) {

		try {
			open();
			Query q = entityManager
					.createQuery("select t from TipoServico t where t.ativo=:ativo order by t.nome");
			q.setParameter("ativo", ativo);
			List<TipoServico> l = q.getResultList();

			return l;

		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}

	public TipoServico findByName(String nome) {

		try {
			open();
			Query q = entityManager
					.createQuery("select t from TipoServico t where t.nome=:nome");
			q.setParameter("nome", nome);
			TipoServico ts = (TipoServico) q.getSingleResult();

			return ts;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}

	@Override
	public List<TipoServico> findAll(String nome, String ativo) {

		try {
			open();
			String q = "select t from TipoServico t where ";

			if (!nome.equals("")) {
				q += " t.nome like '%" + nome + "%' and";
			}

			if (ativo.equals("Todos")) {
				q += " t.ativo in (0,1)";
			} else if (ativo.equals("Ativo")) {
				q += " ativo = 1";
			} else {
				q += " ativo = 0";
			}

			q += " order by t.nome";

			Query query = entityManager.createQuery(q);
			List<TipoServico> l = query.getResultList();

			return l;

		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}

	}

	@Override
	public List<String> DDL() {
		try {
			open();
			String q = "select t.nome from TipoServico t where t.ativo = 1 order by t.nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}
}
