package com.view.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import com.jpautil.JpaUtil;

import java.awt.Color;

public class desktop extends JFrame {

	private JPanel contentPane;
	private JPanel body;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					desktop frame = new desktop();
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
	public desktop() {
		JpaUtil.createEntityManagerFactory();
		setLocale(new Locale("pt", "BR"));
		// setMinimumSize(new Dimension(1024, 768));
		setTitle("Stock Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(contentPane);

		JPanel menubar = new JPanel();
		menubar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		menubar.setBounds(new Rectangle(0, 0, 0, 154));
		contentPane.add(menubar, BorderLayout.NORTH);
		menubar.setLayout(new BoxLayout(menubar, BoxLayout.X_AXIS));

		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.revalidate();
				body.repaint();
			}
		});
		home.setToolTipText("\u00C1rea de trabalho");
		home.setMargin(new Insets(0, 0, 0, 0));
		home.setIcon(new ImageIcon(desktop.class
				.getResource("/imagens/home.png")));
		menubar.add(home);

		JButton produtos = new JButton("");
		produtos.setToolTipText("Produtos");
		produtos.setMargin(new Insets(0, 0, 0, 0));
		produtos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				body.removeAll();
				com.view.produtos.Produtos container_produtos = new com.view.produtos.Produtos();
				container_produtos.setVisible(true);
				body.add(container_produtos, BorderLayout.CENTER);
				body.revalidate();
				body.repaint();

			}
		});
		produtos.setIcon(new ImageIcon(desktop.class
				.getResource("/imagens/produtos.png")));
		menubar.add(produtos);

		JButton fornecedores = new JButton("");
		fornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				body.removeAll();
				body.revalidate();
				body.repaint();
			}
		});
		fornecedores.setToolTipText("Fornecedores");
		fornecedores.setMargin(new Insets(0, 0, 0, 0));
		fornecedores.setIcon(new ImageIcon(desktop.class
				.getResource("/imagens/fornecedor.png")));
		menubar.add(fornecedores);

		body = new JPanel();
		body.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));

		JPanel footer = new JPanel();
		footer.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		contentPane.add(footer, BorderLayout.SOUTH);
	}
}
