package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
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

import javax.swing.ImageIcon;

import TableModels.DefaultTableModel;
import TableModels.FornecedorTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JRadioButton;

public class BuscarFornecedores extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadForn;
	private JButton JBEditForn;
	private JButton JBBuscar;
	private JLabel JLFiltro;
	private JTextField pesquisa;
	private FornecedoresControl _fornecedorControl = new FornecedoresControl();
	private JScrollPane scroll;
	private JTable tabela;
	private DefaultTableModel<Fornecedor> model;
	protected String valor;
	private JButton JBSair;
	private JRadioButton radionome;
	private JRadioButton radiocpfcnpj;
	private JComboBox tiposervicos;

	private Permissao Fornecedores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			BuscarFornecedores dialog = new BuscarFornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarFornecedores() {

		Fornecedores = PermissoesManager.buscarPermissao(Modulos.Fornecedores);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BuscarFornecedores.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar fornecedores");
		setBounds(100, 100, 750, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 122, 722, 354);
			{

				model = new FornecedorTableModel(
						_fornecedorControl.listarTodos());
				tabela = new JTable(model);
				tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scroll.setViewportView(tabela);

				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
				panel.setBounds(12, 11, 722, 100);
				contentPanel.add(panel);
				panel.setLayout(null);
				{
					JLFiltro = new JLabel("Pesquisa");
					JLFiltro.setBounds(10, 15, 61, 14);
					panel.add(JLFiltro);
					JLFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
					JLFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
				}
				{
					pesquisa = new JTextField();
					pesquisa.setBounds(81, 12, 305, 20);
					panel.add(pesquisa);
					pesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
					pesquisa.setColumns(10);
				}
				{
					JBBuscar = new JButton("Pesquisar");
					JBBuscar.setBounds(329, 66, 117, 23);
					panel.add(JBBuscar);
					JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
					JBBuscar.setIcon(new ImageIcon(BuscarFornecedores.class
							.getResource("/Img/Procurar.png")));
					JBBuscar.addActionListener(this);
					JBBuscar.setMnemonic(KeyEvent.VK_F);
				}

				JLabel lblServioPrestado = new JLabel("Tipo servi\u00E7o");
				lblServioPrestado.setHorizontalAlignment(SwingConstants.RIGHT);
				lblServioPrestado.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblServioPrestado.setBounds(396, 15, 85, 14);
				panel.add(lblServioPrestado);

				tiposervicos = new JComboBox(
						_fornecedorControl.DDLTipoServico());
				tiposervicos.removeItem("SELECIONE");
				tiposervicos.setFont(new Font("Tahoma", Font.PLAIN, 13));
				tiposervicos.setBounds(491, 12, 221, 22);
				panel.add(tiposervicos);

				radionome = new JRadioButton("Nome");
				radionome.setSelected(true);
				radionome.setFont(new Font("Tahoma", Font.PLAIN, 13));
				radionome.setBounds(81, 34, 71, 23);
				panel.add(radionome);

				radiocpfcnpj = new JRadioButton("CPF/CNPJ");
				radiocpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
				radiocpfcnpj.setBounds(154, 34, 85, 23);
				panel.add(radiocpfcnpj);

				ButtonGroup bg = new ButtonGroup();
				bg.add(radionome);
				bg.add(radiocpfcnpj);

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadForn = new JButton("Cadastrar");
				JBCadForn.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadForn.setIcon(new ImageIcon(BuscarFornecedores.class
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
				JBEditForn.setIcon(new ImageIcon(BuscarFornecedores.class
						.getResource("/Img/edit_add16.png")));
				JBEditForn.addActionListener(this);
				JBEditForn.setMnemonic(KeyEvent.VK_E);
				JBEditForn.setVisible(Fornecedores.isAlterar());
				buttonPane.add(JBEditForn);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(BuscarFornecedores.class
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
				EditFormFornecedor jdtcf = new EditFormFornecedor(null, model);
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
					EditFormFornecedor jdtcf = new EditFormFornecedor(
							model.find(linha), model);
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

			String coluna = radionome.isSelected() ? "nome" : "cpfcnpj";
			String _valor = pesquisa.getText();
			String tiposervico = tiposervicos.getSelectedItem().toString();

			model.setLinhas(_fornecedorControl.listarTodos(coluna, _valor,
					tiposervico));
			// } else {
			// model.setLinhas(_fornecedorControl.listarTodos());
			// }

		}// final do botão buscar fornecedores

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da ação do botão
}
