package com.main.TipoItem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.applications.*;
import com.domain.TipoItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox;
	private TipoItemApplication tiapp;
	private TipoItem tipoItem;
	private int op;
	private TableModelTipoItem tmti;
	private int rowIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditForm frame = new EditForm(0, "Novo", new TipoItem(),
							null, 0);
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
	public EditForm(int opcao, String title, TipoItem tipo,
			TableModelTipoItem tmti, int rowIndex) {

		tipoItem = tipo;
		op = opcao;
		this.tmti = tmti;
		this.rowIndex = rowIndex;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 363, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipoItem = new JLabel("Tipo item:");
		lblTipoItem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoItem.setBounds(10, 20, 61, 14);
		contentPane.add(lblTipoItem);

		textField = new JTextField(tipoItem.getNome());
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				GetTipoEvent();
			}
		});
		textField.setBounds(69, 18, 190, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Salvar();
			}
		});
		btnNewButton.setBounds(120, 49, 89, 23);
		contentPane.add(btnNewButton);

		chckbxNewCheckBox = new JCheckBox("Ativo");
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GetChkEvent();
			}
		});
		chckbxNewCheckBox.setBounds(265, 17, 73, 23);
		chckbxNewCheckBox.setSelected(tipoItem.isAtivo());
		contentPane.add(chckbxNewCheckBox);

	}

	private void GetTipoEvent() {
		tipoItem.setNome(textField.getText());

	}

	private void GetChkEvent() {
		tipoItem.setAtivo(chckbxNewCheckBox.isSelected());
		System.out.println(tipoItem.isAtivo());
	}

	private void Salvar() {

		tiapp = new TipoItemApplication();
		boolean success = true;
		switch (op) {
		case 0:
			TipoItem novo = new TipoItem();
			novo.setNome(textField.getText());
			novo.setAtivo(chckbxNewCheckBox.isSelected());
			success = tiapp.Add(novo);
			if (success) {
				tmti.Inserir(novo);
				textField.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Falha no cadastro");
			}
			break;
		case 1:
			tipoItem.setNome(textField.getText());
			tipoItem.setAtivo(chckbxNewCheckBox.isSelected());
			success = tiapp.Update(tipoItem);
			if (success) {
				tmti.Editar(rowIndex, tipoItem);
			} else {
				JOptionPane.showMessageDialog(null, "Falha no atualizar");
			}
			break;

		}

	}
}
