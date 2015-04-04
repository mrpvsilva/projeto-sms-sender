package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.TipoServicoControl;
import Dominio.TipoServico;
import TableModels.AbstractDefaultTableModel;

public class JDTelaCadTipoServico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JCheckBox chkAtivo;
	private JButton JBSalvar;
	private JButton JBSair;
	private int id;
	private TipoServico tipoServico;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private AbstractDefaultTableModel<TipoServico> model;
	private JButton JBNovo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public JDTelaCadTipoServico(AbstractDefaultTableModel<TipoServico> model,
			int id) {
		setTitle("SIGA - cadastro de tipo de servi\u00E7o");
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
				JBSalvar = new JButton("Salvar");
				JBSalvar.setIcon(new ImageIcon(JDTelaCadTipoServico.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				JBSalvar.addActionListener(this);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{

				JBNovo = new JButton("Novo");
				JBNovo.setIcon(new ImageIcon(JDTelaCadTipoServico.class
						.getResource("/Img/window_new16.png")));
				JBNovo.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovo);

			}
			JBSair = new JButton("Sair");
			JBSair.setIcon(new ImageIcon(JDTelaCadTipoServico.class
					.getResource("/Img/exit16.png")));
			JBSair.setMnemonic(KeyEvent.VK_Q);
			JBSair.addActionListener(this);
			buttonPane.add(JBSair);
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
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Tipo servi�o cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void atualizar() {

		tipoServico.setNome(tfNome.getText());
		tipoServico.setAtivo(chkAtivo.isSelected());

		String out = tipoServicoControl.atualizar(tipoServico);

		if (out == null) {
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Tipo servi�o atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSalvar) {
			if (tfNome.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo nome obrigat�rio.");
				return;
			}

			if (id == 0) {
				cadastrar();
			} else {
				atualizar();
			}
		}

		if (e.getSource() == JBSair) {
			tipoServicoControl.dispose();
			this.dispose();
		}
	}

	private void carregarGrid() {
		if (model != null)
			model.setLinhas(tipoServicoControl.listarTodos());
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
