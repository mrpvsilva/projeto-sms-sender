package Util;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

public class Validate {

	/**
	 * APLICA NO JTEXTFIELD A MENSAGEM DE ERRO.
	 * */
	public static JTextComponent validarJTextField(final JTextComponent campo,
			final JLabel label_erro, final String msg) {
		label_erro.setText(msg);
		campo.requestFocus();
		campo.setBorder(new LineBorder(new Color(255, 0, 0)));
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					return;
				if (campo.getText().length() > 0) {
					label_erro.setText("");
					campo.setBorder(new LineBorder(new Color(171, 173, 179)));
				}else{
					label_erro.setText(msg);					
					campo.setBorder(new LineBorder(new Color(255, 0, 0)));
				}
			}
		});
		return campo;
	}

	public static void validarJFormatTextField(final JFormattedTextField campo,
			final JLabel label_erro, final String msg) {

		label_erro.setText(msg);
		campo.requestFocus();
		campo.setBorder(new LineBorder(new Color(255, 0, 0)));
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (campo.getValue() != null) {
					campo.setBorder(new LineBorder(new Color(171, 173, 179)));
					label_erro.setText("");
				} else {
					label_erro.setText(msg);
					campo.setBorder(new LineBorder(new Color(255, 0, 0)));
				}
			}
		});

	}

	public static void validarJTable(final JTable table,
			final JLabel label_erro, final String msg) {
		label_erro.setText(msg);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting())
							return;

						int cont = table.getModel().getRowCount();

						if (cont < 1) {
							label_erro.setText(msg);
						} else {
							label_erro.setText("");
						}

					}
				});

	}
}
