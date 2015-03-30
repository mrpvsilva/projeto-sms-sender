package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Extra.Extras;
import Extra.Mascaras;
import Extra.Validacoes;
import Model.ClientesBean;
import javax.swing.ImageIcon;


public class JDTelaCadCli extends JDialog implements ActionListener{

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
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private MaskFormatter maskFone;
	private JTextField JTFNomeGuerra;
	private JComboBox<String> JCBEvento;
	private JFormattedTextField JFFConvExtra;
	private JFormattedTextField JFFFone1;
	private JFormattedTextField JFFFone2;
	private Extras ext = new Extras();
	private JFormattedTextField JFFCpf;
	private JFormattedTextField JFFFone;
	private JComboBox<String> JCBOperadora2;
	private JComboBox<String> JCBOperadora1;
	private JComboBox<String> JCBOperadora;
	private JComboBox<String> JCBSn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadCli dialog = new JDTelaCadCli();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDTelaCadCli() throws ParseException {
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 581, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 11, 46, 14);
		contentPanel.add(JLNome);
		
		JLabel JLResponsavel = new JLabel("Resp.");
		JLResponsavel.setBounds(10, 36, 46, 14);
		contentPanel.add(JLResponsavel);
		
		JLabel JLRg = new JLabel("RG");
		JLRg.setBounds(10, 61, 46, 14);
		contentPanel.add(JLRg);
		
		JLabel JLTipoEvento = new JLabel("Evento");
		JLTipoEvento.setBounds(281, 61, 46, 14);
		contentPanel.add(JLTipoEvento);
		
		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setBounds(10, 86, 46, 14);
		contentPanel.add(JLCpf);
		
		JLabel JLEmail = new JLabel("Email");
		JLEmail.setBounds(10, 111, 46, 14);
		contentPanel.add(JLEmail);
		
		JLabel JLEnd = new JLabel("End.");
		JLEnd.setBounds(10, 136, 46, 14);
		contentPanel.add(JLEnd);
		
		JLabel JLFone = new JLabel("Fone");
		JLFone.setBounds(10, 165, 46, 14);
		contentPanel.add(JLFone);
		
		JLabel JLFone1 = new JLabel("Fone1");
		JLFone1.setBounds(281, 165, 46, 14);
		contentPanel.add(JLFone1);
		
		JLabel JLFone2 = new JLabel("Fone2");
		JLFone2.setBounds(10, 190, 46, 14);
		contentPanel.add(JLFone2);
		
		JLabel lblConvExtra = new JLabel("Conv. Extra");
		lblConvExtra.setBounds(281, 86, 66, 14);
		contentPanel.add(lblConvExtra);
		
		JLabel JLNomeGuerra = new JLabel("Nome Guerra");
		JLNomeGuerra.setBounds(281, 190, 81, 14);
		contentPanel.add(JLNomeGuerra);
		
		JTFNome = new JTextField();
		JTFNome.setBounds(58, 8, 428, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);
		
		JTFResp = new JTextField();
		JTFResp.setBounds(58, 33, 428, 20);
		contentPanel.add(JTFResp);
		JTFResp.setColumns(10);
		
		maskRG= new MaskFormatter(Mascaras.maskRg);
		JTFRg = new JFormattedTextField(maskRG);
		JTFRg.setBounds(58, 58, 118, 20);
		contentPanel.add(JTFRg);
		JTFRg.setColumns(10);
		
		JCBEvento = new JComboBox<String>();
		JCBEvento.setBounds(368, 58, 118, 20);
		contentPanel.add(JCBEvento);
		
		maskCpf= new MaskFormatter(Mascaras.maskCpf);
		JFFCpf = new JFormattedTextField(maskCpf);
		JFFCpf.setBounds(58, 83, 118, 20);
		contentPanel.add(JFFCpf);
		
		JTFEmail = new JTextField();
		JTFEmail.setBounds(58, 108, 428, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);
		
		JTFEnd = new JTextField();
		JTFEnd.setBounds(58, 133, 428, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);
		
		maskFone = new MaskFormatter(Mascaras.mask9digi);
		JFFFone = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone.setBounds(58, 161, 118, 20);
		contentPanel.add(JFFFone);
		
		JFFConvExtra = new JFormattedTextField(maskRG);
		JFFConvExtra.setBounds(368, 83, 118, 20);
		contentPanel.add(JFFConvExtra);
		
		JFFFone1 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone1).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone1.setBounds(368, 161, 118, 20);
		contentPanel.add(JFFFone1);
		
		JFFFone2 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone2).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone2.setBounds(58, 187, 118, 20);
		contentPanel.add(JFFFone2);
		
		JTFNomeGuerra = new JTextField();
		JTFNomeGuerra.setBounds(368, 187, 118, 20);
		contentPanel.add(JTFNomeGuerra);
		JTFNomeGuerra.setColumns(10);
		
		JCBOperadora1 = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora1.addItem(item);
		}
		JCBOperadora1.setBounds(496, 161, 59, 20);
		contentPanel.add(JCBOperadora1);
		
		JCBOperadora = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora.addItem(item);
		}
		JCBOperadora.setBounds(186, 162, 59, 20);
		contentPanel.add(JCBOperadora);
		
		JCBOperadora2 = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora2.addItem(item);
		}
		JCBOperadora2.setBounds(186, 187, 59, 20);
		contentPanel.add(JCBOperadora2);
		
		JCBSn = new JComboBox<String>();
		for(String item : ext.getSimNao()){
			JCBOperadora2.addItem(item);
		}
		JCBSn.setBounds(496, 187, 59, 20);
		contentPanel.add(JCBSn);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadCli = new JButton("Salvar");
				JBCadCli.setIcon(new ImageIcon(JDTelaCadCli.class.getResource("/Img/Confirmar.png")));
				buttonPane.add(JBCadCli);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBNovoCad = new JButton("Novo");
				JBNovoCad.setIcon(new ImageIcon("C:\\Users\\Aru\u00E3Melo\\Downloads\\user_male_add216.png"));
				JBNovoCad.addActionListener(this);
				buttonPane.add(JBNovoCad);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadCli){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja cadastrar o cliente?")){
				
				if(JTFNome.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Nome em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if(JTFRg.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Rg em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if(Validacoes.ValidaCpfCnpj(JFFCpf.getText()))
					JOptionPane.showMessageDialog(null, "Cpf inválido.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if(JTFEmail.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Email em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if(JTFEnd.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Endereço em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if(Validacoes.ValidaTelefone(JFFFone.getText()))
					JOptionPane.showMessageDialog(null, "Telefone inválido/em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else if (JCBSn.getSelectedItem().toString().equals("SIM") && JTFNomeGuerra.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Nome guerra em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
				else{
					ClientesBean cliBean = new ClientesBean();
					
					cliBean.setNomecompcli(JTFNome.getText());
					cliBean.setRespcli(JTFResp.getText());
					cliBean.setRgcli(JTFRg.getText());
					cliBean.setCpfcli(Extras.FormatCnpjCpf(JFFCpf.getText())); 					// Formata depois seta
					cliBean.setEmailcli(JTFEmail.getText());
					cliBean.setEndcli(JTFEnd.getText());
					
					cliBean.setTelefonecli(Extras.FormatFone(JFFFone.getText())); 				// Formata depois seta
					cliBean.setTelefonecli1(Extras.FormatFone(JFFFone1.getText())); 			// Formata depois seta
					cliBean.setTelefonecli2(Extras.FormatFone(JFFFone2.getText())); 			// Formata depois seta
					
					cliBean.setTipoeventocli(JCBEvento.getSelectedItem().toString());
					cliBean.setConvidadosextrascli(Integer.parseInt(JFFConvExtra.getText()));  	// Formata depois seta
					cliBean.setNomeguerracli(JTFNomeGuerra.getText());
					
					cliBean.setOperadora(JCBOperadora.getSelectedItem().toString());
					cliBean.setOperadora1(JCBOperadora1.getSelectedItem().toString());
					cliBean.setOperadora2(JCBOperadora2.getSelectedItem().toString());
					
				}// final da validação
				
			}// final da confirmação
			
		}// final do botão cadastrar usuário
		
		if(acao.getSource() == JBNovoCad){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja cadastrar o cliente?")){
				
				JTFNome.setText("");
				JTFResp.setText("");
				JTFRg.setText("");
				JFFCpf.setText(""); 				
				JTFEmail.setText("");
				JTFEnd.setText("");
				
				JFFFone.setText(""); 				
				JFFFone1.setText(""); 			
				JFFFone2.setText(""); 			
				
				JCBEvento.setSelectedItem("");
				JFFConvExtra.setText("");  	
				JTFNomeGuerra.setText("");
				
				JCBOperadora.setSelectedItem("TIM");
				JCBOperadora1.setSelectedItem("TIM");
				JCBOperadora2.setSelectedItem("TIm");
				
			}// final da confirmação
			
		}// final do botão atualizar usuário
		
	}// final da ação do botão
}
