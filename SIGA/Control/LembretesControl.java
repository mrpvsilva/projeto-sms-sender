package Control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Dominio.Lembrete;
import Dominio.Usuario;
import Extra.Extras;
import Interfaces.ILembreteRepository;
import Interfaces.IUsuarioRepository;
import Model.LembretesModel;
import Repositories.LembreteRepository;
import Repositories.UsuarioRepository;

public class LembretesControl {

	LembretesModel lembMod = new LembretesModel();

	private ILembreteRepository _lembreteRepository = new LembreteRepository();
	private IUsuarioRepository _usuarioRepository = new UsuarioRepository();

	/* Envia filtros para view JDTelaBuscarLemb */
	/** Fun��o de cadastro de lembrete */
	public String Cadastrar(Lembrete lembrete) {

		lembrete.setRemetente(Extras.getUsuarioLogado());

		if (!_lembreteRepository.add(lembrete))
			return "Falha no cadastro do lembrete";

		return null;
	}

	/** Fun��o de atualiza��o de lembrete */
	public String Atualizar(Lembrete lembrete) {

		if (!_lembreteRepository.update(lembrete))
			return "Falha no atualiza��o do lembrete";

		return null;
	}

	/** Fun��o de listagem de todos os lembrete */
	public List<Lembrete> BuscarTodos() {
		return _lembreteRepository.findAll(Extras.getUsuarioLogado());
	}

	/**
	 * Fun��o de listagem de todos os lembrete com filtro de data e assunto
	 */
	public List<Lembrete> BuscarTodos(Date dataInicial, Date dataFinal,
			String assunto) {
		return _lembreteRepository.findAll(dataInicial, dataFinal,
				assunto.equals("Selecione") ? null : assunto,
				Extras.getUsuarioLogado());
	}

	/**
	 * Fun��o de buscar do destinatario pelo nome
	 */
	public Usuario BuscarDestinatario(String usuario) {
		return _usuarioRepository.getUsuario(usuario);
	}

	/**
	 * Fun��o de listagem dos nomes dos destinatarios
	 */
	public String[] DDLDestinatarios() {
		List<Usuario> l = _usuarioRepository.findAll();
		String[] ddl = new String[l.size() + 1];
		ddl[0] = "Selecione";
		for (int i = 0; i < l.size(); i++) {
			ddl[i + 1] = l.get(i).getUsuario();
		}
		return ddl;

	}

	/**
	 * Fun��o de busca de um lembrete pelo id
	 */
	public Lembrete BuscarLembrete(int id) {
		return _lembreteRepository.find(id);
	}

	public List<Lembrete> notificarLembretesUsuario() {
		return _lembreteRepository.notificarLembretes(Extras.getUsuarioLogado());
	}
	
	public void limparLembretesNotificacao(){
		
		_lembreteRepository.limparLembretesNotificacao(Extras.getUsuarioLogado());
		
	}
}
