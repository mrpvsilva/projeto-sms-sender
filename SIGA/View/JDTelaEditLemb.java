package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import Control.LembretesControl;
import Dominio.Lembrete;

import javax.swing.JTextField;

public class JDTelaEditLemb extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBAtuLemb;
	private JButton JBExcLemb;
	private JLabel JLDataContato;

	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JComboBox JCBUsuario;
	private JTextArea JTALemb;
	private int _id;
	private LembretesControl _lembreteControl = new LembretesControl();
	private Lembrete _lembrete;
	private JTextField JTFAssunto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditLemb dialog = new JDTelaEditLemb(6);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditLemb(int id) {
		_id = id;
		setBounds(100, 100, 450, 349);
		setTitle("SIGA - edi\u00E7\u00E3o de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDataContato = new JLabel("Data contato");
			JLDataContato.setBounds(15, 61, 74, 14);
			contentPanel.add(JLDataContato);

		}

		JLabel JLUsuario = new JLabel("Destinatário");
		JLUsuario.setBounds(15, 101, 46, 14);
		contentPanel.add(JLUsuario);

		JLabel lblAssunto = new JLabel("Assunto");
		lblAssunto.setBounds(15, 24, 63, 14);
		contentPanel.add(lblAssunto);

		JTFAssunto = new JTextField();
		JTFAssunto.setBounds(99, 21, 224, 20);
		contentPanel.add(JTFAssunto);
		JTFAssunto.setColumns(10);

		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePanel.setPreferredSize(new java.awt.Dimension(202, 182));
		datePicker.setBounds(99, 57, 224, 30);
		contentPanel.add(datePicker);

		JCBUsuario = new JComboBox(_lembreteControl.DDLRemetentes());
		JCBUsuario.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == e.SELECTED
						&& !e.getItem().toString().equals("Selecione")) {
					_lembrete.setDestinatario(_lembreteControl
							.BuscarDestinatario(e.getItem().toString()));
				}
			}
		});
		JCBUsuario.setBounds(99, 98, 224, 20);
		contentPanel.add(JCBUsuario);

		JLabel lblLembrete = new JLabel("Lembrete");
		lblLembrete.setBounds(15, 134, 63, 14);
		contentPanel.add(lblLembrete);

		JTALemb = new JTextArea();
		JTALemb.setLineWrap(true);
		JTALemb.setBounds(104, 129, 325, 133);
		contentPanel.add(JTALemb);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuLemb = new JButton("Atualizar");
				JBAtuLemb.addActionListener(this);
				buttonPane.add(JBAtuLemb);
				getRootPane().setDefaultButton(JBAtuLemb);
			}
			{
				JBExcLemb = new JButton("Excluir");
				JBExcLemb.addActionListener(this);
				buttonPane.add(JBExcLemb);
			}
		}

		PreencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBAtuLemb) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja atualizar o lembrete?")) {

				if (JTFAssunto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Assunto em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				} else if (datePicker.getJFormattedTextField().getText()
						.isEmpty())
					JOptionPane.showMessageDialog(null,
							"Data do lembrete em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (JCBUsuario.getSelectedItem().toString()
						.equals("Selecione"))
					JOptionPane.showMessageDialog(null,
							"Destinatário do lembrete em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTALemb.getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Anotações do lembrete em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					_lembrete.setAssunto(JTFAssunto.getText());
					_lembrete.setTexto(JTALemb.getText());
					_lembrete.setDatahora((Date) datePicker.getModel()
							.getValue());

					String out = _lembreteControl.Atualizar(_lembrete);
					if (out == null) {
						PreencherCampos();
						JOptionPane.showMessageDialog(null,
								"Lembrete atualizado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, out);
					}

				}// final da validação

			}// final da confirmação

		}// final do botão salvar lembrete

		if (acao.getSource() == JBExcLemb) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja excluir o lembrete?")) {

			}// final da confirmação

		}// final do botão novo lembrete

	}

	@SuppressWarnings({ "unused", "deprecation" })
	private void PreencherCampos() {
		_lembrete = _lembreteControl.BuscarLembrete(_id);
		JTFAssunto.setText(_lembrete.getAssunto());
		Date data = _lembrete.getDatahora();
		datePicker.getModel().setDate(1900 + data.getYear(), data.getMonth(),
				data.getDate());
		datePicker.getModel().setSelected(true);
		JCBUsuario.setSelectedItem(_lembrete.getDestinatario().getUsuario());
		JTALemb.setText(_lembrete.getTexto());

	}
}
