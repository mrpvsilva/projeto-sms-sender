package com.view.produtos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dominio.Produto;
import com.util.carrinho.Carrinho;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Quantidade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfqtd;
	private Produto produto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Quantidade dialog = new Quantidade(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Quantidade(Produto produto) {
		this.produto = produto;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 121, 136);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 11, 86, 14);
		contentPanel.add(lblQuantidade);

		tfqtd = new JTextField();
		tfqtd.setHorizontalAlignment(SwingConstants.RIGHT);
		tfqtd.setText("1");
		tfqtd.setBounds(10, 34, 106, 20);
		tfqtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		contentPanel.add(tfqtd);
		tfqtd.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int q = Integer.parseInt(tfqtd.getText());
						if (produto.getQuantidade() >= q) {
							Carrinho.add(produto, q);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Quantidade escolhida é maior do que há em estoque.","Atenção",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
