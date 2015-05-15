package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import Extra.Extras;
import Extra.Mascaras;

public class JDTelaFormCliente extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBNovoCad;
	private JTextField JTFNome;
	private JTextField JTFResp;
	private JTextField JTFRg;
	private MaskFormatter maskRG;
	private MaskFormatter maskCpf;
	private JTextField JTFEnd;
	private MaskFormatter maskFone;
	private Extras ext = new Extras();
	private JFormattedTextField JFFCpf;
	private JButton JBSair;
	private JPanel endereco;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JPanel panel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaFormCliente dialog = new JDTelaFormCliente();
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
	public JDTelaFormCliente() throws ParseException {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaFormCliente.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 616, 617);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		maskRG = new MaskFormatter(Mascaras.maskRg);

		maskCpf = new MaskFormatter(Mascaras.maskCpf);

		maskFone = new MaskFormatter(Mascaras.mask9digi);
		// for(String item : ext.getOperadora()){
		// JCBOperadora1.addItem(item);
		// }
		// for(String item : ext.getOperadora()){
		// JCBOperadora.addItem(item);
		// }
		// for(String item : ext.getOperadora()){
		// JCBOperadora2.addItem(item);
		// }
		// for(String item : ext.getSimNao()){
		// JCBOperadora2.addItem(item);
		// }

		JPanel dadospessoais = new JPanel();
		dadospessoais.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		dadospessoais.setBounds(10, 26, 554, 157);
		contentPanel.add(dadospessoais);
		dadospessoais.setLayout(null);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 28, 46, 14);
		dadospessoais.add(JLNome);
		JLNome.setHorizontalAlignment(SwingConstants.RIGHT);
		JLNome.setFont(new Font("Tahoma", Font.BOLD, 13));

		JTFNome = new JTextField();
		JTFNome.setBounds(68, 25, 428, 20);
		dadospessoais.add(JTFNome);
		JTFNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFNome.setColumns(10);

		JLabel JLResponsavel = new JLabel("Resp.");
		JLResponsavel.setBounds(10, 56, 46, 14);
		dadospessoais.add(JLResponsavel);
		JLResponsavel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLResponsavel.setFont(new Font("Tahoma", Font.BOLD, 13));

		JTFResp = new JTextField();
		JTFResp.setBounds(68, 53, 428, 20);
		dadospessoais.add(JTFResp);
		JTFResp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFResp.setColumns(10);
		JTFRg = new JFormattedTextField(maskRG);
		JTFRg.setBounds(68, 83, 162, 20);
		dadospessoais.add(JTFRg);
		JTFRg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFRg.setColumns(10);

		JLabel JLRg = new JLabel("RG");
		JLRg.setBounds(10, 86, 46, 14);
		dadospessoais.add(JLRg);
		JLRg.setHorizontalAlignment(SwingConstants.RIGHT);
		JLRg.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setBounds(251, 86, 46, 14);
		dadospessoais.add(JLCpf);
		JLCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCpf.setFont(new Font("Tahoma", Font.BOLD, 13));
		JFFCpf = new JFormattedTextField(maskCpf);
		JFFCpf.setBounds(312, 84, 184, 20);
		dadospessoais.add(JFFCpf);
		JFFCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 117, 46, 14);
		dadospessoais.add(lblEmail);

		// JFormattedTextField formattedTextField = new
		// JFormattedTextField((AbstractFormatter) null);
		// formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// formattedTextField.setColumns(10);
		// formattedTextField.setBounds(68, 114, 162, 20);
		// dadospessoais.add(formattedTextField);

		endereco = new JPanel();
		endereco.setLayout(null);
		endereco.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		endereco.setBounds(10, 194, 554, 157);
		contentPanel.add(endereco);

		JLabel JLEnd = new JLabel("Logradouro");
		JLEnd.setBounds(0, 27, 95, 15);
		endereco.add(JLEnd);
		JLEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		JLEnd.setFont(new Font("Tahoma", Font.BOLD, 13));

		JTFEnd = new JTextField();
		JTFEnd.setBounds(105, 24, 391, 20);
		endereco.add(JTFEnd);
		JTFEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFEnd.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCep.setBounds(0, 56, 95, 15);
		endereco.add(lblCep);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(105, 53, 146, 20);
		endereco.add(textField);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBairro.setBounds(265, 55, 62, 15);
		endereco.add(lblBairro);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(340, 55, 156, 20);
		endereco.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(105, 82, 146, 20);
		endereco.add(textField_2);

		JLabel lblN = new JLabel("Cidade");
		lblN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblN.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblN.setBounds(0, 85, 95, 15);
		endereco.add(lblN);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBounds(105, 113, 391, 20);
		endereco.add(textField_4);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblComplemento.setBounds(0, 116, 95, 15);
		endereco.add(lblComplemento);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Telefones",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 362, 554, 157);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 534, 87);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadCli = new JButton("Salvar");
				JBCadCli.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadCli.setIcon(new ImageIcon(JDTelaFormCliente.class
						.getResource("/Img/Confirmar.png")));
				buttonPane.add(JBCadCli);
				JBCadCli.setMnemonic(KeyEvent.VK_S);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBNovoCad = new JButton("Novo");
				JBNovoCad.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovoCad.setIcon(new ImageIcon(JDTelaFormCliente.class
						.getResource("/Img/window_new16.png")));
				JBNovoCad.setMnemonic(KeyEvent.VK_N);
				JBNovoCad.addActionListener(this);
				buttonPane.add(JBNovoCad);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(JDTelaFormCliente.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadCli) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o cliente?")) {

				// if(JTFNome.getText().trim().isEmpty())
				// JOptionPane.showMessageDialog(null,
				// "Nome em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if(JTFRg.getText().trim().isEmpty())
				// JOptionPane.showMessageDialog(null,
				// "Rg em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if(Validacoes.ValidaCpfCnpj(JFFCpf.getText()))
				// JOptionPane.showMessageDialog(null,
				// "Cpf inválido.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if(JTFEmail.getText().trim().isEmpty())
				// JOptionPane.showMessageDialog(null,
				// "Email em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if(JTFEnd.getText().trim().isEmpty())
				// JOptionPane.showMessageDialog(null,
				// "Endereço em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if(Validacoes.ValidaTelefone(JFFFone.getText()))
				// JOptionPane.showMessageDialog(null,
				// "Telefone inválido/em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else if (JCBSn.getSelectedItem().toString().equals("SIM") &&
				// JTFNomeGuerra.getText().trim().isEmpty())
				// JOptionPane.showMessageDialog(null,
				// "Nome guerra em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				// else{
				// ClientesBean cliBean = new ClientesBean();
				//
				// cliBean.setNomecompcli(JTFNome.getText());
				// cliBean.setRespcli(JTFResp.getText());
				// cliBean.setRgcli(JTFRg.getText());
				// cliBean.setCpfcli(Extras.FormatCnpjCpf(JFFCpf.getText())); //
				// Formata depois seta
				// cliBean.setEmailcli(JTFEmail.getText());
				// cliBean.setEndcli(JTFEnd.getText());
				//
				// cliBean.setTelefonecli(Extras.FormatFone(JFFFone.getText()));
				// // Formata depois seta
				// cliBean.setTelefonecli1(Extras.FormatFone(JFFFone1.getText()));
				// // Formata depois seta
				// cliBean.setTelefonecli2(Extras.FormatFone(JFFFone2.getText()));
				// // Formata depois seta
				//
				// cliBean.setTipoeventocli(JCBEvento.getSelectedItem().toString());
				// cliBean.setConvidadosextrascli(Integer.parseInt(JFFConvExtra.getText()));
				// // Formata depois seta
				// cliBean.setNomeguerracli(JTFNomeGuerra.getText());
				//
				// cliBean.setOperadora(JCBOperadora.getSelectedItem().toString());
				// cliBean.setOperadora1(JCBOperadora1.getSelectedItem().toString());
				// cliBean.setOperadora2(JCBOperadora2.getSelectedItem().toString());

				// }// final da validação

			}// final da confirmação

		}// final do botão cadastrar usuário

		// if(acao.getSource() == JBNovoCad){
		//
		// if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
		// "Deseja cadastrar o cliente?")){
		//
		// JTFNome.setText("");
		// JTFResp.setText("");
		// JTFRg.setText("");
		// JFFCpf.setText("");
		// JTFEmail.setText("");
		// JTFEnd.setText("");
		//
		// JFFFone.setText("");
		// JFFFone1.setText("");
		// JFFFone2.setText("");
		//
		// JCBEvento.setSelectedItem("");
		// JFFConvExtra.setText("");
		// JTFNomeGuerra.setText("");
		//
		// JCBOperadora.setSelectedItem("TIM");
		// JCBOperadora1.setSelectedItem("TIM");
		// JCBOperadora2.setSelectedItem("TIm");
		//
		// }// final da confirmação
		//
		// }// final do botão atualizar usuário

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}// final da ação do botão
}
