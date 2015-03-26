package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import TableModels.TipoItemTableModel;
import Control.TipoItemControl;
import Dominio.TipoItem;

public class JDTelaEditFormTipoServico extends JDialog implements
		ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private int id;
	private JTextField tfnome;
	private JCheckBox chckbxAtivo;
	private TipoItem tipoItem;
	private TipoItemControl tipoItemControl = new TipoItemControl();
	private TipoItemTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditFormTipoServico dialog = new JDTelaEditFormTipoServico(1,
					null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditFormTipoServico(int id, TipoItemTableModel model) {
		this.id = id;
		this.model = model;
		setResizable(false);

		setBounds(100, 100, 281, 147);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 21, 46, 14);
		contentPanel.add(lblNome);

		tfnome = new JTextField();
		tfnome.setBounds(66, 18, 184, 20);
		contentPanel.add(tfnome);
		tfnome.setColumns(10);

		chckbxAtivo = new JCheckBox("Ativo");
		chckbxAtivo.setSelected(true);
		chckbxAtivo.setBounds(62, 42, 97, 23);
		contentPanel.add(chckbxAtivo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		preencherCampos();
	}

	private void preencherCampos() {
		if (id == 0)
			return;

		tipoItem = tipoItemControl.buscarTipoItem(id);
		tfnome.setText(tipoItem.getNome());
		chckbxAtivo.setSelected(tipoItem.isAtivo());

	}

	private void cadastrar() {
		tipoItem = new TipoItem();
		tipoItem.setNome(tfnome.getText());
		tipoItem.setAtivo(chckbxAtivo.isSelected());
		String out = tipoItemControl.cadastra(tipoItem);

		if (out == null) {
			JOptionPane.showMessageDialog(null,
					"Tipo serviço cadastrado com sucesso.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	private void atualizar() {
		tipoItem.setNome(tfnome.getText());
		tipoItem.setAtivo(chckbxAtivo.isSelected());
		String out = tipoItemControl.atualizar(tipoItem);

		if (out == null) {
			JOptionPane.showMessageDialog(null,
					"Tipo serviço atualizado com sucesso.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (id == 0) {
				cadastrar();
			} else {
				atualizar();
			}

			if (model != null)
				model.setTipoItens(tipoItemControl.ListarTodos());
		}
	}
}
