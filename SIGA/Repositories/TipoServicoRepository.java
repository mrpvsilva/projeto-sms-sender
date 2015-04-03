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
		open();
		Query q = entityManager.createQuery("from TipoServico order by nome");
		List<TipoServico> l = q.getResultList();
		close();
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<TipoServico> findAll(boolean ativo) {
		open();
		Query q = entityManager
				.createQuery("from TipoServico where ativo=:ativo order by nome");
		q.setParameter("ativo", ativo);
		List<TipoServico> l = q.getResultList();
		close();
		return l;
	}

	public TipoServico findByName(String nome) {
		open();
		Query q = entityManager
				.createQuery("from TipoServico where nome=:nome");
		q.setParameter("nome", nome);
		TipoServico ts = (TipoServico) q.getSingleResult();
		close();
		return ts;
	}

	@Override
	public List<TipoServico> findAll(String nome, String ativo) {
		open();
		String q = "from TipoServico where ";

		if (!nome.equals("")) {
			q += " nome like '%" + nome + "%' and";
		}

		if (ativo.equals("Todos")) {
			q += " ativo in (0,1)";
		} else if (ativo.equals("Ativo")) {
			q += " ativo = 1";
		} else {
			q += " ativo = 0";
		}

		q += " order by nome";

		Query query = entityManager.createQuery(q);
		List<TipoServico> l = query.getResultList();
		close();
		return l;
	}

}
