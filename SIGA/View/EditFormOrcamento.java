package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Control.OrcamentoControl;
import Dominio.Cliente;
import Dominio.Evento;
import Dominio.EventoItem;
import Dominio.Item;
import Dominio.TipoItem;
import Dominio.TiposEvento;
import TableModels.ClienteTableModel;
import TableModels.DefaultTableModel;
import TableModels.EventoItemTableModel;
import Util.DateTimePicker;
import Util.EditFormType;
import Util.JOutlookBar;
import Util.StatusEvento;

import java.awt.Font;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

public class EditFormOrcamento extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField nconvidados;
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
	private DefaultTableModel<Cliente> _modelClientes;
	private JButton addCliente;
	private List<DefaultTableModel<EventoItem>> models;

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
		_tipo = EditFormType.cadastrar;
		start();
	}

	/**
	 * Construtor para cadastro de orçamento apartir da tela busca de orçamento
	 */
	public EditFormOrcamento(DefaultTableModel<Evento> modelOrcamento) {
		_modelOrcamento = modelOrcamento;
		_evento = new Evento();
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
		_tipo = tipo;
		_modelOrcamento = modelOrcamento;
		start();
	}

	public void start() {
		models = new ArrayList<DefaultTableModel<EventoItem>>();
		setResizable(false);
		_orcamentoControl = new OrcamentoControl();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Novo or\u00E7amento");
		setModal(true);
		setBounds(100, 100, 628, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do evento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 603, 142);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			datahora = new DateTimePicker();
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
		nome.setBounds(119, 23, 474, 22);
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
			lblTipoDoEvento.setBounds(279, 60, 99, 22);
			panel.add(lblTipoDoEvento);
		}

		tipoevento = new JComboBox(TiposEvento.values());
		tipoevento.removeItem(TiposEvento.TODOS);
		tipoevento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tipoevento.setBounds(388, 60, 200, 22);
		panel.add(tipoevento);
		{
			JLabel lblNConvidados = new JLabel("N\u00BA convidados");
			lblNConvidados.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNConvidados.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNConvidados.setBounds(10, 93, 99, 22);
			panel.add(lblNConvidados);
		}
		{
			nconvidados = new JTextField();
			nconvidados.setFont(new Font("Tahoma", Font.PLAIN, 13));
			nconvidados.setBounds(119, 94, 57, 22);
			panel.add(nconvidados);
			nconvidados.setColumns(10);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 164, 603, 452);
		contentPanel.add(tabbedPane);
		{
			JPanel tab_clientes = new JPanel();
			tabbedPane.addTab("Clientes", null, tab_clientes, null);
			tab_clientes.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 578, 359);
			tab_clientes.add(scrollPane);

			_modelClientes = new ClienteTableModel();
			table_clientes = new JTable(_modelClientes);
			table_clientes.setFont(new Font("Tahoma", Font.PLAIN, 13));

			table_clientes.getColumnModel().getColumn(2).setMinWidth(0);
			table_clientes.getColumnModel().getColumn(2).setMaxWidth(0);

			table_clientes.getColumnModel().getColumn(3).setMinWidth(0);
			table_clientes.getColumnModel().getColumn(3).setMaxWidth(0);

			scrollPane.setViewportView(table_clientes);

			addCliente = new JButton("");
			addCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddClienteEvento ace = new AddClienteEvento(_modelClientes);
					ace.setLocationRelativeTo(null);
					ace.setVisible(true);
				}
			});
			addCliente.setIcon(new ImageIcon(EditFormOrcamento.class
					.getResource("/Img/plus.png")));
			addCliente.setToolTipText("Adicionar cliente ao evento");
			addCliente.setBounds(10, 375, 23, 23);
			tab_clientes.add(addCliente);
		}

		// }

		JPanel tab_servicos = new JPanel();
		tabbedPane.addTab("Servi\u00E7os", null, tab_servicos, null);
		JOutlookBar outlookBar = new JOutlookBar();
		JScrollPane tab_itens = new JScrollPane();
		for (TipoItem i : _orcamentoControl.listarTipoItens()) {
			outlookBar.addBar(i.getNome(), getPanelEventoItens(i.getItens()));
		}

		tab_itens
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tab_itens
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane.addTab("Itens", null, tab_itens, null);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout(0, 0));
		container.add(outlookBar);
		tab_itens.setViewportView(container);

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
			nconvidados.setEditable(false);
			salvar.setVisible(false);
			addCliente.setVisible(false);
		}

		carregarCampos();
	}

	private void submit() {

		_evento.setNome(nome.getText());
		_evento.setDataEvento(datahora.getDate());
		_evento.setTipo(tipoevento.getSelectedItem().toString());
		_evento.setNumConvidados(Integer.parseInt(nconvidados.getText()));
		_evento.setClientes(_modelClientes.getLinhas());

		for (DefaultTableModel<EventoItem> defaultTableModel : models) {

			for (EventoItem ei : defaultTableModel.getLinhas()) {

				if (ei.isIncluso())
					_evento.addItem(ei);

			}
		}

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
			JOptionPane.showMessageDialog(null, "Falha ao salvar o orçamento");
		}

		System.out.println(success);

	}

	private void carregarCampos() {
		if (_tipo != EditFormType.cadastrar) {

			nome.setText(_evento.getNome());
			datahora.setDate(_evento.getDataEvento());
			tipoevento.setSelectedItem(TiposEvento.valueOf(_evento.getTipo()));
			nconvidados.setText(_evento.getNumConvidados() + "");
			_modelClientes.setLinhas(_evento.getClientes());

			for (int i = 0; i < models.size(); i++) {
				for (int j = 0; j < models.get(i).getLinhas().size(); j++) {

					EventoItem ei = models.get(i).getLinhas().get(j);

					for (EventoItem e : _evento.getItens()) {

						if (ei.getItem().getId() == e.getItem().getId()) {
							e.setIncluso(true);
							models.get(i).getLinhas().set(j, e);
						}

					}

				}

			}

		}
	}

	private JPanel getPanelEventoItens(List<Item> itens) {

		List<EventoItem> eventoItens = new ArrayList<EventoItem>();

		for (Item item : itens) {
			EventoItem ei = new EventoItem(_evento, item, 1);
			eventoItens.add(ei);
		}

		DefaultTableModel<EventoItem> model = new EventoItemTableModel(
				eventoItens);

		models.add(model);
		JPanel p = new JPanel(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 560, 320);
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		p.add(scrollPane);
		return p;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelar) {
			this.dispose();
		}

	}
}
