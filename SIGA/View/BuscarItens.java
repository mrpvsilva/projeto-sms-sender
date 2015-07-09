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
import Dominio.TipoCobranca;

import javax.swing.ImageIcon;

import TableModels.DefaultTableModel;
import TableModels.ItemTableModel;
import Util.Modulos;
import Util.PermissoesManager;
import Util.TipoAtivo;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.SwingConstants;

public class BuscarItens extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadServ;
	private JButton JBEditServ;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField item;
	private JComboBox<String> tipocobranca;
	private ServicosControl servCont = new ServicosControl();
	private DefaultTableModel<Item> model;
	private JTable tabela;
	private JScrollPane scroll;
	private ServicosControl _servicoControl = new ServicosControl();
	private JButton JBSair;
	private Permissao Itens;
	private JLabel lblNewLabel;
	private JComboBox<String> ativos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarItens dialog = new BuscarItens();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarItens() {
		Itens = PermissoesManager.buscarPermissao(Modulos.Itens);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BuscarItens.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setTitle("SIGA - buscar itens");
		setBounds(100, 100, 958, 735);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(12, 153, 930, 508);
			{

				model = new ItemTableModel();
				tabela = new JTable(model);
				tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scroll.setViewportView(tabela);

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadServ = new JButton("Cadastrar");
				JBCadServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadServ.setIcon(new ImageIcon(BuscarItens.class
						.getResource("/Img/save16.png")));
				JBCadServ.addActionListener(this);
				JBCadServ.setMnemonic(KeyEvent.VK_C);
				JBCadServ.setVisible(Itens.isCadastrar());
				buttonPane.add(JBCadServ);

			}
			{
				JBEditServ = new JButton("Editar");
				JBEditServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBEditServ.setMnemonic(KeyEvent.VK_E);
				JBEditServ.setIcon(new ImageIcon(BuscarItens.class
						.getResource("/Img/edit_add16.png")));
				JBEditServ.setVisible(Itens.isAlterar());
				JBEditServ.addActionListener(this);
				buttonPane.add(JBEditServ);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(BuscarItens.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}

		JPanel panel = new JPanel();
		panel.setBounds(158, 11, 570, 131);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			item = new JTextField();
			item.setBounds(97, 11, 451, 20);
			panel.add(item);
			item.setFont(new Font("Tahoma", Font.PLAIN, 13));
			item.setColumns(10);
		}
		{
			JLFiltro = new JLabel("Item");
			JLFiltro.setBounds(41, 14, 46, 14);
			panel.add(JLFiltro);
			JLFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
			JLFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		{
			lblNewLabel = new JLabel("Tipo cobran\u00E7a");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(0, 45, 87, 14);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		{
			tipocobranca = new JComboBox(TipoCobranca.values());
			tipocobranca.setBounds(97, 42, 219, 20);
			panel.add(tipocobranca);
			tipocobranca.removeItem(TipoCobranca.SELECIONE);
			tipocobranca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.setBounds(228, 93, 99, 23);
			panel.add(JBBuscar);
			JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBBuscar.setIcon(new ImageIcon(BuscarItens.class
					.getResource("/Img/Procurar.png")));
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.addActionListener(this);
		}

		getRootPane().setDefaultButton(JBBuscar);

		JLabel lblNewLabel_1 = new JLabel("Ativos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(337, 46, 46, 14);
		panel.add(lblNewLabel_1);

		ativos = new JComboBox(TipoAtivo.values());
		ativos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ativos.setBounds(393, 42, 155, 20);
		panel.add(ativos);
		
		pesquisar();
	}

	private void pesquisar() {
		TipoCobranca tipo = (TipoCobranca) tipocobranca.getSelectedItem();
		TipoAtivo ativo = (TipoAtivo) ativos.getSelectedItem();
		String i = item.getText();
		model.setLinhas(_servicoControl.listarTodos(i, tipo, ativo));
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadServ) {
			EditFormItem jdtcs = new EditFormItem(0, model);
			jdtcs.setVisible(true);
			jdtcs.setLocationRelativeTo(null);

		}

		if (acao.getSource() == JBEditServ) {

			int linha = tabela.getSelectedRow();
			if (linha > -1) {
				EditFormItem jdtcs = new EditFormItem(model.getId(linha), model);
				jdtcs.setVisible(true);
				jdtcs.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}

		if (acao.getSource() == JBBuscar) {
			pesquisar();
		}

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}// final da ação do botão
}
