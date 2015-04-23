package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JDTelaCadLemb extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvLemb;
	private JButton JBNovLemb;
	private JLabel JLDataContato;

	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JComboBox<String> JCBUsuario;
	private JTextArea JTALemb;
	private LembretesControl _lembreteControl = new LembretesControl();
	private JLabel lblAssunto;
	private JTextField JTFAssunto;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			JDTelaCadLemb dialog = new JDTelaCadLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadLemb() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaCadLemb.class.getResource("/Img/CNPJ G200.png")));
		setModal(true);
		setResizable(true);
		setBounds(100, 100, 450, 341);
		setTitle("SIGA - cadastro de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDataContato = new JLabel("Data contato");
			JLDataContato.setBounds(10, 53, 74, 14);
			contentPanel.add(JLDataContato);

			model = new UtilDateModel();
			datePanel = new JDatePanelImpl(model);
			datePanel.setPreferredSize(new java.awt.Dimension(202, 182));
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.setBounds(99, 53, 154, 30);
			contentPanel.add(datePicker);

		}

		JLabel JLUsuario = new JLabel("Destinatário");
		JLUsuario.setBounds(10, 95, 74, 14);
		contentPanel.add(JLUsuario);

		JCBUsuario = new JComboBox<String>(_lembreteControl.DDLRemetentes());
		JCBUsuario.setBounds(99, 94, 325, 20);
		contentPanel.add(JCBUsuario);

		JLabel lblLembrete = new JLabel("Lembrete");
		lblLembrete.setBounds(10, 131, 63, 14);
		contentPanel.add(lblLembrete);

		JTALemb = new JTextArea();
		JTALemb.setLineWrap(true);
		JTALemb.setBounds(99, 126, 325, 133);
		contentPanel.add(JTALemb);

		lblAssunto = new JLabel("Assunto");
		lblAssunto.setBounds(10, 22, 63, 14);
		contentPanel.add(lblAssunto);

		JTFAssunto = new JTextField();
		JTFAssunto.setBounds(99, 19, 325, 20);
		contentPanel.add(JTFAssunto);
		JTFAssunto.setColumns(10);

		JLabel JLHora = new JLabel("Hora");
		JLHora.setBounds(263, 53, 46, 14);
		contentPanel.add(JLHora);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(296, 50, 35, 20);
		contentPanel.add(spinner);

		JLabel lblMinuto = new JLabel("Minuto");
		lblMinuto.setBounds(341, 53, 46, 14);
		contentPanel.add(lblMinuto);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(389, 50, 35, 20);
		contentPanel.add(spinner_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvLemb = new JButton("Salvar");
				JBSalvLemb.setIcon(new ImageIcon(JDTelaCadLemb.class
						.getResource("/Img/Confirmar.png")));
				JBSalvLemb.addActionListener(this);
				JBSalvLemb.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvLemb);
				getRootPane().setDefaultButton(JBSalvLemb);
			}
			{
				JBNovLemb = new JButton("Novo");
				JBNovLemb.setIcon(new ImageIcon(JDTelaCadLemb.class
						.getResource("/Img/lembreteAdd.png")));
				JBNovLemb.addActionListener(this);
				JBNovLemb.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovLemb);
			}

			JBSair = new JButton("Sair");
			JBSair.setIcon(new ImageIcon(JDTelaCadLemb.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvLemb) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o lembrete?")) {

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

					// LembretesBean lembBean = new LembretesBean();
					//
					// // lembBean.setDtcontatolemb(dtcontatolemb); o que seria
					// // aqui? Não lembro //
					// // lembBean.setDtagendadalemb(); Converter para DateTime
					// lembBean.setUsuario(JCBUsuario.getSelectedItem().toString());
					// lembBean.setAssunto(JTALemb.getText());

					Lembrete l = new Lembrete();

					l.setAssunto(JTFAssunto.getText());
					l.setTexto(JTALemb.getText());
					l.setDatahora((Date) datePicker.getModel().getValue());

					l.setDestinatario(_lembreteControl
							.BuscarDestinatario(JCBUsuario.getSelectedItem()
									.toString()));

					String out = _lembreteControl.Cadastrar(l);
					if (out == null) {
						JOptionPane.showMessageDialog(null,
								"Lembrete cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, out);
					}

				}// final da validação

			}// final da confirmação

		}// final do botão salvar lembrete

		if (acao.getSource() == JBNovLemb) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo lembrete?")) {

				datePicker.getJFormattedTextField().setText("");
				JCBUsuario.setSelectedItem("Selecionar");
				JTALemb.setText("");

			}// final da confirmação

		}// final do botão novo lembrete

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}
}
