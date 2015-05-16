package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Control.FornecedoresControl;
import Dominio.Fornecedor;
import Dominio.Permissao;
import Interfaces.IAcessoManager;

import javax.swing.ImageIcon;

import Managers.AcessoManager;
import PersistenceManagerFactory.Factory;
import TableModels.DefaultTableModel;
import TableModels.FornecedorTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JDTelaBuscarForn extends JDialog implements ActionListener {

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
	private FornecedoresControl _fornecedorControl = new FornecedoresControl();
	private JScrollPane scroll;
	private JTable tabela;
	private DefaultTableModel<Fornecedor> model;
	protected String valor;
	private JButton JBSair;
	
	private Permissao Fornecedores;

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
		
		Fornecedores = PermissoesManager.buscarPermissao(Modulos.Fornecedores);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaBuscarForn.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar fornecedores");
		setBounds(100, 100, 479, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBBuscar.setIcon(new ImageIcon(JDTelaBuscarForn.class
					.getResource("/Img/Procurar.png")));
			JBBuscar.addActionListener(this);
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.setBounds(338, 12, 99, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
			JLFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
			JLFiltro.setBounds(0, 12, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(162, 12, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			JCBFiltro = new JComboBox<String>();
			JCBFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
			for (String item : _fornecedorControl.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(56, 12, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 425, 158);
			{

				model = new FornecedorTableModel(
						_fornecedorControl.listarTodos());
				tabela = new JTable(model);
				tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scroll.setViewportView(tabela);

				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadForn = new JButton("Cadastrar");
				JBCadForn.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadForn.setIcon(new ImageIcon(JDTelaBuscarForn.class
						.getResource("/Img/save16.png")));
				JBCadForn.addActionListener(this);
				JBCadForn.setMnemonic(KeyEvent.VK_C);
				JBCadForn.setVisible(Fornecedores.isCadastrar());
				buttonPane.add(JBCadForn);
				getRootPane().setDefaultButton(JBCadForn);
			}
			{
				JBEditForn = new JButton("Editar");
				JBEditForn.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBEditForn.setIcon(new ImageIcon(JDTelaBuscarForn.class
						.getResource("/Img/edit_add16.png")));
				JBEditForn.addActionListener(this);
				JBEditForn.setMnemonic(KeyEvent.VK_E);
				JBEditForn.setVisible(Fornecedores.isAlterar());
				buttonPane.add(JBEditForn);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(JDTelaBuscarForn.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadForn) {

			try {
				JDTelaCadForn jdtcf = new JDTelaCadForn(null,model);
				jdtcf.setVisible(true);
				jdtcf.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}// final do try e catch

		}// final do botão cadastrar fornecedores

		if (acao.getSource() == JBEditForn) {

			try {
				int linha = tabela.getSelectedRow();
				if (linha > -1) {					
					JDTelaCadForn jdtcf = new JDTelaCadForn(model.find(linha),model);
					jdtcf.setVisible(true);
					jdtcf.setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha");
				}
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}// final do try e catch

		}// final do botão atualizar fornecedores

		if (acao.getSource() == JBBuscar) {

			String coluna = JCBFiltro.getSelectedItem().toString();
			coluna = coluna == "CNPJ" ? "cpfcnpj" : coluna == "NOME" ? "nome"
					: "";
			String _valor = JTFBuscar.getText();

			if (!_valor.equals("")) {
				model.setLinhas(_fornecedorControl.listarTodos(coluna, _valor));
			} else {
				model.setLinhas(_fornecedorControl.listarTodos());
			}

		}// final do botão buscar fornecedores

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da ação do botão

}
