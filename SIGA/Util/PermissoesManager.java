package Util;

import Dominio.Permissao;
import Interfaces.IAcessoManager;
import Managers.AcessoManager;

public class PermissoesManager {

	private static IAcessoManager manager;

	public static Permissao buscarPermissao(Modulos modulo) {

		if (manager == null)
			manager = new AcessoManager();

		String m = modulo.toString().replace("_", " ");
		return manager.buscarPermissao(m);

	}

}


