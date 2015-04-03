package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import Control.UsuarioControl;
import Dominio.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDTelaTrocarSenha extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField jpswNova;
	private JPasswordField pswdRepetir;
	private UsuarioControl usuariocontrol = new UsuarioControl();
	private Usuario usuario;
	private JButton btnSalvar;
	private JButton btnCancelar;

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaTrocarSenha dialog = new JDTelaTrocarSenha(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaTrocarSenha(Usuario usuario) {
		this.usuario = usuario;
		setTitle("Alterar senha");
		setBounds(100, 100, 343, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNovaSenha = new JLabel("Nova senha");
		lblNovaSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaSenha.setBounds(10, 31, 83, 14);
		contentPanel.add(lblNovaSenha);

		jpswNova = new JPasswordField();
		jpswNova.setBounds(103, 28, 215, 20);
		contentPanel.add(jpswNova);

		JLabel lblRepetirSenha = new JLabel("Repetir senha");
		lblRepetirSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepetirSenha.setBounds(10, 62, 85, 14);
		contentPanel.add(lblRepetirSenha);

		pswdRepetir = new JPasswordField();
		pswdRepetir.setBounds(103, 59, 215, 20);
		contentPanel.add(pswdRepetir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalvar = new JButton("Salvar");
				btnSalvar.setActionCommand("OK");
				btnSalvar.addActionListener(this);
				buttonPane.add(btnSalvar);

				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				btnCancelar.addActionListener(this);
				buttonPane.add(btnCancelar);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnSalvar) {
			String ns = new String(jpswNova.getPassword());
			String rs = new String(pswdRepetir.getPassword());

			if (!ns.equals(rs)) {
				JOptionPane.showMessageDialog(null, "Senhas n�o conferem");
			} else {
				usuario.setSenha(ns);
				String o = usuariocontrol.Atualizar(usuario);

				if (o == null) {
					JOptionPane.showMessageDialog(null,
							"Senha alterada com sucesso.");

				} else {
					JOptionPane.showMessageDialog(null,
							"Falha na altera��o da senha.");
				}
				this.dispose();
			}
		}

		if (e.getSource() == btnCancelar) {
			this.dispose();
		}

	}
}
