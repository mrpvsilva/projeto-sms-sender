package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.UsuarioControl;
import Dominio.Usuario;
import Util.Factory;

import java.awt.Font;

import javax.swing.SwingConstants;

public class JFTelaLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTFLogin;
	private JPasswordField JPFSenha;
	private JButton JBAcessar;

	/**
	 * ; Launch the application.
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// INICIA UMA SESSIONFACTORY DO HIBERNATE PARA SER UTILIZADO EM
				// TODA A
				// APLICAÇÃO.
				// PersistenceManagerFactory.getEntityManager();
				Factory.createEntityManager();
				//
			}
		}).start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFTelaLogin frame = new JFTelaLogin();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public JFTelaLogin() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JFTelaLogin.class.getResource("/Img/CNPJ G200.png")));

		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel JLLogin = new JLabel("Login");
		JLLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		JLLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLLogin.setBounds(64, 130, 46, 15);
		contentPane.add(JLLogin);

		JLabel JLSenha = new JLabel("Senha");
		JLSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		JLSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLSenha.setBounds(64, 166, 46, 14);
		contentPane.add(JLSenha);

		JTFLogin = new JTextField();
		JLLogin.setLabelFor(JTFLogin);
		JTFLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFLogin.setBounds(118, 128, 224, 20);
		contentPane.add(JTFLogin);
		JTFLogin.setColumns(10);

		JPFSenha = new JPasswordField();
		
		JPFSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JPFSenha.setEchoChar('*');
		JPFSenha.setBounds(118, 164, 224, 20);
		contentPane.add(JPFSenha);

		JBAcessar = new JButton("Acessar");		

		JBAcessar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBAcessar.addActionListener(this);
		JBAcessar.setBounds(154, 212, 101, 23);
		contentPane.add(JBAcessar);

		JLabel JLIcone = new JLabel("");
		JLIcone.setIcon(new ImageIcon(JFTelaLogin.class
				.getResource("/Img/CNPJ G200.png")));
		JLIcone.setBounds(137, 11, 224, 98);
		contentPane.add(JLIcone);
		getRootPane().setDefaultButton(JBAcessar);
	}

	private void entrar() {
		/* Validação do usuário e senha */
		if (JTFLogin.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Usuário em branco",
					"Autenticação", JOptionPane.ERROR_MESSAGE);
			JTFLogin.requestFocus();
		} else if (new String(JPFSenha.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha em branco",
					"Autenticação", JOptionPane.ERROR_MESSAGE);

		} else {
			UsuarioControl usuCon = new UsuarioControl();
			// UsuarioBean usuBean = new UsuarioBean();
			Usuario u = new Usuario();
			u.setUsuario(JTFLogin.getText());
			u.setSenha(new String(JPFSenha.getPassword()));

			// usuBean.setResposta(usuCon.Logar(usuBean).getResposta());

			u = usuCon.Logar(u);

			if (u != null) {
				if (!u.trocarSenha()) {
					JFTelaPrincipal jftp = new JFTelaPrincipal();
					jftp.setVisible(true);
					this.dispose();
				} else {

					JDTelaTrocarSenha trocar = new JDTelaTrocarSenha(u);
					trocar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					trocar.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Usuário ou senha incorretos.");
			}

		}
		/* Final da validação do usuário */
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBAcessar) {
			entrar();
		}// final do botão Acessar

	}// final do método de ação dos botões
}
