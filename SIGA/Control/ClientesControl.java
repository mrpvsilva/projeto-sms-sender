package Control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import Dominio.Cliente;
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

	public boolean cadastrar(Cliente cliente) {

		try {
			return clientes.add(cliente);
		} catch (HibernateException e) {
			return false;
		}

	}

	public boolean atualizar(Cliente cliente) {
		try {
			return clientes.update(cliente);
		} catch (HibernateException e) {
			return false;
		}

	}

	/* Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros() {

		return new ClientesModel().FiltroCli();
	}

}
