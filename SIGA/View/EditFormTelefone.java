package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import Dominio.Operadoras;
import Dominio.Telefone;
import TableModels.DefaultTableModel;

import java.awt.Color;

public class EditFormTelefone extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvar;
	private JButton JBSair;
	private int _linha = -1;
	private Telefone _telefone;
	private DefaultTableModel<Telefone> _model;
	private JTextField tfddd;
	private JTextField tfnumero;
	private JComboBox cboperadoras;
	private JLabel msg_erro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormTelefone dialog = new EditFormTelefone(-1, null, null);
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
	public EditFormTelefone(int linha, Telefone telefone,
			DefaultTableModel<Telefone> telmodel) {
		setTitle("SIGA - cad. telefone");
		_telefone = telefone;
		_model = telmodel;
		_linha = linha;
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 265, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDdd = new JLabel("DDD");
		lblDdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDdd.setBounds(0, 17, 85, 14);
		contentPanel.add(lblDdd);

		Document doc = new Util.MaxLenghtDocument(2);
		tfddd = new JTextField(doc, "", 8);
		tfddd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfddd.setBounds(95, 11, 52, 20);
		tfddd.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				msg_erro.setText("");
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}

			}
		});
		contentPanel.add(tfddd);
		tfddd.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(0, 40, 85, 14);
		contentPanel.add(lblNmero);
		Document maxnumero = new Util.MaxLenghtDocument(9);
		tfnumero = new JTextField(maxnumero, "", 8);
		tfnumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfnumero.setBounds(95, 38, 128, 20);
		contentPanel.add(tfnumero);
		tfnumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				msg_erro.setText("");
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}

			}
		});
		tfnumero.setColumns(10);

		JLabel lblOperadora = new JLabel("Operadora");
		lblOperadora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOperadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperadora.setBounds(0, 67, 85, 14);
		contentPanel.add(lblOperadora);

		cboperadoras = new JComboBox(Operadoras.values());
		cboperadoras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboperadoras.setBounds(95, 65, 128, 20);
		contentPanel.add(cboperadoras);

		msg_erro = new JLabel("");
		msg_erro.setForeground(Color.RED);
		msg_erro.setBounds(10, 91, 239, 14);
		contentPanel.add(msg_erro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvar.setIcon(new ImageIcon(EditFormTelefone.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(EditFormTelefone.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_X);
				buttonPane.add(JBSair);
			}
		}

		PreencherCampos();
	}

	private void showErro(String msg) {
		msg_erro.setText("* " + msg);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					msg_erro.setText("");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private void Create() {
		_telefone.setDdd(tfddd.getText());
		_telefone.setNumero(tfnumero.getText());
		_telefone.setOperadora(cboperadoras.getSelectedItem().toString());
		_model.add(_telefone);
	}

	private void Update() {
		_telefone.setDdd(tfddd.getText());
		_telefone.setNumero(tfnumero.getText());
		_telefone.setOperadora(cboperadoras.getSelectedItem().toString());
		_model.update(_linha, _telefone);
	}

	private void PreencherCampos() {
		if (_linha > -1) {
			tfddd.setText(_telefone.getDdd());
			tfnumero.setText(_telefone.getNumero());
			cboperadoras.setSelectedItem(Operadoras.valueOf(_telefone
					.getOperadora()));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSalvar) {
			if (tfddd.getText().length() < 1) {
				tfddd.requestFocus();
				showErro("DDD é obirgatório");
				return;
			} else if (tfnumero.getText().length() < 1) {
				tfnumero.requestFocus();
				showErro("Número é obrigatório");
				return;
			}else if(cboperadoras.getSelectedItem()==Operadoras.SELECIONE){
				cboperadoras.requestFocus();
				showErro("Selecione uma operadora");
				return;
			}

			if (_linha > -1) {
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
