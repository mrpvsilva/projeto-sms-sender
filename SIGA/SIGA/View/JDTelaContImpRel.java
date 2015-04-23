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

public class JDTelaContImpRel extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaContImpRel dialog = new JDTelaContImpRel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaContImpRel() {
		setResizable(false);
		setModal(true);
		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A - relatório impressão de contrato");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setBounds(10, 11, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setBounds(335, 11, 89, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setBounds(153, 11, 172, 20);
			contentPanel.add(JTFBuscar);
			JTFBuscar.setColumns(10);
		}
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
