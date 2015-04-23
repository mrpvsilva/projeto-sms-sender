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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

import com.controllers.ProdutoController;
import com.dominio.Produto;
import com.tablemodels.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import jmoneyfield.JMoneyField;

import java.awt.event.KeyAdapter;

public class EditProduto extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfnome;
	private JMoneyField tfcusto;
	private JMoneyField tfvalor;
	private JTextField tfquantidade;
	private Produto produto;
	private ProdutoController controller;
	private JButton okButton;
	private DefaultTableModel<Produto> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditProduto dialog = new EditProduto(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditProduto(Produto produto, DefaultTableModel<Produto> model) {
		setTitle("Cadastrar");
		this.produto = produto;
		controller = new ProdutoController();
		this.model = model;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelnomeproduto = new JPanel();
		panelnomeproduto.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panelnomeproduto.setBounds(10, 11, 424, 65);
		contentPanel.add(panelnomeproduto);
		panelnomeproduto.setLayout(null);

		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoProduto.setBounds(10, 10, 107, 14);
		panelnomeproduto.add(lblNomeDoProduto);

		tfnome = new JTextField();
		tfnome.setHorizontalAlignment(SwingConstants.LEFT);
		tfnome.setBounds(10, 30, 404, 20);
		panelnomeproduto.add(tfnome);
		tfnome.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBounds(10, 85, 210, 65);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCusto = new JLabel("Custo");
		lblCusto.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusto.setBounds(10, 10, 36, 14);
		panel.add(lblCusto);

		tfcusto = new JMoneyField();
		tfcusto.setHorizontalAlignment(SwingConstants.RIGHT);
		tfcusto.setBounds(10, 30, 190, 20);
		panel.add(tfcusto);
		tfcusto.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBounds(230, 85, 210, 65);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		tfvalor = new JMoneyField();
		tfvalor.setHorizontalAlignment(SwingConstants.RIGHT);
		tfvalor.setBounds(10, 30, 190, 20);
		tfvalor.setColumns(10);
		panel_1.add(tfvalor);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 10, 34, 14);
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblValor);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_2.setBounds(10, 160, 210, 65);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 10, 70, 14);
		panel_2.add(lblQuantidade);

		tfquantidade = new JTextField();
		tfquantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		tfquantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		tfquantidade.setBounds(10, 30, 190, 20);
		panel_2.add(tfquantidade);
		tfquantidade.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Salvar");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		preencherCampos();
	}

	private void cadastrar() throws BadLocationException {
		produto = new Produto();
		produto.setNome(tfnome.getText());
		produto.setCusto(tfcusto.getValor());
		produto.setValor(tfvalor.getValor());
		produto.setQuantidade(!tfquantidade.getText().isEmpty() ? Integer
				.parseInt(tfquantidade.getText()) : 0);

		String valid = produto.valid();

		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			produto = null;
			return;
		}

		controller.cadastrar(produto);
		if (model != null)
			model.setLinhas(controller.listarTodos());
		JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.",
				"Sucesso", JOptionPane.PLAIN_MESSAGE);
		dispose();

	}

	private void alterar() throws BadLocationException {
		produto.setNome(tfnome.getText());
		produto.setCusto(tfcusto.getValor());
		produto.setValor(tfvalor.getValor());
		produto.setQuantidade(!tfquantidade.getText().isEmpty() ? Integer
				.parseInt(tfquantidade.getText()) : 0);

		String valid = produto.valid();

		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			produto = null;
			return;
		}
		controller.alterar(produto);
		if (model != null)
			model.setLinhas(controller.listarTodos());
		JOptionPane.showMessageDialog(null, "Produto alterado com sucesso.",
				"Sucesso", JOptionPane.PLAIN_MESSAGE);
		dispose();
	}

	private void preencherCampos() {

		if (produto == null)
			return;

		setTitle("Alterar");
		tfnome.setText(produto.getNome());
		tfcusto.setValor(produto.getCusto());
		tfvalor.setValor(produto.getValor());
		tfquantidade.setText(produto.getQuantidade() + "");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {

			if (produto == null)
				try {
					cadastrar();
				} catch (BadLocationException e1) {

				}
			else
				try {
					alterar();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		}

	}
}
