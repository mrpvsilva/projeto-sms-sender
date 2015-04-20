package Control;

import java.util.List;

import Dominio.Formulario;
import Interfaces.IFormularioRepository;
import Repositories.FormularioRepository;

public class FormularioControl {

	private IFormularioRepository formularioRepository;

	public FormularioControl() {
		formularioRepository = new FormularioRepository();
	}

	public List<Formulario> listarTodos() {
		return formularioRepository.findAll();
	}

}
