package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import Control.FornecedoresControl;
import Dominio.TelefoneFornecedor;
import Extra.Extras;
import Extra.Mascaras;
import Extra.Validacoes;
import Model.FornecedoresBean;

import java.awt.event.KeyAdapter;

public class JDTelaCadForn extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFNome;
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private JTextField JTFSite;
	private JButton JBNovForn;
	private JButton JBSalvForn;
	private JFormattedTextField JFFFone1;
	private JFormattedTextField JFFFone;
	private JFormattedTextField JFFRg;
	private JFormattedTextField JFFCnpj;
	private MaskFormatter maskFone;
	private JComboBox<String> JCBTpServ;
	private JFormattedTextField JFFFone2;
	private MaskFormatter maskRG;
	private MaskFormatter maskCnpj;
	private JLabel JLBairro;
	private JTextField JTFBairro;
	private JLabel lblCep;
	private JTextField JFFCep;
	private JCheckBox JCBCpfMask;
	private MaskFormatter maskCep;
	private JComboBox<String> JCBOperadora1;
	private Extras ext = new Extras();
	private JComboBox<String> JCBOperadora;
	private JComboBox<String> JCBOperadora2;
	private FornecedoresControl _fornecedorControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadForn dialog = new JDTelaCadForn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public JDTelaCadForn() throws ParseException {
		_fornecedorControl = new FornecedoresControl();
		setBounds(100, 100, 581, 300);
		setTitle("SIGA  - cadastro de fornecedores");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 11, 46, 14);
		contentPanel.add(JLNome);

		JLabel JLCnpj = new JLabel("Cnpj");
		JLCnpj.setBounds(10, 36, 35, 14);
		contentPanel.add(JLCnpj);

		JLabel JLRg = new JLabel("Rg");
		JLRg.setBounds(296, 39, 35, 14);
		contentPanel.add(JLRg);

		JLabel JLTelefone1 = new JLabel("Fone");
		JLTelefone1.setBounds(10, 67, 46, 14);
		contentPanel.add(JLTelefone1);

		JLabel JLTelefone2 = new JLabel("Fone 1");
		JLTelefone2.setBounds(296, 70, 46, 14);
		contentPanel.add(JLTelefone2);

		JLabel JLTelefone3 = new JLabel("Fone 2");
		JLTelefone3.setBounds(10, 95, 46, 14);
		contentPanel.add(JLTelefone3);

		JLabel JLEmail = new JLabel("Email");
		JLEmail.setBounds(10, 120, 46, 14);
		contentPanel.add(JLEmail);

		JLabel JLEnd = new JLabel("Ender.");
		JLEnd.setBounds(10, 151, 59, 14);
		contentPanel.add(JLEnd);

		JTFNome = new JTextField();
		JTFNome.setBounds(48, 8, 438, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		maskCnpj = new MaskFormatter(Mascaras.maskCnpj);
		JFFCnpj = new JFormattedTextField(maskCnpj);
		JFFCnpj.setBounds(48, 36, 118, 20);
		contentPanel.add(JFFCnpj);

		maskRG = new MaskFormatter(Mascaras.maskRg);
		JFFRg = new JFormattedTextField(maskRG);
		((JFormattedTextField) JFFRg)
				.setFocusLostBehavior(JFormattedTextField.COMMIT); // Permite o
																	// usuÃ¡rio
																	// avanÃ§ar
																	// o campo
																	// sem
																	// preencher
																	// todos a
																	// mÃ¡scara
		JFFRg.setBounds(368, 36, 118, 20);
		contentPanel.add(JFFRg);

		JLabel JLSite = new JLabel("Site");
		JLSite.setBounds(10, 203, 46, 14);
		contentPanel.add(JLSite);

		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setBounds(296, 98, 76, 14);
		contentPanel.add(JLTpServ);

		maskFone = new MaskFormatter(Mascaras.mask9digi);
		JFFFone = new JFormattedTextField(maskFone);
		JFFFone.setBounds(48, 64, 118, 20);
		JFFFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == 112) {
					JFFFone.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask9digi);
						JFFFone = new JFormattedTextField(maskFone);
						JFFFone.setBounds(48, 64, 118, 20);
						contentPanel.add(JFFFone);
					} catch (ParseException e) {

					}
				}

				if (k.getKeyCode() == 113) {
					JFFFone.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask8digi);
						JFFFone = new JFormattedTextField(maskFone);
						JFFFone.setBounds(48, 64, 118, 20);
						contentPanel.add(JFFFone);
					} catch (ParseException e) {

					}
				}
			}
		});
		contentPanel.add(JFFFone);

		JFFFone1 = new JFormattedTextField(maskFone);
		JFFFone1.setToolTipText("1- Tim \r\n2- Oi \r\n3- Vivo \r\n4- Claro\r\n5- Fixo \r\n6- Fax");
		JFFFone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == 112) {
					JFFFone1.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask9digi);
						JFFFone1 = new JFormattedTextField(maskFone);
						JFFFone1.setBounds(306, 64, 118, 20);
						contentPanel.add(JFFFone1);
					} catch (ParseException e) {

					}
				}

				if (k.getKeyCode() == 113) {
					JFFFone1.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask8digi);
						JFFFone1 = new JFormattedTextField(maskFone);
						JFFFone1.setBounds(306, 64, 118, 20);
						contentPanel.add(JFFFone1);
					} catch (ParseException e) {

					}
				}
			}
		});
		JFFFone1.setBounds(368, 67, 118, 20);
		contentPanel.add(JFFFone1);

		JFFFone2 = new JFormattedTextField(maskFone);
		JFFFone2.setToolTipText("1- Tim \r\n2- Oi \r\n3- Vivo \r\n4- Claro\r\n5- Fixo \r\n6- Fax");
		JFFFone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == 112) {
					JFFFone2.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask9digi);
						JFFFone2 = new JFormattedTextField(maskFone);
						JFFFone2.setBounds(48, 92, 118, 20);
						contentPanel.add(JFFFone2);
					} catch (ParseException e) {

					}
				}

				if (k.getKeyCode() == 113) {
					JFFFone2.setBounds(0, 0, 0, 0);
					try {
						maskFone = new MaskFormatter(Mascaras.mask8digi);
						JFFFone2 = new JFormattedTextField(maskFone);
						JFFFone2.setBounds(48, 92, 118, 20);
						contentPanel.add(JFFFone2);
					} catch (ParseException e) {

					}
				}
			}
		});
		JFFFone2.setBounds(48, 92, 118, 20);
		contentPanel.add(JFFFone2);

		JTFEmail = new JTextField();
		JTFEmail.setBounds(48, 120, 438, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);

		JTFEnd = new JTextField();
		JTFEnd.setBounds(48, 148, 438, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);

		JTFSite = new JTextField();
		JTFSite.setBounds(48, 200, 438, 20);
		contentPanel.add(JTFSite);
		JTFSite.setColumns(10);

		JCBTpServ = new JComboBox<String>(_fornecedorControl.DDLTipoServico());
		JCBTpServ.setBounds(368, 95, 118, 20);

		contentPanel.add(JCBTpServ);

		JLBairro = new JLabel("Bairro");
		JLBairro.setBounds(10, 175, 46, 14);
		contentPanel.add(JLBairro);

		JTFBairro = new JTextField();
		JTFBairro.setBounds(48, 172, 118, 20);
		contentPanel.add(JTFBairro);
		JTFBairro.setColumns(10);

		lblCep = new JLabel("CEP");
		lblCep.setBounds(296, 178, 46, 14);
		contentPanel.add(lblCep);

		maskCep = new MaskFormatter(Mascaras.maskCep);
		JFFCep = new JFormattedTextField(maskCep);
		JFFCep.setBounds(368, 175, 118, 20);
		contentPanel.add(JFFCep);
		JFFCep.setColumns(10);

		JCBCpfMask = new JCheckBox("CPF");
		JCBCpfMask.setBounds(172, 34, 56, 23);
		JCBCpfMask.addActionListener(this);
		contentPanel.add(JCBCpfMask);

		JCBOperadora1 = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora1.addItem(item);
		}
		JCBOperadora1.setBounds(496, 67, 59, 20);
		contentPanel.add(JCBOperadora1);

		JCBOperadora = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora.addItem(item);
		}
		JCBOperadora.setBounds(176, 64, 59, 20);
		contentPanel.add(JCBOperadora);

		JCBOperadora2 = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora2.addItem(item);
		}
		JCBOperadora2.setBounds(176, 92, 59, 20);
		contentPanel.add(JCBOperadora2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvForn = new JButton("Salvar");
				JBSalvForn.addActionListener(this);
				buttonPane.add(JBSalvForn);
				getRootPane().setDefaultButton(JBSalvForn);
			}
			{
				JBNovForn = new JButton("Novo");
				JBNovForn.addActionListener(this);
				buttonPane.add(JBNovForn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o fornecedor?")) {

				if (JTFNome.getText().isEmpty()) // Valida Nome
					JOptionPane.showMessageDialog(null, "Nome em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaCpfCnpj(JFFCnpj.getText())) // Valida
																		// CpfCnpj
					JOptionPane.showMessageDialog(null, "Cpf/Cnpj inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JFFRg.getText().trim().isEmpty()) // Valida Rg
					JOptionPane.showMessageDialog(null, "Rg em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaTelefone(JFFFone.getText())) // Valida
																		// Telefone
					JOptionPane.showMessageDialog(null, "Telefone inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFEmail.getText().trim().isEmpty()) // Valida Email
					JOptionPane.showMessageDialog(null, "Email em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFEnd.getText().trim().isEmpty()) // Valida Endereço
					JOptionPane.showMessageDialog(null, "Endereço em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFBairro.getText().trim().isEmpty()) // Valida Bairro
					JOptionPane.showMessageDialog(null, "Bairro em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaCep(JFFCep.getText())) // Valida Cep
					JOptionPane.showMessageDialog(null, "Cep inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFSite.getText().trim().isEmpty()) // Valida Site
					JOptionPane.showMessageDialog(null, "Site em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					FornecedoresBean fornBean = new FornecedoresBean();

					fornBean.setNomeforn(JTFNome.getText());
					fornBean.setCnpjforn(Extras.FormatCnpjCpf(JFFCnpj.getText())); // Formata
																					// e
																					// seta
																					// no
																					// Bean
					fornBean.setRgforn(JFFRg.getText());

					fornBean.AddTelefone(new TelefoneFornecedor(Extras
							.FormatDDD(Extras.FormatFone(JFFFone.getText())),
							Extras.FormatFoneBD(Extras.FormatFone(JFFFone
									.getText())), JCBOperadora
									.getSelectedItem().toString()));

					fornBean.AddTelefone(new TelefoneFornecedor(Extras
							.FormatDDD(Extras.FormatFone(JFFFone1.getText())),
							Extras.FormatFoneBD(Extras.FormatFone(JFFFone1
									.getText())), JCBOperadora1
									.getSelectedItem().toString()));

					fornBean.AddTelefone(new TelefoneFornecedor(Extras
							.FormatDDD(Extras.FormatFone(JFFFone2.getText())),
							Extras.FormatFoneBD(Extras.FormatFone(JFFFone2
									.getText())), JCBOperadora2
									.getSelectedItem().toString()));

					fornBean.setEmailforn(JTFEmail.getText());
					fornBean.setEndforn(JTFEnd.getText());
					fornBean.setBairro(JTFBairro.getText());

					fornBean.setCep(Extras.FormatCep(JFFCep.getText())); // Formata
																			// e
																			// seta
																			// no
																			// Bean
					fornBean.setSiteforn(JTFSite.getText());

					fornBean.setTiposervprestadoforn(JCBTpServ
							.getSelectedItem().toString());

					/* Colocar o método de cadastrar */
					String out = _fornecedorControl.Cadastrar(fornBean);
					if (out == null) {
						JOptionPane.showMessageDialog(null,
								"Forncedor cadastrado");
					} else {
						JOptionPane.showMessageDialog(null, out);
					}
					/* Acredito que esteja todas as validações possíveis */

				}// Final da validação

			}// final da pergunta se o deseja cadastrar o fornecedor

		}// final do botão salvar

		if (acao.getSource() == JBNovForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo fornecedor?")) {

				JTFNome.setText("");
				JFFCnpj.setText("");
				JFFRg.setText("");
				JFFFone.setText("");
				JFFFone1.setText("");
				JFFFone2.setText("");
				JCBOperadora.setSelectedItem("TIM");
				JCBOperadora1.setSelectedItem("TIM");
				JCBOperadora2.setSelectedItem("TIM");
				JTFEmail.setText("");
				JTFEnd.setText("");
				JTFBairro.setText("");
				JFFCep.setText("");
				JTFSite.setText("");

			}// Final da pergunta se deseja cadastrar um novo fornecedor

		}// final do botão novo

		if (acao.getSource() == JCBCpfMask) {

			if (JCBCpfMask.isSelected()) {
				try {
					JFFCnpj.setBounds(0, 0, 0, 0);
					maskCnpj = new MaskFormatter(Mascaras.maskCpf);
					JFFCnpj = new JFormattedTextField(maskCnpj);
					JFFCnpj.setBounds(48, 36, 118, 20);
					contentPanel.add(JFFCnpj);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				try {
					JFFCnpj.setBounds(0, 0, 0, 0);
					maskCnpj = new MaskFormatter(Mascaras.maskCnpj);
					JFFCnpj = new JFormattedTextField(maskCnpj);
					JFFCnpj.setBounds(48, 36, 118, 20);
					contentPanel.add(JFFCnpj);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			} // final marcar se campo irá usar máscara de CPF ou CNPJ

		}

	}
}
