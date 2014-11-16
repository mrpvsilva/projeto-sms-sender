import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Formulario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mnFerramentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
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
	public void LimparCampos() {
		textArea.setText(null);
		textField.setText(null);
		textField_1.setText(null);

	}

	public Formulario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Acer\\Desktop\\Mobile-Marketing_green.png"));
		setTitle("Cliente SMS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("DDD:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(33, 46, 47, 14);
		contentPane.add(lblNome);

		textField = new JTextField();
		textField.setBounds(90, 43, 200, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					enviarsms();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(90, 191, 200, 23);
		contentPane.add(btnNewButton);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(10, 74, 70, 14);
		contentPane.add(lblNmero);

		textField_1 = new JTextField();
		textField_1.setBounds(90, 71, 200, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTexto.setBounds(33, 99, 47, 14);
		contentPane.add(lblTexto);

		textArea = new JTextArea();
		textArea.setRows(5);
		textArea.setBounds(90, 102, 200, 78);

		contentPane.add(textArea);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 323, 21);
		contentPane.add(menuBar);

		mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);

		JMenuItem mntmNewMenuItem = new JMenuItem("Configura\u00E7\u00E3o");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				configuracoes cfg = new configuracoes();
				cfg.setVisible(true);

			}
		});
		mnFerramentas.add(mntmNewMenuItem);
	}

	public void enviarsms() throws IOException {
		try {

			Properties prop = ConfigManager.GetProp();
			String url = prop.getProperty("urlrest");
			String user = prop.getProperty("user");
			String pass = prop.getProperty("pass");
			String userpass = user + ":" + pass;
			String encoding = Base64.getEncoder().encodeToString(
					userpass.getBytes());

			String ddd = textField.getText();
			String numero = textField_1.getText();
			String txt = textArea.getText().replace(' ', '+');

			String dados = "ddd=" + ddd + "&numero=" + numero + "&txtsms="
					+ txt;

			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(dados);
			out.flush();
			out.close();

			String msg = "";
			try {
				connection.connect();
				/*
				 * BufferedReader br = new BufferedReader(new InputStreamReader(
				 * connection.getInputStream()));
				 */
				int responsecode = connection.getResponseCode();
				if (responsecode == 200) {
					LimparCampos();
					msg = "sms enviado com sucesso";

				} else {
					msg = connection.getResponseMessage();
				}
			} catch (Exception ex) {
				msg = ex.getMessage();
			}

			JOptionPane.showMessageDialog(null, msg);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
