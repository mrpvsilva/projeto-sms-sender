package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Servico;
import Interfaces.IServicoRepository;

public class ServicoRepository extends RepositoryBase<Servico>
		implements IServicoRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> findAll() {
		try {
			
			Query query = entityManager
					.createQuery("select t from Servico t order by t.nome");
			List<Servico> l = query.getResultList();

			return l;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 

	}

	@SuppressWarnings("unchecked")
	public List<Servico> findAll(boolean ativo) {

		try {
		
			Query q = entityManager
					.createQuery("select t from Servico t where t.ativo=:ativo order by t.nome");
			q.setParameter("ativo", ativo);
			List<Servico> l = q.getResultList();

			return l;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	public Servico findByName(String nome) {

		try {
			
			Query q = entityManager
					.createQuery("select t from Servico t where t.nome=:nome");
			q.setParameter("nome", nome);
			Servico ts = (Servico) q.getSingleResult();

			return ts;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@Override
	public List<Servico> findAll(String nome, String ativo) {

		try {
			
			String q = "select t from Servico t where ";

			if (!nome.equals("")) {
				q += " t.nome like '%" + nome + "%' and";
			}

			if (ativo.equals("Todos")) {
				q += " t.ativo in (0,1)";
			} else if (ativo.equals("Ativo")) {
				q += " t.ativo = 1";
			} else {
				q += " t.ativo = 0";
			}

			q += " order by t.nome";

			Query query = entityManager.createQuery(q);
			List<Servico> l = query.getResultList();

			return l;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 

	}

	@Override
	public List<String> DDL() {
		try {
			
			String q = "select t.nome from Servico t where t.ativo = 1 order by t.nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}
}
