package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class JDTelaEditCli extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBAtuCli;
	private JButton JBExcCli;
	private JTextField JTFNome;
	private JTextField JTFResp;
	private JTextField JTFRg;
	private MaskFormatter maskRG;
	private MaskFormatter maskCpf;
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private MaskFormatter maskFone;
	private JTextField JTFNomeGuerra;
	private JFormattedTextField JFFFone;
	private JFormattedTextField JFFConvExtra;
	private JFormattedTextField JFFFone1;
	private JComboBox<String> JCBEvento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditCli dialog = new JDTelaEditCli();
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
	public JDTelaEditCli() throws ParseException {
		setTitle("SIGA - atualiza\u00E7\u00E3o de cliente");
		setBounds(100, 100, 450, 300);
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
		JLTipoEvento.setBounds(239, 61, 46, 14);
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
		JLFone1.setBounds(239, 165, 46, 14);
		contentPanel.add(JLFone1);
		
		JLabel JLFone2 = new JLabel("Fone2");
		JLFone2.setBounds(10, 190, 46, 14);
		contentPanel.add(JLFone2);
		
		JLabel lblConvExtra = new JLabel("Conv. Extra");
		lblConvExtra.setBounds(239, 83, 66, 14);
		contentPanel.add(lblConvExtra);
		
		JLabel JLNomeGuerra = new JLabel("Nome Guerra");
		JLNomeGuerra.setBounds(239, 190, 81, 14);
		contentPanel.add(JLNomeGuerra);
		
		JTFNome = new JTextField();
		JTFNome.setBounds(58, 8, 366, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);
		
		JTFResp = new JTextField();
		JTFResp.setBounds(58, 33, 366, 20);
		contentPanel.add(JTFResp);
		JTFResp.setColumns(10);
		
		
		JTFRg = new JFormattedTextField(maskRG);
		JTFRg.setBounds(58, 58, 118, 20);
		contentPanel.add(JTFRg);
		JTFRg.setColumns(10);
		
		JCBEvento = new JComboBox<String>();
		JCBEvento.setBounds(306, 58, 118, 20);
		contentPanel.add(JCBEvento);
		
		JFormattedTextField JFFCpf = new JFormattedTextField();
		JFFCpf.setBounds(58, 83, 118, 20);
		contentPanel.add(JFFCpf);
		
		JTFEmail = new JTextField();
		JTFEmail.setBounds(58, 108, 366, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);
		
		JTFEnd = new JTextField();
		JTFEnd.setBounds(58, 133, 366, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);
		
		
		JFFFone = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone.setBounds(58, 161, 118, 20);
		contentPanel.add(JFFFone);
		
		JFFConvExtra = new JFormattedTextField(maskRG);
		JFFConvExtra.setBounds(306, 83, 118, 20);
		contentPanel.add(JFFConvExtra);
		
		JFFFone1 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone1).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone1.setBounds(306, 161, 118, 20);
		contentPanel.add(JFFFone1);
		
		JFormattedTextField JFFFone2 = new JFormattedTextField(maskFone);
		((JFormattedTextField) JFFFone2).setFocusLostBehavior(JFormattedTextField.COMMIT);
		JFFFone2.setBounds(58, 187, 118, 20);
		contentPanel.add(JFFFone2);
		
		JTFNomeGuerra = new JTextField();
		JTFNomeGuerra.setBounds(306, 187, 118, 20);
		contentPanel.add(JTFNomeGuerra);
		JTFNomeGuerra.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuCli = new JButton("Atualizar");
				buttonPane.add(JBAtuCli);
				getRootPane().setDefaultButton(JBAtuCli);
			}
			{
				JBExcCli = new JButton("Excluir");
				JBExcCli.addActionListener(this);
				buttonPane.add(JBExcCli);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBAtuCli){
			
		}// final do botão atualizar cliente
		
		if(acao.getSource() == JBExcCli){
			
		}// final do botão excluir cliente
		
	}// final da ação do botão
}
