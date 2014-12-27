package com.main.TipoItem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.domain.TipoItem;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainTipoItens extends JFrame {

	private JPanel contentPane;
	private TableModelTipoItem tmti;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainTipoItens frame = new MainTipoItens();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainTipoItens() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(33, 11, 290, 206);
		contentPane.add(scrollPane);

		tmti = new TableModelTipoItem();

		table = new JTable(tmti);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(40);

		scrollPane.setViewportView(table);

		JButton btnNovoTipoItem = new JButton("Novo");
		btnNovoTipoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditForm form = new EditForm(0, "Cadastrar", new TipoItem(),
						tmti,0);
				form.setVisible(true);
			}
		});
		btnNovoTipoItem.setBounds(33, 228, 78, 23);
		contentPane.add(btnNovoTipoItem);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lineSelected = table.getSelectedRow();

				if (lineSelected < 0) {
					System.out.println("Selecione uma linha");
				} else {
					TipoItem tipo = tmti.GetTipoItem(lineSelected);
					EditForm ef = new EditForm(1, "Editar", tipo, tmti,lineSelected);
					ef.setVisible(true);
				}

			}
		});
		btnEditar.setBounds(121, 228, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tmti.fireTableDataChanged();
			}
		});
		btnAtualizar.setBounds(220, 228, 89, 23);
		contentPane.add(btnAtualizar);

	}
}
