package com.view.caixa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.BadLocationException;

import jmoneyfield.JMoneyField;

import com.controllers.VendaController;
import com.dominio.Produto;
import com.dominio.ProdutoVendido;
import com.dominio.Venda;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.ProdutoTableModel;
import com.tablemodels.ProdutoVendidoTableModel;
import com.util.carrinho.Carrinho;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;

public class Caixa extends JPanel {
	private JMoneyField tftotal;
	private JMoneyField tfdesconto;
	private JMoneyField tfvalorpago;
	private JMoneyField tftroco;
	private JTable table;
	private DefaultTableModel<ProdutoVendido> tableModel;
	private DefaultTableModel<Produto> tableModelProdutos;
	private Venda venda;
	private VendaController controller;
	private JButton btnRemoverProduto;
	private JButton btnFinalizarVenda;
	private JTextField txtpsq;
	private JTextField txtqtd;
	private JTable table_1;
	private JButton btnaddproduto;

	public Caixa() {
		controller = new VendaController();
		setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(1024, 600));
		tableModel = new ProdutoVendidoTableModel();
		JPanel top = new JPanel();
		FlowLayout fl_top = (FlowLayout) top.getLayout();
		add(top, BorderLayout.NORTH);

		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		center.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						btnRemoverProduto.setEnabled(true);

					}
				});
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				btnRemoverProduto.setEnabled(false);

			}
		});

		scrollPane.setViewportView(table);

		JPanel bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));

		JPanel bottom_center = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottom_center.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		bottom_center.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bottom.add(bottom_center, BorderLayout.CENTER);

		btnRemoverProduto = new JButton("Remover produto");
		btnRemoverProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					Carrinho.removerProduto(linha);
					carregarVenda();
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um produto.");
				}
			}
		});

		JButton btnNovaVenda = new JButton("Nova venda");
		btnNovaVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Carrinho.limparCarrinho();
				carregarVenda();
			}
		});

		btnaddproduto = new JButton("Adicionar produto");
		btnaddproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarProduto();
			}
		});
		btnaddproduto.setEnabled(false);
		btnaddproduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bottom_center.add(btnaddproduto);
		btnaddproduto.setHorizontalAlignment(SwingConstants.LEFT);
		bottom_center.add(btnNovaVenda);
		btnRemoverProduto.setEnabled(false);
		bottom_center.add(btnRemoverProduto);

		btnFinalizarVenda = new JButton("Finalizar venda");
		btnFinalizarVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFinalizarVenda.setEnabled(false);
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnFinalizarVenda.setEnabled(false);
				try {
					venda.setDesconto(tfdesconto.getValor()!=null?tfdesconto.getValor():new BigDecimal(0));
					venda.setValorRecebido(tfvalorpago.getValor());
					venda.setTroco(tftroco.getValor());
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				controller.cadastrar(venda);
				Carrinho.limparCarrinho();
				carregarVenda();
			}
		});
		bottom_center.add(btnFinalizarVenda);

		JPanel bottom_left = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottom_left.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		bottom.add(bottom_left, BorderLayout.WEST);

		JPanel bottom_rigth = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) bottom_rigth.getLayout();
		flowLayout_7.setHgap(10);
		bottom.add(bottom_rigth, BorderLayout.EAST);

		JPanel bottom_bottom = new JPanel();
		bottom.add(bottom_bottom, BorderLayout.SOUTH);

		JPanel bottom_top = new JPanel();
		bottom.add(bottom_top, BorderLayout.NORTH);

		JPanel rigth = new JPanel();
		add(rigth, BorderLayout.EAST);
		rigth.setLayout(new BorderLayout(0, 0));

		JPanel pagamento = new JPanel();
		pagamento.setBorder(new LineBorder(Color.LIGHT_GRAY));
		rigth.add(pagamento, BorderLayout.CENTER);
		pagamento.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel paneltotal = new JPanel();
		pagamento.add(paneltotal);
		paneltotal.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblValorTotal = new JLabel("Total da venda");
		paneltotal.add(lblValorTotal);
		lblValorTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tftotal = new JMoneyField();
		paneltotal.add(tftotal);
		tftotal.setBackground(new Color(176, 224, 230));
		tftotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tftotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tftotal.setText("");
		tftotal.setEditable(false);
		tftotal.setColumns(20);

		JPanel paneldesconto = new JPanel();
		pagamento.add(paneldesconto);
		paneldesconto.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesconto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paneldesconto.add(lblDesconto);

		tfdesconto = new JMoneyField();
		tfdesconto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					calcularTroco();
					liberarVenda();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tfdesconto.setHorizontalAlignment(SwingConstants.RIGHT);
		tfdesconto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfdesconto.setColumns(15);
		paneldesconto.add(tfdesconto);

		JPanel panelvalorpago = new JPanel();
		pagamento.add(panelvalorpago);
		panelvalorpago.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblValorPago = new JLabel("Valor recebido");
		lblValorPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelvalorpago.add(lblValorPago);

		tfvalorpago = new JMoneyField();
		tfvalorpago.setHorizontalAlignment(SwingConstants.RIGHT);
		tfvalorpago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfvalorpago.setColumns(15);
		tfvalorpago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					calcularTroco();
					liberarVenda();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		panelvalorpago.add(tfvalorpago);

		JPanel paneltroco = new JPanel();
		pagamento.add(paneltroco);
		paneltroco.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTroco = new JLabel("Valor troco");
		lblTroco.setHorizontalAlignment(SwingConstants.LEFT);
		paneltroco.add(lblTroco);
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tftroco = new JMoneyField();
		tftroco.setEditable(false);
		tftroco.setHorizontalAlignment(SwingConstants.RIGHT);
		tftroco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tftroco.setColumns(15);
		paneltroco.add(tftroco);

		JLabel label = new JLabel("");
		pagamento.add(label);

		JPanel left_pag = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) left_pag.getLayout();
		rigth.add(left_pag, BorderLayout.WEST);

		JPanel rigth_pag = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) rigth_pag.getLayout();
		rigth.add(rigth_pag, BorderLayout.EAST);

		JPanel left = new JPanel();
		add(left, BorderLayout.WEST);
		left.setLayout(new BorderLayout(0, 0));

		JPanel left_left = new JPanel();
		left.add(left_left, BorderLayout.WEST);

		JPanel lef_rigth = new JPanel();
		left.add(lef_rigth, BorderLayout.EAST);

		JPanel left_center = new JPanel();
		left_center.setBorder(new LineBorder(Color.LIGHT_GRAY));
		left.add(left_center, BorderLayout.CENTER);
		left_center.setLayout(new BorderLayout(5, 5));

		JPanel pesquisa = new JPanel();
		pesquisa.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pesquisa.add(lblProduto);

		txtpsq = new JTextField();
		txtpsq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = txtpsq.getText();
				if (e.getKeyCode() == 10 || txt.length() > 1) {
					pesquisarProduto();
				} else {
					tableModelProdutos.clear();
				}
			}
		});
		txtpsq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pesquisa.add(txtpsq);
		txtpsq.setColumns(12);

		JLabel lblNewLabel = new JLabel("Quant.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pesquisa.add(lblNewLabel);
		left_center.add(pesquisa, BorderLayout.NORTH);

		txtqtd = new JTextField();
		txtqtd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtqtd.setHorizontalAlignment(SwingConstants.RIGHT);
		txtqtd.setText("1");
		txtqtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		pesquisa.add(txtqtd);
		txtqtd.setColumns(5);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 10)
					pesquisarProduto();

			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisarProduto();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pesquisa.add(btnPesquisar);

		JScrollPane scrollPane_1 = new JScrollPane();

		left_center.add(scrollPane_1, BorderLayout.CENTER);

		tableModelProdutos = new ProdutoTableModel();
		table_1 = new JTable(tableModelProdutos);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					adicionarProduto();
				}
			}
		});

		table_1.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						int linha = table_1.getSelectedRow();
						boolean enable = tableModelProdutos.find(linha)
								.getQuantidade() > 0 ? true : false;
						btnaddproduto.setEnabled(enable);

					}
				});
		table_1.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				btnaddproduto.setEnabled(false);

			}
		});

		table_1.getColumnModel().getColumn(0).setMinWidth(0);
		table_1.getColumnModel().getColumn(0).setMaxWidth(0);
		table_1.getColumnModel().getColumn(2).setMinWidth(0);
		table_1.getColumnModel().getColumn(2).setMaxWidth(0);

		scrollPane_1.setViewportView(table_1);
		carregarVenda();
	}

	private void calcularTroco() throws BadLocationException {
		BigDecimal total = tftotal.getValor() != null ? tftotal.getValor()
				: new BigDecimal(0);
		BigDecimal desc = tfdesconto.getValor() != null ? tfdesconto.getValor()
				: new BigDecimal(0);
		BigDecimal pag = tfvalorpago.getValor() != null ? tfvalorpago
				.getValor() : new BigDecimal(0);

		BigDecimal troco = pag.subtract(total.subtract(desc));
		if (troco.doubleValue() > 0) {
			tftroco.setValor(troco);
		} else {
			tftroco.setValor(new BigDecimal(0));
		}

	}

	private void liberarVenda() throws BadLocationException {
		BigDecimal desc = tfdesconto.getValor() != null ? tfdesconto.getValor()
				: new BigDecimal(0);
		BigDecimal vp = tfvalorpago.getValor() != null ? tfvalorpago.getValor()
				: new BigDecimal(0);

		BigDecimal vt = tftotal.getValor() != null ? tftotal.getValor()
				: new BigDecimal(0);

		vp = vp.add(desc);

		if (vt.doubleValue() != 0 && vp.doubleValue() >= vt.doubleValue())
			btnFinalizarVenda.setEnabled(true);
		else
			btnFinalizarVenda.setEnabled(false);
	}

	private void carregarVenda() {
		venda = Carrinho.getCarrinho();
		tableModel.setLinhas(venda.getProdutosvendidos());
		tftotal.setValor(venda.getValortotal());
		tfdesconto.setValor(null);
		tfvalorpago.setValor(null);
		tftroco.setValor(null);
	}

	private void pesquisarProduto() {
		String txt = txtpsq.getText();
		if (txt.length() > 0)
			tableModelProdutos.setLinhas(controller.pesquisarProduto(txt));
	}

	private void adicionarProduto() {
		int qtd = !txtqtd.getText().isEmpty() ? Integer.parseInt(txtqtd
				.getText()) : 0;
		int linha = table_1.getSelectedRow();

		if (qtd < 1) {

			JOptionPane.showMessageDialog(null, "Informe a quantidade",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			txtqtd.requestFocus();
			return;
		}

		if (linha < 0) {
			JOptionPane.showMessageDialog(null, "Selecione um produto.",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			txtqtd.requestFocus();
			return;
		}

		Produto p = tableModelProdutos.find(linha);

		if (p.getQuantidade() < qtd) {
			JOptionPane.showMessageDialog(null,
					"Quantidade informada maior do que há em estoque",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			txtqtd.requestFocus();
			return;
		}

		Carrinho.add(p, qtd);
		carregarVenda();

	}

	public static void main(String[] args) {

	}
}
