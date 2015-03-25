package Control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Fornecedor;
import Dominio.TipoServico;
import Interfaces.IFornecedorRepository;
import Interfaces.ITipoServicoRepository;
import Model.FornecedoresBean;
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

	public String Cadastrar(Fornecedor fornecedor) {

		// mudar campo tipo telefone para guardar a operadora

		if (!_fornecedorRepository.Add(fornecedor))
			return "Falha no cadastro do fornecedor";

		return null;
	}

	public String Atualizar(Fornecedor fornecedor) {
		if (!_fornecedorRepository.Update(fornecedor))
			return "Falha na atualização do fornecedor";

		return null;
	}

	public List<Fornecedor> ListarTodos() {
		return _fornecedorRepository.FindAll();
	}

	public List<Fornecedor> ListarTodos(String coluna, String valor) {
		return _fornecedorRepository.FindAll(coluna, valor);
	}

	public Fornecedor BuscarFornecedor(int id) {
		return _fornecedorRepository.GetById(id);
	}

	public String[] DDLTipoServico() {
		List<TipoServico> l = _tipoServicorepository.FindAll(true);
		String[] ddl = new String[l.size() + 1];
		ddl[0] = "Selecione";
		for (int i = 0; i < l.size(); i++) {
			ddl[i + 1] = l.get(i).getNome();
		}
		return ddl;

	}

	public TipoServico BuscarTipoServico(String nome) {
		return _tipoServicorepository.getTipoByName(nome);
	}

}
