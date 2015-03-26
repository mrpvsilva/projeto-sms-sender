package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;

public class JDTelaCadUsu extends JDialog implements ActionListener {

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
	private int id;
	private Usuario usuario;
	private JButton btnResetarSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadUsu dialog = new JDTelaCadUsu(1);
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
	public JDTelaCadUsu(int id) throws ParseException {
		this.id = id;
		setBounds(100, 100, 368, 192);
		setTitle("SIGA - cadastro de usu\u00E1rio");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLUsuario = new JLabel("Usu\u00E1rio");
			JLUsuario.setBounds(10, 11, 46, 14);
			contentPanel.add(JLUsuario);
		}

		JTFUsuario = new JTextField();
		JTFUsuario.setBounds(60, 8, 289, 20);
		contentPanel.add(JTFUsuario);
		JTFUsuario.setColumns(10);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 39, 46, 14);
		contentPanel.add(JLNome);

		JTFNome = new JTextField();
		JTFNome.setBounds(60, 36, 289, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setBounds(10, 64, 46, 14);
		contentPanel.add(JLCpf);
		maskCpf = new MaskFormatter(Mascaras.maskCpf);
		JFFCpf = new JFormattedTextField(maskCpf);
		JFFCpf.setBounds(60, 61, 176, 20);
		contentPanel.add(JFFCpf);
		JFFCpf.setColumns(10);

		JLPerfil = new JLabel("Perfil");
		JLPerfil.setBounds(10, 89, 46, 14);
		contentPanel.add(JLPerfil);

		JCBPerfil = new JComboBox(_usuarioControl.DDLPerfis());
		JCBPerfil.setBounds(60, 86, 176, 20);
		contentPanel.add(JCBPerfil);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvUsu = new JButton("Salvar");
				JBSalvUsu.addActionListener(this);
				buttonPane.add(JBSalvUsu);
				getRootPane().setDefaultButton(JBSalvUsu);
			}
			{
				JBNovUsu = new JButton("Novo");
				buttonPane.add(JBNovUsu);
			}

			btnResetarSenha = new JButton("Resetar senha");
			buttonPane.add(btnResetarSenha);
		}

		preencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvUsu) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o usu�rio?")) {

				if (JTFUsuario.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Usu�rio em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFNome.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Nome em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					if (id == 0) {
						cadastrar();
					} else {
						atualizar();
					}

				}// final da valida��o

			}// final da confirma��o

		}// final do bot�o salvar usu�rio

		if (acao.getSource() == btnResetarSenha) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja resetar a senha do usu�rio?")) {

				usuario.setSenha(usuario.getCpf());
				String out = _usuarioControl.Atualizar(usuario);

				if (out == null) {
					preencherCampos();
					JOptionPane.showMessageDialog(null,
							"Senha resetada com sucesso.");
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

	}

	private void cadastrar() {
		usuario = new Usuario();
		usuario.setUsuario(JTFUsuario.getText());
		usuario.setSenha(Extras.FormatCnpjCpf(JFFCpf.getText()));
		usuario.setNomeCompleto(JTFNome.getText());
		usuario.setCpf(Extras.FormatCnpjCpf(JFFCpf.getText()));
		usuario.setPerfil(JCBPerfil.getSelectedItem().toString());

		String out = _usuarioControl.Cadastrar(usuario);

		if (out == null) {
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
		usuario.setPerfil(JCBPerfil.getSelectedItem().toString());

		String out = _usuarioControl.Atualizar(usuario);

		if (out == null) {
			preencherCampos();
			JOptionPane.showMessageDialog(null, "Usu�rio atualizado.");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void preencherCampos() {
		if (id == 0) {
			btnResetarSenha.setVisible(false);
			return;
		}

		JBNovUsu.setVisible(false);
		usuario = _usuarioControl.BuscarUsuario(id);

		JTFNome.setText(usuario.getNomeCompleto());
		JTFUsuario.setText(usuario.getUsuario());
		JFFCpf.setText(usuario.getCpf());
		JCBPerfil.setSelectedItem(usuario.getPerfil());

	}
}
