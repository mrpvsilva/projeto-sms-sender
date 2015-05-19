package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import Control.LembretesControl;
import Dominio.Lembrete;
import Dominio.Permissao;

import javax.swing.ImageIcon;

import TableModels.DefaultTableModel;
import TableModels.LembreteTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.util.Date;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import javax.swing.JTextArea;

public class JDTelaBuscarLemb extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadLemb;
	private JButton JBEditLemb;
	private JButton JBBuscar;
	private JScrollPane scroll;
	private JTable tabela;
	private LembretesControl _lembreteControl;
	private JButton JBSair;
	private Permissao Lembretes;
	private JLabel lblDataFinal;
	private JLabel lblDestinatrios;
	private DefaultTableModel<Lembrete> modelLembretes;
	private JDatePickerImpl datePickerFinal;
	private JDatePickerImpl datePickerInicio;
	private JComboBox destinatarios;
	private JTextArea texto;
	private JLabel lblMensagem;

	public static void main(String[] args) {
		try {
			JDTelaBuscarLemb dialog = new JDTelaBuscarLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarLemb() {

		_lembreteControl = new LembretesControl();
		modelLembretes = new LembreteTableModel(_lembreteControl.BuscarTodos());
		Lembretes = PermissoesManager.buscarPermissao(Modulos.Lembretes);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaBuscarLemb.class.getResource("/Img/CNPJ G200.png")));
		setModal(true);
		setResizable(false);
		setTitle("SIGA - buscar lembretes");
		setBounds(100, 100, 841, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			// Cria��o da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(10, 135, 815, 383);

			tabela = new JTable(modelLembretes);
			tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));

			tabela.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							int linha = tabela.getSelectedRow();
							if (linha > -1) {
								String t = modelLembretes.find(linha)
										.getTexto();
								texto.setText(t);
							}
						}
					});

			scroll.setViewportView(tabela);

			JPanel filtros = new JPanel();
			filtros.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
			filtros.setBounds(10, 11, 815, 113);
			contentPanel.add(filtros);
			filtros.setLayout(null);

			JLabel lblDataInicial = new JLabel("Data inicial");
			lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDataInicial.setBounds(10, 11, 240, 23);
			filtros.add(lblDataInicial);
			{
				UtilDateModel datemodelinicio = new UtilDateModel();
				JDatePanelImpl datePanelinicio = new JDatePanelImpl(
						datemodelinicio);
				datePanelinicio.setBounds(72, 57, 200, 180);
				datePickerInicio = new JDatePickerImpl(datePanelinicio);
				datePickerInicio.getJFormattedTextField()
						.setHorizontalAlignment(SwingConstants.RIGHT);
				datePickerInicio.getJFormattedTextField().setFont(
						new Font("Tahoma", Font.PLAIN, 13));
				datePickerInicio.setBounds(10, 33, 240, 33);
				filtros.add(datePickerInicio);

			}
			{
				UtilDateModel datemodelfinal = new UtilDateModel();
				JDatePanelImpl datePanelFinal = new JDatePanelImpl(
						datemodelfinal);
				{
					lblDataFinal = new JLabel("Data final");
					lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblDataFinal.setBounds(260, 11, 242, 23);
					filtros.add(lblDataFinal);
				}

				datePickerFinal = new JDatePickerImpl(datePanelFinal);
				datePickerFinal.getJFormattedTextField().setFont(
						new Font("Tahoma", Font.PLAIN, 13));
				datePickerFinal.setBounds(260, 33, 242, 33);
				filtros.add(datePickerFinal);

			}
			{
				lblDestinatrios = new JLabel("Destinat\u00E1rios");
				lblDestinatrios.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblDestinatrios.setBounds(514, 11, 227, 23);
				filtros.add(lblDestinatrios);
			}

			destinatarios = new JComboBox(_lembreteControl.DDLDestinatarios());
			destinatarios.setFont(new Font("Tahoma", Font.PLAIN, 13));
			destinatarios.setBounds(514, 33, 227, 23);
			filtros.add(destinatarios);
			{
				JBBuscar = new JButton("Pesquisar");
				JBBuscar.setBounds(332, 77, 120, 23);
				filtros.add(JBBuscar);
				JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBBuscar.setIcon(new ImageIcon(JDTelaBuscarLemb.class
						.getResource("/Img/Procurar.png")));
				JBBuscar.addActionListener(this);
				JBBuscar.setMnemonic(KeyEvent.VK_F);
			}

		}

		texto = new JTextArea();
		texto.setEditable(false);
		texto.setBounds(10, 550, 815, 125);
		contentPanel.add(texto);
		{
			lblMensagem = new JLabel("Mensagem");
			lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMensagem.setBounds(10, 529, 115, 15);
			contentPanel.add(lblMensagem);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JBCadLemb = new JButton("Cadastrar");
			JBCadLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBCadLemb.setIcon(new ImageIcon(JDTelaBuscarLemb.class
					.getResource("/Img/save16.png")));
			JBCadLemb.addActionListener(this);
			JBCadLemb.setMnemonic(KeyEvent.VK_C);
			JBCadLemb.setVisible(Lembretes.isCadastrar());
			buttonPane.add(JBCadLemb);
			getRootPane().setDefaultButton(JBCadLemb);
		}
		{
			JBEditLemb = new JButton("Editar");
			JBEditLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBEditLemb.setIcon(new ImageIcon(JDTelaBuscarLemb.class
					.getResource("/Img/edit_add16.png")));
			JBEditLemb.addActionListener(this);
			JBEditLemb.setVisible(Lembretes.isAlterar());
			JBEditLemb.setMnemonic(KeyEvent.VK_E);
			buttonPane.add(JBEditLemb);
		}
		{
			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(JDTelaBuscarLemb.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadLemb) {
			JDTelaCadLemb jdtcl = new JDTelaCadLemb(modelLembretes);
			jdtcl.setVisible(true);
			jdtcl.setLocationRelativeTo(null);
		}// final do bot�o cadastrar lembretes

		if (acao.getSource() == JBEditLemb) {

			int linha = tabela.getSelectedRow();
			if (linha > -1) {
				JDTelaCadLemb jdtcl = new JDTelaCadLemb(modelLembretes,
						modelLembretes.find(linha));
				jdtcl.setVisible(true);
				jdtcl.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do bot�o atualizar lembretes

		if (acao.getSource() == JBBuscar) {
			Pesquisar();
		}// final do bot�o buscar lembretes

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}// final da a��o do bot�o

	private void Pesquisar() {
		texto.setText("");
		Date dataInicial = (Date) datePickerInicio.getModel().getValue();
		Date dataFinal = (Date) datePickerFinal.getModel().getValue();
		String destinatario = destinatarios.getSelectedItem().toString();

		modelLembretes.setLinhas(_lembreteControl.BuscarTodos(dataInicial,
				dataFinal, destinatario));

	}
}
