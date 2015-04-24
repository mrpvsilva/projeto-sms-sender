package com.view.produtos;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.controllers.ProdutoController;
import com.dominio.Produto;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.ProdutoTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Produtos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel<Produto> produtosModel;
	private ProdutoController pc;
	private JTextField tfnome;
	private JButton btnAlterar;
	private JButton btnAdicionarAoCarrinho;

	/**
	 * Create the panel.
	 */
	public Produtos() {
		pc = new ProdutoController();
		produtosModel = new ProdutoTableModel(pc.listarTodos());
		setLayout(new BorderLayout(0, 0));

		JPanel containerprodutos = new JPanel();
		add(containerprodutos);
		containerprodutos.setLayout(new BorderLayout(0, 0));

		JPanel filtros = new JPanel();
		containerprodutos.add(filtros, BorderLayout.NORTH);
		filtros.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel lblNome = new JLabel("Nome do produto");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		filtros.add(lblNome);

		tfnome = new JTextField();
		tfnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisar();

				if (tfnome.getText().length() > 1
						|| tfnome.getText().length() == 0)
					pesquisar();
			}
		});
		filtros.add(tfnome);
		tfnome.setColumns(30);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisar();
			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisar();
			}
		});
		filtros.add(btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(100, 100));
		containerprodutos.add(scrollPane);
		scrollPane.setMaximumSize(new Dimension(500, 200));

		table = new JTable(produtosModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						btnAlterar.setEnabled(true);
						btnAdicionarAoCarrinho.setEnabled(true);

					}
				});
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				btnAlterar.setEnabled(false);
				btnAdicionarAoCarrinho.setEnabled(false);

			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel buttonpane = new JPanel();
		containerprodutos.add(buttonpane, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) buttonpane.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProduto ep = new EditProduto(null, produtosModel);
				ep.setVisible(true);
			}
		});
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonpane.add(btnNovo);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					EditProduto ep = new EditProduto(produtosModel.find(linha),
							produtosModel);
					ep.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um produto.", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonpane.add(btnAlterar);

		btnAdicionarAoCarrinho = new JButton("Adicionar ao carrinho");
		btnAdicionarAoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					new Quantidade(produtosModel.find(linha)).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um produto.", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnAdicionarAoCarrinho.setEnabled(false);
		buttonpane.add(btnAdicionarAoCarrinho);

		JPanel left = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) left.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		containerprodutos.add(left, BorderLayout.WEST);

		JPanel rigth = new JPanel();
		containerprodutos.add(rigth, BorderLayout.EAST);

	}

	private void pesquisar() {
		String nome = tfnome.getText();

		if (nome.isEmpty()) {
			produtosModel.setLinhas(pc.listarTodos());
		} else {
			produtosModel.setLinhas(pc.listarTodos(nome));
		}

	}
}
