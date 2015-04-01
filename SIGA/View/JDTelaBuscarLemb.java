package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import Control.LembretesControl;
import Dominio.Lembrete;
import Extra.Extras;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class JDTelaBuscarLemb extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadLemb;
	private JButton JBEditLemb;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private JScrollPane scroll;
	private JTable tabela;
	private DefaultTableModel model;
	private LembretesControl _lembreteControl = new LembretesControl();
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarLemb dialog = new JDTelaBuscarLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarLemb() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDTelaBuscarLemb.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar lembretes");
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
			JBBuscar.setIcon(new ImageIcon(JDTelaBuscarLemb.class.getResource("/Img/Procurar.png")));
			JBBuscar.addActionListener(this);
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.setBounds(325, 11, 99, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(149, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			JCBFiltro = new JComboBox<String>(_lembreteControl.Filtros());
			JCBFiltro.setBounds(40, 11, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 412, 158);
			{
				final TableModel tabelaModel = new DefaultTableModel(
						new String[][] {}, new String[] { "ID", "Data",
								"Assunto", "Destinatario" }) {
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

				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

				CarregarGrid(_lembreteControl.BuscarTodos());

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadLemb = new JButton("Cadastrar");
				JBCadLemb.setIcon(new ImageIcon(JDTelaBuscarLemb.class.getResource("/Img/save16.png")));
				JBCadLemb.addActionListener(this);
				JBCadLemb.setMnemonic(KeyEvent.VK_C);
				buttonPane.add(JBCadLemb);
				getRootPane().setDefaultButton(JBCadLemb);
			}
			{
				JBEditLemb = new JButton("Editar");
				JBEditLemb.setIcon(new ImageIcon(JDTelaBuscarLemb.class.getResource("/Img/edit_add16.png")));
				JBEditLemb.addActionListener(this);
				JBEditLemb.setMnemonic(KeyEvent.VK_E);
				buttonPane.add(JBEditLemb);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setIcon(new ImageIcon(JDTelaBuscarLemb.class.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadLemb) {
			JDTelaCadLemb jdtcl = new JDTelaCadLemb();
			jdtcl.setVisible(true);
			jdtcl.setLocationRelativeTo(null);
		}// final do botão cadastrar lembretes

		if (acao.getSource() == JBEditLemb) {

			int linha = tabela.getSelectedRow();
			if (linha > -1) {

				JDTelaEditLemb jdtel = new JDTelaEditLemb(
						Integer.parseInt(model.getValueAt(linha, 0).toString()));
				jdtel.setVisible(true);
				jdtel.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do botão atualizar lembretes

		if (acao.getSource() == JBBuscar) {
			Pesquisar();
		}// final do botão buscar lembretes

		if(acao.getSource() == JBSair){
			this.dispose();
		}
	}// final da ação do botão

	private void Pesquisar() {
		String campo = JCBFiltro.getSelectedItem().toString() == "ASSUNTO" ? "assunto"
				: "iddestinatario";
		String value = JTFBuscar.getText();

		if (!value.equals("")) {

			CarregarGrid(_lembreteControl.BuscarTodos(campo, value));
		} else {
			CarregarGrid(_lembreteControl.BuscarTodos());
		}

	}

	private void CarregarGrid(List<Lembrete> lista) {

		model.setRowCount(0);
		for (Lembrete l : lista) {

			model.addRow(new Object[] { l.getId(),
					Extras.FormatDate(l.getDatahora()), l.getAssunto(),
					l.getDestinatario().getUsuario() });
		}

	}

}
