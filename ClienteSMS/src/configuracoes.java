import java.awt.EventQueue;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class configuracoes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private Properties prop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					configuracoes frame = new configuracoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public configuracoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUrl = new JLabel("Url:");
		lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUrl.setBounds(29, 14, 46, 14);
		contentPane.add(lblUrl);

		textField = new JTextField();
		textField.setBounds(85, 11, 239, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Us\u00FAario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 42, 65, 14);
		contentPane.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(85, 39, 239, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 67, 55, 14);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(85, 64, 239, 20);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SalvarConfiguracoes();

			}
		});
		btnNewButton.setBounds(118, 95, 164, 23);
		contentPane.add(btnNewButton);
		CarregarConfiguracoes();

	}

	private void CarregarConfiguracoes() {

		prop = ConfigManager.GetProp();

		String url = prop.getProperty("urlrest");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("pass");

		textField.setText(url);
		textField_1.setText(user);
		passwordField.setText(pass);

	}

	private void SalvarConfiguracoes() {
		String url = textField.getText();
		String user = textField_1.getText();
		String pass = new String(passwordField.getPassword());

		prop.setProperty("urlrest", url);
		prop.setProperty("user", user);
		prop.setProperty("pass", pass);

		ConfigManager.SalvarProp(prop);
		CarregarConfiguracoes();
		JOptionPane.showMessageDialog(null, "Configurações salvas com sucesso");

	}
}
