package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JDTelaCadUsu extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvUsu;
	private JButton JBNovUsu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadUsu dialog = new JDTelaCadUsu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadUsu() {
		setBounds(100, 100, 450, 300);
		setTitle("SIGA - cadastro de usu\u00E1rio");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvUsu = new JButton("Salvar");
				buttonPane.add(JBSalvUsu);
				getRootPane().setDefaultButton(JBSalvUsu);
			}
			{
				JBNovUsu = new JButton("Novo");
				buttonPane.add(JBNovUsu);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBSalvUsu){
			
		}// final do botão salvar usuário
		
		if(acao.getSource() == JBNovUsu){
			
		}// final do botão novo usuário

	}

}
