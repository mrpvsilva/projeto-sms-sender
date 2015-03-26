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
import TableModels.TelefoneTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFormTelefone extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private int _linha = -1;
	private Telefone _telefone;
	private TelefoneTableModel _model;
	private JTextField tfddd;
	private JTextField tfnumero;
	private JComboBox cboperadoras;
	private String[] operadoras = new String[] { "CLARO", "OI", "TIM", "VIVO" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormTelefone dialog = new EditFormTelefone(-1, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditFormTelefone(int linha, Telefone telefone,
			TelefoneTableModel model) {
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

		cboperadoras = new JComboBox(operadoras);
		cboperadoras.setBounds(65, 65, 128, 20);
		contentPanel.add(cboperadoras);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
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
		if (e.getSource() == okButton) {
			if (_telefone != null) {
				Update();
			} else {
				Create();
			}
			this.dispose();
		}

		if (e.getSource() == cancelButton) {
			this.dispose();
		}

	}
}
