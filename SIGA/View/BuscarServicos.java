package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.TipoServicoControl;
import Dominio.Evento;
import Dominio.EventoServico;
import Dominio.Permissao;
import Dominio.Servico;
import Dominio.TipoCobranca;
import TableModels.DefaultTableModel;
import TableModels.ServicoTableModel;
import Util.Modulos;
import Util.PermissoesManager;

public class BuscarServicos extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Servico> model;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private JTextField tfNome;
	private JComboBox cbAtivo;
	private JButton JBPesquisar;
	private JButton JBCadastrar;
	private JButton JBEditar;
	private JButton JBSair;
	private Permissao permissao;
	private JLabel lblAtivo;
	private JButton btnAdicionar;

	private DefaultTableModel<EventoServico> servicos;
	private Evento evento;
	private JLabel lblTipoDeCobrana;
	private JComboBox tipocobranca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			BuscarServicos dialog = new BuscarServicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public BuscarServicos(Evento evento,
			DefaultTableModel<EventoServico> servicos) {
		this.servicos = servicos;
		this.evento = evento;
		start();
	}

	public BuscarServicos() {
		start();
	}

	public void start() {
		permissao = PermissoesManager.buscarPermissao(Modulos.Servicos);
		setResizable(false);
		setModal(true);
		setTitle("SIGA - busca de servi\u00E7o");
		setBounds(100, 100, 626, 623);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 600, 434);
		contentPanel.add(scrollPane);

		model = new ServicoTableModel();
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));

		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 11, 568, 93);
		contentPanel.add(panel);
		panel.setLayout(null);
		
				JLabel lblNome = new JLabel("Nome");
				lblNome.setBounds(10, 3, 105, 14);
				panel.add(lblNome);
				lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
				
				lblTipoDeCobrana = new JLabel("Tipo de cobran\u00E7a");
				lblTipoDeCobrana.setBounds(10, 31, 105, 14);
				panel.add(lblTipoDeCobrana);
				lblTipoDeCobrana.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTipoDeCobrana.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				tipocobranca = new JComboBox(TipoCobranca.values());
				tipocobranca.setBounds(128, 28, 192, 20);
				panel.add(tipocobranca);
				tipocobranca.removeItem(TipoCobranca.SELECIONE);
				tipocobranca.setSelectedIndex(0);
				tipocobranca.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
						tfNome = new JTextField();
						tfNome.setBounds(128, 0, 396, 20);
						panel.add(tfNome);
						tfNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
						tfNome.setColumns(10);
						
								lblAtivo = new JLabel("Ativo");
								lblAtivo.setBounds(322, 31, 46, 14);
								panel.add(lblAtivo);
								lblAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
								lblAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
								
										cbAtivo = new JComboBox(new String[] { "Todos", "Ativo", "Inativo" });
										cbAtivo.setBounds(383, 28, 141, 20);
										panel.add(cbAtivo);
										cbAtivo.setSelectedIndex(0);
										cbAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
										
												JBPesquisar = new JButton("Pesquisar");
												JBPesquisar.setBounds(229, 59, 120, 23);
												panel.add(JBPesquisar);
												JBPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
												JBPesquisar.setMnemonic(KeyEvent.VK_F);
												JBPesquisar.setIcon(new ImageIcon(BuscarServicos.class
														.getResource("/Img/Procurar.png")));
												JBPesquisar.addActionListener(this);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadastrar = new JButton("Cadastrar");
				JBCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBCadastrar.setMnemonic(KeyEvent.VK_C);
				JBCadastrar.setIcon(new ImageIcon(BuscarServicos.class
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
				JBEditar.setIcon(new ImageIcon(BuscarServicos.class
						.getResource("/Img/edit_add16.png")));
				JBEditar.addActionListener(this);
				JBEditar.setMnemonic(KeyEvent.VK_A);
				buttonPane.add(JBEditar);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(BuscarServicos.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);

			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int l = table.getSelectedRow();

					if (l > -1) {
						servicos.add(new EventoServico(evento, model.find(l)));
						pesquisar();
					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um serviço", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnAdicionar.setVisible(false);
			buttonPane.add(btnAdicionar);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
		isAddServico();
	}

	private void isAddServico() {

		if (servicos != null) {
			cbAtivo.setSelectedItem("Ativo");
			cbAtivo.setVisible(false);
			lblAtivo.setVisible(false);
			btnAdicionar.setVisible(true);
			JBCadastrar.setVisible(false);
			JBEditar.setVisible(false);
			table.getColumnModel().getColumn(2).setMinWidth(0);
			table.getColumnModel().getColumn(2).setMaxWidth(0);

		}

		pesquisar();
	}

	private void filtro(List<Servico> l) {

		if (servicos != null) {
			model.clear();
			for (Servico servico : l) {
				int c = 0;
				for (EventoServico e : servicos.getLinhas()) {
					if (servico.getId() == e.getServico().getId())
						c++;
				}

				if (c == 0)
					model.add(servico);

			}
		} else {
			model.setLinhas(l);
		}

	}

	private void pesquisar() {

		String nome = tfNome.getText();
		String ativo = cbAtivo.getSelectedItem().toString();
		TipoCobranca tipo = (TipoCobranca) tipocobranca.getSelectedItem();
		filtro(tipoServicoControl.listarTodos(nome, ativo,tipo));

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
				Servico s = model.get(linha);
				EditFormServico tscad = new EditFormServico(model, s);
				tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tscad.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecione um serviço");
			}
		}

		if (e.getSource() == JBCadastrar) {
			EditFormServico tscad = new EditFormServico(model);
			tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			tscad.setVisible(true);
		}

	}
}
