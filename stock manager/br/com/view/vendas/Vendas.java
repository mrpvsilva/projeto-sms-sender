package com.view.vendas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jmoneyfield.JMoneyField;

import com.dominio.ProdutoVendido;
import com.dominio.Venda;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.ProdutoVendidoTableModel;
import com.util.carrinho.Carrinho;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class Vendas extends JPanel {
	private JMoneyField tftotal;
	private JMoneyField tfdesconto;
	private JMoneyField tfvalorpago;
	private JMoneyField tftroco;
	private JTable table;
	private DefaultTableModel<ProdutoVendido> tableModel;
	private Venda venda;

	public Vendas() {
		setLayout(new BorderLayout(0, 0));
		tableModel = new ProdutoVendidoTableModel();
		JPanel top = new JPanel();
		FlowLayout fl_top = (FlowLayout) top.getLayout();
		fl_top.setVgap(50);
		add(top, BorderLayout.NORTH);

		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		center.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));

		JPanel bottom_center = new JPanel();
		bottom_center.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bottom.add(bottom_center, BorderLayout.CENTER);

		JButton btnNovaVenda = new JButton("Nova venda");
		bottom_center.add(btnNovaVenda);

		JButton btnNewButton = new JButton("Cancelar venda");
		bottom_center.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnFinalizarVenda = new JButton("Finalizar venda");
		bottom_center.add(btnFinalizarVenda);

		JPanel bottom_left = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottom_left.getLayout();
		flowLayout_1.setHgap(30);
		bottom.add(bottom_left, BorderLayout.WEST);

		JPanel bottom_rigth = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) bottom_rigth.getLayout();
		flowLayout_7.setHgap(148);
		bottom.add(bottom_rigth, BorderLayout.EAST);

		JPanel bottom_bottom = new JPanel();
		bottom.add(bottom_bottom, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		bottom.add(panel, BorderLayout.NORTH);

		JPanel rigth = new JPanel();
		add(rigth, BorderLayout.EAST);
		rigth.setLayout(new BorderLayout(0, 0));

		JPanel pagamento = new JPanel();
		pagamento.setBorder(new LineBorder(Color.LIGHT_GRAY));
		rigth.add(pagamento, BorderLayout.CENTER);
		pagamento.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("");
		pagamento.add(lblNewLabel);

		JPanel paneltotal = new JPanel();
		pagamento.add(paneltotal);
		paneltotal.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblValorTotal = new JLabel("Total a pagar");
		lblValorTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		paneltotal.add(lblValorTotal);

		tftotal = new JMoneyField();
		tftotal.setBackground(new Color(176, 224, 230));
		tftotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		tftotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tftotal.setText("");
		tftotal.setEditable(false);
		paneltotal.add(tftotal);
		tftotal.setColumns(20);

		JPanel paneldesconto = new JPanel();
		pagamento.add(paneldesconto);
		paneldesconto.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesconto.setFont(new Font("Tahoma", Font.BOLD, 16));
		paneldesconto.add(lblDesconto);

		tfdesconto = new JMoneyField();
		tfdesconto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					BigDecimal vt = tftotal.getValor().subtract(
							tfdesconto.getValor());
					tftroco.setValor(tfvalorpago.getValor().subtract(vt));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tfdesconto.setHorizontalAlignment(SwingConstants.RIGHT);
		tfdesconto.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfdesconto.setColumns(15);
		paneldesconto.add(tfdesconto);

		JPanel panelvalorpago = new JPanel();
		pagamento.add(panelvalorpago);
		panelvalorpago.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblValorPago = new JLabel("Valor pago");
		lblValorPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorPago.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelvalorpago.add(lblValorPago);

		tfvalorpago = new JMoneyField();
		tfvalorpago.setHorizontalAlignment(SwingConstants.RIGHT);
		tfvalorpago.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfvalorpago.setColumns(15);
		tfvalorpago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					BigDecimal vt = tftotal.getValor().subtract(
							tfdesconto.getValor());
					tftroco.setValor(tfvalorpago.getValor().subtract(vt));
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

		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setHorizontalAlignment(SwingConstants.LEFT);
		paneltroco.add(lblTroco);
		lblTroco.setFont(new Font("Tahoma", Font.BOLD, 16));

		tftroco = new JMoneyField();
		tftroco.setEditable(false);
		tftroco.setHorizontalAlignment(SwingConstants.RIGHT);
		tftroco.setFont(new Font("Tahoma", Font.BOLD, 12));
		tftroco.setColumns(15);
		paneltroco.add(tftroco);

		JLabel label_1 = new JLabel("");
		pagamento.add(label_1);

		JLabel label_2 = new JLabel("");
		pagamento.add(label_2);

		JLabel lblNewLabel_1 = new JLabel("");
		pagamento.add(lblNewLabel_1);

		JPanel left_pag = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) left_pag.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(20);
		rigth.add(left_pag, BorderLayout.WEST);

		JPanel rigth_pag = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) rigth_pag.getLayout();
		flowLayout_4.setVgap(10);
		flowLayout_4.setHgap(10);
		rigth.add(rigth_pag, BorderLayout.EAST);

		JPanel left = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) left.getLayout();
		flowLayout_2.setHgap(30);
		add(left, BorderLayout.WEST);
		carregarVenda();
	}

	private void carregarVenda() {
		venda = Carrinho.getCarrinho();
		tableModel.setLinhas(venda.getProdutosvendidos());
		tftotal.setValor(venda.getValortotal());
		tfdesconto.setValor(new BigDecimal(0.00));
		tfvalorpago.setValor(new BigDecimal(0.00));

	}
}
