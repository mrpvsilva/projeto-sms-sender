package com.main.Item;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.applications.ItemApplication;
import com.applications.TipoItemApplication;
import com.domain.Item;
import com.domain.TipoItem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EditItem extends JFrame {

	private JPanel contentPane;
	private Item itemViewModel;
	private JTextField tf_nome;
	private JTextArea tf_descricao;
	private JFormattedTextField tf_valorCusto;
	private JFormattedTextField tf_valorComercial;
	private JComboBox ddlTipoItem;
	private List<TipoItem> tipoItens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item item =  new Item(); //ItemApplication().GetById(5);
					EditItem frame = new EditItem(item);
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
	public EditItem(Item item) {

		itemViewModel = item;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(10, 11, 70, 14);
		contentPane.add(lblNome);

		tf_nome = new JTextField();
		tf_nome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				itemViewModel.setNome(tf_nome.getText());
			}
		});
		tf_nome.setBounds(90, 8, 350, 20);
		tf_nome.setColumns(10);
		tf_nome.setText(item.getNome());
		contentPane.add(tf_nome);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setBounds(10, 38, 70, 14);
		contentPane.add(lblDescrio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(91, 36, 350, 66);
		contentPane.add(scrollPane);

		tf_descricao = new JTextArea();
		tf_descricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				itemViewModel.setDescricao(tf_descricao.getText());
			}
		});
		tf_descricao.setLineWrap(true);
		tf_descricao.setText(item.getDescricao());
		scrollPane.setViewportView(tf_descricao);

		JLabel lblNewLabel = new JLabel("Valor custo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(9, 116, 70, 14);
		contentPane.add(lblNewLabel);

		DecimalFormat format = new DecimalFormat();
		format.applyPattern("#,##0.00");
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setFormat(format);
		formatter.setAllowsInvalid(false);

		tf_valorCusto = new JFormattedTextField(new DefaultFormatterFactory(
				formatter));
		tf_valorCusto.setText(item.getValorCusto() + "");
		tf_valorCusto.setBounds(90, 113, 111, 20);
		contentPane.add(tf_valorCusto);

		JLabel lblValorComercial = new JLabel("Valor comercial:");
		lblValorComercial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorComercial.setBounds(211, 116, 95, 14);
		contentPane.add(lblValorComercial);

		tf_valorComercial = new JFormattedTextField(
				new DefaultFormatterFactory(formatter));

		tf_valorComercial.setBounds(329, 113, 111, 20);
		tf_valorComercial.setText(item.getValorComercial() + "");
		contentPane.add(tf_valorComercial);

		JLabel lblTipoItem = new JLabel("Tipo Item:");
		lblTipoItem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoItem.setBounds(10, 155, 70, 14);
		contentPane.add(lblTipoItem);

		ddlTipoItem = new JComboBox();
		ddlTipoItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SetTipoItem(ddlTipoItem.getSelectedItem().toString());
			}
		});
		ddlTipoItem.setBounds(90, 152, 350, 20);
		contentPane.add(ddlTipoItem);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean succes = true;
				itemViewModel.setValorCusto(Double.parseDouble(tf_valorCusto
						.getText().replace(".", "").replace(",", ".")));
				itemViewModel.setValorComercial(Double
						.parseDouble(tf_valorComercial.getText()
								.replace(".", "").replace(",", ".")));
				succes = new ItemApplication().Add(itemViewModel);

				if (succes) {
					JOptionPane.showMessageDialog(null, "Sucesso no cadastro");
				} else {
					JOptionPane.showMessageDialog(null, "Falha no cadastro");
				}

			}
		});
		btnNewButton.setBounds(183, 205, 87, 23);
		contentPane.add(btnNewButton);
		DDLTipoItem();
	}

	private void DDLTipoItem() {
		tipoItens = new TipoItemApplication().getTipoItens(true);

		for (TipoItem tipoItem : tipoItens) {
			ddlTipoItem.addItem(tipoItem.getNome());
			if (itemViewModel.getTipoitem().getId() == tipoItem.getId()) {
				ddlTipoItem.setSelectedIndex(ddlTipoItem.getItemCount() - 1);
			}
		}
	}

	private void SetTipoItem(String nome) {

		for (TipoItem tipoItem : tipoItens) {

			if (tipoItem.getNome() == nome) {
				itemViewModel.setTipoitem(tipoItem);

			}

		}

	}
}
