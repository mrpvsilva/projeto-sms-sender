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
import javax.swing.JPasswordField;

import Control.UsuarioControl;
import Dominio.Usuario;
import Extra.Extras;
import Extra.Mascaras;
import Model.UsuarioBean;

import javax.swing.JComboBox;

public class JDTelaEditUsu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBAtuUsu;
	private JButton JBExcUsu;
	private JLabel JLUsuario;
	private JTextField JTFUsuario;
	private JTextField JTFNome;
	private JTextField JFFCpf;
	private JPasswordField JPFSenha;
	private JLabel JLPerfil;
	private JComboBox JCBPerfil;
	private int _id;
	private UsuarioControl _usuarioControl = new UsuarioControl();
	private Usuario _usuario;
	private JButton btnResetarSenha;
	private MaskFormatter maskCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditUsu dialog = new JDTelaEditUsu(2);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDTelaEditUsu(int id) throws ParseException {
		_id = id;
		setBounds(100, 100, 376, 227);
		setTitle("SIGA - edição de usu\u00E1rio");
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

		JLabel JLSenha = new JLabel("Senha");
		JLSenha.setBounds(10, 36, 46, 14);
		contentPanel.add(JLSenha);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 64, 46, 14);
		contentPanel.add(JLNome);

		JTFNome = new JTextField();
		JTFNome.setBounds(60, 61, 289, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		JLabel JLCpf = new JLabel("Cpf");
		JLCpf.setBounds(10, 89, 46, 14);
		contentPanel.add(JLCpf);

		maskCpf = new MaskFormatter(Mascaras.maskCpf);
		JFFCpf = new JFormattedTextField(maskCpf);
		JFFCpf.setBounds(60, 86, 176, 20);
		contentPanel.add(JFFCpf);
		JFFCpf.setColumns(10);

		JPFSenha = new JPasswordField();
		JPFSenha.setBounds(60, 33, 289, 20);
		contentPanel.add(JPFSenha);

		JLPerfil = new JLabel("Perfil");
		JLPerfil.setBounds(10, 117, 46, 14);
		contentPanel.add(JLPerfil);

		JCBPerfil = new JComboBox(_usuarioControl.DDLPerfis());
		JCBPerfil.setBounds(60, 114, 176, 20);
		contentPanel.add(JCBPerfil);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuUsu = new JButton("Atualizar");
				JBAtuUsu.addActionListener(this);
				buttonPane.add(JBAtuUsu);
				getRootPane().setDefaultButton(JBAtuUsu);
			}

			btnResetarSenha = new JButton("Resetar senha");
			btnResetarSenha.addActionListener(this);
			buttonPane.add(btnResetarSenha);
			{
				JBExcUsu = new JButton("Excluir");
				buttonPane.add(JBExcUsu);
			}
		}

		PreencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBAtuUsu) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja atualizar o usuário?")) {

				if (JTFUsuario.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Usuário em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFNome.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Nome em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					UsuarioBean usuBean = new UsuarioBean();

					usuBean.setLogin(JTFUsuario.getText());
					usuBean.setSenha(new String(JPFSenha.getPassword()));
					usuBean.setNome(JTFNome.getText());
					usuBean.setCpf(Extras.FormatCnpjCpf(JFFCpf.getText()));
					usuBean.setPerfil(JCBPerfil.getSelectedItem().toString());

					_usuario.setNomeCompleto(JTFUsuario.getText());
					_usuario.setCpf(JFFCpf.getText());
					_usuario.setPerfil(JCBPerfil.getSelectedItem().toString());

					String out = _usuarioControl.Atualizar(_usuario);

					if (out == null) {
						JOptionPane.showMessageDialog(null,
								"Usuário atualizado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, out);
					}

				}// final da validação

			}// final da confirmação

		}// final do botão salvar usuário

		if (acao.getSource() == btnResetarSenha) {
			_usuario.setSenha(_usuario.getCpf());
			String out = _usuarioControl.Atualizar(_usuario);

			if (out == null) {
				JOptionPane.showMessageDialog(null,
						"Senha resetada com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Falha no reset da senha");
			}

		}

		if (acao.getSource() == JBExcUsu) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja excluir o usuário?")) {

			}// final da confirmação

		}// final do botão novo usuário

	}

	private void PreencherCampos() {
		_usuario = _usuarioControl.BuscarUsuario(_id);
		JTFUsuario.setText(_usuario.getUsuario());
		JTFNome.setText(_usuario.getNomeCompleto());
		JFFCpf.setText(_usuario.getCpf());
		JCBPerfil.setSelectedItem(_usuario.getPerfil());
	}
}
