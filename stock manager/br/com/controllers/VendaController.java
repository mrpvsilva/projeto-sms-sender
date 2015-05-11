package com.controllers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.dominio.Produto;
import com.dominio.ProdutoVendido;
import com.dominio.Venda;
import com.repositorios.StockEntities;
import com.view.fornecedores.EditFornecedor;

public class VendaController {
	private StockEntities db;

	public VendaController() {
		db = new StockEntities();
	}

	public List<Venda> listarTodos() {
		return db.Vendas.findAll();
	}

	public void cadastrar(Venda venda) {
		try {
			db.Vendas.add(venda);
			for (ProdutoVendido pv : venda.getProdutosvendidos()) {
				Produto p = pv.getProduto();
				int q = p.getQuantidade() - pv.getQuantidade();
				p.setQuantidade(q);
				db.Produtos.update(p);
			}
		} catch (Exception ex) {

		}
	}

	public List<Produto> pesquisarProduto(String pesquisa) {
		return db.Produtos.findAll(pesquisa);
	}

	public void gerarRelatorioVendas(String tipoRelatorio) throws JRException {
		InputStream path;

		JasperReport report;
		JasperPrint print;
		switch (tipoRelatorio) {
		case "Mês":
			path = EditFornecedor.class
					.getResourceAsStream("/relatorios/vendasmes.jasper");
			//report = JasperCompileManager. (path);
			print = JasperFillManager
					.fillReport(path, null, new JRBeanCollectionDataSource(
							db.Vendas.buscarVendasMes()));
			JasperExportManager.exportReportToPdfFile(print,
					"C:/Users/Acer/Desktop/vendasmes.pdf");
			break;

		case "Dia":
			path = EditFornecedor.class
					.getResourceAsStream("/relatorios/vendasdia.jasper");
			//report = JasperCompileManager.compileReport(path);
			print = JasperFillManager
					.fillReport(path, null, new JRBeanCollectionDataSource(
							db.Vendas.buscarVendasDia()));
			JasperExportManager.exportReportToPdfFile(print,
					"C:/Users/Acer/Desktop/vendasdia.pdf");
			break;

		}

		JOptionPane.showMessageDialog(null, "Relátorio gerado com sucesso");

	}

	public static void main(String[] args) {

		try {

			new VendaController().gerarRelatorioVendas("Dia");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
