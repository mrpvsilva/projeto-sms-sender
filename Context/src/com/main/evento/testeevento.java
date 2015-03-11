package com.main.evento;

import com.applications.ClienteApplication;
import com.applications.EventoApplication;
import com.applications.ItemApplication;
import com.domain.Cliente;
import com.domain.Evento;
import com.domain.Item;

public class testeevento {

	public static void main(String[] args) {
		
		
		Evento evento = new EventoApplication().GetById(7);	

		ClienteApplication ca = new ClienteApplication();
		Cliente cliente = ca.GetById(29);
		

		evento.addCliente(cliente);		
		new EventoApplication().Update(evento);
		
		
		Item cerveja = new ItemApplication().GetById(2);
		cliente.addItemExtra(cerveja, evento, 36);
		
		
		ca.Add(cliente);
		
		
		

	}
}
