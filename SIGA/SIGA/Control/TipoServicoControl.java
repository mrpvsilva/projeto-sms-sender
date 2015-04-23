package Control;

import java.util.List;

import Dominio.TipoServico;
import Interfaces.ITipoServicoRepository;
import Repositories.TipoServicoRepository;

public class TipoServicoControl {

	private ITipoServicoRepository tipoServicoRepository = new TipoServicoRepository();

	public String cadastrar(TipoServico tipoServico) {

		if (!tipoServicoRepository.add(tipoServico))
			return "Falha no cadastro do tipo de serviço.";

		return null;
	}

	public String atualizar(TipoServico tipoServico) {

		if (!tipoServicoRepository.update(tipoServico))
			return "Falha na atualização do tipo de serviço.";

		return null;
	}

	public TipoServico buscarTipoServico(int id) {
		return tipoServicoRepository.find(id);
	}

	public List<TipoServico> listarTodos() {
		return tipoServicoRepository.findAll();
	}

	public List<TipoServico> listarTodos(String nome, String ativo) {
		return tipoServicoRepository.findAll(nome, ativo);
	}

	

}
