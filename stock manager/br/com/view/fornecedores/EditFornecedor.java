package com.view.fornecedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.controllers.FornecedorController;
import com.dominio.Fornecedor;
import com.dominio.Telefone;
import com.tablemodels.DefaultTableModel;
import com.tablemodels.TelefoneTableModel;

import javax.swing.ImageIcon;
import java.awt.Font;

public class EditFornecedor extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfnome;
	private JTextField tfemail;
	private JTextField tfsite;
	private Fornecedor fornecedor;
	private FornecedorController controller;
	private JButton okButton;
	private DefaultTableModel<Fornecedor> tableModelFornecedor;
	private JTable table;
	private DefaultTableModel<Telefone> tableModelTelefone;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFornecedor dialog = new EditFornecedor(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditFornecedor(Fornecedor fornecedor,
			DefaultTableModel<Fornecedor> tableModelFornecedor) {
		setTitle("Cadastrar");
		this.fornecedor = fornecedor;
		controller = new FornecedorController();
		this.tableModelFornecedor = tableModelFornecedor;
		this.tableModelTelefone = new TelefoneTableModel();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelnomeproduto = new JPanel();
		panelnomeproduto.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panelnomeproduto.setBounds(10, 11, 424, 65);
		contentPanel.add(panelnomeproduto);
		panelnomeproduto.setLayout(null);

		JLabel lblNomeDoProduto = new JLabel("Nome do fornecedor");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoProduto.setBounds(10, 10, 136, 14);
		panelnomeproduto.add(lblNomeDoProduto);

		tfnome = new JTextField();
		tfnome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfnome.setHorizontalAlignment(SwingConstants.LEFT);
		tfnome.setBounds(10, 30, 404, 20);
		panelnomeproduto.add(tfnome);
		tfnome.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBounds(10, 311, 424, 65);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCusto = new JLabel("Email");
		lblCusto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCusto.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusto.setBounds(10, 10, 36, 14);
		panel.add(lblCusto);

		tfemail = new JTextField();
		tfemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfemail.setHorizontalAlignment(SwingConstants.LEFT);
		tfemail.setBounds(10, 30, 404, 20);
		panel.add(tfemail);
		tfemail.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBounds(10, 235, 424, 65);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		tfsite = new JTextField();
		tfsite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfsite.setHorizontalAlignment(SwingConstants.LEFT);
		tfsite.setBounds(10, 30, 404, 20);
		tfsite.setColumns(10);
		panel_1.add(tfsite);

		JLabel lblValor = new JLabel("Site");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 10, 34, 14);
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblValor);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_2.setBounds(10, 88, 424, 136);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblQuantidade = new JLabel("Telefones");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(10, 10, 79, 14);
		panel_2.add(lblQuantidade);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 30, 361, 92);
		panel_2.add(scrollPane);

		table = new JTable(tableModelTelefone);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						button_1.setEnabled(true);
						button_2.setEnabled(true);

					}
				});
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				button_1.setEnabled(false);
				button_2.setEnabled(false);

			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(EditFornecedor.class
				.getResource("/imagens/add.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditTelefone eft = new EditTelefone(null, tableModelTelefone,
						-1);
				eft.setVisible(true);
			}
		});
		button.setToolTipText("Novo telefone");
		button.setBounds(385, 30, 26, 26);
		panel_2.add(button);

		button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(EditFornecedor.class
				.getResource("/imagens/edit.png")));
		button_1.setEnabled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();
				Telefone t = tableModelTelefone.find(linha);
				EditTelefone et = new EditTelefone(t, tableModelTelefone, linha);
				et.setVisible(true);

			}
		});
		button_1.setToolTipText("Alterar telefone");
		button_1.setBounds(385, 63, 26, 26);
		panel_2.add(button_1);

		button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(EditFornecedor.class
				.getResource("/imagens/delete.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				tableModelTelefone.remove(linha);
			}
		});
		button_2.setEnabled(false);
		button_2.setToolTipText("Remover telefone");
		button_2.setBounds(385, 98, 26, 26);
		panel_2.add(button_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Salvar");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.addActionListener(this);
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

		preencherCampos();
	}

	private void cadastrar() {

		fornecedor = new Fornecedor();
		fornecedor.setNome(tfnome.getText());
		List<Telefone> telefones = tableModelTelefone.getLinhas();
		for (Telefone telefone : telefones) {
			telefone.setFornecedor(fornecedor);
		}
		fornecedor.setTelefones(telefones);
		fornecedor.setSite(tfsite.getText());
		fornecedor.setEmail(tfemail.getText());

		String valid = fornecedor.valid();

		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			fornecedor = null;
			return;
		}

		controller.cadastrar(fornecedor);
		if (tableModelFornecedor != null)
			tableModelFornecedor.setLinhas(controller.listarTodos());
		JOptionPane.showMessageDialog(null,
				"Fornecedor cadastrado com sucesso.", "Sucesso",
				JOptionPane.PLAIN_MESSAGE);
		dispose();

	}

	private void alterar() {

		fornecedor.setNome(tfnome.getText());
		List<Telefone> telefones = tableModelTelefone.getLinhas();
		for (Telefone telefone : telefones) {
			telefone.setFornecedor(fornecedor);
		}
		fornecedor.setTelefones(telefones);
		fornecedor.setSite(tfsite.getText());
		fornecedor.setEmail(tfemail.getText());

		String valid = fornecedor.valid();
		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			fornecedor = null;
			return;
		}
		controller.alterar(fornecedor);
		if (tableModelFornecedor != null)
			tableModelFornecedor.setLinhas(controller.listarTodos());
		JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso.",
				"Sucesso", JOptionPane.PLAIN_MESSAGE);
		dispose();
	}

	private void preencherCampos() {

		if (fornecedor == null)
			return;

		setTitle("Alterar");
		tfnome.setText(fornecedor.getNome());
		tableModelTelefone.setLinhas(fornecedor.getTelefones());
		tfsite.setText(fornecedor.getSite());
		tfemail.setText(fornecedor.getEmail());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {

			if (fornecedor == null)
				cadastrar();
			else
				alterar();

		}

	}
}
