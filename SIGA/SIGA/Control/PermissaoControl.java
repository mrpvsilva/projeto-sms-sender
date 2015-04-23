package Control;

import Interfaces.IPermissaoRepository;
import Repositories.PermissaoRepository;

public class PermissaoControl {

	private IPermissaoRepository permissaoRepository;

	public PermissaoControl() {
		permissaoRepository = new PermissaoRepository();
	}	

}
