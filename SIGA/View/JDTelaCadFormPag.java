package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class JDTelaCadFormPag extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFFormPag;
	private JButton JBSalvar;
	private JButton JBNovo;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadFormPag dialog = new JDTelaCadFormPag();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadFormPag() {
		setResizable(false);
		setModal(true);
		setTitle("SIGA - cadastro de forma de pagamento");
		setBounds(100, 100, 450, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Forma de pagamento");
			lblNewLabel.setBounds(10, 10, 121, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JTFFormPag = new JTextField();
			JTFFormPag.setBounds(141, 7, 283, 20);
			contentPanel.add(JTFFormPag);
			JTFFormPag.setColumns(10);
		}
		
		JCheckBox JChBAtivo = new JCheckBox("Ativo?");
		JChBAtivo.setBounds(10, 31, 121, 14);
		contentPanel.add(JChBAtivo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setIcon(new ImageIcon(JDTelaCadFormPag.class.getResource("/Img/Confirmar.png")));
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				JBSalvar.addActionListener(this);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBNovo = new JButton("Novo");
				JBNovo.setIcon(new ImageIcon(JDTelaCadFormPag.class.getResource("/Img/window_new16.png")));
				JBNovo.setMnemonic(KeyEvent.VK_N);
				JBNovo.addActionListener(this);
				buttonPane.add(JBNovo);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setIcon(new ImageIcon(JDTelaCadFormPag.class.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource()==JBSalvar){
			
		}// final do botão salvar
		
		if(acao.getSource() == JBSair){
			this.dispose();
		}// final do botão sair
		
		if(acao.getSource()== JBNovo){
			
		}// final do botão novo
		
	}
}
