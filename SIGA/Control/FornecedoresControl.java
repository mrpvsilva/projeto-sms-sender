package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Fornecedor;
import Dominio.TipoServico;
import Interfaces.IFornecedorRepository;
import Interfaces.ITipoServicoRepository;
import Model.FornecedoresModel;
import Repositories.FornecedorRepository;
import Repositories.TipoServicoRepository;

public class FornecedoresControl {

	FornecedoresModel fornMod = new FornecedoresModel();
	private IFornecedorRepository _fornecedorRepository = new FornecedorRepository();
	private ITipoServicoRepository _tipoServicorepository = new TipoServicoRepository();

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

	public List<Fornecedor> listarTodos(String campo, String valor,
			String tipoServico) {
		return _fornecedorRepository.findAll(campo, valor, tipoServico);
	}

	public Fornecedor buscarFornecedor(int id) {
		return _fornecedorRepository.find(id);
	}

	public Object[] DDLTipoServico() {
		List<String> l = _tipoServicorepository.DDL();
		l.add(0, "SELECIONE");
		return l.toArray();
	}

	public TipoServico buscarTipoServico(String nome) {
		return _tipoServicorepository.findByName(nome);
	}

}
