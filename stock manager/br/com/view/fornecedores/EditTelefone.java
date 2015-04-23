package com.view.fornecedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

import com.dominio.Telefone;
import com.tablemodels.DefaultTableModel;
import com.util.plaindocument.MaxLenghtDocument;

public class EditTelefone extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfddd;
	private JTextField tfnumero;
	private Telefone telefone;
	private JButton okButton;
	private DefaultTableModel<Telefone> tableModelTelefone;
	private JComboBox operadoras;
	private int linha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditTelefone dialog = new EditTelefone(null, null, -1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditTelefone(Telefone telefone,
			DefaultTableModel<Telefone> tableModelTelefone, int linha) {
		setTitle("Cadastrar");
		this.telefone = telefone;
		this.tableModelTelefone = tableModelTelefone;
		this.linha = linha;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 221, 229);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelnomeproduto = new JPanel();
		panelnomeproduto.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panelnomeproduto.setBounds(10, 11, 62, 65);
		contentPanel.add(panelnomeproduto);
		panelnomeproduto.setLayout(null);

		JLabel lblNomeDoProduto = new JLabel("DDD");
		lblNomeDoProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoProduto.setBounds(10, 10, 118, 14);
		panelnomeproduto.add(lblNomeDoProduto);

		Document doc = new MaxLenghtDocument(2);
		tfddd = new JTextField(doc, "", 8);
		tfddd.setHorizontalAlignment(SwingConstants.RIGHT);
		tfddd.setBounds(10, 30, 40, 20);
		panelnomeproduto.add(tfddd);
		tfddd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}

			}
		});
		tfddd.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBounds(10, 86, 194, 65);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCusto = new JLabel("Operadora");
		lblCusto.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusto.setBounds(10, 10, 70, 14);
		panel.add(lblCusto);

		operadoras = new JComboBox(new String[] { "Claro", "Oi", "Net", "Tim",
				"Vivo" });
		operadoras.setBounds(10, 30, 171, 20);
		panel.add(operadoras);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBounds(82, 11, 122, 65);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		Document maxnumero = new MaxLenghtDocument(9);
		tfnumero = new JTextField(maxnumero, "", 8);
		tfnumero.setHorizontalAlignment(SwingConstants.RIGHT);
		tfnumero.setBounds(10, 30, 100, 20);
		tfnumero.setColumns(10);
		tfnumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_1.add(tfnumero);

		JLabel lblValor = new JLabel("N\u00FAmero");
		lblValor.setBounds(10, 11, 66, 14);
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblValor);
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

	private void cadastrar() {

		telefone = new Telefone();
		telefone.setDdd(tfddd.getText());
		telefone.setNumero(tfnumero.getText());
		telefone.setOperadora(operadoras.getSelectedItem().toString());

		String valid = telefone.valid();

		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			telefone = null;
			return;
		}
		if (tableModelTelefone != null)
			tableModelTelefone.add(telefone);
		JOptionPane.showMessageDialog(null, "Telefone adicionado com sucesso.");
		this.dispose();
	}

	private void alterar() {

		telefone.setDdd(tfddd.getText());
		telefone.setNumero(tfnumero.getText());
		telefone.setOperadora(operadoras.getSelectedItem().toString());

		String valid = telefone.valid();
		if (!valid.isEmpty()) {
			JOptionPane.showMessageDialog(null, valid, "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			telefone = null;
			return;
		}

		if (tableModelTelefone != null)
			tableModelTelefone.update(linha, telefone);

		JOptionPane.showMessageDialog(null, "Telefone alterado com sucesso.");
		this.dispose();

	}

	private void preencherCampos() {

		if (telefone == null)
			return;

		setTitle("Alterar");
		tfddd.setText(telefone.getDdd() + "");
		tfnumero.setText(telefone.getNumero() + "");
		operadoras.setSelectedItem(telefone.getOperadora());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (telefone == null)
				cadastrar();
			else
				alterar();

		}

	}
}
