package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class JDTelaEditFormTipoServico extends JDialog implements
		ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvar;
	private JButton JBNovo;
	private int id;
	private JTextField tfnome;
	private JCheckBox chckbxAtivo;
	private TipoItem tipoItem;
	private TipoItemControl tipoItemControl = new TipoItemControl();
	private TipoItemTableModel model;
	private JButton JBSair;

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
		setTitle("SIGA - edi\u00E7\u00E3o tipo servi\u00E7o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDTelaEditFormTipoServico.class.getResource("/Img/CNPJ G200.png")));
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
				JBSalvar = new JButton("Salvar");
				JBSalvar.setIcon(new ImageIcon(JDTelaEditFormTipoServico.class.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBNovo = new JButton("Novo");
				JBNovo.setIcon(new ImageIcon(JDTelaEditFormTipoServico.class.getResource("/Img/window_new16.png")));
				JBNovo.addActionListener(this);
				JBNovo.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovo);
			}
			
			JBSair = new JButton("Sair");
			JBSair.setIcon(new ImageIcon(JDTelaEditFormTipoServico.class.getResource("/Img/exit16.png")));
			JBSair.setMnemonic(KeyEvent.VK_Q);
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
		if (e.getSource() == JBSalvar) {
			if (id == 0) {
				cadastrar();
			} else {
				atualizar();
			}

			if (model != null)
				model.setTipoItens(tipoItemControl.ListarTodos());
		}
		
		if(e.getSource() == JBSair){
			this.dispose();
		}
	}
}
