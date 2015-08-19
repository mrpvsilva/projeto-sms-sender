package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Cliente;
import Dominio.GridRecords;
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

	public List<Cliente> listarTodos() {
		return clientes.findAll();
	}

	public void _listarTodos(String valor, String campo,
			GridRecords<Cliente> grid) {
		grid.setLista(clientes.findAll(valor, campo, grid.getPageIndex(),
				grid.getRecordsCount()));
	}

	public int countClientes() {
		return clientes.countCliente();
	}

	public boolean cadastrar(Cliente cliente) {

		try {
			return clientes.add(cliente);
		} catch (Exception e) {
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
