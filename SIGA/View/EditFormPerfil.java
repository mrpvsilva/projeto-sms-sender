package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

import Control.ModuloControl;
import Control.PerfilControl;
import Dominio.Modulo;
import Dominio.Perfil;
import Dominio.Permissao;
import TableModels.DefaultTableModel;
import TableModels.PermissaoTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Toolkit;

public class EditFormPerfil extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfnomeperfil;
	// private int linha;
	private DefaultTableModel<Perfil> modelPerfil;
	private DefaultTableModel<Permissao> modelPermissao;
	private Perfil perfil;
	private PerfilControl perfilControl;
	private JButton btncancelar;
	private JButton btncadastar;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public EditFormPerfil(Perfil perfil,
			DefaultTableModel<Perfil> modelPerfil) {
		setTitle("Salvar perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditFormPerfil.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		this.perfil = perfil;
		this.modelPerfil = modelPerfil;
		perfilControl = new PerfilControl();
		modelPermissao = new PermissaoTableModel();

		setBounds(100, 100, 601, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome do perfil");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(0, 24, 114, 14);
		contentPanel.add(lblNome);

		tfnomeperfil = new JTextField();
		tfnomeperfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfnomeperfil.setBounds(124, 21, 451, 20);
		contentPanel.add(tfnomeperfil);
		tfnomeperfil.setColumns(10);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 71, 565, 291);
		contentPanel.add(tabbedPane);

		JPanel tabpermissoes = new JPanel();
		tabbedPane.addTab("Permiss\u00F5es", null, tabpermissoes, null);
		tabpermissoes.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 540, 241);
		tabpermissoes.add(scrollPane);

		table = new JTable(modelPermissao);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btncadastar = new JButton("Salvar");
				btncadastar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btncadastar.addActionListener(this);
				btncadastar.setActionCommand("OK");
				buttonPane.add(btncadastar);
				// getRootPane().setDefaultButton(btncadastar);
			}
			{
				btncancelar = new JButton("Cancelar");
				btncancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btncancelar.addActionListener(this);
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		carregarCampos();
	}

	private void cadastrar() {

		perfil.setNome(tfnomeperfil.getText());
		perfil.setPermissoes(modelPermissao.getLinhas());

		if (perfilControl.cadastrar(perfil)) {
			carregarModel();

			JOptionPane.showMessageDialog(null, "Perfil cadastro com sucesso.");
		} else {
			JOptionPane.showMessageDialog(null, "Falha no cadastro do perfil.");
		}
	}

	private void alterar() {

		perfil.setNome(tfnomeperfil.getText());
		perfil.setPermissoes(modelPermissao.getLinhas());

		if (perfilControl.atualizar(perfil)) {
			carregarModel();
			JOptionPane.showMessageDialog(null, "Perfil alterado com sucesso.");
		} else {
			JOptionPane
					.showMessageDialog(null, "Falha no alteração do perfil.");
		}
	}

	private void carregarModel() {
		if (modelPerfil != null)
			modelPerfil.setLinhas(perfilControl.listarTodos());

	}

	private void carregarCampos() {
		// if (linha < 0)
		if (perfil == null) {
			perfil = new Perfil();
			List<Permissao> permissoes = new ArrayList<Permissao>();
			List<Modulo> modulos = new ModuloControl().listarTodos();

			for (Modulo modulo : modulos) {
				permissoes.add(new Permissao(modulo, perfil));
			}
			modelPermissao.setLinhas(permissoes);
			table.setModel(modelPermissao);
			return;
		}

		// int id = model.getId(linha);
		// perfil = perfilControl.buscarPerfil(id);
		tfnomeperfil.setText(perfil.getNome());
		modelPermissao.setLinhas(perfil.getPermissoes());
		table.setModel(modelPermissao);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btncadastar) {
			if (perfil.getId() == 0) {
				cadastrar();
			} else {
				alterar();
			}
		} else if (e.getSource() == btncancelar) {
			System.exit(0);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormPerfil dialog = new EditFormPerfil(null, null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
