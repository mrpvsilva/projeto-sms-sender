package com.view.fornecedores;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.controllers.FornecedorController;
import com.dominio.Fornecedor;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.FornecedorTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class Fornecedores extends JPanel {
	private JTextField textField;
	private JTable table;
	private FornecedorController controller;
	private DefaultTableModel<Fornecedor> tablemodel;
	private JButton btnAlterar;

	public Fornecedores() {
		controller = new FornecedorController();
		tablemodel = new FornecedorTableModel(controller.listarTodos());

		setLayout(new BorderLayout(0, 0));
		JPanel filtrospane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filtrospane.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(filtrospane, BorderLayout.NORTH);

		JLabel lblNomeDoFornecedor = new JLabel("Nome do fornecedor");
		lblNomeDoFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filtrospane.add(lblNomeDoFornecedor);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisar();

				if (textField.getText().length() > 1
						|| textField.getText().length() == 0)
					pesquisar();
			}
		});
		filtrospane.add(textField);
		textField.setColumns(30);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisar();

			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		filtrospane.add(btnPesquisar);

		JPanel gridpane = new JPanel();
		add(gridpane, BorderLayout.CENTER);
		gridpane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		gridpane.add(scrollPane);

		table = new JTable(tablemodel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						btnAlterar.setEnabled(true);

					}
				});
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				btnAlterar.setEnabled(false);
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel buttonspane = new JPanel();
		add(buttonspane, BorderLayout.SOUTH);
		buttonspane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JButton btnNovo = new JButton("Novo fornecedor");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditFornecedor ef = new EditFornecedor(null, tablemodel);
				ef.setVisible(true);
			}
		});
		buttonspane.add(btnNovo);

		btnAlterar = new JButton("Alterar fornecedor");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				EditFornecedor ef = new EditFornecedor(tablemodel.find(linha),
						tablemodel);
				ef.setVisible(true);
			}
		});
		buttonspane.add(btnAlterar);
		
		JPanel left = new JPanel();
		add(left, BorderLayout.WEST);
		
		JPanel rigth = new JPanel();
		add(rigth, BorderLayout.EAST);

	}

	private void pesquisar() {
		String nome = textField.getText();

		if (nome.isEmpty()) {
			tablemodel.setLinhas(controller.listarTodos());
		} else {
			tablemodel.setLinhas(controller.listarTodos(nome));
		}

	}
}
