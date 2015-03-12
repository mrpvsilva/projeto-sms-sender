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

public class JDTelaBuscarForn extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadForn;
	private JButton JBEditForn;
	private JButton JBBuscar;
	private JLabel JLFiltro;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarForn dialog = new JDTelaBuscarForn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarForn() {
		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A - buscar fornecedores");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setBounds(335, 11, 89, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setBounds(10, 11, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(153, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			JCBFiltro = new JComboBox<String>();
			JCBFiltro.setBounds(39, 8, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadForn = new JButton("Cadastrar");
				buttonPane.add(JBCadForn);
				getRootPane().setDefaultButton(JBCadForn);
			}
			{
				JBEditForn = new JButton("Alterar");
				buttonPane.add(JBEditForn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {


		if(acao.getSource() == JBCadForn){
			
		}// final do bot�o cadastrar fornecedores
		
		if(acao.getSource() == JBEditForn){
			
		}// final do bot�o atualizar fornecedores
		
	}// final da a��o do bot�o

}
