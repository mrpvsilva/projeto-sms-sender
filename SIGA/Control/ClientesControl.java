package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Dominio.Cliente;
import Dominio.GridRecords;
import Dominio.ModelState;
import Interfaces.IClienteRepository;
import Model.ClientesModel;
import Repositories.ClienteRepository;

public class ClientesControl {

	private IClienteRepository clientes;

	public ClientesControl() {
		clientes = new ClienteRepository();
	}

	public List<Cliente> pesquisar(String valor, String campo) {
		return clientes.findAll(valor, campo);
	}

	// public List<Cliente> listarTodos() {
	// return clientes.findAll();
	// }

	public void _listarTodos(String valor, String campo,
			GridRecords<Cliente> grid) {

		grid.setTotalRecords(clientes.countCliente(valor, campo));
		grid.setLista(clientes.findAll(valor, campo, grid.getPageIndex(),
				grid.getRecordsCount()));
	}

	public boolean cadastrar(Cliente cliente) {

		ModelState state = cliente.modelState();

		if (!state.isValid()) {
			JOptionPane.showMessageDialog(null, state.MensagemErro(),
					"Atenção", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			clientes.add(cliente);
			JOptionPane.showMessageDialog(null,
					"Cliente cadastrado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao cadastar o cliente\n"
					+ e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	public boolean atualizar(Cliente cliente) {
		try {
			return clientes.update(cliente);
		} catch (Exception e) {
			return false;
		}

	}

	/* Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros() {
		return new ClientesModel().FiltroCli();
	}

}
