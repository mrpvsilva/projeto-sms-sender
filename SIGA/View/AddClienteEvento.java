package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
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
import Extra.Mascaras;
import TableModels.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JCheckBox;

public class AddClienteEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField cpfcnpj;
	private MaskFormatter maskcpf;
	private MaskFormatter maskcnpj;
	private DefaultTableModel<Cliente> _modelCliente;
	private OrcamentoControl control;
	private JLabel cliente;
	private Cliente _cliente;
	private JPanel encontrado;
	private JButton novo;
	private JLabel ok;
	private JCheckBox chckbxCnpj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddClienteEvento dialog = new AddClienteEvento(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddClienteEvento(DefaultTableModel<Cliente> modelCliente) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		_modelCliente = modelCliente;
		start();
	}

	public void start() {
		control = new OrcamentoControl();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 372, 203);
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
				maskcpf = new MaskFormatter(Mascaras.maskCpf);
				maskcnpj = new MaskFormatter(Mascaras.maskCnpj);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cpfcnpj = new JFormattedTextField(maskcpf);
			cpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cpfcnpj.setBounds(71, 23, 178, 22);
			contentPanel.add(cpfcnpj);
			cpfcnpj.setColumns(10);

		}
		{
			JButton buscarcliente = new JButton("Buscar");
			buscarcliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarCliente();
				}
			});
			buscarcliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			buscarcliente.setBounds(259, 21, 89, 22);
			contentPanel.add(buscarcliente);
		}
		{
			encontrado = new JPanel();
			encontrado.setVisible(false);
			encontrado.setBounds(10, 82, 339, 47);
			contentPanel.add(encontrado);
			encontrado.setLayout(null);
			{
				cliente = new JLabel("New label");
				cliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cliente.setBounds(10, 11, 265, 25);
				encontrado.add(cliente);
			}
			{
				ok = new JLabel("");
				ok.setToolTipText("Adicionar");
				ok.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Cliente c = _modelCliente.get(_cliente.getId());
						if (c == null) {
							_modelCliente.add(_cliente);
							AddClienteEvento.this.dispose();
						}

						cpfcnpj.setValue(null);
						encontrado.setVisible(false);
						JOptionPane.showMessageDialog(null,
								"Cliente já adicionado ao evento", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				});
				ok.setIcon(new ImageIcon(AddClienteEvento.class
						.getResource("/Img/OK.png")));
				ok.setVisible(false);
				ok.setBounds(285, 15, 16, 16);
				encontrado.add(ok);
			}
		}

		chckbxCnpj = new JCheckBox("CNPJ");
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
							AddClienteEvento.this.dispose();
							EditFormCliente efc = new EditFormCliente(
									_modelCliente);
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
	}

	private void buscarCliente() {
		String _cpfcnpj = cpfcnpj.getText().replace(".", "").replace("-", "")
				.replace("/", "");

		_cliente = control.buscarCliente(_cpfcnpj);

		if (_cliente != null) {
			cliente.setText(_cliente.getNomeCompleto());
			ok.setVisible(true);
			novo.setVisible(false);
		} else {
			ok.setVisible(false);
			novo.setVisible(true);
			cliente.setText("Cliente não encontrado");
		}
		encontrado.setVisible(true);
	}
}
