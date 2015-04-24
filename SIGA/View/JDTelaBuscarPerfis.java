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
import TableModels.AbstractDefaultTableModel;
import TableModels.PerfilTableModel;
import TableModels.PermissaoTableModel;

import javax.swing.JTable;
import javax.swing.JTabbedPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Dialog.ModalityType;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JDTelaBuscarPerfis extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAlterar;
	private JButton btnCadastrar;
	private AbstractDefaultTableModel<Perfil> tableModelPerfil;
	private AbstractDefaultTableModel<Permissao> tableModelPermissao;
	private PerfilControl perfilControl;
	private JTable tablePerfis;
	private JTable tablePermissões;
	private JTextField tfnome;

	public JDTelaBuscarPerfis() {
		setTitle("Buscar perfis");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.perfilControl = new PerfilControl();
		this.tableModelPerfil = new PerfilTableModel(
				perfilControl.listarTodos());

		this.tableModelPermissao = new PermissaoTableModel();

		setBounds(100, 100, 1024, 768);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 262, 604);
		contentPanel.add(scrollPane);
		{
			tablePerfis = new JTable(tableModelPerfil);
			tablePerfis.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					int linha = tablePerfis.getSelectedRow();
					List<Permissao> linhas = perfilControl
							.ListarTodos(tableModelPerfil.find(linha).getId());
					tableModelPermissao.setLinhas(linhas);
				}
			});
			tablePerfis.getColumnModel().getColumn(0).setMinWidth(0);
			tablePerfis.getColumnModel().getColumn(0).setMaxWidth(0);
			scrollPane.setViewportView(tablePerfis);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(282, 79, 726, 604);
		contentPanel.add(tabbedPane);
		{
			JPanel panel = new JPanel();
			tabbedPane.addTab("Permiss\u00F5es", null, panel, null);
			panel.setLayout(null);
			{
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 11, 701, 505);
				panel.add(scrollPane_1);

				tablePermissões = new JTable(tableModelPermissao);
				tablePermissões.getColumnModel().getColumn(0).setMinWidth(0);
				tablePermissões.getColumnModel().getColumn(0).setMaxWidth(0);
				scrollPane_1.setViewportView(tablePermissões);
			}

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 527, 701, 38);
			panel.add(panel_1);

			JButton btnSalvar = new JButton("Salvar");
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
		pesquisapanel.setBounds(10, 11, 530, 39);
		contentPanel.add(pesquisapanel);
		pesquisapanel.setLayout(null);

		JLabel lblNomeDoPerfil = new JLabel("Nome do perfil");
		lblNomeDoPerfil.setBounds(0, 9, 90, 14);
		lblNomeDoPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		pesquisapanel.add(lblNomeDoPerfil);

		tfnome = new JTextField();
		tfnome.setBounds(100, 6, 229, 20);
		pesquisapanel.add(tfnome);
		tfnome.setColumns(10);

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(339, 5, 111, 23);
		pesquisapanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCadastrar = new JButton("Cadastrar");
				btnCadastrar.addActionListener(this);
				btnCadastrar.setActionCommand("OK");
				buttonPane.add(btnCadastrar);
				getRootPane().setDefaultButton(btnCadastrar);
			}
			{
				btnAlterar = new JButton("Alterar");
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
			JDTelaEditFormPerfil efp = new JDTelaEditFormPerfil(null,
					tableModelPerfil);
			efp.setVisible(true);
		} else if (e.getSource() == btnAlterar) {
			int linha = tablePerfis.getSelectedRow();
			if (linha > -1) {
				Perfil p = tableModelPerfil.getLinhas().get(
						tablePerfis.getSelectedRow());
				JDTelaEditFormPerfil efp = new JDTelaEditFormPerfil(p,
						tableModelPerfil);
				efp.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}

	}

	public static void main(String[] args) {
		try {
			JDTelaBuscarPerfis dialog = new JDTelaBuscarPerfis();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
