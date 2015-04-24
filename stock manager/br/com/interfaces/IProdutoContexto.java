package com.interfaces;

import java.util.List;

import com.dominio.Produto;

public interface IProdutoContexto extends IBaseContext<Produto> {

	public List<Produto> findAll(String nome);

}
