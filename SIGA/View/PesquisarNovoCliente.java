package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import Control.OrcamentoControl;
import Dominio.Cliente;
import Extra.Extras;
import Extra.Mascaras;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.JCheckBox;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PesquisarNovoCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField cpfcnpj;

	private OrcamentoControl control;
	private JLabel cliente;
	private Cliente _cliente;
	private JPanel encontrado;
	private JButton novo;
	private JCheckBox chckbxCnpj;
	private JButton buscarcliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisarNovoCliente dialog = new PesquisarNovoCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisarNovoCliente() {
		setTitle("Pesquisar de cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				PesquisarNovoCliente.class
						.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		start();
	}

	public void start() {
		control = new OrcamentoControl();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 478, 181);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCpfcnpj = new JLabel("CPF/CNPJ");
			lblCpfcnpj.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCpfcnpj.setBounds(10, 21, 55, 22);
			contentPanel.add(lblCpfcnpj);
		}
		{

			try {
				cpfcnpj = new JFormattedTextField(new DefaultFormatterFactory(
						new MaskFormatter(Mascaras.maskCpf)));
				cpfcnpj.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {

						String c = cpfcnpj.getText().replace(".", "")
								.replace("/", "").replace("-", "")
								.replace(" ", "");
						if ((!chckbxCnpj.isSelected() && c.length() == 11)
								|| (chckbxCnpj.isSelected() && c.length() == 14)) {
							if (Extras.validarCPFCNPJ(c)) {
								buscarcliente.setEnabled(true);
							} else {
								cliente.setText("CPF ou CNPJ é inválido");
							}
						} else {
							limparBusca();
						}
					}
				});

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cpfcnpj.setBounds(71, 23, 178, 22);
			contentPanel.add(cpfcnpj);
			cpfcnpj.setColumns(10);

		}
		{
			buscarcliente = new JButton("Pesquisar");
			buscarcliente.setIcon(new ImageIcon(PesquisarNovoCliente.class
					.getResource("/Img/Procurar.png")));
			buscarcliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarCliente();
				}
			});
			buscarcliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			buscarcliente.setBounds(259, 21, 125, 22);
			contentPanel.add(buscarcliente);
		}
		{
			encontrado = new JPanel();
			encontrado.setBounds(10, 66, 452, 47);
			contentPanel.add(encontrado);
			encontrado.setLayout(null);
			{
				cliente = new JLabel("");
				cliente.setFont(new Font("Tahoma", Font.BOLD, 13));
				cliente.setBounds(10, 11, 406, 25);
				encontrado.add(cliente);
			}
		}

		chckbxCnpj = new JCheckBox("CNPJ");
		chckbxCnpj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limparBusca();
			}
		});
		chckbxCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cnpj = chckbxCnpj.isSelected();
				String t = cpfcnpj.getText().replace(".", "").replace("-", "")
						.replace("/", "");
				cpfcnpj.setValue(null);
				if (cnpj) {
					try {
						cpfcnpj.setFormatterFactory(new DefaultFormatterFactory(
								new MaskFormatter(Mascaras.maskCnpj)));
						cpfcnpj.setText(t);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					try {
						cpfcnpj.setFormatterFactory(new DefaultFormatterFactory(
								new MaskFormatter(Mascaras.maskCpf)));
						cpfcnpj.setText(t);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		chckbxCnpj.setBounds(71, 46, 178, 22);
		contentPanel.add(chckbxCnpj);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				novo = new JButton("Novo");
				novo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							PesquisarNovoCliente.this.dispose();
							EditFormCliente efc = new EditFormCliente(cpfcnpj
									.getValue().toString(),chckbxCnpj.isSelected());
							efc.setLocationRelativeTo(null);
							efc.setVisible(true);

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				novo.setVisible(false);
				novo.setFont(new Font("Tahoma", Font.PLAIN, 13));
				novo.setActionCommand("OK");
				buttonPane.add(novo);
				getRootPane().setDefaultButton(novo);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		limparBusca();
		getRootPane().setDefaultButton(buscarcliente);
	}

	private void limparBusca() {
		buscarcliente.setEnabled(false);
		novo.setVisible(false);
		cliente.setText("");

	}

	private void buscarCliente() {
		String _cpfcnpj = cpfcnpj.getText().replace(".", "").replace("-", "")
				.replace("/", "");

		_cliente = control.buscarCliente(_cpfcnpj);

		if (_cliente != null) {
			cliente.setText(_cliente.getNomeCompleto());
			novo.setVisible(false);
		} else {
			novo.setVisible(true);
			cliente.setText("Cliente ainda não cadastrado");
		}

	}

}
