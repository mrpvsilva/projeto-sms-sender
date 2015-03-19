package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import Extra.Extras;
import Extra.Validacoes;
import Model.FornecedoresBean;

public class JDTelaEditForn extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFNome;
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private JTextField JTFSite;
	private JButton JBExclForn;
	private JButton JBAtuForn;
	private JFormattedTextField JFFFone1;
	private JFormattedTextField JFFFone;
	private JFormattedTextField JFFRg;
	private JFormattedTextField JFFCnpj;
	private JComboBox<String> JCBTpServ;
	private JFormattedTextField JFFFone2;
	private JLabel JLBairro;
	private JTextField JTFBairro;
	private JLabel lblCep;
	private JTextField JFFCep;
	private JComboBox<String> JCBOperadora1;
	private JComboBox<String> JCBOperadora;
	private JComboBox<String> JCBOperadora2;
	private Extras ext = new Extras();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditForn dialog = new JDTelaEditForn("");
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
	public JDTelaEditForn(String CpfCnpj) throws ParseException  {
		
		setBounds(100, 100, 581, 300);
		setTitle("SIGA  - edi\u00E7\u00E3o de fornecedores");
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
		JLRg.setBounds(279, 42, 35, 14);
		contentPanel.add(JLRg);
		
		JLabel JLTelefone1 = new JLabel("Fone");
		JLTelefone1.setBounds(10, 67, 46, 14);
		contentPanel.add(JLTelefone1);
		
		JLabel JLTelefone2 = new JLabel("Fone 1");
		JLTelefone2.setBounds(279, 67, 46, 14);
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
		
		JFFCnpj = new JFormattedTextField();
		JFFCnpj.setBounds(48, 36, 118, 20);
		contentPanel.add(JFFCnpj);
		
		JFFRg = new JFormattedTextField();
		JFFRg.setBounds(368, 36, 118, 20);
		contentPanel.add(JFFRg);
		
		JLabel JLSite = new JLabel("Site");
		JLSite.setBounds(10, 203, 46, 14);
		contentPanel.add(JLSite);
		
		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setBounds(279, 95, 79, 14);
		contentPanel.add(JLTpServ);
		
		JFFFone = new JFormattedTextField();
		JFFFone.setBounds(48, 64, 118, 20);
		contentPanel.add(JFFFone);
		
		JFFFone1 = new JFormattedTextField();
		JFFFone1.setBounds(368, 67, 118, 20);
		contentPanel.add(JFFFone1);
		
		JFFFone2 = new JFormattedTextField();
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
		
		JCBTpServ = new JComboBox<String>();
		JCBTpServ.setBounds(368, 92, 118, 20);
		
		contentPanel.add(JCBTpServ);
		
		JLBairro = new JLabel("Bairro");
		JLBairro.setBounds(10, 175, 46, 14);
		contentPanel.add(JLBairro);
		
		JTFBairro = new JTextField();
		JTFBairro.setBounds(48, 172, 118, 20);
		contentPanel.add(JTFBairro);
		JTFBairro.setColumns(10);
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(279, 178, 46, 14);
		contentPanel.add(lblCep);
		
		JFFCep = new JTextField();
		JFFCep.setBounds(368, 175, 118, 20);
		contentPanel.add(JFFCep);
		JFFCep.setColumns(10);
		
		JCBOperadora1 = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora1.addItem(item);
		}
		JCBOperadora1.setBounds(496, 67, 59, 20);
		contentPanel.add(JCBOperadora1);
		
		JCBOperadora = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora.addItem(item);
		}
		JCBOperadora.setBounds(176, 64, 59, 20);
		contentPanel.add(JCBOperadora);
		
		JCBOperadora2 = new JComboBox<String>();
		for(String item : ext.getOperadora()){
			JCBOperadora2.addItem(item);
		}
		JCBOperadora2.setBounds(176, 92, 59, 20);
		contentPanel.add(JCBOperadora2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuForn = new JButton("Atualizar");
				JBAtuForn.addActionListener(this);
				buttonPane.add(JBAtuForn);
				getRootPane().setDefaultButton(JBAtuForn);
			}
			{
				JBExclForn = new JButton("Excluir");
				JBExclForn.addActionListener(this);
				buttonPane.add(JBExclForn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBAtuForn){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja atualizar o fornecedor?")){
				
			
			if(JTFNome.getText().isEmpty()) // Valida Nome
				JOptionPane.showMessageDialog(null, "Nome em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(!Validacoes.ValidaCpfCnpj(JFFCnpj.getText())) // Valida CpfCnpj
				JOptionPane.showMessageDialog(null, "Cpf/Cnpj inválido.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JFFRg.getText().trim().isEmpty()) // Valida Rg
				JOptionPane.showMessageDialog(null, "Rg em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(!Validacoes.ValidaTelefone(JFFFone.getText())) // Valida Telefone
				JOptionPane.showMessageDialog(null, "Telefone inválido.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JTFEmail.getText().trim().isEmpty()) // Valida Email
				JOptionPane.showMessageDialog(null, "Email em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JTFEnd.getText().trim().isEmpty()) // Valida Endereço
				JOptionPane.showMessageDialog(null, "Endereço em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JTFBairro.getText().trim().isEmpty()) // Valida Bairro
				JOptionPane.showMessageDialog(null, "Bairro em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(Validacoes.ValidaCep(JFFCep.getText())) // Valida Cep
				JOptionPane.showMessageDialog(null, "Cep inválido.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JTFSite.getText().trim().isEmpty()) // Valida Site
				JOptionPane.showMessageDialog(null, "Site em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else{
				
				FornecedoresBean fornBean = new FornecedoresBean();
				
				fornBean.setNomeforn(JTFNome.getText());
				fornBean.setCnpjforn(Extras.FormatCnpjCpf(JFFCnpj.getText())); 		// Formata e seta no Bean
				fornBean.setRgforn(JFFRg.getText());
				
				fornBean.setTelefonesforn (  Extras.FormatFoneBD(Extras.FormatFone(JFFFone.getText()))   ); 	// Formata e seta no Bean somente os números
				fornBean.setTelefonesforn1(  Extras.FormatFoneBD(Extras.FormatFone(JFFFone1.getText()))  ); 	// Formata e seta no Bean somente os números
				fornBean.setTelefonesforn2(  Extras.FormatFoneBD(Extras.FormatFone(JFFFone2.getText()))  ); 	// Formata e seta no Bean somente os números
				
				fornBean.setDdd  ( Extras.FormatDDD(Extras.FormatFone(JFFFone.getText()))  ); 	// Formata e seta no Bean somente os DDD
				fornBean.setDdd1 ( Extras.FormatDDD(Extras.FormatFone(JFFFone1.getText())) ); 	// Formata e seta no Bean somente os DDD
				fornBean.setDdd2 ( Extras.FormatDDD(Extras.FormatFone(JFFFone2.getText())) ); 	// Formata e seta no Bean somente os DDD
				
				fornBean.setOperadora(JCBOperadora.getSelectedItem().toString()); 	
				fornBean.setOperadora1(JCBOperadora1.getSelectedItem().toString());	
				fornBean.setOperadora2(JCBOperadora2.getSelectedItem().toString());	
				
				fornBean.setEmailforn(JTFEmail.getText());
				fornBean.setEndforn(JTFEnd.getText());
				fornBean.setBairro(JTFBairro.getText());
				
				fornBean.setCep(Extras.FormatCep(JFFCep.getText()));				// Formata e seta no Bean
				fornBean.setSiteforn(JTFSite.getText());
				
				/* Colocar o método de cadastrar */
				/* Acredito que esteja todas as validações possíveis */
				
			}// final da validação
			
			}// final da confirmação
			
			
		}// final do botão atualizar
		
		if(acao.getSource() == JBExclForn){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja excluir o fornecedor?")){
				
			}// final da confirmação
			
		}// final do botão excluir
	}
}
