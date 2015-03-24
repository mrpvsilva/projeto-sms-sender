package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import Control.FornecedoresControl;
import Dominio.Fornecedor;

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
	private DefaultTableModel model;
	protected String valor;

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
		setTitle("SIGA - buscar fornecedores");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.addActionListener(this);
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
			for (String item : _fornecedorControl.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(43, 12, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 412, 158);
			{
				final TableModel tabelaModel = new DefaultTableModel(
						new String[][] {}, new String[] { "ID", "CNPJ/CPF",
								"Nome", "Telefone" }) {
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

				// for (LancamentoBean lanc : lancControl.getLancamentos()) {
				// Laço necessário para incluir registro na tabela
				// model.addRow(new Object[]{new
				// Integer(lanc.getIdLancamento()),formatas.format(lanc.getDtCompra()),lanc.getNAutorizacao(),lanc.getSelectedConveniada()});
				CarregarGrid(_fornecedorControl.ListarTodos());
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
				JBCadForn.addActionListener(this);
				buttonPane.add(JBCadForn);
				getRootPane().setDefaultButton(JBCadForn);
			}
			{
				JBEditForn = new JButton("Alterar");
				JBEditForn.addActionListener(this);
				buttonPane.add(JBEditForn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadForn) {

			try {
				JDTelaCadForn jdtcf = new JDTelaCadForn();
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
					JDTelaEditForn jdtef = new JDTelaEditForn(
							Integer.parseInt(String.valueOf(tabela.getValueAt(
									linha, 0))));
					jdtef.setVisible(true);
					jdtef.setLocationRelativeTo(null);
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
				CarregarGrid(_fornecedorControl.ListarTodos(coluna, _valor));
			} else {
				CarregarGrid(_fornecedorControl.ListarTodos());
			}

		}// final do botão buscar fornecedores

	}// final da ação do botão

	private void CarregarGrid(List<Fornecedor> lista) {
		model.setRowCount(0);
		for (Fornecedor f : lista) {

			model.addRow(new Object[] {
					f.getId(),
					f.getCpfcnpj(),
					f.getNome(),
					"(" + f.getTelefones().get(0).getDdd() + ")"
							+ f.getTelefones().get(0).getNumero() + "-"
							+ f.getTelefones().get(0).getOperadora() });

		}

	}
}
