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
import javax.swing.JLabel;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BuscarLembretes extends JDialog implements ActionListener {

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

	private JDatePickerImpl datePickerInicial;
	private DateModel<Date> datemodelinicial;
	private JDatePickerImpl datePickerFinal;
	private DateModel<Date> datemodelfinal;

	private JTextArea texto;
	private JLabel lblMensagem;
	private JTextField assunto;
	private JLabel msg_range_data;
	private JButton btnMarcarComoNo;

	public static void main(String[] args) {
		try {
			BuscarLembretes dialog = new BuscarLembretes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarLembretes() {

		_lembreteControl = new LembretesControl();
		modelLembretes = new LembreteTableModel(_lembreteControl.BuscarTodos());
		Lembretes = PermissoesManager.buscarPermissao(Modulos.Lembretes);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BuscarLembretes.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setModal(true);
		setResizable(false);
		setTitle("SIGA - buscar lembretes");
		setBounds(100, 100, 841, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
			scroll.setBounds(10, 106, 815, 412);

			tabela = new JTable(modelLembretes);
			tabela.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			tabela.getColumnModel().getColumn(0).setWidth(30);
			tabela.getColumnModel().getColumn(0).setMaxWidth(30);
			tabela.getColumnModel().getColumn(0).setMinWidth(30);
			
			
			
			tabela.getColumnModel().getColumn(1).setWidth(200);
			tabela.getColumnModel().getColumn(1).setMaxWidth(350);
			tabela.getColumnModel().getColumn(1).setMinWidth(150);
			
			
				
			tabela.getColumnModel().getColumn(2).setMinWidth(300);		
			
		
			tabela.getColumnModel().getColumn(3).setMaxWidth(150);
			tabela.getColumnModel().getColumn(3).setMinWidth(150);
			
			tabela.getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					texto.setText("");
					btnMarcarComoNo.setEnabled(false);

				}
			});
			tabela.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							int linha = tabela.getSelectedRow();
							if (linha > -1) {
								Lembrete l = modelLembretes.find(linha);
								String t = l.getTexto();
								texto.setText(t);
								l.marcarComoLido();
								_lembreteControl.Atualizar(l);

								if (l.isLido()) {
									btnMarcarComoNo.setEnabled(true);
								}
							}
						}
					});

			scroll.setViewportView(tabela);

			JPanel filtros = new JPanel();
			filtros.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
			filtros.setBounds(10, 11, 815, 84);
			contentPanel.add(filtros);
			filtros.setLayout(null);

			JLabel lblDataInicial = new JLabel("Data inicial");
			lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDataInicial.setBounds(313, 11, 150, 23);
			filtros.add(lblDataInicial);
			{
				datemodelinicial = new UtilDateModel();
				datemodelinicial.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {

						if (datemodelinicial.isSelected()) {

							try {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String o = sdf.format(datemodelinicial
										.getValue());
								Date ini = sdf.parse(o);
								Date fim = datemodelfinal.getValue();

								if (fim == null) {
									datemodelfinal.setValue(ini);
									msg_range_data.setText("");
								} else {
									o = sdf.format(fim);
									fim = sdf.parse(o);

									if (fim.compareTo(ini) < 0) {
										msg_range_data
												.setText("Data final deve ser igual ou maior a data inicial.");
										datePickerFinal
												.getJFormattedTextField()
												.setText("");

									} else {
										msg_range_data.setText("");
									}

								}

							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							msg_range_data.setText("");
						}
					}

				});
				JDatePanelImpl datePanelinicio = new JDatePanelImpl(
						datemodelinicial);

				datePanelinicio.setBounds(72, 57, 200, 180);
				datePickerInicial = new JDatePickerImpl(datePanelinicio);
				datePickerInicial.getJFormattedTextField()
						.setHorizontalAlignment(SwingConstants.RIGHT);
				datePickerInicial.getJFormattedTextField().setFont(
						new Font("Tahoma", Font.PLAIN, 13));
				datePickerInicial.setBounds(313, 33, 150, 33);

				filtros.add(datePickerInicial);

			}
			{
				datemodelfinal = new UtilDateModel();
				datemodelfinal.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						if (datemodelfinal.isSelected()) {

							try {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String o = sdf.format(datemodelfinal.getValue());
								Date fim = sdf.parse(o);
								Date ini = datemodelinicial.getValue();

								if (ini == null) {
									datemodelinicial.setValue(fim);
								} else {
									o = sdf.format(ini);
									ini = sdf.parse(o);

									if (fim.compareTo(ini) < 0) {
										msg_range_data
												.setText("Data final deve ser igual ou maior a data inicial.");
										// datePickerFinal.getJFormattedTextField().setText("");
										datemodelfinal.setValue(null);
									} else {
										msg_range_data.setText("");
									}

								}

							} catch (Exception ex) {

							}

						} else if (datemodelfinal.getValue() != null) {
							msg_range_data.setText("");
						}
					}
				});
				JDatePanelImpl datePanelFinal = new JDatePanelImpl(
						datemodelfinal);
				{
					lblDataFinal = new JLabel("Data final");
					lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblDataFinal.setBounds(486, 11, 150, 23);
					filtros.add(lblDataFinal);
				}

				datePickerFinal = new JDatePickerImpl(datePanelFinal);
				datePickerFinal.getJFormattedTextField().setFont(
						new Font("Tahoma", Font.PLAIN, 13));
				datePickerFinal.setBounds(486, 33, 150, 33);
				filtros.add(datePickerFinal);

			}
			{
				lblDestinatrios = new JLabel("Assunto");
				lblDestinatrios.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblDestinatrios.setBounds(10, 11, 280, 23);
				filtros.add(lblDestinatrios);
			}
			{
				JBBuscar = new JButton("Pesquisar");
				JBBuscar.setBounds(665, 33, 120, 23);
				filtros.add(JBBuscar);
				JBBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBBuscar.setIcon(new ImageIcon(BuscarLembretes.class
						.getResource("/Img/Procurar.png")));
				JBBuscar.addActionListener(this);
				JBBuscar.setMnemonic(KeyEvent.VK_F);
			}

			assunto = new JTextField();
			assunto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			assunto.setBounds(10, 33, 280, 23);
			filtros.add(assunto);
			assunto.setColumns(10);

			msg_range_data = new JLabel("");
			msg_range_data.setForeground(Color.RED);
			msg_range_data.setFont(new Font("Tahoma", Font.PLAIN, 13));
			msg_range_data.setBounds(313, 61, 323, 23);
			filtros.add(msg_range_data);

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
			JBCadLemb.setIcon(new ImageIcon(BuscarLembretes.class
					.getResource("/Img/save16.png")));
			JBCadLemb.addActionListener(this);
			JBCadLemb.setMnemonic(KeyEvent.VK_C);
			JBCadLemb.setVisible(Lembretes.isCadastrar());
			buttonPane.add(JBCadLemb);
		}
		{
			JBEditLemb = new JButton("Editar");
			JBEditLemb.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBEditLemb.setIcon(new ImageIcon(BuscarLembretes.class
					.getResource("/Img/edit_add16.png")));
			JBEditLemb.addActionListener(this);
			JBEditLemb.setVisible(Lembretes.isAlterar());
			JBEditLemb.setMnemonic(KeyEvent.VK_E);
			buttonPane.add(JBEditLemb);
		}
		{
			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(BuscarLembretes.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);

			btnMarcarComoNo = new JButton("Marcar como n\u00E3o lido");
			btnMarcarComoNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int linha = tabela.getSelectedRow();
					if (linha > -1) {
						Lembrete l = modelLembretes.find(linha);
						String t = l.getTexto();
						texto.setText(t);
						l.marcarComoNaoLido();
						if (_lembreteControl.Atualizar(l) == null)
							modelLembretes.update(linha, l);
					}
				}
			});
			buttonPane.add(btnMarcarComoNo);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}

		getRootPane().setDefaultButton(JBBuscar);

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadLemb) {
			EditFormLembrete jdtcl = new EditFormLembrete(modelLembretes);
			jdtcl.setVisible(true);
			jdtcl.setLocationRelativeTo(null);
		}// final do botão cadastrar lembretes

		if (acao.getSource() == JBEditLemb) {

			int linha = tabela.getSelectedRow();
			if (linha > -1) {
				EditFormLembrete jdtcl = new EditFormLembrete(modelLembretes,
						modelLembretes.find(linha));
				jdtcl.setVisible(true);
				jdtcl.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		}// final do botão atualizar lembretes

		if (acao.getSource() == JBBuscar) {
			Pesquisar();
		}// final do botão buscar lembretes

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}// final da ação do botão

	private void Pesquisar() {
		texto.setText("");
		Date dataInicial = (Date) datePickerInicial.getModel().getValue();
		Date dataFinal = (Date) datePickerFinal.getModel().getValue();

		modelLembretes.setLinhas(_lembreteControl.BuscarTodos(dataInicial,
				dataFinal, assunto.getText()));

	}
}
