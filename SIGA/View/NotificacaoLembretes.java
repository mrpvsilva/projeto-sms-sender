package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import Control.LembretesControl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificacaoLembretes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel texto;
	private JComboBox comboBox;
	private BuscarLembretes bl;
	private final int esperaPadrao = 300000;
	private int espera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NotificacaoLembretes dialog = new NotificacaoLembretes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NotificacaoLembretes() {
		espera = esperaPadrao;
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Notifica\u00E7\u00E3o de lembretes");

		setBounds(100, 100, 450, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			texto = new JLabel("");
			texto.setHorizontalAlignment(SwingConstants.CENTER);
			texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			texto.setBounds(10, 11, 414, 20);
			contentPanel.add(texto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Visualizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						fechar();
						espera = esperaPadrao;
						bl = new BuscarLembretes();
						bl.setLocationRelativeTo(null);
						bl.setVisible(true);
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						fechar();
						new LembretesControl().limparLembretesNotificacao();
						espera = esperaPadrao;
						
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnAdiar = new JButton("Adiar");
				btnAdiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int min = Integer.parseInt(comboBox.getSelectedItem()
								.toString());
						espera = min * 60000;
						fechar();
					}
				});
				btnAdiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				buttonPane.add(btnAdiar);
			}
			{
				comboBox = new JComboBox();
				comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "1",
						"5", "10", "15", "20", "30", "50", "60" }));
				buttonPane.add(comboBox);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Min.");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				buttonPane.add(lblNewLabel_1);
			}

		}
	}

	private void fechar() {
		setVisible(false);
	}

	private void setMensagem(int qtd) {

		String text = String.format("Você possui %s %s", qtd,
				qtd > 1 ? "lembretes" : "lembrete");
		texto.setText(text);
	}

	public void buscarLembretesUsuario() {

		int qtd = new LembretesControl().notificarLembretesUsuario().size();
		if (qtd > 0) {
			setMensagem(qtd);
			setVisible(bl == null || !bl.isVisible());
		}
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {

		}

	}

}
