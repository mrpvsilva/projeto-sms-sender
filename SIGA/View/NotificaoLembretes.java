package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificaoLembretes extends JWindow {

	private String texto;

	public NotificaoLembretes(String texto) {
		this.texto = texto;
		getContentPane()
				.setBackground(UIManager.getColor("CheckBox.highlight"));
		start();
	}

	private void start() {
		JPanel content = (JPanel) getContentPane();
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NotificaoLembretes.this.dispose();
			}
		});
		btnNewButton.setToolTipText("Fechar notifica\u00E7\u00E3o");
		btnNewButton.setIcon(new ImageIcon(NotificaoLembretes.class
				.getResource("/Img/close16.png")));
		btnNewButton.setBounds(278, 3, 20, 20);
		getContentPane().add(btnNewButton);

		JLabel lblVocPossui = new JLabel(texto);
		lblVocPossui.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblVocPossui.setBounds(10, 37, 280, 20);
		getContentPane().add(lblVocPossui);
		Calendar data = Calendar.getInstance();
		int width = 300;
		int height = 100;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		setVisible(true);
	}
}
