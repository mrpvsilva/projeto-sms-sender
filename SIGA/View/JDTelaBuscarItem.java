package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

import Control.ServicosControl;
import Dominio.Item;
import Dominio.Permissao;

import javax.swing.ImageIcon;

import TableModels.DefaultTableModel;
import TableModels.ItemTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JDTelaBuscarItem extends JDialog implements ActionListener {

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
	private DefaultTableModel<Item> model;
	private JTable tabela;
	private JScrollPane scroll;
	private ServicosControl _servicoControl = new ServicosControl();
	private JButton JBSair;
	private Permissao Itens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarItem dialog = new JDTelaBuscarItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarItem() {
		Itens = PermissoesManager.buscarPermissao(Modulos.Itens);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaBuscarItem.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar itens");
		setBounds(100, 100, 470, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
			JLFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
			JLFiltro.setBounds(0, 11, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBBuscar.setIcon(new ImageIcon(JDTelaBuscarItem.class
					.getResource("/Img/Procurar.png")));
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.addActionListener(this);
			JBBuscar.setBounds(340, 11, 99, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(164, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			JCBFiltro = new JComboBox<String>();
			JCBFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
			for (String item : servCont.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(56, 11, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 59, 426, 158);
			{

				model = new ItemTableModel(_servicoControl.listarTodos());
				tabela = new JTable(model);
				tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scroll.setViewportView(tabela);
				;

				tabela.getColumnModel().getColumn(0).setMinWidth(0);
				tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadServ = new JButton("Cadastrar");
				JBCadServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadServ.setIcon(new ImageIcon(JDTelaBuscarItem.class
						.getResource("/Img/save16.png")));
				JBCadServ.addActionListener(this);
				JBCadServ.setMnemonic(KeyEvent.VK_C);
				JBCadServ.setVisible(Itens.isCadastrar());
				buttonPane.add(JBCadServ);
				getRootPane().setDefaultButton(JBCadServ);
			}
			{
				JBEditServ = new JButton("Editar");
				JBEditServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBEditServ.setMnemonic(KeyEvent.VK_E);
				JBEditServ.setIcon(new ImageIcon(JDTelaBuscarItem.class
						.getResource("/Img/edit_add16.png")));
				JBEditServ.setVisible(Itens.isAlterar());
				JBEditServ.addActionListener(this);
				buttonPane.add(JBEditServ);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(JDTelaBuscarItem.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadServ) {
			JDTelaCadItem jdtcs = new JDTelaCadItem(0,model);
			jdtcs.setVisible(true);
			jdtcs.setLocationRelativeTo(null);

		}// final do botão cadastrar serviços

		if (acao.getSource() == JBEditServ) {

			int linha = tabela.getSelectedRow();
			if (linha > -1) {
				JDTelaCadItem jdtcs = new JDTelaCadItem(model.getId(linha),model);
				jdtcs.setVisible(true);
				jdtcs.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do botão atualizar serviços

		if (acao.getSource() == JBBuscar) {

			String campo = JCBFiltro.getSelectedItem().toString() == "ITEM" ? "nome"
					: "descricao";
			String val = JTFBuscar.getText();
			//
			// if (txt.equals("")) {
			// model.setLinhas(_servicoControl.listarTodos());
			// } else {
			// model.setLinhas(_servicoControl.listarTodos(campo, txt));
			// }

			model.setLinhas(_servicoControl.listarTodos(campo, val));

		}// final do botão buscar serviços

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}// final da ação do botão

}
