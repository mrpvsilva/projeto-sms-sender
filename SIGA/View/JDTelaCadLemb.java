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

import net.sourceforge.jdatepicker.DateModel;
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

import java.awt.Font;

import javax.swing.SwingConstants;

import TableModels.DefaultTableModel;
import Util.Validate;

import java.awt.Color;

public class JDTelaCadLemb extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvLemb;
	private JButton JBNovLemb;
	private JLabel JLDataContato;

	private DateModel<Date> model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JComboBox JCBUsuario;
	private JTextArea JTALemb;
	private LembretesControl _lembreteControl;
	private JLabel lblAssunto;
	private JTextField JTFAssunto;
	private JButton JBSair;
	private DefaultTableModel<Lembrete> _modelLembretes;
	private Lembrete _lembrete;
	private SpinnerNumberModel modelHora;
	private SpinnerNumberModel modelMinuto;
	private JLabel erro_message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			JDTelaCadLemb dialog = new JDTelaCadLemb(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construtor padr�o para cadastro de lembrete apartir da tela inicial do
	 * sistema
	 */
	public JDTelaCadLemb() {
		_lembrete = new Lembrete();
		start();
	}

	/**
	 * Construtor utilizado para cadastro de lembrete apartir da tela de busca
	 * de lembretes
	 */
	public JDTelaCadLemb(DefaultTableModel<Lembrete> modelLembretes) {
		_modelLembretes = modelLembretes;
		_lembrete = new Lembrete();
		start();

	}

	/**
	 * Construtor utilizado para edi��o de lembrete apartir da tela de busca de
	 * lembretes
	 */
	public JDTelaCadLemb(DefaultTableModel<Lembrete> modelLembretes,
			Lembrete lembrete) {
		_modelLembretes = modelLembretes;
		_lembrete = lembrete;
		start();
		carregarCampos();
	}

	private void start() {
		_lembreteControl = new LembretesControl();

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaCadLemb.class.getResource("/Img/CNPJ G200.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 505, 372);
		setTitle("SIGA - cadastro de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDataContato = new JLabel("Data");
			JLDataContato.setHorizontalAlignment(SwingConstants.RIGHT);
			JLDataContato.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLDataContato.setBounds(0, 53, 96, 14);
			contentPanel.add(JLDataContato);

			model = new UtilDateModel();
			datePanel = new JDatePanelImpl(model);
			datePanel.setPreferredSize(new java.awt.Dimension(202, 182));
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.getJFormattedTextField().setFont(
					new Font("Tahoma", Font.PLAIN, 13));
			datePicker.setBounds(106, 56, 154, 30);
			contentPanel.add(datePicker);

		}

		JLabel JLUsuario = new JLabel("Destinat�rio");
		JLUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		JLUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLUsuario.setBounds(0, 95, 96, 14);
		contentPanel.add(JLUsuario);

		JCBUsuario = new JComboBox(_lembreteControl.DDLDestinatarios());
		JCBUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JCBUsuario.setBounds(106, 92, 350, 20);
		contentPanel.add(JCBUsuario);

		JLabel lblLembrete = new JLabel("Lembrete");
		lblLembrete.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLembrete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLembrete.setBounds(0, 131, 96, 14);
		contentPanel.add(lblLembrete);

		JTALemb = new JTextArea();
		JTALemb.setLineWrap(true);
		JTALemb.setBounds(106, 131, 350, 133);
		contentPanel.add(JTALemb);

		lblAssunto = new JLabel("Assunto");
		lblAssunto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAssunto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssunto.setBounds(0, 22, 96, 14);
		contentPanel.add(lblAssunto);

		JTFAssunto = new JTextField();
		JTFAssunto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFAssunto.setBounds(106, 22, 350, 20);
		contentPanel.add(JTFAssunto);
		JTFAssunto.setColumns(10);

		JLabel JLHora = new JLabel("Hora");
		JLHora.setHorizontalAlignment(SwingConstants.RIGHT);
		JLHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLHora.setBounds(270, 53, 46, 14);
		contentPanel.add(JLHora);

		JSpinner hora = new JSpinner();
		modelHora = new SpinnerNumberModel(0, 0, 59, 1);
		hora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hora.setModel(modelHora);
		hora.setBounds(326, 53, 35, 20);
		contentPanel.add(hora);

		JLabel lblMinuto = new JLabel("Minuto");
		modelMinuto = new SpinnerNumberModel(0, 0, 59, 1);
		lblMinuto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinuto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinuto.setBounds(371, 53, 46, 14);
		contentPanel.add(lblMinuto);

		JSpinner minuto = new JSpinner();
		minuto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		minuto.setModel(modelMinuto);
		minuto.setBounds(421, 53, 35, 20);
		contentPanel.add(minuto);

		erro_message = new JLabel("");
		erro_message.setForeground(Color.RED);
		erro_message.setFont(new Font("Tahoma", Font.BOLD, 13));
		erro_message.setBounds(106, 275, 350, 15);
		contentPanel.add(erro_message);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvLemb = new JButton("Salvar");
				JBSalvLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvLemb.setIcon(new ImageIcon(JDTelaCadLemb.class
						.getResource("/Img/Confirmar.png")));
				JBSalvLemb.addActionListener(this);
				JBSalvLemb.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvLemb);
				getRootPane().setDefaultButton(JBSalvLemb);
			}
			{
				JBNovLemb = new JButton("Novo");
				JBNovLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovLemb.setIcon(new ImageIcon(JDTelaCadLemb.class
						.getResource("/Img/lembreteAdd.png")));
				JBNovLemb.addActionListener(this);
				JBNovLemb.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovLemb);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(JDTelaCadLemb.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	private void carregarCampos() {

		JTFAssunto.setText(_lembrete.getAssunto());
		JTALemb.setText(_lembrete.getTexto());
		Date data = _lembrete.getDatahora();
		model.setValue(data);
		modelHora.setValue(data.getHours());
		modelMinuto.setValue(data.getMinutes());
		JCBUsuario.setSelectedItem(_lembrete.getDestinatario().getUsuario());

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvLemb) {

			int hora = (int) modelHora.getValue();
			int min = (int) modelMinuto.getValue();

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o lembrete?")) {

				if (JTFAssunto.getText().isEmpty()) {
					Validate.validarJTextField(JTFAssunto, erro_message,
							"*Campo assunto � obrigat�tio");
				} else if (datePicker.getJFormattedTextField().getText()
						.isEmpty())
					Validate.validarJFormatTextField(
							datePicker.getJFormattedTextField(), erro_message,
							"*Campo data � obrigat�rio");
				else if (hora == 0)
					JOptionPane.showMessageDialog(null,
							"Coloque um hor�rio para o lembrete.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JCBUsuario.getSelectedItem().toString()
						.equals("Selecione"))
					JOptionPane.showMessageDialog(null,
							"Selecione um destinat�rio.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (JTALemb.getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Anota��es do lembrete em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					_lembrete.setAssunto(JTFAssunto.getText());
					_lembrete.setTexto(JTALemb.getText());

					Date data = (Date) datePicker.getModel().getValue();
					data.setHours(hora);
					data.setMinutes(min);

					_lembrete.setDatahora(data);

					_lembrete.setDestinatario(_lembreteControl
							.BuscarDestinatario(JCBUsuario.getSelectedItem()
									.toString()));

					String out = "";
					if (_lembrete.getId() == 0)
						out = _lembreteControl.Cadastrar(_lembrete);
					else
						out = _lembreteControl.Atualizar(_lembrete);

					if (out == null) {

						if (_modelLembretes != null)
							_modelLembretes.setLinhas(_lembreteControl
									.BuscarTodos());

						JOptionPane.showMessageDialog(null,
								"Lembrete salvo com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, out);
					}

				}// final da valida��o

			}// final da confirma��o

		}// final do bot�o salvar lembrete

		if (acao.getSource() == JBNovLemb) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo lembrete?")) {

				datePicker.getJFormattedTextField().setText("");
				JCBUsuario.setSelectedItem("Selecionar");
				JTALemb.setText("");

			}// final da confirma��o

		}// final do bot�o novo lembrete

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}
}
