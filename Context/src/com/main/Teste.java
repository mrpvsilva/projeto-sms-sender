package com.main;


import com.applications.TipoItemApplication;
import com.domain.*;

public class Teste {

	public static void main(String[] args) {

		TipoItem tipo = new TipoItem();
		tipo.setNome("Quentes");

		new TipoItemApplication().Add(tipo);
		tipo = new TipoItemApplication().GetById(1);

		/*
		 * Item item = new Item();
		 * 
		 * /*item.setAtivo(true); item.setDescricaoitem("desc item");
		 * item.setNomeitem("nome item 2"); item.setTipoitem(tipo);
		 * item.setValoritem(500);
		 */

		// item = new ItemApplication().GetById(1);

		System.out.println(tipo.getNome());

	}
}
