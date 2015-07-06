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

public class BuscarOrcamentos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel<Evento> _modelOrcamento;
	private OrcamentoControl _orcamentoControl;
	private JTextField nome;

	private JDatePickerImpl datePickerInicial;
	private DateModel<Date> datemodelinicial;
	private JDatePickerImpl datePickerFinal;
	private DateModel<Date> datemodelfinal;
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
		setBounds(100, 100, 686, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 178, 660, 396);
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
		filtros.setBounds(8, 11, 652, 144);
		contentPanel.add(filtros);
		filtros.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(68, 14, 71, 14);
		filtros.add(lblNome);

		nome = new JTextField();
		nome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nome.setBounds(149, 11, 391, 20);
		filtros.add(nome);
		nome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(68, 78, 71, 14);
		filtros.add(lblNewLabel);

		tiposeventos = new JComboBox(TiposEvento.values());
		tiposeventos.removeItem(TiposEvento.SELECIONE);
		tiposeventos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tiposeventos.setBounds(149, 78, 150, 20);
		filtros.add(tiposeventos);

		JLabel lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicial.setBounds(68, 42, 71, 14);
		filtros.add(lblDataInicial);
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
			datemodelinicial = new UtilDateModel();
			datemodelinicial.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					if (datemodelinicial.isSelected()) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							String o = sdf.format(datemodelinicial.getValue());
							Date ini = sdf.parse(o);
							Date fim = datemodelfinal.getValue();

							if (fim == null) {
								datemodelfinal.setValue(ini);
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
					datemodelinicial);
			datePickerInicial = new JDatePickerImpl(datePanelInicial);
			datePickerInicial.getJFormattedTextField().setFont(
					new Font("Tahoma", Font.PLAIN, 13));
			datePickerInicial.setBounds(149, 42, 150, 30);
			filtros.add(datePickerInicial);
		}

		JLabel lblDataFinal = new JLabel("Data final");
		lblDataFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataFinal.setBounds(309, 44, 71, 14);
		filtros.add(lblDataFinal);
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
									erro_datas
											.setText("Data final deve ser igual ou maior a data inicial.");
									datemodelfinal.setValue(null);
								} else {
									erro_datas.setText("");
								}

							}

						} catch (Exception ex) {

						}

					} else if (datemodelfinal.getValue() != null) {
						erro_datas.setText("");
					}
				}
			});

			JDatePanelImpl datePaneilFinal = new JDatePanelImpl(datemodelfinal);
			datePickerFinal = new JDatePickerImpl(datePaneilFinal);
			datePickerFinal.getJFormattedTextField().setFont(
					new Font("Tahoma", Font.PLAIN, 13));
			datePickerFinal.setLocation(390, 42);
			datePickerFinal.setSize(150, 30);
			filtros.add(datePickerFinal);
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
		btnNewButton.setBounds(262, 110, 118, 23);
		filtros.add(btnNewButton);

		erro_datas = new JLabel("");
		erro_datas.setForeground(Color.RED);
		erro_datas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erro_datas.setBounds(319, 78, 304, 23);
		filtros.add(erro_datas);
	}

	private void pesquisar() {
		String n = nome.getText();
		Date inicio = datemodelinicial.getValue();
		Date fim = datemodelfinal.getValue();
		TiposEvento tipoEvento = (TiposEvento) tiposeventos.getSelectedItem();

		_modelOrcamento.setLinhas(_orcamentoControl.listarTodos(n, inicio, fim,
				tipoEvento));

	}
}
