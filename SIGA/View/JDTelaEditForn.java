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
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

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
	private JTextField JTFCep;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditForn dialog = new JDTelaEditForn();
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
	public JDTelaEditForn() throws ParseException  {
		
		setBounds(100, 100, 450, 300);
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
		JLRg.setBounds(197, 39, 35, 14);
		contentPanel.add(JLRg);
		
		JLabel JLTelefone1 = new JLabel("Fone");
		JLTelefone1.setBounds(10, 67, 46, 14);
		contentPanel.add(JLTelefone1);
		
		JLabel JLTelefone2 = new JLabel("Fone 1");
		JLTelefone2.setBounds(197, 64, 46, 14);
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
		JTFNome.setBounds(48, 8, 376, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);
		
		JFFCnpj = new JFormattedTextField();
		JFFCnpj.setBounds(48, 36, 118, 20);
		contentPanel.add(JFFCnpj);
		
		JFFRg = new JFormattedTextField();
		JFFRg.setBounds(286, 33, 118, 20);
		contentPanel.add(JFFRg);
		
		JLabel JLSite = new JLabel("Site");
		JLSite.setBounds(10, 200, 46, 14);
		contentPanel.add(JLSite);
		
		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setBounds(197, 92, 79, 14);
		contentPanel.add(JLTpServ);
		
		JFFFone = new JFormattedTextField();
		JFFFone.setBounds(48, 64, 118, 20);
		contentPanel.add(JFFFone);
		
		JFFFone1 = new JFormattedTextField();
		JFFFone1.setBounds(286, 64, 118, 20);
		contentPanel.add(JFFFone1);
		
		JFFFone2 = new JFormattedTextField();
		JFFFone2.setBounds(48, 92, 118, 20);
		contentPanel.add(JFFFone2);
		
		JTFEmail = new JTextField();
		JTFEmail.setBounds(48, 120, 376, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);
		
		JTFEnd = new JTextField();
		JTFEnd.setBounds(48, 148, 376, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);
		
		JTFSite = new JTextField();
		JTFSite.setBounds(48, 197, 376, 20);
		contentPanel.add(JTFSite);
		JTFSite.setColumns(10);
		
		JCBTpServ = new JComboBox<String>();
		JCBTpServ.setBounds(286, 89, 118, 20);
		
		contentPanel.add(JCBTpServ);
		
		JLBairro = new JLabel("Bairro");
		JLBairro.setBounds(10, 175, 46, 14);
		contentPanel.add(JLBairro);
		
		JTFBairro = new JTextField();
		JTFBairro.setBounds(48, 172, 118, 20);
		contentPanel.add(JTFBairro);
		JTFBairro.setColumns(10);
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(197, 175, 46, 14);
		contentPanel.add(lblCep);
		
		JTFCep = new JTextField();
		JTFCep.setBounds(286, 172, 118, 20);
		contentPanel.add(JTFCep);
		JTFCep.setColumns(10);
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
			
		}// final do botão atualizar
		
		if(acao.getSource() == JBExclForn){
			
		}// final do botão excluir
	}
}
