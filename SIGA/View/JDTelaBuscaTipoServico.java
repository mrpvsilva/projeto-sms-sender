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
import TableModels.TipoServicoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class JDTelaBuscaTipoServico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TipoServicoTableModel model;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private JTextField tfNome;
	private JComboBox cbAtivo;

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
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		scrollPane.setViewportView(table);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(32, 11, 46, 14);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(93, 8, 285, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblAtivo = new JLabel("Ativo");
		lblAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtivo.setBounds(32, 34, 46, 14);
		contentPanel.add(lblAtivo);

		cbAtivo = new JComboBox(new String[] { "Todos", "Ativo", "Inativo" });
		cbAtivo.setBounds(93, 31, 141, 20);
		contentPanel.add(cbAtivo);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(178, 65, 110, 23);
		contentPanel.add(btnPesquisar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNovo = new JButton("Cadastrar");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(
								model, 0);

						tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						tscad.setVisible(true);
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				getRootPane().setDefaultButton(btnNovo);
			}
			{
				JButton btnAlterar = new JButton("Alterar");
				btnAlterar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int linha = table.getSelectedRow();
						if (linha > -1) {
							int id = model.getId(linha);
							JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(
									model, id);

							tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							tscad.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null,
									"Selecione uma linha");
						}
					}
				});
				btnAlterar.setActionCommand("Cancel");
				buttonPane.add(btnAlterar);
			}
		}
	}

	private void pesquisar() {

		String nome = tfNome.getText();
		String ativo = cbAtivo.getSelectedItem().toString();

		model.setTipoServico(tipoServicoControl.listarTodos(nome, ativo));

	}
}
