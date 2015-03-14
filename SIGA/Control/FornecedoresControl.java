package Control;

import java.util.ArrayList;
import java.util.List;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Fornecedor;
import Dominio.TelefoneFornecedor;
import Dominio.TipoServico;
import Interfaces.IFornecedorRepository;
import Interfaces.ITipoServicoRepository;
import Model.FornecedoresBean;
import Model.FornecedoresModel;
import Repositories.FornecedorRepository;
import Repositories.TipoServicoRepository;

public class FornecedoresControl {

	FornecedoresModel fornMod = new FornecedoresModel();
	private IFornecedorRepository _fornecedorRepository = new FornecedorRepository(	PersistenceManagerFactory.getPersistanceManager());

	private ITipoServicoRepository _tipoServicorepository = new TipoServicoRepository(
			PersistenceManagerFactory.getPersistanceManager());

	/* Envia filtros para a JDTelaBuscarForn */
	public ArrayList<String> Filtros() {

		return fornMod.FiltroForn();

	}// final do método filtros

	public String Cadastrar(FornecedoresBean fornecedor) {

		Fornecedor f = new Fornecedor();

		f.setCpfcnpj(fornecedor.getCnpjforn().replace(".", "").replace("/", "")
				.replace("-", ""));
		f.setNome(fornecedor.getNomeforn());
		// falta o campo valor do serviço
		f.setValorservico(500);
		//
		f.setRg(fornecedor.getRgforn());
		f.setEmail(fornecedor.getEmailforn());
		f.setSite(fornecedor.getSiteforn());
		f.setEndereco(fornecedor.getEndforn());

		// o campo tipo servico é obrigatorio
		TipoServico ts = _tipoServicorepository.getTipoByName(fornecedor
				.getTiposervprestadoforn());
		f.setTiposervico(ts);

		// ADICIONA OS TELEFONES DO FORNECEDOR
		String telefones = fornecedor.getTelefonesforn().replace("(", "")
				.replace("-", "").replace(")", "-");

		String[] tels = telefones.split("-");
		int ddd = Integer.parseInt(tels[0]);
		String numero = tels[1];

		TelefoneFornecedor tf = new TelefoneFornecedor();
		tf.setDdd(ddd);
		tf.setNumero(numero);
		tf.setTipo("RESIDENCIAL");
		f.addTelefoneFornecedor(tf);

		if (!_fornecedorRepository.Add(f))
			return "Falha no cadastro do fornecedor";

		return null;
	}

	public String[] DDLTipoServico() {
		List<TipoServico> l = _tipoServicorepository.FindAll();

		String[] ddl = new String[l.size() + 1];

		ddl[0] = "Selecione";

		for (int i = 0; i < l.size(); i++) {
			ddl[i + 1] = l.get(i).getNome();
		}

		return ddl;

	}

}
