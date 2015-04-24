package com.view.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.util.jpautil.JpaUtil;
import com.view.fornecedores.EditFornecedor;

public class SplashScreen extends JWindow {

	public SplashScreen() {
		// TODO Auto-generated constructor stub
	}

	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Configura a posição e o tamanho da janela
		int width = 400;
		int height = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Constrói o splash screen
		ImageIcon logo = new ImageIcon(
				EditFornecedor.class.getResource("/imagens/logo.png"));
		JLabel label = new JLabel(logo);

		JLabel copyrt = new JLabel("Copyright " + LocalDate.now().getYear(),
				JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		Color oraRed = new Color(156, 20, 20, 255);
		// content.setBorder(BorderFactory.createLineBorder(oraRed, 10));
		// Torna visível
		setVisible(true);
		try {
			JpaUtil.createEntityManagerFactory();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(
					null,
					"Falha na inicialização do Stock Manager\n"
							+ ex.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
		} finally {
			setVisible(false);
			dispose();
		}
	}

	public static void main(String[] args) {
		// Mostra uma imagem com o título da aplicação
		SplashScreen splash = new SplashScreen();
		splash.showSplash();
		Desktop frame = new Desktop();
		frame.setVisible(true);
	}

}
