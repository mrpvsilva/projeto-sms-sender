package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import org.hibernate.HibernateException;

import PersistenceManagerFactory.Factory;

public class Splash extends JWindow {
	public Splash() {
	}

	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		Calendar data = Calendar.getInstance();
		int width = 400;
		int height = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Constrói o splash screen
		ImageIcon logo = new ImageIcon(
				Splash.class.getResource("/Img/splash_logo.png"));
		JLabel label = new JLabel(logo);

		JLabel copyrt = new JLabel("Copyright " + data.get(Calendar.YEAR),
				JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		setVisible(true);
		try {
			Factory.createEntityManager();
		} catch (HibernateException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Falha na inicialização do Stock Manager\n"
									+ e.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(
					null,
					"Falha na inicialização do Stock Manager\n"
							+ ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			setVisible(false);
			dispose();
		}
	}

	public static void main(String[] args) {

		Splash splash = new Splash();
		splash.showSplash();
		JFTelaLogin frame = new JFTelaLogin();
		frame.setVisible(true);
	}

}
