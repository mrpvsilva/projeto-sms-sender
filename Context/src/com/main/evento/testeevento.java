package com.main.evento;

import com.applications.ClienteApplication;
import com.applications.EventoApplication;
import com.applications.FornecedorApplication;
import com.applications.ItemApplication;
import com.applications.TipoServicoApplication;
import com.domain.Cliente;
import com.domain.ClienteItemExtra;
import com.domain.Evento;
import com.domain.EventoItem;
import com.domain.Fornecedor;
import com.domain.Item;
import com.domain.Telefone;
import com.domain.TelefoneCliente;
import com.domain.TelefoneFornecedor;
import com.domain.TipoServico;
import com.domain.TipoTelefone;

public class testeevento {

	public static void main(String[] args) {

		ClienteApplication ca = new ClienteApplication();

		Cliente c1 = ca.GetById(29);
		Item cerveja = new ItemApplication().GetById(1);
		c1.addItemExtra(cerveja, 20);

		ca.Add(c1);

	}
}
