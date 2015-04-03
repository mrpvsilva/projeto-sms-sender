package Control;

import java.util.List;

import javax.swing.JOptionPane;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.FormaPagamento;
import Dominio.TipoPagamento;
import Interfaces.IFormaPagamento;
import Interfaces.ITipoPagamentoRepository;
import Model.UsuarioBean;
import Model.UsuarioModel;
import Repositories.FormaPagamentoRepository;
import Repositories.TipoPagamentoRepository;

public class FinanceiroControl {
	private IFormaPagamento _formpag = new FormaPagamentoRepository();
	private ITipoPagamentoRepository _tppagamento = new TipoPagamentoRepository();

	public UsuarioBean Logar(UsuarioBean usuAut) {

		UsuarioModel usuMod = new UsuarioModel();

		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));

		/* Validação da autenticação */
		if (usuAut.getResposta()) {
			return usuAut;
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.",
					"SIGA - Autenticação", JOptionPane.ERROR_MESSAGE);
			return usuAut;
		}

	}// final do método Logar

	public List<TipoPagamento> allTpPagamentos() {
		return _tppagamento.findAll();
	}

	public List<FormaPagamento> allFormaPagamento() {
		return _formpag.findAll();
	}

}
