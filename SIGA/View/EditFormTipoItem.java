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
import javax.swing.JComboBox;
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

public class EditFormTipoItem extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvar;
	private JButton JBNovo;
	private TipoItem tipoItem ;
	private JTextField tfnome;
	private JCheckBox chckbxAtivo;
	private TipoItemControl tipoItemControl = new TipoItemControl();
	private DefaultTableModel<TipoItem> model;
	private JButton JBSair;
	private JComboBox tipoItens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormTipoItem dialog = new EditFormTipoItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditFormTipoItem() {
		tipoItem = new TipoItem();
		start();
	}
	
	/**
	 * Construtor para cadastro utilizado pela tela de cadastro de item
	 */
	public EditFormTipoItem(JComboBox tipoItens) {
		this.tipoItens = tipoItens;
		tipoItem = new TipoItem();
		start();
	}

	/**
	 * Construtor para cadastro, utilizado pela tela de busca de tipo item
	 * 	
	 */
	public EditFormTipoItem(DefaultTableModel<TipoItem> model) {
		this.model = model;
		tipoItem = new TipoItem();
		start();
	}

	/**
	 * Construtor para edição, utilizado pela tela de busca de tipo item
	 * 	
	 */
	public EditFormTipoItem(TipoItem tipoItem ,DefaultTableModel<TipoItem> model) {
		this.tipoItem = tipoItem;
		this.model = model;
		start();
	}

	public void start() {
		setResizable(false);
		setModal(true);

		setTitle("SIGA-Salvar tipo item");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditFormTipoItem.class.getResource("/Img/LOGO_LOGIN_GDA.png")));

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
				JBSalvar.setIcon(new ImageIcon(EditFormTipoItem.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.addActionListener(this);
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{
				JBNovo = new JButton("Novo");
				JBNovo.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovo.setIcon(new ImageIcon(EditFormTipoItem.class
						.getResource("/Img/window_new16.png")));
				JBNovo.addActionListener(this);
				JBNovo.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovo);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(EditFormTipoItem.class
					.getResource("/Img/exit16.png")));
			JBSair.setMnemonic(KeyEvent.VK_Q);
			JBSair.addActionListener(this);
			buttonPane.add(JBSair);
		}

		preencherCampos();
	}

	private void preencherCampos() {	
		tfnome.setText(tipoItem.getNome());
		chckbxAtivo.setSelected(tipoItem.isAtivo());

	}

	private void cadastrar() {	
		
		String out = tipoItemControl.cadastra(tipoItem);
		if (out == null) {
			if(tipoItens!=null)
				tipoItens.addItem(tipoItem.getNome());
			
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Tipo serviço cadastrado com sucesso.");

		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	private void atualizar() {
	
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
			
			tipoItem.setNome(tfnome.getText());
			tipoItem.setAtivo(chckbxAtivo.isSelected());
			
			if (tipoItem.getId()==0) {
				cadastrar();
			} else {
				atualizar();
			}
			
			this.dispose();
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
