package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import TableModels.TipoItemTableModel;
import Control.TipoItemControl;
import Dominio.TipoItem;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JDTelaBuscarTS extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TipoItemControl tipoItemControl = new TipoItemControl();
	private TipoItemTableModel model;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnPesquisar;
	private JLabel lblNome;
	private JLabel lblAtivo;
	private JTextField tffiltronome;
	private JComboBox jcfiltroativo;

	public JDTelaBuscarTS() {
		setResizable(false);
		setBounds(100, 100, 304, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 278, 261);
		contentPanel.add(scrollPane);

		model = new TipoItemTableModel(tipoItemControl.ListarTodos());
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);
		{
			lblNome = new JLabel("Nome");
			lblNome.setBounds(30, 14, 46, 14);
			contentPanel.add(lblNome);
		}
		{
			lblAtivo = new JLabel("Ativo");
			lblAtivo.setBounds(30, 42, 46, 14);
			contentPanel.add(lblAtivo);
		}
		{
			tffiltronome = new JTextField();
			tffiltronome.setBounds(77, 11, 169, 20);
			contentPanel.add(tffiltronome);
			tffiltronome.setColumns(10);
		}

		jcfiltroativo = new JComboBox();
		jcfiltroativo.setModel(new DefaultComboBoxModel(new String[] { "Todos",
				"Ativo", "Inativo" }));
		jcfiltroativo.setBounds(77, 39, 169, 20);
		contentPanel.add(jcfiltroativo);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(102, 70, 105, 23);
		btnPesquisar.addActionListener(this);
		contentPanel.add(btnPesquisar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Cadastrar");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Alterar");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			JDTelaEditFormTipoServico ef = new JDTelaEditFormTipoServico(0,
					model);
			ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ef.setVisible(true);
		}

		if (e.getSource() == cancelButton) {

			int linha = table.getSelectedRow();

			if (linha > -1) {
				int id = Integer.parseInt(String.valueOf(model.getValueAt(
						linha, 0)));
				JDTelaEditFormTipoServico ef = new JDTelaEditFormTipoServico(
						id, model);
				ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ef.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}
		if (e.getSource() == btnPesquisar) {
			String nome = tffiltronome.getText();
			String ativo = jcfiltroativo.getSelectedItem().toString();
			carregarGrid(tipoItemControl.ListarTodos(nome, ativo));
		}

	}

	private void carregarGrid(List<TipoItem> lista) {
		model.setTipoItens(lista);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarTS dialog = new JDTelaBuscarTS();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
