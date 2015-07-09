package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.OrcamentoControl;
import Dominio.Evento;
import Dominio.EventoItem;
import Dominio.Item;
import TableModels.DefaultTableModel;
import TableModels.ItemEventoTableModel;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddItemEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Item> _modelItens;
	private DefaultTableModel<EventoItem> _modelEventoItens;
	private JTextField _qtd;
	private Evento _evento;
	private OrcamentoControl controller;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddItemEvento dialog = new AddItemEvento(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddItemEvento(DefaultTableModel<EventoItem> modelEventoItens,
			Evento evento) {
		controller = new OrcamentoControl();
		_modelItens = new ItemEventoTableModel();
		_modelEventoItens = modelEventoItens;
		preencherListaItens(controller.pesquisarItens("Todos"));
		_evento = evento;
		setModal(true);
		setBounds(100, 100, 662, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBounds(10, 11, 626, 47);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblTipoItem = new JLabel("Tipo item");
		lblTipoItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoItem.setBounds(106, 13, 67, 16);
		panel.add(lblTipoItem);

		comboBox = new JComboBox(controller.DDLTipoItens());
		comboBox.addItem("Todos");
		comboBox.setSelectedItem("Todos");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(171, 12, 182, 22);
		panel.add(comboBox);

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipoItem = comboBox.getSelectedItem().toString();
				preencherListaItens(controller.pesquisarItens(tipoItem));

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(363, 11, 89, 23);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 626, 282);
		contentPanel.add(scrollPane);

		table = new JTable(_modelItens);
		scrollPane.setViewportView(table);

		_qtd = new JTextField();
		_qtd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		_qtd.setBounds(550, 364, 86, 20);
		contentPanel.add(_qtd);
		_qtd.setColumns(10);

		JLabel lblQuant = new JLabel("Quant.");
		lblQuant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuant.setBounds(503, 367, 46, 14);
		contentPanel.add(lblQuant);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton add = new JButton("Adicionar");
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int l = table.getSelectedRow();
						if (l > -1) {
							Item i = _modelItens.find(l);
							if (!isExist(i)) {
								String qtd = _qtd.getText();
								if (!qtd.isEmpty()) {

									int q = Integer.parseInt(qtd);

									EventoItem ei = new EventoItem(_evento, i);
									_modelEventoItens.add(ei);
									_modelItens.remove(l);

								} else {
									JOptionPane.showMessageDialog(null,
											"Informe a quantidade", "Atenção",
											JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Item já adicionado ao evento.",
										"Atenção", JOptionPane.WARNING_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Selecione um item", "Atenção",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				add.setFont(new Font("Tahoma", Font.PLAIN, 13));
				add.setActionCommand("OK");
				buttonPane.add(add);
				getRootPane().setDefaultButton(add);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void preencherListaItens(List<Item> itens) {

		_modelItens.clear();

		if (!itens.isEmpty()) {

			for (Item item : itens) {
				int cont = 0;
				for (EventoItem e : _modelEventoItens.getLinhas()) {
					if (e.getItem().getId() == item.getId())
						cont++;
				}

				if (cont == 0)
					_modelItens.add(item);
			}

		}
	}

	private boolean isExist(Item item) {

		for (EventoItem e : _modelEventoItens.getLinhas()) {

			if (e.getItem().getId() == item.getId())
				return true;

		}

		return false;
	}
}
