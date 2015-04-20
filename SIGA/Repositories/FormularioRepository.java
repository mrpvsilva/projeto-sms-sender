package Repositories;

import java.util.List;

import javax.persistence.Query;
import Dominio.Formulario;
import Interfaces.IFormularioRepository;

public class FormularioRepository extends RepositoryBase<Formulario> implements
		IFormularioRepository {

	@Override
	public List<Formulario> findAll() {
		open();
		String q = "from Formulario order by nome";
		Query query = entityManager.createQuery(q, Formulario.class);
		return query.getResultList();
	}

}
