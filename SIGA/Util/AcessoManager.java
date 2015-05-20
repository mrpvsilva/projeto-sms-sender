package Util;

import Dominio.Permissao;
import Extra.Extras;
import Interfaces.IAcessoManager;
import Interfaces.IPermissaoRepository;
import Repositories.PermissaoRepository;

public class AcessoManager implements IAcessoManager {

	private IPermissaoRepository permissaoposistory;

	public AcessoManager() {
		permissaoposistory = new PermissaoRepository();
	}

	@Override
	public Permissao buscarPermissao(String modulo) {

		for (Permissao permissao : Extras.getUsuarioLogado().getPerfil()
				.getPermissoes()) {
			if (permissao.getModulo().getNome().equals(modulo))
				return permissao;
		}

		return new Permissao();

	}

}
