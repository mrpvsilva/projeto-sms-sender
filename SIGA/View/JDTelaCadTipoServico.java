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
import javax.swing.JCheckBox;

import Control.TipoServicoControl;
import Dominio.TipoServico;

import javax.swing.SwingConstants;

import TableModels.TipoServicoTableModel;

public class JDTelaCadTipoServico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JCheckBox chkAtivo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private int id;
	private TipoServico tipoServico;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private TipoServicoTableModel model;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public JDTelaCadTipoServico(TipoServicoTableModel model, int id) {
		this.model = model;
		this.id = id;
		setBounds(100, 100, 340, 147);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(0, 37, 42, 14);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(52, 34, 179, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);

		chkAtivo = new JCheckBox("Ativo");
		chkAtivo.setSelected(true);
		chkAtivo.setBounds(237, 33, 97, 23);
		contentPanel.add(chkAtivo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalvar = new JButton("Salvar");
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				btnSalvar.addActionListener(this);
				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(this);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}

		preencherCampos();
	}

	private void preencherCampos() {
		if (id == 0)
			return;

		tipoServico = tipoServicoControl.buscarTipoServico(id);

		tfNome.setText(tipoServico.getNome());
		chkAtivo.setSelected(tipoServico.isAtivo());

	}

	private void cadastrar() {

		tipoServico = new TipoServico();
		tipoServico.setNome(tfNome.getText());
		tipoServico.setAtivo(chkAtivo.isSelected());

		String out = tipoServicoControl.cadastrar(tipoServico);

		if (out == null) {
			JOptionPane.showMessageDialog(null,
					"Tipo serviço cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void atualizar() {

		tipoServico.setNome(tfNome.getText());
		tipoServico.setAtivo(chkAtivo.isSelected());

		String out = tipoServicoControl.atualizar(tipoServico);

		if (out == null) {
			JOptionPane.showMessageDialog(null,
					"Tipo serviço atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			if (tfNome.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo nome obrigatório.");
				return;
			}

			if (id == 0) {
				cadastrar();
			} else {
				atualizar();
			}

			model.setTipoServico(tipoServicoControl.listarTodos());
		}

		if (e.getSource() == btnCancelar) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		try {
			JDTelaCadTipoServico dialog = new JDTelaCadTipoServico(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
