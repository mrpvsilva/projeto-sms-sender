package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.OrcamentoControl;
import Dominio.Evento;
import TableModels.DefaultTableModel;
import TableModels.OrcamentoTableModel;
import java.awt.Font;

public class BuscarOrcamentos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Evento> _modelOrcamento;
	private OrcamentoControl _orcamentoControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarOrcamentos dialog = new BuscarOrcamentos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarOrcamentos() {
		_orcamentoControl = new OrcamentoControl();
		_modelOrcamento = new OrcamentoTableModel(
				_orcamentoControl.listarTodos());
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 686, 493);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 56, 660, 365);
			contentPanel.add(scrollPane);
			{
				table = new JTable(_modelOrcamento);
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Novo");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Alterar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			JButton btnGerarDocumento = new JButton("Gerar documento");
			btnGerarDocumento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			buttonPane.add(btnGerarDocumento);
		}
	}
}
