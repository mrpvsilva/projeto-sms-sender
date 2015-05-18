package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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

import Control.TipoItemControl;
import Dominio.TipoItem;
import TableModels.DefaultTableModel;
import java.awt.Font;

public class JDTelaEditFormTipoItem extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvar;
	private JButton JBNovo;
	private int id;
	private JTextField tfnome;
	private JCheckBox chckbxAtivo;
	private TipoItem tipoItem;
	private TipoItemControl tipoItemControl = new TipoItemControl();
	private DefaultTableModel<TipoItem> model;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditFormTipoItem dialog = new JDTelaEditFormTipoItem(0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditFormTipoItem(int id,
			DefaultTableModel<TipoItem> model) {
		setResizable(false);
		setModal(true);

		setTitle("SIGA - edi\u00E7\u00E3o tipo servi\u00E7o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaEditFormTipoItem.class.getResource("/Img/CNPJ G200.png")));
		this.id = id;
		this.model = model;

		setBounds(100, 100, 281, 147);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 21, 46, 14);
		contentPanel.add(lblNome);

		tfnome = new JTextField();
		tfnome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfnome.setBounds(66, 18, 184, 20);
		contentPanel.add(tfnome);
		tfnome.setColumns(10);

		chckbxAtivo = new JCheckBox("Ativo");
		chckbxAtivo.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxAtivo.setSelected(true);
		chckbxAtivo.setBounds(62, 42, 97, 23);
		contentPanel.add(chckbxAtivo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvar.setIcon(new ImageIcon(JDTelaEditFormTipoItem.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBNovo = new JButton("Novo");
				JBNovo.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovo.setIcon(new ImageIcon(JDTelaEditFormTipoItem.class
						.getResource("/Img/window_new16.png")));
				JBNovo.addActionListener(this);
				JBNovo.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovo);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(JDTelaEditFormTipoItem.class
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
			carregarGrid();
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
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Tipo serviço atualizado com sucesso.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	private void carregarGrid() {
		if (model != null)
			model.setLinhas(tipoItemControl.ListarTodos());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSalvar) {
			if (id == 0) {
				cadastrar();
			} else {
				atualizar();
			}
		}

		if (e.getSource() == JBNovo) {
			tfnome.setText("");
			chckbxAtivo.setSelected(true);
		}

		if (e.getSource() == JBSair) {
			this.dispose();
		}
	}
}
