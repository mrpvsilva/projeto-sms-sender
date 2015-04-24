package com.interfaces;

import java.util.List;

import com.dominio.Fornecedor;

public interface IFornecedorContext extends IBaseContext<Fornecedor> {

	public List<Fornecedor> findAll(String nome);

}
