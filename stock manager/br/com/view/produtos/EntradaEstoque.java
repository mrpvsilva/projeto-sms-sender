package com.view.produtos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controllers.ProdutoController;
import com.dominio.Produto;
import com.tablemodels.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class EntradaEstoque extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField tfqtd;
	private ProdutoController controller;
	private DefaultTableModel<Produto> tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EntradaEstoque dialog = new EntradaEstoque(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EntradaEstoque(Produto produto, DefaultTableModel<Produto> tableModel) {
		setTitle("Entrada de estoque");
		controller = new ProdutoController();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 414, 77);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoProduto.setBounds(10, 11, 143, 14);
		panel.add(lblNomeDoProduto);

		textField = new JTextField(produto.getNome());
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBounds(10, 27, 394, 20);
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(10, 99, 414, 60);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(10, 11, 105, 14);
		panel_1.add(lblQuantidade);

		tfqtd = new JTextField();
		tfqtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfqtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		tfqtd.setBounds(10, 27, 200, 20);
		panel_1.add(tfqtd);
		tfqtd.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!tfqtd.getText().isEmpty()) {
							int q = Integer.parseInt(tfqtd.getText());
							produto.setQuantidade(q + produto.getQuantidade());
							controller.alterar(produto);
							tableModel.setLinhas(controller.listarTodos());
							JOptionPane.showMessageDialog(null,
									"Entrada realizada com sucesso.");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Informe um quantidade válida.", "Atenção",
									JOptionPane.WARNING_MESSAGE);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
