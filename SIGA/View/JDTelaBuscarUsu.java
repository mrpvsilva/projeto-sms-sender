package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.UsuarioControl;
import Dominio.Usuario;

import javax.swing.ImageIcon;

import TableModels.AbstractDefaultTableModel;
import TableModels.UsuarioTableModel;

public class JDTelaBuscarUsu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadUsu;
	private JButton JBEditUsu;
	private JLabel JLFiltro;
	public static JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private UsuarioControl usuCont = new UsuarioControl();
	private AbstractDefaultTableModel<Usuario> model;
	private JTable tabela;
	private JScrollPane scroll;
	protected String valor;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarUsu dialog = new JDTelaBuscarUsu();
			// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarUsu() {
		setResizable(false);
		setTitle("SIGA - buscar usu�rio");
		setBounds(100, 100, 476, 300);
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
			JBBuscar.setIcon(new ImageIcon(JDTelaBuscarUsu.class
					.getResource("/Img/Procurar.png")));
			JBBuscar.addActionListener(this);
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.setBounds(335, 11, 115, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 10) {
						Pesquisar();
					}
				}
			});
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(153, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			// Cria��o da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 438, 158);
			{

				model = new UsuarioTableModel(usuCont.BuscarTodos());
				tabela = new JTable(model);
				scroll.setViewportView(tabela);
				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			}
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
				JBCadUsu.setIcon(new ImageIcon(JDTelaBuscarUsu.class
						.getResource("/Img/save16.png")));
				buttonPane.add(JBCadUsu);
				JBCadUsu.addActionListener(this);
				JBCadUsu.setMnemonic(KeyEvent.VK_C);
				getRootPane().setDefaultButton(JBCadUsu);
			}
			{
				JBEditUsu = new JButton("Editar");
				JBEditUsu.setIcon(new ImageIcon(JDTelaBuscarUsu.class
						.getResource("/Img/edit_add16.png")));
				JBEditUsu.setMnemonic(KeyEvent.VK_E);
				buttonPane.add(JBEditUsu);
				{
					JBSair = new JButton("Sair");
					JBSair.setIcon(new ImageIcon(JDTelaBuscarUsu.class
							.getResource("/Img/exit16.png")));
					JBSair.addActionListener(this);
					JBSair.setMnemonic(KeyEvent.VK_Q);
					buttonPane.add(JBSair);
				}
				JBEditUsu.addActionListener(this);
			}
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadUsu) {

			try {
				JDTelaCadUsu jdtcu = new JDTelaCadUsu(0,model);
				jdtcu.setVisible(true);
				jdtcu.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar m�scara", JOptionPane.ERROR_MESSAGE);
			}

		}// final do bot�o cadastrar usu�rio

		if (acao.getSource() == JBEditUsu) {
			int linha = tabela.getSelectedRow();

			if (linha > -1) {

				try {
					int id = model.getId(linha);
					JDTelaCadUsu jdtcu = new JDTelaCadUsu(id, model);
					jdtcu.setVisible(true);
					jdtcu.setLocationRelativeTo(null);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do bot�o atualizar usu�rio

		if (acao.getSource() == JBBuscar) {
			Pesquisar();
		}// final do bot�o buscar usu�rio

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da a��o do bot�o

	private void Pesquisar() {
		String campo = JCBFiltro.getSelectedItem().toString() == "USUARIO" ? "usuario"
				: JCBFiltro.getSelectedItem().toString() == "NOME" ? "nomecompleto"
						: "cpf";
		String value = JTFBuscar.getText();
		if (!value.equals("")) {
			model.setLinhas(usuCont.BuscarTodos(campo, value));
		} else {
			model.setLinhas(usuCont.BuscarTodos());
		}
	}

}
