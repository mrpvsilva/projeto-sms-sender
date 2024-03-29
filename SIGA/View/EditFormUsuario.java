package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import Control.UsuarioControl;
import Dominio.Usuario;
import Extra.Extras;
import Extra.Mascaras;
import TableModels.DefaultTableModel;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;

public class EditFormUsuario extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvUsu;
	private JButton JBNovUsu;
	private JLabel JLUsuario;
	private JTextField JTFUsuario;
	private JTextField JTFNome;
	private JTextField JFFCpf;
	private MaskFormatter maskCpf;
	private JLabel JLPerfil;
	private JComboBox JCBPerfil;
	private UsuarioControl _usuarioControl = new UsuarioControl();
	private DefaultTableModel<Usuario> model;
	// private int id;
	private Usuario usuario;
	private JButton btnResetarSenha;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormUsuario dialog = new EditFormUsuario(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public EditFormUsuario(Usuario usuario, DefaultTableModel<Usuario> model)
			throws ParseException {
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditFormUsuario.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		// this.id = id;
		this.usuario = usuario;
		this.model = model;
		setBounds(100, 100, 458, 174);
		setTitle("salvar de usu\u00E1rio");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLUsuario = new JLabel("Usu\u00E1rio");
			JLUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			JLUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLUsuario.setBounds(0, 11, 71, 14);
			contentPanel.add(JLUsuario);
		}

		JTFUsuario = new JTextField();
		JTFUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFUsuario.setBounds(81, 5, 350, 20);
		contentPanel.add(JTFUsuario);
		JTFUsuario.setColumns(10);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setHorizontalAlignment(SwingConstants.RIGHT);
		JLNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLNome.setBounds(0, 36, 71, 14);
		contentPanel.add(JLNome);

		JTFNome = new JTextField();
		JTFNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFNome.setBounds(81, 33, 350, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLCpf.setBounds(0, 60, 71, 14);
		contentPanel.add(JLCpf);
		maskCpf = new MaskFormatter(Mascaras.maskCpf);
		JFFCpf = new JFormattedTextField(maskCpf);
		JFFCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JFFCpf.setBounds(81, 58, 176, 20);
		contentPanel.add(JFFCpf);
		JFFCpf.setColumns(10);

		JLPerfil = new JLabel("Perfil");
		JLPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLPerfil.setBounds(267, 60, 46, 14);
		contentPanel.add(JLPerfil);

		JCBPerfil = new JComboBox(_usuarioControl.DDLPerfis());
		JCBPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JCBPerfil.setBounds(315, 58, 116, 20);
		contentPanel.add(JCBPerfil);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvUsu = new JButton("Salvar");
				JBSalvUsu.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvUsu.setIcon(new ImageIcon(EditFormUsuario.class
						.getResource("/Img/Confirmar.png")));
				JBSalvUsu.addActionListener(this);
				JBSalvUsu.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvUsu);
				getRootPane().setDefaultButton(JBSalvUsu);
			}
			{
				JBNovUsu = new JButton("Novo");
				JBNovUsu.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovUsu.setIcon(new ImageIcon(EditFormUsuario.class
						.getResource("/Img/user_blue_add216.png")));
				JBNovUsu.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovUsu);
			}

			btnResetarSenha = new JButton("Resetar senha");
			btnResetarSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnResetarSenha.setIcon(new ImageIcon(EditFormUsuario.class
					.getResource("/Img/sync.png")));
			btnResetarSenha.addActionListener(this);
			btnResetarSenha.setMnemonic(KeyEvent.VK_R);
			buttonPane.add(btnResetarSenha);

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(EditFormUsuario.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}

		preencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvUsu) {

			if (JTFUsuario.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Usu�rio em branco.",
						"Erro ao salvar", JOptionPane.ERROR_MESSAGE);
			else if (JTFNome.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Nome em branco.",
						"Erro ao salvar", JOptionPane.ERROR_MESSAGE);
			else {

				if (usuario == null) {
					cadastrar();
				} else {
					atualizar();
				}

			}// final da valida��o

		}// final do bot�o salvar usu�rio

		if (acao.getSource() == btnResetarSenha) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja resetar a senha do usu�rio?")) {

				usuario.resetarSenha();
				String out = _usuarioControl.Atualizar(usuario);

				if (out == null) {
					preencherCampos();
					JOptionPane.showMessageDialog(null,
							"Senha resetada com sucesso.");
					model.setLinhas(_usuarioControl.BuscarTodos());
				} else {
					JOptionPane.showMessageDialog(null,
							"Falha ao resetar a senha");
				}

			}

		}

		if (acao.getSource() == JBNovUsu) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo usu�rio?")) {

				JTFUsuario.setText("");
				JTFNome.setText("");

			}
		}

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}

	private void cadastrar() {
		usuario = new Usuario();
		usuario.setUsuario(JTFUsuario.getText());
		usuario.setNomeCompleto(JTFNome.getText());
		usuario.setCpf(Extras.FormatCnpjCpf(JFFCpf.getText()));
		usuario.resetarSenha();
		usuario.setPerfil(_usuarioControl.getPerfil(JCBPerfil.getSelectedItem()
				.toString()));

		String out = _usuarioControl.Cadastrar(usuario);

		if (out == null) {
			model.setLinhas(_usuarioControl.BuscarTodos());
			preencherCampos();
			JOptionPane.showMessageDialog(null, "Usu�rio cadastardo.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void atualizar() {

		usuario.setUsuario(JTFUsuario.getText());
		usuario.setNomeCompleto(JTFNome.getText());
		usuario.setCpf(Extras.FormatCnpjCpf(JFFCpf.getText()));
		usuario.setPerfil(_usuarioControl.getPerfil(JCBPerfil.getSelectedItem()
				.toString()));

		String out = _usuarioControl.Atualizar(usuario);

		if (out == null) {
			model.setLinhas(_usuarioControl.BuscarTodos());
			preencherCampos();
			JOptionPane.showMessageDialog(null, "Usu�rio atualizado.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void preencherCampos() {
		if (usuario == null) {
			btnResetarSenha.setVisible(false);
			return;
		}

		JBNovUsu.setVisible(false);
		// usuario = _usuarioControl.BuscarUsuario(id);

		JTFNome.setText(usuario.getNomeCompleto());
		JTFUsuario.setText(usuario.getUsuario());
		JFFCpf.setText(usuario.getCpf());
		JCBPerfil.setSelectedItem(usuario.getPerfil().getNome());

	}
}
