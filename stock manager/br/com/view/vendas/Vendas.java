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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import net.sf.jasperreports.engine.JRException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		add(top, BorderLayout.NORTH);

		JPanel bottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottom.getLayout();
		flowLayout_1.setVgap(10);
		add(bottom, BorderLayout.SOUTH);

		JPanel center = new JPanel();
		center.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		center.add(panel, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable(tableModelVenda);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		center.add(panel_1, BorderLayout.CENTER);

		JLabel lblRelatrio = new JLabel("Relat\u00F3rio de vendas");
		lblRelatrio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lblRelatrio);

		JComboBox comboBox = new JComboBox(new String[] { "Mês", "Dia" });
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(comboBox);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipo = comboBox.getSelectedItem().toString();
				try {
					vc.gerarRelatorioVendas(tipo);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnGerar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnGerar);

	}

}
