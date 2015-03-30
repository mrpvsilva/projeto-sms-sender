package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.UsuarioControl;
import Dominio.Usuario;

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
	private DefaultTableModel model;
	private JTable tabela;
	private JScrollPane scroll;
	protected String valor;

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
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 412, 158);
			{
				final TableModel tabelaModel = new DefaultTableModel(
						new String[][] {}, new String[] { "ID", "Usuário",
								"Nome", "CPF", "Perfil" }) {
					/**
																		 * 
																		 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int col) {
						return false;
					}
				};

				tabela = new JTable();
				scroll.setViewportView(tabela);
				tabela.setModel(tabelaModel);
				model = (DefaultTableModel) tabela.getModel();
				model.fireTableDataChanged();

				CarregarGrid(usuCont.BuscarTodos());
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
				buttonPane.add(JBCadUsu);
				JBCadUsu.addActionListener(this);
				getRootPane().setDefaultButton(JBCadUsu);
			}
			{
				JBEditUsu = new JButton("Alterar");
				buttonPane.add(JBEditUsu);
				JBEditUsu.addActionListener(this);
			}
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadUsu) {

			try {
				JDTelaCadUsu jdtcu = new JDTelaCadUsu(0);
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
					JDTelaCadUsu jdtcu = new JDTelaCadUsu(
							Integer.parseInt(String.valueOf(tabela.getValueAt(
									linha, 0))));
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

	}// final da ação do botão

	private void Pesquisar() {
		String campo = JCBFiltro.getSelectedItem().toString() == "USUARIO" ? "usuario"
				: JCBFiltro.getSelectedItem().toString() == "NOME" ? "nomecompleto"
						: "cpf";
		String value = JTFBuscar.getText();
		if (!value.equals("")) {
			CarregarGrid(usuCont.BuscarTodos(campo, value));
		} else {
			CarregarGrid(usuCont.BuscarTodos());
		}
	}

	private void CarregarGrid(List<Usuario> lista) {
		model.setRowCount(0);
		for (Usuario u : lista) {
			model.addRow(new Object[] { u.getId(), u.getUsuario(),
					u.getNomeCompleto(), u.getCpf(), u.getPerfil() });
		}
	}
}
