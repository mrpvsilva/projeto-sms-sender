package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class JDTelaCadForn extends JDialog implements ActionListener{

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
	private JTextField textField;
	private JLabel lblCep;
	private JTextField textField_1;
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
	 * @throws ParseException 
	 */
	public JDTelaCadForn() throws ParseException {
		
		setBounds(100, 100, 450, 300);
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
		
		maskCnpj = new MaskFormatter("##.###.###/####-##");
		JFFCnpj = new JFormattedTextField(maskCnpj);
		JFFCnpj.setBounds(48, 36, 118, 20);
		contentPanel.add(JFFCnpj);
		
		maskRG= new MaskFormatter("#######");
		JFFRg = new JFormattedTextField(maskRG);
		JFFRg.setBounds(286, 33, 118, 20);
		contentPanel.add(JFFRg);
		
		JLabel JLSite = new JLabel("Site");
		JLSite.setBounds(10, 200, 46, 14);
		contentPanel.add(JLSite);
		
		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setBounds(197, 92, 79, 14);
		contentPanel.add(JLTpServ);
		
		maskFone = new MaskFormatter("(##)####-####");
		JFFFone = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone.setBounds(48, 64, 118, 20);
		contentPanel.add(JFFFone);
		
		JFFFone1 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFCnpj).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone1.setBounds(286, 64, 118, 20);
		contentPanel.add(JFFFone1);
		
		JFFFone2 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFCnpj).setFocusLostBehavior(JFormattedTextField.COMMIT);
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
		
		textField = new JTextField();
		textField.setBounds(48, 172, 118, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(197, 175, 46, 14);
		contentPanel.add(lblCep);
		
		textField_1 = new JTextField();
		textField_1.setBounds(286, 172, 118, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
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

		if(acao.getSource() == JBSalvForn){
			
		}// final do botão salvar
		
		if(acao.getSource() == JBNovForn){
			
		}// final do botão novo

	}
}
