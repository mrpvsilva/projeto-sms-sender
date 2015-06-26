package Control;

import java.util.List;

import Dominio.Servico;
import Interfaces.IServicoRepository;
import Repositories.ServicoRepository;

public class TipoServicoControl {

	private IServicoRepository tipoServicoRepository = new ServicoRepository();

	public String cadastrar(Servico tipoServico) {

		if (!tipoServicoRepository.add(tipoServico))
			return "Falha no cadastro do tipo de serviço.";

		return null;
	}

	public String atualizar(Servico tipoServico) {

		if (!tipoServicoRepository.update(tipoServico))
			return "Falha na atualização do tipo de serviço.";

		return null;
	}

	public Servico buscarTipoServico(long id) {
		return tipoServicoRepository.find(id);
	}

	public List<Servico> listarTodos() {
		return tipoServicoRepository.findAll();
	}

	public List<Servico> listarTodos(String nome, String ativo) {
		return tipoServicoRepository.findAll(nome, ativo);
	}

	

}
