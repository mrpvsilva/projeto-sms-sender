package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import TableModels.DefaultTableModel;
import Control.ModuloControl;
import Dominio.Modulo;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JDTelaEditFormModulo extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfnome;
	private ModuloControl moduloControl;
	private Modulo modulo;
	private DefaultTableModel<Modulo> model;
	private JButton btnsalvar;
	private JButton btncancelar;

	public JDTelaEditFormModulo(Modulo modulo,
			DefaultTableModel<Modulo> model) {
		setResizable(false);
		setModal(true);
		moduloControl = new ModuloControl();
		this.modulo = modulo;
		this.model = model;
		setBounds(100, 100, 424, 135);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lblNomeDoMdulo = new JLabel("Nome do m\u00F3dulo");
		lblNomeDoMdulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeDoMdulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDoMdulo.setBounds(0, 25, 120, 14);
		contentPanel.add(lblNomeDoMdulo);
		{
			tfnome = new JTextField();
			tfnome.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfnome.setBounds(130, 22, 278, 20);
			contentPanel.add(tfnome);
			tfnome.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnsalvar = new JButton("Salvar");
				btnsalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnsalvar.setActionCommand("OK");
				btnsalvar.addActionListener(this);
				buttonPane.add(btnsalvar);
				
			}
			{
				btncancelar = new JButton("Cancelar");
				btncancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btncancelar.addActionListener(this);
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}
		carregarCampo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnsalvar) {
			if (modulo == null) {
				cadastrar();
			} else {
				alterar();
			}
		} else if (e.getSource() == btncancelar) {
			this.dispose();
		}

	}

	private void cadastrar() {
		modulo = new Modulo();
		modulo.setNome(tfnome.getText());
		if (moduloControl.cadastrar(modulo)) {
			carregarModel();
			JOptionPane.showMessageDialog(null,
					"Módulo cadastrado com sucesso.");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Falha na cadastro do módulo.");
		}
	}

	private void alterar() {
		modulo.setNome(tfnome.getText());
		if (moduloControl.alterar(modulo)) {
			carregarModel();
			JOptionPane.showMessageDialog(null, "Módulo alterado com sucesso.");
			this.dispose();
		} else {
			JOptionPane
					.showMessageDialog(null, "Falha na alteração do módulo.");
		}
	}

	private void carregarModel() {
		if (model != null)
			model.setLinhas(moduloControl.listarTodos());
	}

	private void carregarCampo() {
		if (modulo == null)
			return;

		tfnome.setText(modulo.getNome());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditFormModulo dialog = new JDTelaEditFormModulo(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
}
