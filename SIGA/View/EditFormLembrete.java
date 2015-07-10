package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import Control.LembretesControl;
import Dominio.Lembrete;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import java.awt.Font;

import javax.swing.SwingConstants;

import TableModels.DefaultTableModel;

import java.awt.Color;

import Util.DateTimePicker;

public class EditFormLembrete extends JDialog implements ActionListener {

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

	private JComboBox JCBUsuario;
	private JTextArea JTALemb;
	private LembretesControl _lembreteControl;
	private JLabel lblAssunto;
	private JTextField JTFAssunto;
	private JButton JBSair;
	private DefaultTableModel<Lembrete> _modelLembretes;
	private Lembrete _lembrete;	
	private JLabel erro_message;
	private DateTimePicker datahora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			EditFormLembrete dialog = new EditFormLembrete(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construtor padrão para cadastro de lembrete apartir da tela inicial do
	 * sistema
	 */
	public EditFormLembrete() {
		_lembrete = new Lembrete();
		start();
	}

	/**
	 * Construtor utilizado para cadastro de lembrete apartir da tela de busca
	 * de lembretes
	 */
	public EditFormLembrete(DefaultTableModel<Lembrete> modelLembretes) {
		_modelLembretes = modelLembretes;
		_lembrete = new Lembrete();
		start();

	}

	/**
	 * Construtor utilizado para edição de lembrete apartir da tela de busca de
	 * lembretes
	 */
	public EditFormLembrete(DefaultTableModel<Lembrete> modelLembretes,
			Lembrete lembrete) {
		_modelLembretes = modelLembretes;
		_lembrete = lembrete;
		start();
		carregarCampos();
	}

	private void start() {
		_lembreteControl = new LembretesControl();

		setIconImage(Toolkit.getDefaultToolkit().getImage(EditFormLembrete.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 505, 372);
		setTitle("SIGA - cadastro de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDataContato = new JLabel("Data/Hora");
			JLDataContato.setHorizontalAlignment(SwingConstants.RIGHT);
			JLDataContato.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLDataContato.setBounds(0, 53, 96, 14);
			contentPanel.add(JLDataContato);

			model = new UtilDateModel();
			datePanel = new JDatePanelImpl(model);
			datePanel.setPreferredSize(new java.awt.Dimension(202, 182));

		}

		JLabel JLUsuario = new JLabel("Destinatário");
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

		erro_message = new JLabel("");
		erro_message.setForeground(Color.RED);
		erro_message.setFont(new Font("Tahoma", Font.BOLD, 13));
		erro_message.setBounds(106, 275, 350, 15);
		contentPanel.add(erro_message);

		datahora = new DateTimePicker();
		datahora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		datahora.setBounds(106, 50, 173, 22);
		datahora.setFormats(DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT));
		datahora.setTimeFormat(DateFormat.getTimeInstance(DateFormat.SHORT));
		datahora.setDate(new Date());
		contentPanel.add(datahora);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvLemb = new JButton("Salvar");
				JBSalvLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvLemb.setIcon(new ImageIcon(EditFormLembrete.class
						.getResource("/Img/Confirmar.png")));
				JBSalvLemb.addActionListener(this);
				JBSalvLemb.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvLemb);
				getRootPane().setDefaultButton(JBSalvLemb);
			}
			{
				JBNovLemb = new JButton("Novo");
				JBNovLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovLemb.setIcon(new ImageIcon(EditFormLembrete.class
						.getResource("/Img/lembreteAdd.png")));
				JBNovLemb.addActionListener(this);
				JBNovLemb.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovLemb);
			}

			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(EditFormLembrete.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	private void carregarCampos() {

		JTFAssunto.setText(_lembrete.getAssunto());
		JTALemb.setText(_lembrete.getTexto());
		datahora.setDate(_lembrete.getDatahora());
		JCBUsuario.setSelectedItem(_lembrete.getDestinatario().getUsuario());

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvLemb) {
		

			if (JTFAssunto.getText().isEmpty())
				JOptionPane.showMessageDialog(null,
						"O campo assunto é obrigatótio", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
			else if (datahora.getDate() == null)
				JOptionPane.showMessageDialog(null,
						"O campo data/hora é obrigatótio", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);			
			else if (JCBUsuario.getSelectedItem().toString()
					.equals("Selecione"))
				JOptionPane.showMessageDialog(null,
						"Selecione um destinatário.", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
			else if (JTALemb.getText().isEmpty())
				JOptionPane.showMessageDialog(null,
						"Mensagem do lembrete é obrigatároio.",
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			else {

				_lembrete.setAssunto(JTFAssunto.getText());
				_lembrete.setTexto(JTALemb.getText());
				_lembrete.setDatahora(datahora.getDate());
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

			}// final da validação

		}// final do botão salvar lembrete

		if (acao.getSource() == JBNovLemb) {

			// if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
			// "Deseja cadastrar um novo lembrete?")) {
			//
			// datePicker.getJFormattedTextField().setText("");
			// JCBUsuario.setSelectedItem("Selecionar");
			// JTALemb.setText("");
			//
			// }// final da confirmação

		}// final do botão novo lembrete

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}
}
