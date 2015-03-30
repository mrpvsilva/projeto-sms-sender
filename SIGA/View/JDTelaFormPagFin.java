package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.EventoControl;
import Dominio.TipoPagamento;

public class JDTelaFormPagFin extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private JComboBox JCBTpPagamento;
	private EventoControl evtControl = new EventoControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaFormPagFin dialog = new JDTelaFormPagFin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaFormPagFin() {
		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A - formas de pagamento");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel JLTipoPagamento = new JLabel("Tipo pagamento");
		JLTipoPagamento.setBounds(10, 11, 111, 14);
		contentPanel.add(JLTipoPagamento);
		
		JCBTpPagamento = new JComboBox();
		for (TipoPagamento item : evtControl.allTpPagamentos()) {
			JCBTpPagamento.addItem(item.getDescricao());
		}
		JCBTpPagamento.setBounds(117, 8, 166, 20);
		contentPanel.add(JCBTpPagamento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadCli = new JButton("Cadastrar");
				JBCadCli.setActionCommand("OK");
				buttonPane.add(JBCadCli);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBEditCli = new JButton("Alterar");
				JBEditCli.setActionCommand("Cancel");
				buttonPane.add(JBEditCli);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadCli){
			
		}// final do botão cadastrar cliente
		
		if(acao.getSource() == JBEditCli){
			
		}// final do botão atualizar cliente
		
	}// final da ação do botão
}
