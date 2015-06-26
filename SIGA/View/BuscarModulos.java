package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.ModuloControl;
import Dominio.Modulo;
import Dominio.Permissao;
import TableModels.DefaultTableModel;
import TableModels.ModuloTableModel;
import Util.PermissoesManager;

public class BuscarModulos extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Modulo> model;
	private ModuloControl moduloControl;
	private JButton btncadastrar;
	private JButton btnalterar;
	private JTextField tfpesquisa;
	private Permissao Modulos;

	public BuscarModulos() {
		Modulos = PermissoesManager.buscarPermissao(Util.Modulos.Modulos);
		setResizable(false);
		setModal(true);
		setTitle("Buscar m\u00F3dulos");
		moduloControl = new ModuloControl();
		setBounds(100, 100, 374, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 345, 370);
		contentPanel.add(scrollPane);

		model = new ModuloTableModel(moduloControl.listarTodos());
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));		
		scrollPane.setViewportView(table);

		JLabel lblNomeDoMdulo = new JLabel("Nome do m\u00F3dulo");
		lblNomeDoMdulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeDoMdulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDoMdulo.setBounds(0, 14, 115, 14);
		contentPanel.add(lblNomeDoMdulo);

		tfpesquisa = new JTextField();
		tfpesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfpesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent key) {
				if (key.getKeyCode() == 10)
					pesquisar();

				if (tfpesquisa.getText().length() == 0)
					pesquisar();
			}
		});
		tfpesquisa.setBounds(125, 11, 230, 20);
		contentPanel.add(tfpesquisa);
		tfpesquisa.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisar();
			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(135, 42, 104, 23);
		contentPanel.add(btnPesquisar);
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.LEFT);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btncadastrar = new JButton("Cadastrar");
				btncadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btncadastrar.setActionCommand("OK");
				btncadastrar.addActionListener(this);
				btncadastrar.setVisible(Modulos.isCadastrar());
				buttonPane.add(btncadastrar);
				// getRootPane().setDefaultButton(btncadastrar);
			}
			{
				btnalterar = new JButton("Alterar");
				btnalterar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnalterar.addActionListener(this);
				btnalterar.setActionCommand("Cancel");
				btnalterar.setVisible(Modulos.isAlterar());
				buttonPane.add(btnalterar);
			}
		}
	}

	private void pesquisar() {
		String nome = tfpesquisa.getText();

		if (nome.equals("")) {
			model.setLinhas(moduloControl.listarTodos());
		} else {
			model.setLinhas(moduloControl.listarTodos(nome));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btncadastrar) {
			EditFormModulo efm = new EditFormModulo(null, model);
			efm.setVisible(true);
		} else if (e.getSource() == btnalterar) {

			int linha = table.getSelectedRow();
			if (linha > -1) {
				EditFormModulo efm = new EditFormModulo(
						model.find(linha), model);
				efm.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha.");
			}
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarModulos dialog = new BuscarModulos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
