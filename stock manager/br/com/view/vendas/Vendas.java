package com.view.vendas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.controllers.VendaController;
import com.dominio.Venda;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.VendaTableModel;

public class Vendas extends JPanel {
	private JTable table;
	private DefaultTableModel<Venda> tableModelVenda;
	private VendaController vc;

	/**
	 * Create the panel.
	 */
	public Vendas() {
		vc = new VendaController();
		tableModelVenda = new VendaTableModel(vc.listarTodos());
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel left = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) left.getLayout();
		flowLayout_2.setHgap(10);
		add(left, BorderLayout.WEST);
		
		JPanel rigth = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) rigth.getLayout();
		flowLayout_3.setHgap(10);
		add(rigth, BorderLayout.EAST);
		
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setVgap(10);
		add(top, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottom.getLayout();
		flowLayout_1.setVgap(10);
		add(bottom, BorderLayout.SOUTH);
		
		JPanel center = new JPanel();
		center.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(center, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		center.add(scrollPane);
		
		table = new JTable(tableModelVenda);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

	}

}
