package Control;

import java.util.ArrayList;
import java.util.List;
import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Fornecedor;
import Dominio.TipoServico;
import Interfaces.IFornecedorRepository;
import Interfaces.ITipoServicoRepository;
import Model.FornecedoresModel;
import Repositories.FornecedorRepository;
import Repositories.TipoServicoRepository;

public class FornecedoresControl {

	FornecedoresModel fornMod = new FornecedoresModel();
	private IFornecedorRepository _fornecedorRepository = new FornecedorRepository(
			PersistenceManagerFactory.getPersistanceManager());

	private ITipoServicoRepository _tipoServicorepository = new TipoServicoRepository(
			PersistenceManagerFactory.getPersistanceManager());

	/* Envia filtros para a JDTelaBuscarForn */
	public ArrayList<String> Filtros() {

		return fornMod.FiltroForn();

	}// final do método filtros

	public String cadastrar(Fornecedor fornecedor) {

		if (!_fornecedorRepository.add(fornecedor))
			return "Falha no cadastro do fornecedor";

		return null;
	}

	public String atualizar(Fornecedor fornecedor) {
		if (!_fornecedorRepository.update(fornecedor))
			return "Falha na atualização do fornecedor";

		return null;
	}

	public List<Fornecedor> listarTodos() {
		return _fornecedorRepository.findAll();
	}

	public List<Fornecedor> listarTodos(String coluna, String valor) {
		return _fornecedorRepository.findAll(coluna, valor);
	}

	public Fornecedor buscarFornecedor(int id) {
		return _fornecedorRepository.find(id);
	}

	public String[] DDLTipoServico() {
		List<TipoServico> l = _tipoServicorepository.findAll(true);
		String[] ddl = new String[l.size() + 1];
		ddl[0] = "Selecione";
		for (int i = 0; i < l.size(); i++) {
			ddl[i + 1] = l.get(i).getNome();
		}
		return ddl;

	}

	public TipoServico buscarTipoServico(String nome) {
		return _tipoServicorepository.findByName(nome);
	}

}
