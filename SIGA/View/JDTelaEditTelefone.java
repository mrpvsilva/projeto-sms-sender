package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.Telefone;
import Dominio.TelefoneFornecedor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import TableModels.AbstractDefaultTableModel;
import TableModels.TelefoneTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class JDTelaEditTelefone extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvar;
	private JButton JBSair;
	private int _linha = -1;
	private TelefoneFornecedor _telefone;
	private AbstractDefaultTableModel<TelefoneFornecedor> _model;
	private JTextField tfddd;
	private JTextField tfnumero;
	private JComboBox<String> cboperadoras;
	private String[] operadoras = new String[] { "CLARO", "OI", "TIM", "VIVO" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditTelefone dialog = new JDTelaEditTelefone(-1, null, null);
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditTelefone(int linha, TelefoneFornecedor telefone,
			AbstractDefaultTableModel<TelefoneFornecedor> model) {
		setTitle("SIGA - cad. telefone");
		_telefone = telefone;
		_model = model;
		_linha = linha;
		setBounds(100, 100, 223, 178);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDdd = new JLabel("DDD");
		lblDdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDdd.setBounds(0, 13, 62, 14);
		contentPanel.add(lblDdd);

		tfddd = new JTextField();
		tfddd.setBounds(65, 11, 52, 20);
		contentPanel.add(tfddd);
		tfddd.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(0, 38, 62, 14);
		contentPanel.add(lblNmero);

		tfnumero = new JTextField();
		tfnumero.setBounds(65, 35, 128, 20);
		contentPanel.add(tfnumero);
		tfnumero.setColumns(10);

		JLabel lblOperadora = new JLabel("Operadora");
		lblOperadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperadora.setBounds(0, 67, 62, 14);
		contentPanel.add(lblOperadora);

		cboperadoras = new JComboBox<String>(operadoras);
		cboperadoras.setBounds(65, 65, 128, 20);
		contentPanel.add(cboperadoras);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setIcon(new ImageIcon(JDTelaEditTelefone.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setIcon(new ImageIcon(JDTelaEditTelefone.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_X);
				buttonPane.add(JBSair);
			}
		}

		PreencherCampos();
	}

	private void Create() {
		_telefone = new TelefoneFornecedor(tfddd.getText(), tfnumero.getText(),
				cboperadoras.getSelectedItem().toString());
		_model.add(_telefone);
	}

	private void Update() {
		_telefone.setDdd(tfddd.getText());
		_telefone.setNumero(tfnumero.getText());
		_telefone.setOperadora(cboperadoras.getSelectedItem().toString());
		_model.update(_linha, _telefone);
	}

	private void PreencherCampos() {
		if (_telefone != null) {
			tfddd.setText(_telefone.getDdd());
			tfnumero.setText(_telefone.getNumero());
			cboperadoras.setSelectedItem(_telefone.getOperadora());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSalvar) {
			if (_telefone != null) {
				Update();
			} else {
				Create();
			}
			this.dispose();
		}

		if (e.getSource() == JBSair) {
			this.dispose();
		}

	}
}
