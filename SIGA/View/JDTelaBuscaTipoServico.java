package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.TipoServicoControl;
import Dominio.Permissao;
import Dominio.TipoServico;
import PersistenceManagerFactory.Factory;
import TableModels.AbstractDefaultTableModel;
import TableModels.TipoServicoTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Font;

public class JDTelaBuscaTipoServico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private AbstractDefaultTableModel<TipoServico> model;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private JTextField tfNome;
	private JComboBox cbAtivo;
	private JButton JBPesquisar;
	private JButton JBCadastrar;
	private JButton JBEditar;
	private JButton JBSair;
	private Permissao permissao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			JDTelaBuscaTipoServico dialog = new JDTelaBuscaTipoServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscaTipoServico() {
		permissao =PermissoesManager.buscarPermissao(Modulos.Tipo_servicos);
		setResizable(false);
		setModal(true);
		setTitle("SIGA - buscar tipo de servi\u00E7o");
		setBounds(100, 100, 450, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 414, 280);
		contentPanel.add(scrollPane);

		model = new TipoServicoTableModel(tipoServicoControl.listarTodos());
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		scrollPane.setViewportView(table);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(32, 11, 46, 14);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNome.setBounds(93, 8, 285, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblAtivo = new JLabel("Ativo");
		lblAtivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtivo.setBounds(32, 34, 46, 14);
		contentPanel.add(lblAtivo);

		cbAtivo = new JComboBox(new String[] { "Todos", "Ativo", "Inativo" });
		cbAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbAtivo.setBounds(93, 31, 141, 20);
		contentPanel.add(cbAtivo);

		JBPesquisar = new JButton("Pesquisar");
		JBPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBPesquisar.setMnemonic(KeyEvent.VK_F);
		JBPesquisar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class
				.getResource("/Img/Procurar.png")));
		JBPesquisar.addActionListener(this);
		JBPesquisar.setBounds(178, 65, 120, 23);
		contentPanel.add(JBPesquisar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadastrar = new JButton("Cadastrar");
				JBCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadastrar.setMnemonic(KeyEvent.VK_C);
				JBCadastrar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class
						.getResource("/Img/save16.png")));
				JBCadastrar.setVisible(permissao.isCadastrar());
				JBCadastrar.addActionListener(this);
				buttonPane.add(JBCadastrar);
				getRootPane().setDefaultButton(JBCadastrar);
			}
			{
				JBEditar = new JButton("Editar");
				JBEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBEditar.setVisible(permissao.isAlterar());
				JBEditar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class
						.getResource("/Img/edit_add16.png")));
				JBEditar.addActionListener(this);
				JBEditar.setMnemonic(KeyEvent.VK_A);
				buttonPane.add(JBEditar);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	private void pesquisar() {

		String nome = tfNome.getText();
		String ativo = cbAtivo.getSelectedItem().toString();

		model.setLinhas(tipoServicoControl.listarTodos(nome, ativo));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSair) {
			this.dispose();
		}

		if (e.getSource() == JBPesquisar) {
			pesquisar();
		}

		if (e.getSource() == JBEditar) {
			int linha = table.getSelectedRow();
			if (linha > -1) {
				int id = model.getId(linha);
				JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(model, id);
				tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tscad.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}

		if (e.getSource() == JBCadastrar) {
			JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(model, 0);
			tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			tscad.setVisible(true);
		}

	}
}
