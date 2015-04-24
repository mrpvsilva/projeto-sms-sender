package com.dominio;

import javax.persistence.EntityManager;

import com.util.jpautil.JpaUtil;

public class teste {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		try {

			Produto p1 = manager.find(Produto.class, 1l);
			Produto p2 = manager.find(Produto.class, 2l);
			Venda venda = new Venda();
			
			venda.addProduto(p1, 2);			
			venda.addProduto(p2, 2);

			manager.getTransaction().begin();
			manager.persist(venda);
			manager.getTransaction().commit();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			manager.getTransaction().rollback();
		}

	}
}
