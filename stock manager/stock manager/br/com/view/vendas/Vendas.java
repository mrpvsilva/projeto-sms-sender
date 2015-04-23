package com.view.vendas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.dominio.ProdutoVendido;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.ProdutoVendidoTableModel;

public class Vendas extends JPanel {
	private JTextField txtR;
	private JTextField txtR_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel<ProdutoVendido> tableModel;

	public Vendas() {
		setLayout(new BorderLayout(0, 0));
		tableModel = new ProdutoVendidoTableModel();
		JPanel bucarproduto = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bucarproduto.getLayout();
		flowLayout.setVgap(50);
		add(bucarproduto, BorderLayout.NORTH);

		JPanel gridlistaproduto = new JPanel();
		add(gridlistaproduto, BorderLayout.CENTER);
		gridlistaproduto.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		gridlistaproduto.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JPanel bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));

		JPanel botoes = new JPanel();
		botoes.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bottom.add(botoes, BorderLayout.CENTER);

		JButton btnNovaVenda = new JButton("Nova venda");
		botoes.add(btnNovaVenda);

		JButton btnNewButton = new JButton("Cancelar venda");
		botoes.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnFinalizarVenda = new JButton("Finalizar venda");
		botoes.add(btnFinalizarVenda);

		JPanel bottom_left = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottom_left.getLayout();
		flowLayout_1.setHgap(10);
		bottom.add(bottom_left, BorderLayout.WEST);

		JPanel bottom_rigth = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) bottom_rigth.getLayout();
		flowLayout_7.setHgap(139);
		bottom.add(bottom_rigth, BorderLayout.EAST);

		JPanel bottom_bottom = new JPanel();
		bottom.add(bottom_bottom, BorderLayout.SOUTH);

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

		JLabel lblValorTotal = new JLabel("Total a pagar");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		paneltotal.add(lblValorTotal);

		txtR = new JTextField();
		txtR.setBackground(new Color(176, 224, 230));
		txtR.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtR.setHorizontalAlignment(SwingConstants.RIGHT);
		txtR.setText("R$ 0,00");
		txtR.setEditable(false);
		paneltotal.add(txtR);
		txtR.setColumns(10);

		JPanel paneldesconto = new JPanel();
		pagamento.add(paneldesconto);
		paneldesconto.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesconto.setFont(new Font("Tahoma", Font.BOLD, 16));
		paneldesconto.add(lblDesconto);

		txtR_1 = new JTextField();
		txtR_1.setText("R$ 0,00");
		txtR_1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtR_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtR_1.setColumns(10);
		paneldesconto.add(txtR_1);

		JPanel panelvalorpago = new JPanel();
		pagamento.add(panelvalorpago);
		panelvalorpago.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblValorPago = new JLabel("Valor pago");
		lblValorPago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorPago.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelvalorpago.add(lblValorPago);

		textField = new JTextField();
		textField.setText("R$ 0,00");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));
		textField.setColumns(10);
		panelvalorpago.add(textField);

		JPanel paneltroco = new JPanel();
		pagamento.add(paneltroco);
		paneltroco.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setHorizontalAlignment(SwingConstants.RIGHT);
		paneltroco.add(lblTroco);
		lblTroco.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("R$ 0,00");
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		textField_1.setColumns(10);
		paneltroco.add(textField_1);

		JLabel lblNewLabel = new JLabel("");
		pagamento.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		pagamento.add(label_1);

		JLabel label_2 = new JLabel("");
		pagamento.add(label_2);

		JLabel lblNewLabel_1 = new JLabel("");
		pagamento.add(lblNewLabel_1);

		JPanel left_pag = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) left_pag.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		rigth.add(left_pag, BorderLayout.WEST);

		JPanel rigth_pag = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) rigth_pag.getLayout();
		flowLayout_4.setVgap(10);
		flowLayout_4.setHgap(10);
		rigth.add(rigth_pag, BorderLayout.EAST);

		JPanel top = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) top.getLayout();
		flowLayout_5.setVgap(10);
		flowLayout_5.setHgap(10);
		rigth.add(top, BorderLayout.NORTH);

		JPanel botton = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) botton.getLayout();
		flowLayout_6.setVgap(10);
		flowLayout_6.setHgap(10);
		rigth.add(botton, BorderLayout.SOUTH);

		JPanel left = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) left.getLayout();
		flowLayout_2.setHgap(10);
		add(left, BorderLayout.WEST);

	}
}
