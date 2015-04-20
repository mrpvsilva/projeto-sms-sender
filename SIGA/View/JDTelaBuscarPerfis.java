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
import TableModels.AbstractDefaultTableModel;
import TableModels.PerfilTableModel;

import javax.swing.JTable;

public class JDTelaBuscarPerfis extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAlterar;
	private JButton btnCadastrar;
	private AbstractDefaultTableModel<Perfil> tableModel;
	private PerfilControl perfilControl;
	private JTable table;

	public JDTelaBuscarPerfis() {
		this.perfilControl = new PerfilControl();
		this.tableModel = new PerfilTableModel(perfilControl.listarTodos());
		setBounds(100, 100, 240, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 64, 180, 227);
		contentPanel.add(scrollPane);
		{
			table = new JTable(tableModel);
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			scrollPane.setViewportView(table);
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadastrar) {
			JDTelaEditFormPerfil efp = new JDTelaEditFormPerfil(null,
					tableModel);
			efp.setVisible(true);
		} else if (e.getSource() == btnAlterar) {
			int linha = table.getSelectedRow();
			if (linha > -1) {
				Perfil p = tableModel.getLinhas().get(table.getSelectedRow());
				JDTelaEditFormPerfil efp = new JDTelaEditFormPerfil(p,
						tableModel);
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
