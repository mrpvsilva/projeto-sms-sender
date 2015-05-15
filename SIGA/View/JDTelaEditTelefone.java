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
import java.awt.Font;

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
	private JComboBox cboperadoras;


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
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 265, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDdd = new JLabel("DDD");
		lblDdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDdd.setBounds(0, 13, 85, 14);
		contentPanel.add(lblDdd);

		tfddd = new JTextField();
		tfddd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfddd.setBounds(95, 7, 52, 20);
		contentPanel.add(tfddd);
		tfddd.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(0, 38, 85, 14);
		contentPanel.add(lblNmero);

		tfnumero = new JTextField();
		tfnumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfnumero.setBounds(95, 31, 128, 20);
		contentPanel.add(tfnumero);
		tfnumero.setColumns(10);

		JLabel lblOperadora = new JLabel("Operadora");
		lblOperadora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperadora.setBounds(0, 67, 85, 14);
		contentPanel.add(lblOperadora);

		cboperadoras = new JComboBox( new String[] { "CLARO", "OI", "TIM", "VIVO" });
		cboperadoras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboperadoras.setBounds(95, 61, 128, 20);
		contentPanel.add(cboperadoras);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvar.setIcon(new ImageIcon(JDTelaEditTelefone.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
