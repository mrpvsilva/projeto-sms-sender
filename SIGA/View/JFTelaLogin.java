package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import persistenceManagerFactory.PersistenceManagerFactory;
import Control.UsuarioControl;
import Model.UsuarioBean;

public class JFTelaLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTFLogin;
	private JPasswordField JPFSenha;
	private JButton JBAcessar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFTelaLogin frame = new JFTelaLogin();
					frame.setVisible(true);
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
		//INICIA UMA SESSIONFACTORY DO HIBERNATE PARA SER UTILIZADO EM TODA A APLICA��O.
		PersistenceManagerFactory.getPersistanceManager();
		//
		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel JLLogin = new JLabel("Login");
		JLLogin.setBounds(62, 131, 46, 14);
		contentPane.add(JLLogin);
		
		JLabel JLSenha = new JLabel("Senha");
		JLSenha.setBounds(62, 167, 46, 14);
		contentPane.add(JLSenha);
		
		JTFLogin = new JTextField();
		JTFLogin.setBounds(118, 128, 224, 20);
		contentPane.add(JTFLogin);
		JTFLogin.setColumns(10);
		
		JPFSenha = new JPasswordField();
		JPFSenha.setEchoChar('*');
		JPFSenha.setBounds(118, 164, 224, 20);
		contentPane.add(JPFSenha);
		
		JBAcessar = new JButton("Acessar");
		JBAcessar.addActionListener(this);
		JBAcessar.setBounds(154, 212, 101, 23);
		contentPane.add(JBAcessar);
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBAcessar){
			
			
			/* Valida��o do usu�rio e senha */
			if(JTFLogin.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Usu�rio em branco","Autentica��o",JOptionPane.ERROR_MESSAGE);
			}else if(new String(JPFSenha.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(null, "Senha em branco","Autentica��o",JOptionPane.ERROR_MESSAGE);
			}else{
				UsuarioControl usuCon = new UsuarioControl();
				UsuarioBean usuBean = new UsuarioBean();
				usuBean.setLogin(JTFLogin.getText());
				usuBean.setSenha(new String(JPFSenha.getPassword()));
				
				usuBean.setResposta(usuCon.Logar(usuBean).getResposta());
				
				if(usuBean.getResposta()){
					JFTelaPrincipal jftp = new JFTelaPrincipal(usuBean);
					jftp.setVisible(true);
					this.dispose();
				}
				
			}
			/* Final da valida��o do usu�rio */
			
			
		}// final do bot�o Acessar
		
	}// final do m�todo de a��o dos bot�es
}
