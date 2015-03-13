package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JDTelaCadLemb extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvLemb;
	private JButton JBNovLemb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadLemb dialog = new JDTelaCadLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadLemb() {
		setBounds(100, 100, 450, 300);
		setTitle("SIGA - cadastro de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvLemb = new JButton("Salvar");
				JBSalvLemb.addActionListener(this);
				buttonPane.add(JBSalvLemb);
				getRootPane().setDefaultButton(JBSalvLemb);
			}
			{
				JBNovLemb = new JButton("Novo");
				JBNovLemb.addActionListener(this);
				buttonPane.add(JBNovLemb);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {
		
		if(acao.getSource() == JBSalvLemb){
			
		}// final do botão salvar lembrete
		
		if(acao.getSource() == JBNovLemb){
			
		}// final do botão novo lembrete
		
	}

}
