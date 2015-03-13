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

import Control.UsuarioControl;

public class JDTelaBuscarUsu extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadUsu;
	private JButton JBEditUsu;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private UsuarioControl usuCont = new UsuarioControl();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarUsu dialog = new JDTelaBuscarUsu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarUsu() {
		setTitle("SIGA - buscar usuário");
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
			JBBuscar.addActionListener(this);
			JBBuscar.setBounds(335, 11, 89, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(153, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		
		JCBFiltro = new JComboBox<String>();
		for (String item : usuCont.Filtros()) {
			JCBFiltro.addItem(item);
		}
		JCBFiltro.setBounds(40, 11, 103, 20);
		contentPanel.add(JCBFiltro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadUsu = new JButton("Cadastrar");
				buttonPane.add(JBCadUsu);
				getRootPane().setDefaultButton(JBCadUsu);
			}
			{
				JBEditUsu = new JButton("Alterar");
				buttonPane.add(JBEditUsu);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadUsu){
			
		}// final do botão cadastrar usuário
		
		if(acao.getSource() == JBEditUsu){
			
		}// final do botão atualizar usuário
		
		if(acao.getSource() == JBBuscar){
			
		}// final do botão buscar usuário
		
	}// final da ação do botão
}
