package View;

import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Dominio.EventoItem;
import TableModels.DefaultTableModel;
import TableModels.EventoItemTableModel;

public class TiposItemPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public TiposItemPanel() {
		setLayout(null);

		DefaultTableModel<EventoItem> modelEventoItem = new EventoItemTableModel();
		Panel p = new Panel(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 584, 300);
		JTable table = new JTable(modelEventoItem);
		scrollPane.setViewportView(table);
		p.add(scrollPane);

	}
}
