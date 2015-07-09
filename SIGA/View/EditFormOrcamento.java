package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Control.OrcamentoControl;
import Dominio.ClienteEvento;
import Dominio.Evento;
import Dominio.EventoItem;
import Dominio.EventoServico;
import Dominio.TipoCobranca;
import Dominio.TiposEvento;
import TableModels.ClienteEventoTableModel;
import TableModels.DefaultTableModel;
import TableModels.EventoItemTableModel;
import TableModels.EventoServicoTableModel;
import Util.DateTimePicker;
import Util.EditFormType;
import Util.StatusEvento;
import Util.Validate;

import java.awt.Font;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditFormOrcamento extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField nConvidadosCliente;
	private Evento _evento;
	private EditFormType _tipo;
	private DefaultTableModel<Evento> _modelOrcamento;
	private DateTimePicker datahora;
	private JComboBox tipoevento;
	private OrcamentoControl _orcamentoControl;
	private JButton salvar;
	private JButton aprovar;
	private JButton cancelar;

	private JTable table_clientes;
	private JTable table_itens;
	private JTable table_servicos;

	private DefaultTableModel<ClienteEvento> modelClientes;
	private DefaultTableModel<EventoItem> modelItens;
	private DefaultTableModel<EventoServico> modelServicos;
	private JButton addCliente;
	private JTextField totalEvento;

	private JLabel erro_dados;
	private JLabel erro_servicos;
	private JLabel erro_itens;
	private JLabel erro_clientes;

	private JTabbedPane tabbedPane;

	private JButton removeCliente;
	private JTextField nClientes;
	private JTextField totalConvidados;
	private JTextField totalporcliente;
	private JTextField nparcelas;
	private JTextField valorparcelaporcliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormOrcamento dialog = new EditFormOrcamento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construtor para cadastro de orçamento apartir da tela principal
	 */
	public EditFormOrcamento() {
		_evento = new Evento();

		_orcamentoControl = new OrcamentoControl();
		_orcamentoControl.buscarServicos(_evento);
		_orcamentoControl.buscarEventoItens(_evento);

		_tipo = EditFormType.cadastrar;
		start();
	}

	/**
	 * Construtor para cadastro de orçamento apartir da tela busca de orçamento
	 */
	public EditFormOrcamento(DefaultTableModel<Evento> modelOrcamento) {
		_modelOrcamento = modelOrcamento;
		_evento = new Evento();

		_orcamentoControl = new OrcamentoControl();
		_orcamentoControl.buscarServicos(_evento);
		_orcamentoControl.buscarEventoItens(_evento);

		_tipo = EditFormType.cadastrar;
		start();
	}

	/**
	 * Construtor para edição ou visualização de orçamento apartir da tela busca
	 * de orçamento
	 */
	public EditFormOrcamento(EditFormType tipo, Evento evento,
			DefaultTableModel<Evento> modelOrcamento) {
		_evento = evento;
		_orcamentoControl = new OrcamentoControl();
		_tipo = tipo;
		_modelOrcamento = modelOrcamento;
		start();
	}

	public void start() {

		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Novo or\u00E7amento");
		setModal(true);
		setBounds(100, 100, 877, 744);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do evento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(86, 11, 678, 222);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			datahora = new DateTimePicker();
			datahora.getEditor().setEditable(false);
			datahora.getEditor().setFont(new Font("Tahoma", Font.PLAIN, 13));
			datahora.setBounds(119, 60, 154, 22);
			datahora.setFormats(DateFormat.getDateTimeInstance(
					DateFormat.MEDIUM, DateFormat.SHORT));
			datahora.setTimeFormat(DateFormat.getTimeInstance(DateFormat.SHORT));
			datahora.setDate(new Date());

			panel.add(datahora);
		}

		JLabel lblNomeDoEvento = new JLabel("Nome do evento");
		lblNomeDoEvento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeDoEvento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDoEvento.setBounds(10, 23, 99, 22);
		panel.add(lblNomeDoEvento);

		nome = new JTextField();
		nome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nome.setBounds(119, 23, 513, 22);
		panel.add(nome);
		nome.setColumns(10);
		{
			JLabel lblDataEHora = new JLabel("Data e hora");
			lblDataEHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDataEHora.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataEHora.setBounds(10, 60, 99, 22);
			panel.add(lblDataEHora);
		}
		{
			JLabel lblTipoDoEvento = new JLabel("Tipo do evento");
			lblTipoDoEvento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoDoEvento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTipoDoEvento.setBounds(318, 60, 99, 22);
			panel.add(lblTipoDoEvento);
		}

		tipoevento = new JComboBox(TiposEvento.values());
		tipoevento.removeItem(TiposEvento.TODOS);
		tipoevento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tipoevento.setBounds(427, 60, 205, 22);
		panel.add(tipoevento);
		{
			JLabel lblNConvidados = new JLabel("N\u00BA convidados/cliente");
			lblNConvidados.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNConvidados.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNConvidados.setBounds(214, 93, 123, 22);
			panel.add(lblNConvidados);
		}
		{
			nConvidadosCliente = new JTextField();
			nConvidadosCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			nConvidadosCliente.setBounds(347, 93, 57, 22);
			panel.add(nConvidadosCliente);
			nConvidadosCliente.setColumns(10);
			nConvidadosCliente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent k) {
					if (!nConvidadosCliente.getText().isEmpty()) {
						calcularConvidados();
						calcularSubTotalItens();
						calcularSubTotalServicos();
						calcularTotalCliente();

					}

				}
			});
		}

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotal.setBounds(63, 130, 46, 15);
		panel.add(lblTotal);

		totalEvento = new JTextField();
		totalEvento.setHorizontalAlignment(SwingConstants.RIGHT);
		totalEvento.setEditable(false);
		totalEvento.setBounds(119, 126, 169, 20);
		panel.add(totalEvento);
		totalEvento.setColumns(10);

		erro_dados = new JLabel("");
		erro_dados.setFont(new Font("Tahoma", Font.BOLD, 13));
		erro_dados.setForeground(Color.RED);
		erro_dados.setBounds(119, 190, 513, 24);
		panel.add(erro_dados);

		JLabel lblNClientes = new JLabel("N\u00BA clientes");
		lblNClientes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNClientes.setBounds(10, 93, 99, 22);
		panel.add(lblNClientes);

		nClientes = new JTextField();
		nClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				if (!nClientes.getText().isEmpty()) {
					calcularConvidados();
					calcularSubTotalItens();
					calcularSubTotalServicos();
					calcularTotalCliente();

				}

			}
		});
		nClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nClientes.setColumns(10);
		nClientes.setBounds(119, 94, 57, 22);
		panel.add(nClientes);

		JLabel lblTotalConvidados = new JLabel("Total convidados");
		lblTotalConvidados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalConvidados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalConvidados.setBounds(466, 93, 99, 22);
		panel.add(lblTotalConvidados);

		totalConvidados = new JTextField();
		totalConvidados.setEditable(false);
		totalConvidados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		totalConvidados.setColumns(10);
		totalConvidados.setBounds(575, 94, 57, 22);
		panel.add(totalConvidados);

		JLabel lblTotalcliente = new JLabel("Total/cliente");
		lblTotalcliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalcliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalcliente.setBounds(376, 127, 76, 15);
		panel.add(lblTotalcliente);

		totalporcliente = new JTextField();
		totalporcliente.setHorizontalAlignment(SwingConstants.RIGHT);
		totalporcliente.setText("R$ 0,00");
		totalporcliente.setEditable(false);
		totalporcliente.setColumns(10);
		totalporcliente.setBounds(462, 125, 170, 20);
		panel.add(totalporcliente);

		JPanel dados_formatura = new JPanel();
		dados_formatura.setBounds(10, 153, 622, 30);
		panel.add(dados_formatura);
		dados_formatura.setLayout(null);

		nparcelas = new JTextField();
		nparcelas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				calcularParcelaPorCliente();
			}
		});
		nparcelas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nparcelas.setColumns(10);
		nparcelas.setBounds(109, 0, 57, 22);
		dados_formatura.add(nparcelas);

		JLabel lblNParcelas = new JLabel("N\u00BA parcelas");
		lblNParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNParcelas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNParcelas.setBounds(0, 0, 99, 22);
		dados_formatura.add(lblNParcelas);

		JLabel lblValorParcelacliente = new JLabel("Valor parcela/cliente");
		lblValorParcelacliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorParcelacliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorParcelacliente.setBounds(261, 4, 143, 15);
		dados_formatura.add(lblValorParcelacliente);

		valorparcelaporcliente = new JTextField();
		valorparcelaporcliente.setHorizontalAlignment(SwingConstants.RIGHT);
		valorparcelaporcliente.setText("R$ 0,00");
		valorparcelaporcliente.setEditable(false);
		valorparcelaporcliente.setColumns(10);
		valorparcelaporcliente.setBounds(414, 3, 169, 20);
		dados_formatura.add(valorparcelaporcliente);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 244, 851, 426);
		contentPanel.add(tabbedPane);
		{
			JPanel tab_clientes = new JPanel();
			tabbedPane.addTab("Clientes", null, tab_clientes, null);
			tab_clientes.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 826, 350);
			tab_clientes.add(scrollPane);

			modelClientes = new ClienteEventoTableModel();

			table_clientes = new JTable(modelClientes);
			table_clientes.getModel().addTableModelListener(
					new TableModelListener() {

						@Override
						public void tableChanged(TableModelEvent e) {
							// TODO Auto-generated method stub
							if (modelClientes.getLinhas().size() == 0) {
								erro_clientes
										.setText("Adicione ao menos um cliente");
							} else {
								erro_clientes.setText("");
							}
						}
					});

			table_clientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table_clientes.getColumnModel().getColumn(3).setMinWidth(0);
			table_clientes.getColumnModel().getColumn(3).setMaxWidth(0);

			scrollPane.setViewportView(table_clientes);

			addCliente = new JButton("");
			addCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddClienteEvento ace = new AddClienteEvento(_evento,
							modelClientes);
					ace.setLocationRelativeTo(null);
					ace.setVisible(true);

				}
			});
			addCliente.setIcon(new ImageIcon(EditFormOrcamento.class
					.getResource("/Img/plus.png")));
			addCliente.setToolTipText("Adicionar cliente");
			addCliente.setBounds(10, 372, 23, 23);
			tab_clientes.add(addCliente);

			removeCliente = new JButton("");
			removeCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int linha = table_clientes.getSelectedRow();

					if (linha > -1) {
						modelClientes.remove(linha);
					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um cliente para remover", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			removeCliente.setIcon(new ImageIcon(EditFormOrcamento.class
					.getResource("/Img/close16.png")));
			removeCliente.setToolTipText("Remover cliente");
			removeCliente.setBounds(35, 372, 23, 23);
			tab_clientes.add(removeCliente);

			erro_clientes = new JLabel("");
			erro_clientes.setForeground(Color.RED);
			erro_clientes.setFont(new Font("Tahoma", Font.BOLD, 13));
			erro_clientes.setBounds(68, 430, 520, 23);
			tab_clientes.add(erro_clientes);
		}

		JPanel tab_itens = new JPanel();
		tabbedPane.addTab("Itens", null, tab_itens, null);
		tab_itens.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 826, 350);
		tab_itens.add(scrollPane);
		modelItens = new EventoItemTableModel();
		table_itens = new JTable(modelItens);
		table_itens.getColumnModel().getColumn(3).setMinWidth(0);
		table_itens.getColumnModel().getColumn(3).setMaxWidth(0);
		table_itens.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				calcularTotal();
				if (modelItens.getLinhas().size() == 0) {
					erro_itens.setText("Adicione ao menos um item");
				} else {
					erro_itens.setText("");
				}
			}
		});

		scrollPane.setViewportView(table_itens);

		JButton addItem = new JButton("");
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddItemEvento aie = new AddItemEvento(modelItens, _evento);
				aie.setLocationRelativeTo(null);
				aie.setVisible(true);
			}
		});
		addItem.setIcon(new ImageIcon(EditFormOrcamento.class
				.getResource("/Img/plus.png")));
		addItem.setToolTipText("Adicionar item");
		addItem.setBounds(10, 372, 23, 23);
		tab_itens.add(addItem);

		JButton removeItem = new JButton("");
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table_itens.getSelectedRow();

				if (linha > -1) {
					modelItens.remove(linha);
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um item para remover", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		removeItem.setIcon(new ImageIcon(EditFormOrcamento.class
				.getResource("/Img/close16.png")));
		removeItem.setToolTipText("Remover item");
		removeItem.setBounds(35, 372, 23, 23);
		tab_itens.add(removeItem);

		erro_itens = new JLabel("");
		erro_itens.setForeground(Color.RED);
		erro_itens.setFont(new Font("Tahoma", Font.BOLD, 13));
		erro_itens.setBounds(68, 430, 520, 23);
		tab_itens.add(erro_itens);

		// }

		JPanel tab_servicos = new JPanel();
		tabbedPane.addTab("Servi\u00E7os", null, tab_servicos, null);
		tab_servicos.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 826, 350);
		tab_servicos.add(scrollPane_1);

		modelServicos = new EventoServicoTableModel();
		table_servicos = new JTable(modelServicos);
		table_servicos.getModel().addTableModelListener(
				new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						calcularTotal();
						if (modelServicos.getLinhas().size() == 0) {
							erro_servicos
									.setText("Adicione ao menos um serviço");
						} else {
							erro_servicos.setText("");
						}
					}
				});

		scrollPane_1.setViewportView(table_servicos);

		JButton removeServico = new JButton("");
		removeServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table_servicos.getSelectedRow();

				if (linha > -1) {
					modelServicos.remove(linha);
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um serviço para remover", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		removeServico.setIcon(new ImageIcon(EditFormOrcamento.class
				.getResource("/Img/close16.png")));
		removeServico.setToolTipText("Remover servi\u00E7o");
		removeServico.setBounds(35, 372, 23, 23);
		tab_servicos.add(removeServico);

		JButton addServico = new JButton("");
		addServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarServicos bs = new BuscarServicos(_evento, modelServicos);
				bs.setLocationRelativeTo(null);
				bs.setVisible(true);
			}
		});
		addServico.setIcon(new ImageIcon(EditFormOrcamento.class
				.getResource("/Img/plus.png")));
		addServico.setToolTipText("Adicionar servi\u00E7o");
		addServico.setBounds(10, 372, 23, 23);
		tab_servicos.add(addServico);

		erro_servicos = new JLabel("");
		erro_servicos.setForeground(Color.RED);
		erro_servicos.setFont(new Font("Tahoma", Font.BOLD, 13));
		erro_servicos.setBounds(68, 430, 520, 23);
		tab_servicos.add(erro_servicos);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salvar = new JButton("Salvar");
				salvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						submit();
					}
				});
				salvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				salvar.setActionCommand("OK");
				buttonPane.add(salvar);
				getRootPane().setDefaultButton(salvar);
			}
			{
				aprovar = new JButton("Aprovar");
				aprovar.setVisible(false);
				aprovar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						_evento.setStatus(StatusEvento.CONTRATO.toString());
						submit();
					}
				});
				aprovar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				aprovar.setActionCommand("Cancel");
				buttonPane.add(aprovar);
			}
			{
				cancelar = new JButton("Cancelar");
				cancelar.addActionListener(this);
				cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				buttonPane.add(cancelar);
			}
		}

		if (_tipo == EditFormType.visualizar) {
			nome.setEditable(false);
			datahora.setEditable(false);
			tipoevento.setEnabled(false);
			nConvidadosCliente.setEditable(false);
			salvar.setVisible(false);
			addCliente.setVisible(false);
			removeCliente.setVisible(false);
			addItem.setVisible(false);
			removeItem.setVisible(false);
			addServico.setVisible(false);
			removeServico.setVisible(false);
		}

		carregarCampos();
		calcularConvidados();
		calcularTotal();
	}

	private void submit() {
		if (valid()) {
			_evento.setNome(nome.getText());
			_evento.setDataEvento(datahora.getDate());
			_evento.setTipo(tipoevento.getSelectedItem().toString());
			_evento.setNumeroClientes(Integer.parseInt(nClientes.getText()));
			_evento.setNumeroParcelas(Integer.parseInt(nparcelas.getText()));
			_evento.setNumeroConvidadosCliente(Integer
					.parseInt(nConvidadosCliente.getText()));
			_evento.setClientes(modelClientes.getLinhas());
			_evento.setItens(modelItens.getLinhas());
			_evento.setServicos(modelServicos.getLinhas());

			boolean success = false;

			if (_tipo == EditFormType.cadastrar)
				success = _orcamentoControl.cadastrar(_evento);
			else
				success = _orcamentoControl.alterar(_evento);

			if (success) {

				if (_modelOrcamento != null) {
					_modelOrcamento.setLinhas(_orcamentoControl.listarTodos());
				}

				if (StatusEvento.valueOf(_evento.getStatus()) == StatusEvento.ORCAMENTO)
					JOptionPane.showMessageDialog(null,
							"Orçamento salvo com sucesso");
				else
					JOptionPane.showMessageDialog(null,
							"Orçamento aprovado com sucesso");

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null,
						"Falha ao salvar o orçamento");
			}

		}

	}

	private void calcularTotal() {
		BigDecimal total = new BigDecimal(0);

		for (EventoItem ei : modelItens.getLinhas()) {
			total = total.add(ei.getSubtotal());
		}

		for (EventoServico es : modelServicos.getLinhas()) {
			total = total.add(es.getSubTotal());
		}

		totalEvento.setText(NumberFormat.getCurrencyInstance().format(total));
		calcularTotalCliente();
	}

	private void calcularTotalCliente() {
		String c = nClientes.getText();
		String t = totalEvento.getText();

		if (!c.isEmpty() && !t.isEmpty()) {
			t = t.replace("R$", "").replace(".", "").replace(",", ".").trim();
			double tt = Double.parseDouble(t) / Double.parseDouble(c);

			totalporcliente.setText(NumberFormat.getCurrencyInstance().format(
					tt));
			calcularParcelaPorCliente();

		}

	}

	private void calcularParcelaPorCliente() {
		String t = totalporcliente.getText();
		String p = nparcelas.getText();

		if (!t.isEmpty() && !p.isEmpty()) {
			t = t.replace("R$", "").replace(".", "").replace(",", ".").trim();
			double tt = Double.parseDouble(t) / Double.parseDouble(p);

			valorparcelaporcliente.setText(NumberFormat.getCurrencyInstance()
					.format(tt));

		}

	}

	private void calcularConvidados() {
		String c = nClientes.getText();
		String cvd = nConvidadosCliente.getText();

		if ((!c.isEmpty() && !cvd.isEmpty())) {
			int cc = Integer.parseInt(c);
			int ccvd = Integer.parseInt(cvd);
			totalConvidados.setText(cc * ccvd + cc+ "");

		}

	}

	/**
	 * Calcula o subtotal dos itens baseado no nmero de clientes e no total de
	 * convidados.
	 */
	private void calcularSubTotalItens() {
		String clientes = nClientes.getText();
		String convidadosDoCliente = nConvidadosCliente.getText();

		if (!clientes.isEmpty() || !convidadosDoCliente.isEmpty()) {

			int nclientes = Integer.parseInt(clientes);
			int nconvidados = Integer.parseInt(convidadosDoCliente);

			for (EventoItem e : modelItens.getLinhas()) {
				e.atualizarSubTotal(nclientes, nconvidados);
			}

			modelItens.AtualizarModel();

		}

	}

	/**
	 * Calcula o subtotal dos servicos baseado no número de clientes e no total
	 * de convidados.
	 */
	private void calcularSubTotalServicos() {
		String clientes = nClientes.getText();
		String convidadosDoCliente = nConvidadosCliente.getText();

		if (!clientes.isEmpty() || !convidadosDoCliente.isEmpty()) {

			int nclientes = Integer.parseInt(clientes);
			int nconvidados = Integer.parseInt(convidadosDoCliente);

			for (EventoServico e : modelServicos.getLinhas()) {
				e.atualizarSubTotal(nclientes, nconvidados);
			}
			modelServicos.AtualizarModel();
		}

	}

	private void carregarCampos() {

		// if (_tipo != EditFormType.cadastrar) {
		nome.setText(_evento.getNome());
		datahora.setDate(_evento.getDataEvento());
		tipoevento.setSelectedItem(TiposEvento.valueOf(_evento.getTipo()));
		nClientes.setText(_evento.getNumeroClientes() + "");
		nConvidadosCliente.setText(_evento.getNumeroConvidadosCliente() + "");
		nparcelas.setText(_evento.getNumeroParcelas() + "");

		// totalconvidados.setText(_evento.getTotalConvidados() + "");
		modelClientes.setLinhas(_evento.getClientes());
		modelItens.setLinhas(_evento.getItens());
		modelServicos.setLinhas(_evento.getServicos());

		// } else {
		// modelItens.setLinhas(_orcamentoControl.buscarEventoItens(_evento));

		// }
	}

	private boolean valid() {

		if (nome.getText().isEmpty()) {
			nome.requestFocus();
			Validate.validarJTextField(nome, erro_dados,
					"Nome do evento é obrigatório");
			return false;
		}

		if (datahora.getDate() == null) {

			return false;
		}
		if (tipoevento.getSelectedItem() == TiposEvento.SELECIONE) {
			tipoevento.setBorder(new LineBorder(new Color(255, 0, 0)));
			tipoevento.requestFocus();
			erro_dados.setText("Selecione o tipo do evento");

			tipoevento.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent i) {

					if (tipoevento.getSelectedItem() != TiposEvento.SELECIONE) {
						erro_dados.setText("");
						tipoevento.setBorder(new LineBorder(new Color(171, 173,
								179)));
					} else {
						erro_dados.setText("Selecione o tipo do evento");
						tipoevento.setBorder(new LineBorder(
								new Color(255, 0, 0)));
					}

				}
			});

			return false;
		}
		if (nConvidadosCliente.getText().isEmpty()) {
			Validate.validarJTextField(nConvidadosCliente, erro_dados,
					"Nº de convidados é obrigatório");
			return false;
		}
		if (modelClientes.getLinhas().size() == 0) {
			tabbedPane.setSelectedIndex(0);
			erro_clientes.setText("Adicione ao menos um cliente");

			return false;
		}
		if (modelItens.getLinhas().size() == 0) {
			tabbedPane.setSelectedIndex(1);
			erro_itens.setText("Adicione ao menos um item");
			return false;
		}
		if (modelServicos.getLinhas().size() == 0) {
			tabbedPane.setSelectedIndex(2);
			erro_servicos.setText("Adicione ao menos um serviço");
			return false;
		}

		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelar) {
			this.dispose();
		}

	}
}
