package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import Control.OrcamentoControl;
import Dominio.Evento;
import Dominio.TiposEvento;
import TableModels.DefaultTableModel;
import TableModels.OrcamentoTableModel;
import Util.EditFormType;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import net.sourceforge.jdatepicker.util.JDatePickerUtil;

public class BuscarOrcamentos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Evento> _modelOrcamento;
	private OrcamentoControl _orcamentoControl;
	private JTextField nome;

	private JDatePickerImpl datePickerInicial;
	private DateModel<Date> dataCadastroInicial;
	private JDatePickerImpl datePickerFinal;
	private DateModel<Date> dataCadastrofinal;

	private JDatePickerImpl datePickerRealizacaoInicial;
	private DateModel<Date> dataRealizacaoInicial;
	private JDatePickerImpl datePickerRealizacaoFinal;
	private DateModel<Date> dataRealizacaofinal;

	private JLabel erro_datas;
	private JComboBox tiposeventos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarOrcamentos dialog = new BuscarOrcamentos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
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

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 852, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 430, 828, 144);
			contentPanel.add(scrollPane);
			{
				_modelOrcamento = new OrcamentoTableModel(
						_orcamentoControl.listarTodos());

				table = new JTable(_modelOrcamento);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {

							int linha = table.getSelectedRow();
							Evento evento = _modelOrcamento.find(linha);
							EditFormOrcamento efo = new EditFormOrcamento(
									EditFormType.visualizar, evento,
									_modelOrcamento);
							efo.setLocationRelativeTo(null);
							efo.setVisible(true);

						}
					}
				});
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(table);
			}
		}

		JPanel filtros = new JPanel();
		filtros.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		filtros.setBounds(10, 11, 652, 366);
		contentPanel.add(filtros);
		filtros.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(68, 14, 71, 14);
		filtros.add(lblNome);

		nome = new JTextField();
		nome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nome.setBounds(149, 11, 452, 20);
		filtros.add(nome);
		nome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(68, 144, 71, 14);
		filtros.add(lblNewLabel);

		tiposeventos = new JComboBox(TiposEvento.values());
		tiposeventos.removeItem(TiposEvento.SELECIONE);
		tiposeventos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tiposeventos.setBounds(149, 141, 150, 20);
		filtros.add(tiposeventos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Novo");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EditFormOrcamento efo = new EditFormOrcamento(
								_modelOrcamento);
						efo.setLocationRelativeTo(null);
						efo.setVisible(true);
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);

			}
			{
				JButton cancelButton = new JButton("Alterar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = table.getSelectedRow();
						if (row > -1) {
							Evento evento = _modelOrcamento.find(row);
							EditFormOrcamento efo = new EditFormOrcamento(
									EditFormType.editar, evento,
									_modelOrcamento);
							efo.setLocationRelativeTo(null);
							efo.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null,
									"Selecione um orçamento", "Atenção",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			JButton btnGerarDocumento = new JButton("Gerar documento");
			btnGerarDocumento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnGerarDocumento.setVisible(false);
			buttonPane.add(btnGerarDocumento);
		}
		{
			dataCadastroInicial = new UtilDateModel();
			dataCadastroInicial.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					if (dataCadastroInicial.isSelected()) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							String o = sdf.format(dataCadastroInicial
									.getValue());
							Date ini = sdf.parse(o);
							Date fim = dataCadastrofinal.getValue();

							if (fim == null) {
								dataCadastrofinal.setValue(ini);
								erro_datas.setText("");
							} else {
								o = sdf.format(fim);
								fim = sdf.parse(o);

								if (fim.compareTo(ini) < 0) {
									erro_datas
											.setText("Data final deve ser igual ou maior a data inicial.");
									datePickerFinal.getJFormattedTextField()
											.setText("");

								} else {
									erro_datas.setText("");
								}

							}

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						erro_datas.setText("");
					}
				}

			});
			JDatePanelImpl datePanelInicial = new JDatePanelImpl(
					dataCadastroInicial);
		}
		{
			dataCadastrofinal = new UtilDateModel();
			dataCadastrofinal.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if (dataCadastrofinal.isSelected()) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							String o = sdf.format(dataCadastrofinal.getValue());
							Date fim = sdf.parse(o);
							Date ini = dataCadastroInicial.getValue();

							if (ini == null) {
								dataCadastroInicial.setValue(fim);
							} else {
								o = sdf.format(ini);
								ini = sdf.parse(o);

								if (fim.compareTo(ini) < 0) {
									erro_datas
											.setText("Data final deve ser igual ou maior a data inicial.");
									dataCadastrofinal.setValue(null);
								} else {
									erro_datas.setText("");
								}

							}

						} catch (Exception ex) {

						}

					} else if (dataCadastrofinal.getValue() != null) {
						erro_datas.setText("");
					}
				}
			});

			JDatePanelImpl datePaneilFinal = new JDatePanelImpl(
					dataCadastrofinal);
		}

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisar();
			}
		});
		getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(BuscarOrcamentos.class
				.getResource("/Img/Procurar.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(293, 175, 118, 23);
		filtros.add(btnNewButton);

		erro_datas = new JLabel("");
		erro_datas.setForeground(Color.RED);
		erro_datas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erro_datas.setBounds(338, 141, 304, 23);
		filtros.add(erro_datas);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Per\u00EDodo de cadastro",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(95, 39, 248, 91);
		filtros.add(panel);
		panel.setLayout(null);

		JLabel lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setBounds(10, 21, 71, 14);
		panel.add(lblDataInicial);
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		JDatePanelImpl datePanelInicial = new JDatePanelImpl(
				dataCadastroInicial);
		datePickerInicial = new JDatePickerImpl(datePanelInicial);
		datePickerInicial.setBounds(91, 21, 150, 30);
		panel.add(datePickerInicial);
		datePickerInicial.getJFormattedTextField().setFont(
				new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblDataFinal = new JLabel("Data final");
		lblDataFinal.setBounds(10, 54, 71, 14);
		panel.add(lblDataFinal);
		lblDataFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JDatePanelImpl datePaneilFinal = new JDatePanelImpl(dataCadastrofinal);
		datePickerFinal = new JDatePickerImpl(datePaneilFinal);
		datePickerFinal.setBounds(91, 52, 150, 30);
		panel.add(datePickerFinal);
		datePickerFinal.getJFormattedTextField().setFont(
				new Font("Tahoma", Font.PLAIN, 13));

		JPanel realizacao = new JPanel();
		{
			dataRealizacaoInicial = new UtilDateModel();
			JDatePanelImpl datePanelRealizacaoInicial = new JDatePanelImpl(
					dataRealizacaoInicial);
			datePickerRealizacaoInicial = new JDatePickerImpl(datePanelInicial);
			datePickerRealizacaoInicial.setBounds(91, 21, 150, 30);
			datePickerRealizacaoInicial.getJFormattedTextField().setFont(
					new Font("Tahoma", Font.PLAIN, 13));
			realizacao.add(datePickerRealizacaoInicial);
			dataRealizacaoInicial.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					if (dataRealizacaoInicial.isSelected()) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String o = sdf.format(dataRealizacaoInicial.getValue());
							Date ini = sdf.parse(o);
							Date fim = dataRealizacaofinal.getValue();

							if (fim == null) {
								dataRealizacaofinal.setValue(ini);
								erro_datas.setText("");
							} else {
								o = sdf.format(fim);
								fim = sdf.parse(o);

								if (fim.compareTo(ini) < 0) {
									erro_datas
											.setText("Data final deve ser igual ou maior a data inicial.");
									datePickerRealizacaoFinal.getJFormattedTextField()
											.setText("");

								} else {
									erro_datas.setText("");
								}

							}

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						erro_datas.setText("");
					}
				}

			});

			dataRealizacaofinal = new UtilDateModel();
			JDatePanelImpl datePanelRealizacaoFinal = new JDatePanelImpl(dataRealizacaofinal);
			datePickerRealizacaoFinal = new JDatePickerImpl(datePanelRealizacaoFinal);
			datePickerRealizacaoFinal.setBounds(91, 52, 150, 30);
			datePickerRealizacaoFinal.getJFormattedTextField().setFont(
					new Font("Tahoma", Font.PLAIN, 13));			
			dataRealizacaofinal.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if (dataRealizacaofinal.isSelected()) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							String o = sdf.format(dataRealizacaofinal
									.getValue());
							Date fim = sdf.parse(o);
							Date ini = dataRealizacaoInicial.getValue();

							if (ini == null) {
								dataRealizacaoInicial.setValue(fim);
							} else {
								o = sdf.format(ini);
								ini = sdf.parse(o);

								if (fim.compareTo(ini) < 0) {
									erro_datas
											.setText("Data final deve ser igual ou maior a data inicial.");
									dataRealizacaofinal.setValue(null);
								} else {
									erro_datas.setText("");
								}

							}

						} catch (Exception ex) {

						}

					} else if (dataRealizacaofinal.getValue() != null) {
						erro_datas.setText("");
					}
				}
			});

		}
		
		realizacao.add(datePickerRealizacaoFinal);
		realizacao.setLayout(null);
		realizacao.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Per\u00EDodo de realiza\u00E7ao evento", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		realizacao.setBounds(353, 39, 248, 91);
		filtros.add(realizacao);

		JLabel label = new JLabel("Data final");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(10, 54, 71, 14);
		realizacao.add(label);

		JLabel label_1 = new JLabel("Data inicial");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(10, 21, 71, 14);
		realizacao.add(label_1);
	}

	private void pesquisar() {
		String n = nome.getText();
		Date inicio = dataCadastroInicial.getValue();
		Date fim = dataCadastrofinal.getValue();
		TiposEvento tipoEvento = (TiposEvento) tiposeventos.getSelectedItem();

		_modelOrcamento.setLinhas(_orcamentoControl.listarTodos(n, inicio, fim,
				tipoEvento));

	}
}
