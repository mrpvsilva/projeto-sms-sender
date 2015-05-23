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
import Dominio.Permissao;
import Dominio.Usuario;

import javax.swing.ImageIcon;

import TableModels.DefaultTableModel;
import TableModels.UsuarioTableModel;
import Util.Modulos;
import Util.PermissoesManager;
import java.awt.Font;
import javax.swing.SwingConstants;

public class BuscarUsuarios extends JDialog implements ActionListener {

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
	private DefaultTableModel<Usuario> model;
	private JTable tabela;
	private JScrollPane scroll;
	protected String valor;
	private JButton JBSair;
	private Permissao permissao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarUsuarios dialog = new BuscarUsuarios();
			// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarUsuarios() {
		permissao = PermissoesManager.buscarPermissao(Modulos.Usuarios);
		setResizable(false);
		setTitle("SIGA - buscar usuário");
		setBounds(100, 100, 574, 333);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
			JLFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLFiltro.setBounds(0, 15, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBBuscar.setIcon(new ImageIcon(BuscarUsuarios.class
					.getResource("/Img/Procurar.png")));
			JBBuscar.addActionListener(this);
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.setBounds(432, 15, 115, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JTFBuscar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 10) {
						Pesquisar();
					}
				}
			});
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(199, 15, 223, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 535, 200);
			{

				model = new UsuarioTableModel(usuCont.BuscarTodos());
				tabela = new JTable(model);
				tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scroll.setViewportView(tabela);
				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			}
		}

		JCBFiltro = new JComboBox<String>();
		JCBFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		for (String item : usuCont.Filtros()) {
			JCBFiltro.addItem(item);
		}
		JCBFiltro.setBounds(58, 15, 131, 20);
		contentPanel.add(JCBFiltro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadUsu = new JButton("Cadastrar");
				JBCadUsu.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadUsu.setIcon(new ImageIcon(BuscarUsuarios.class
						.getResource("/Img/save16.png")));
				buttonPane.add(JBCadUsu);
				JBCadUsu.addActionListener(this);
				JBCadUsu.setVisible(permissao.isCadastrar());
				JBCadUsu.setMnemonic(KeyEvent.VK_C);
				getRootPane().setDefaultButton(JBCadUsu);
			}
			{
				JBEditUsu = new JButton("Editar");
				JBEditUsu.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBEditUsu.setVisible(permissao.isAlterar());
				JBEditUsu.setIcon(new ImageIcon(BuscarUsuarios.class
						.getResource("/Img/edit_add16.png")));
				JBEditUsu.setMnemonic(KeyEvent.VK_E);
				buttonPane.add(JBEditUsu);
				{
					JBSair = new JButton("Sair");
					JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
					JBSair.setIcon(new ImageIcon(BuscarUsuarios.class
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
				JDTelaCadUsu jdtcu = new JDTelaCadUsu(null, model);
				jdtcu.setVisible(true);
				jdtcu.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscara", JOptionPane.ERROR_MESSAGE);
			}

		}// final do botão cadastrar usuário

		if (acao.getSource() == JBEditUsu) {
			int linha = tabela.getSelectedRow();

			if (linha > -1) {

				try {
					// int id = model.getId(linha);
					JDTelaCadUsu jdtcu = new JDTelaCadUsu(model.find(linha),
							model);
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
		}// final do botão atualizar usuário

		if (acao.getSource() == JBBuscar) {
			Pesquisar();
		}// final do botão buscar usuário

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da ação do botão

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
