package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.ClientesControl;
import Dominio.Cliente;
import Dominio.Permissao;
import Interfaces.IAcessoManager;
import Managers.AcessoManager;
import TableModels.ClienteTableModel;
import TableModels.DefaultTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class JDTelaBuscarCli extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private ClientesControl cliCont = new ClientesControl();
	protected String valor;
	private JButton JBSair;
	private Permissao Clientes;
	private JPanel panel;
	private JLabel lblPesquisa;
	private JTextField textField;
	private JRadioButton radiorg;
	private DefaultTableModel<Cliente> modelClientes;
	private ClientesControl controller;
	private JTable table;
	private JButton btnAlterar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarCli dialog = new JDTelaBuscarCli();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarCli() {
		controller = new ClientesControl();
		modelClientes = new ClienteTableModel(controller.listarTodos());
		Clientes = PermissoesManager.buscarPermissao(Modulos.Clientes);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaBuscarCli.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar clientes");
		setBounds(100, 100, 797, 740);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pesquisar",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 11, 765, 107);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPesquisa.setBounds(57, 36, 84, 15);
		panel.add(lblPesquisa);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(151, 34, 323, 20);
		panel.add(textField);
		textField.setColumns(10);

		JRadioButton radionome = new JRadioButton("Nome");

		radionome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radionome.setSelected(true);
		radionome.setBounds(151, 56, 58, 23);

		JRadioButton radiocpf = new JRadioButton("CPF");
		radiocpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radiocpf.setBounds(211, 56, 47, 23);

		radiorg = new JRadioButton("RG");
		radiorg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radiorg.setBounds(260, 56, 47, 23);

		ButtonGroup radiogroup = new ButtonGroup();
		radiogroup.add(radionome);
		radiogroup.add(radiocpf);
		radiogroup.add(radiorg);

		panel.add(radionome);
		panel.add(radiocpf);
		panel.add(radiorg);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPesquisar.setIcon(new ImageIcon(JDTelaBuscarCli.class
				.getResource("/Img/Procurar.png")));
		btnPesquisar.setBounds(484, 33, 118, 23);
		panel.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 765, 537);
		contentPanel.add(scrollPane);
		
		table = new JTable(modelClientes);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JBCadCli = new JButton("Cadastrar");
		JBCadCli.setIcon(new ImageIcon(JDTelaBuscarCli.class
				.getResource("/Img/save16.png")));
		JBCadCli.addActionListener(this);
		JBCadCli.setMnemonic(KeyEvent.VK_C);
		JBCadCli.setVisible(Clientes.isCadastrar());
		buttonPane.add(JBCadCli);
		getRootPane().setDefaultButton(JBCadCli);

		JBEditCli = new JButton("Editar");
		JBEditCli.setIcon(new ImageIcon(JDTelaBuscarCli.class
				.getResource("/Img/edit_add16.png")));
		JBEditCli.setMnemonic(KeyEvent.VK_E);
		JBEditCli.setVisible(Clientes.isAlterar());
		JBEditCli.addActionListener(this);

		JBSair = new JButton("Sair");
		JBSair.setIcon(new ImageIcon(JDTelaBuscarCli.class
				.getResource("/Img/exit16.png")));
		JBSair.addActionListener(this);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(JDTelaBuscarCli.class.getResource("/Img/edit_add16.png")));
		buttonPane.add(btnAlterar);
		JBSair.setMnemonic(KeyEvent.VK_Q);
		buttonPane.add(JBSair);

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadCli) {

			try {
				JDTelaFormCliente cdtcc = new JDTelaFormCliente();
				cdtcc.setVisible(true);
				cdtcc.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,
						"Erro ao carregar máscaras", "Error",
						JOptionPane.ERROR_MESSAGE);
			}// final do try e catch

		}// final do botão cadastrar cliente

		if (acao.getSource() == JBEditCli) {

			// try {
			// // JDTelaEditCli jdtec = new JDTelaEditCli();
			// // jdtec.setVisible(true);
			// // jdtec.setLocationRelativeTo(null);
			// } catch (ParseException e) {
			// JOptionPane.showMessageDialog(null,
			// "Erro ao carregar máscaras","Error",JOptionPane.ERROR_MESSAGE);
			// }// final do try e catch

		}

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da ação do botão
}
