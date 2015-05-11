package com.view.home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import com.util.jpautil.JpaUtil;
import com.view.caixa.Caixa;
import com.view.fornecedores.Fornecedores;
import com.view.vendas.Vendas;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class Desktop extends JFrame {

	private JPanel contentPane;
	private JPanel body;
	private JLabel relogio;

	public Desktop() {
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Desktop.class.getResource("/imagens/logo_small.png")));

		JpaUtil.createEntityManagerFactory();
		setLocale(new Locale("pt", "BR"));
		setMinimumSize(new Dimension(1024, 600));
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
				Caixa telavendas = new Caixa();
				telavendas.setVisible(true);
				body.add(telavendas, BorderLayout.CENTER);
				body.revalidate();
				body.repaint();

			}
		});
		home.setToolTipText("Caixa");
		home.setMargin(new Insets(0, 0, 0, 0));
		home.setIcon(new ImageIcon(Desktop.class.getResource("/imagens/caixa.png")));
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
		produtos.setIcon(new ImageIcon(Desktop.class
				.getResource("/imagens/produtos.png")));
		menubar.add(produtos);

		JButton fornecedores = new JButton("");
		fornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				body.removeAll();
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
				body.add(fornecedores, BorderLayout.CENTER);
				body.revalidate();
				body.repaint();

			}
		});
		fornecedores.setToolTipText("Fornecedores");
		fornecedores.setMargin(new Insets(0, 0, 0, 0));
		fornecedores.setIcon(new ImageIcon(Desktop.class
				.getResource("/imagens/fornecedor.png")));
		menubar.add(fornecedores);
		
		JButton vendas = new JButton("");
		vendas.setToolTipText("Vendas");
		vendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				body.removeAll();
				Vendas vendas = new Vendas();
				vendas.setVisible(true);
				body.add(vendas, BorderLayout.CENTER);
				body.revalidate();
				body.repaint();
			}
		});
		vendas.setMargin(new Insets(0, 0, 0, 0));
		vendas.setIcon(new ImageIcon(Desktop.class.getResource("/imagens/vendas.png")));
		menubar.add(vendas);

		body = new JPanel();
		body.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		body.setLayout(new BorderLayout(0, 0));
		contentPane.add(body, BorderLayout.CENTER);

		JPanel footer = new JPanel();
		footer.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		contentPane.add(footer, BorderLayout.SOUTH);
		footer.setLayout(new BorderLayout(10, 10));

		JLabel copyrigth = new JLabel("Copyright " + LocalDate.now().getYear());
		copyrigth.setHorizontalAlignment(SwingConstants.CENTER);
		footer.add(copyrigth, BorderLayout.CENTER);

		relogio = new JLabel("New label");
		relogio.setHorizontalAlignment(SwingConstants.CENTER);
		footer.add(relogio, BorderLayout.WEST);
		setRelogio();

		Caixa telavendas = new Caixa();
		telavendas.setVisible(true);
		body.add(telavendas, BorderLayout.CENTER);

	}

	private void setRelogio() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					LocalDateTime agora = LocalDateTime.now();
					String datahora = agora.format(DateTimeFormatter
							.ofPattern("dd/MM/yyyy HH:mm:ss"));
					relogio.setText(datahora);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
}
