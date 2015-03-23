package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import Control.ServicosControl;
import Dominio.Item;

public class JDTelaBuscarServ extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadServ;
	private JButton JBEditServ;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private ServicosControl servCont = new ServicosControl();
	private DefaultTableModel model;
	private JTable tabela;
	private JScrollPane scroll;
	protected String Id;
	private ServicosControl _servicoControl = new ServicosControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarServ dialog = new JDTelaBuscarServ();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarServ() {
		setTitle("SIGA - buscar serviços");
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
		{
			JCBFiltro = new JComboBox<String>();
			for (String item : servCont.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(41, 11, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 412, 158);
			{
				final TableModel tabelaModel = new DefaultTableModel(
						new String[][] {},
						new String[] { "ID", "Item", "Ativo" }) {
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

				for (Item item : _servicoControl.BuscarTodos()) {
					model.addRow(new Object[] { item.getId(), item.getNome(),
							item.isAtivo() ? "Ativo" : "Inativo" });
				}

				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

				tabela.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent evt) {
						if (evt.getClickCount() == 1) {
							int linha = tabela.getSelectedRow();
							Id = String.valueOf(tabela.getValueAt(linha, 0));

						}

					}
				});

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadServ = new JButton("Cadastrar");
				JBCadServ.addActionListener(this);
				buttonPane.add(JBCadServ);
				getRootPane().setDefaultButton(JBCadServ);
			}
			{
				JBEditServ = new JButton("Alterar");
				JBEditServ.addActionListener(this);
				buttonPane.add(JBEditServ);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadServ) {
			JDTelaCadServ jdtcs = new JDTelaCadServ();
			jdtcs.setVisible(true);
			jdtcs.setLocationRelativeTo(null);

		}// final do botão cadastrar serviços

		if (acao.getSource() == JBEditServ) {
			if (Id != null) {
				JDTelaEditServ jdtes = new JDTelaEditServ(Integer.parseInt(Id));
				jdtes.setVisible(true);
				jdtes.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do botão atualizar serviços

		if (acao.getSource() == JBBuscar) {

			String campo = JCBFiltro.getSelectedItem().toString() == "ITEM" ? "nome"
					: "descricao";
			String txt = JTFBuscar.getText();

			model.setRowCount(0);
			for (Item item : _servicoControl.BuscarTodos(campo, txt)) {
				model.addRow(new Object[] { item.getId(), item.getNome(),
						item.isAtivo() ? "Ativo" : "Inativo" });
			}

		}// final do botão buscar serviços

	}// final da ação do botão

}
