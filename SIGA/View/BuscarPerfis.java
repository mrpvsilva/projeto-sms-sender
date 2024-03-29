package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Control.PerfilControl;
import Dominio.Perfil;
import Dominio.Permissao;
import TableModels.DefaultTableModel;
import TableModels.PerfilTableModel;
import TableModels.PermissaoTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import javax.swing.JTable;
import javax.swing.JTabbedPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class BuscarPerfis extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAlterar;
	private JButton btnCadastrar;
	private DefaultTableModel<Perfil> tableModelPerfil;
	private DefaultTableModel<Permissao> tableModelPermissao;
	private PerfilControl perfilControl;
	private JTable tablePerfis;
	private JTable tablePermissões;
	private JTextField tfnome;
	private Permissao Perfis;

	public BuscarPerfis() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarPerfis.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		Perfis = PermissoesManager.buscarPermissao(Modulos.Perfis);
		setTitle("Buscar perfis");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.perfilControl = new PerfilControl();
		this.tableModelPerfil = new PerfilTableModel(
				perfilControl.listarTodos());

		this.tableModelPermissao = new PermissaoTableModel();

		setBounds(100, 100, 1024, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 262, 542);
		contentPanel.add(scrollPane);
		{
			tablePerfis = new JTable(tableModelPerfil);
			tablePerfis.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tablePerfis.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					int linha = tablePerfis.getSelectedRow();
					List<Permissao> linhas = perfilControl.ListarTodos(tableModelPerfil.find(linha).getId());
					tableModelPermissao.setLinhas(linhas);
				}
			});
			
			scrollPane.setViewportView(tablePerfis);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(282, 79, 726, 549);
		contentPanel.add(tabbedPane);
		{
			JPanel panel = new JPanel();
			tabbedPane.addTab("Permiss\u00F5es", null, panel, null);
			panel.setLayout(null);
			{
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 11, 701, 450);
				panel.add(scrollPane_1);

				tablePermissões = new JTable(tableModelPermissao);
				tablePermissões.setFont(new Font("Tahoma", Font.PLAIN, 13));
				tablePermissões.getColumnModel().getColumn(0).setMinWidth(0);
				tablePermissões.getColumnModel().getColumn(0).setMaxWidth(0);
				scrollPane_1.setViewportView(tablePermissões);
			}

			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setHgap(15);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_1.setBounds(10, 472, 701, 38);
			panel.add(panel_1);

			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSalvar.setVisible(Perfis.isAlterar());
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int linha = tablePerfis.getSelectedRow();
					if (linha > -1) {
						Perfil perfil = tableModelPerfil.find(linha);
						perfil.setPermissoes(tableModelPermissao.getLinhas());
						if (perfilControl.atualizar(perfil))
							JOptionPane.showMessageDialog(null,
									"Permissões alteradas com sucesso.");
						else
							JOptionPane.showMessageDialog(null,
									"Falha na alteração das permissões.");
					}

				}
			});
			panel_1.add(btnSalvar);
		}

		JPanel pesquisapanel = new JPanel();
		pesquisapanel.setBorder(null);
		pesquisapanel.setBounds(10, 11, 616, 39);
		contentPanel.add(pesquisapanel);
		pesquisapanel.setLayout(null);

		JLabel lblNomeDoPerfil = new JLabel("Nome do perfil");
		lblNomeDoPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeDoPerfil.setBounds(10, 15, 118, 14);
		lblNomeDoPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		pesquisapanel.add(lblNomeDoPerfil);

		tfnome = new JTextField();
		tfnome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfnome.setBounds(142, 12, 278, 20);
		pesquisapanel.add(tfnome);
		tfnome.setColumns(10);

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(430, 11, 111, 23);
		pesquisapanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCadastrar = new JButton("Cadastrar");
				btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnCadastrar.addActionListener(this);
				btnCadastrar.setActionCommand("OK");
				btnCadastrar.setVisible(Perfis.isCadastrar());
				buttonPane.add(btnCadastrar);
				getRootPane().setDefaultButton(btnCadastrar);
			}
			{
				btnAlterar = new JButton("Alterar");
				btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnAlterar.setVisible(Perfis.isAlterar());
				btnAlterar.addActionListener(this);
				btnAlterar.setActionCommand("Cancel");
				buttonPane.add(btnAlterar);
			}
		}
	}

	private void pesquisarPerfil() {
		String nome = tfnome.getText();

		if (nome.equals("")) {
			
		} else {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadastrar) {
			EditFormPerfil efp = new EditFormPerfil(null,
					tableModelPerfil);
			efp.setVisible(true);
		} else if (e.getSource() == btnAlterar) {
			int linha = tablePerfis.getSelectedRow();
			if (linha > -1) {
				Perfil p = tableModelPerfil.getLinhas().get(
						tablePerfis.getSelectedRow());
				EditFormPerfil efp = new EditFormPerfil(p,
						tableModelPerfil);
				efp.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}

	}

	public static void main(String[] args) {
		try {
			BuscarPerfis dialog = new BuscarPerfis();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
