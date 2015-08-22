package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Control.ClientesControl;
import Dominio.Cliente;
import Dominio.ClienteEvento;
import Dominio.Endereco;
import Dominio.Evento;
import Dominio.Telefone;
import Dominio.TelefoneCliente;
import Extra.Extras;
import Extra.Mascaras;
import Extra.Validacoes;
import TableModels.DefaultTableModel;
import TableModels.TelefoneTableModel;
import Util.Validate;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class EditFormCliente extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton salvarSair;
	private JTextField nomeCompleto;
	private JTextField nomeResponsavel;
	private JTextField rg;
	private JTextField endereco;
	private JFormattedTextField cpfcnpj;
	private JButton sair;
	private JTextField cep;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField complemento;
	private JPanel aba_telefones;
	private JTable table;
	private DefaultTableModel<Telefone> modeltelefone;
	private JTextField email;
	private Cliente cliente;
	private ClientesControl controller;
	private boolean editando;
	private JTextField nomeguerra;
	private JFormattedTextField datanascimento;
	private JLabel msg_erro_dado_pessoais;
	private JLabel msg_erro_endereco;
	private JLabel msg_erro_telefones;
	private JButton add_telefone;
	private JTabbedPane tabbedPane;
	private JButton remove_telefone;
	private JButton edit_telefone;
	private DefaultTableModel<Cliente> _modelCliente;
	private DefaultTableModel<ClienteEvento> _modelClienteEvento;
	private Evento _evento;
	private JCheckBox chckbxCnpj;
	private JButton salvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormCliente dialog = new EditFormCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Contrutor chamada para o cadastro do cliente */
	public EditFormCliente() throws ParseException {
		cliente = new Cliente();
		start();
	}

	/** Contrutor usado pela tela de principal e de busca de clientes */
	public EditFormCliente(String cpfcnpjcliente, boolean isCNPJ)
			throws ParseException {
		cliente = new Cliente();
		start();
		if (isCNPJ) {
			try {
				cpfcnpj.setFormatterFactory(new DefaultFormatterFactory(
						new MaskFormatter(Mascaras.maskCnpj)));
				cpfcnpj.setText(cpfcnpjcliente);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			cpfcnpj.setValue(cpfcnpjcliente);
		}
		cpfcnpj.setEnabled(false);
		chckbxCnpj.setSelected(isCNPJ);
		chckbxCnpj.setEnabled(false);
	}

	/** Contrutor usado pela tela de adicionar cliente ao evento */
	public EditFormCliente(Evento evento,
			DefaultTableModel<ClienteEvento> modelCliente)
			throws ParseException {
		cliente = new Cliente();
		_modelClienteEvento = modelCliente;
		_evento = evento;

		start();
	}

	/** Construtor chamado para edição e visualização do cliente */
	public EditFormCliente(boolean editando, Cliente cliente)
			throws ParseException {
		this.editando = editando;
		this.cliente = cliente;

		start();
		modeltelefone.setLinhas(cliente.getTelefones());
		carregarCampos();
		if (!editando)
			visualizando();
	}

	public void start() throws ParseException {
		modeltelefone = new TelefoneTableModel();
		controller = new ClientesControl();
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EditFormCliente.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 616, 574);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel dadospessoais_pane = new JPanel();
		dadospessoais_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		dadospessoais_pane.setBounds(10, 11, 575, 228);
		contentPanel.add(dadospessoais_pane);
		dadospessoais_pane.setLayout(null);

		JLabel JLNome = new JLabel("Nome completo");
		JLNome.setHorizontalAlignment(SwingConstants.RIGHT);
		JLNome.setBounds(10, 28, 96, 15);
		dadospessoais_pane.add(JLNome);
		JLNome.setFont(new Font("Tahoma", Font.PLAIN, 13));

		nomeCompleto = new JTextField();
		nomeCompleto.setBounds(116, 28, 428, 20);
		dadospessoais_pane.add(nomeCompleto);
		nomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeCompleto.setColumns(10);

		JLabel JLResponsavel = new JLabel("Respons\u00E1vel");
		JLResponsavel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLResponsavel.setBounds(10, 56, 96, 15);
		dadospessoais_pane.add(JLResponsavel);
		JLResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		nomeResponsavel = new JTextField();
		nomeResponsavel.setBounds(116, 56, 428, 20);
		dadospessoais_pane.add(nomeResponsavel);
		nomeResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeResponsavel.setColumns(10);
		rg = new JFormattedTextField();
		rg.setBounds(116, 150, 168, 20);
		dadospessoais_pane.add(rg);
		rg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rg.setColumns(10);

		JLabel JLRg = new JLabel("RG");
		JLRg.setHorizontalAlignment(SwingConstants.RIGHT);
		JLRg.setBounds(10, 150, 96, 15);
		dadospessoais_pane.add(JLRg);
		JLRg.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel JLCpf = new JLabel("CPF/CNPJ");
		JLCpf.setBounds(47, 115, 56, 15);
		dadospessoais_pane.add(JLCpf);
		JLCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cpfcnpj = new JFormattedTextField(new DefaultFormatterFactory(
				new MaskFormatter(Mascaras.maskCpf)));
		cpfcnpj.setBounds(113, 113, 171, 20);
		dadospessoais_pane.add(cpfcnpj);
		cpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(288, 181, 62, 15);
		dadospessoais_pane.add(lblEmail);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setColumns(10);
		email.setBounds(360, 182, 184, 20);
		dadospessoais_pane.add(email);

		JLabel lblNomeDeGuerra = new JLabel("Nome de guerra");
		lblNomeDeGuerra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDeGuerra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeDeGuerra.setBounds(10, 87, 96, 15);
		dadospessoais_pane.add(lblNomeDeGuerra);

		nomeguerra = new JTextField();
		nomeguerra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeguerra.setColumns(10);
		nomeguerra.setBounds(116, 85, 428, 20);
		dadospessoais_pane.add(nomeguerra);

		JLabel data_nasc = new JLabel("Data de nasc.");
		data_nasc.setHorizontalAlignment(SwingConstants.RIGHT);
		data_nasc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		data_nasc.setBounds(10, 181, 96, 15);
		dadospessoais_pane.add(data_nasc);

		datanascimento = new JFormattedTextField(
				new MaskFormatter("##/##/####"));
		datanascimento.setValue(null);
		datanascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		datanascimento.setColumns(10);
		datanascimento.setBounds(116, 181, 168, 20);
		dadospessoais_pane.add(datanascimento);

		msg_erro_dado_pessoais = new JLabel("");
		msg_erro_dado_pessoais.setForeground(Color.RED);
		msg_erro_dado_pessoais.setBounds(116, 173, 428, 14);
		dadospessoais_pane.add(msg_erro_dado_pessoais);

		chckbxCnpj = new JCheckBox("CNPJ");
		chckbxCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cnpj = chckbxCnpj.isSelected();
				String t = cpfcnpj.getText().replace(".", "").replace("-", "")
						.replace("/", "");
				cpfcnpj.setValue(null);
				if (cnpj) {
					try {
						cpfcnpj.setFormatterFactory(new DefaultFormatterFactory(
								new MaskFormatter(Mascaras.maskCnpj)));
						cpfcnpj.setText(t);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					try {
						cpfcnpj.setFormatterFactory(new DefaultFormatterFactory(
								new MaskFormatter(Mascaras.maskCpf)));
						cpfcnpj.setText(t);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		chckbxCnpj.setBounds(313, 112, 97, 23);
		dadospessoais_pane.add(chckbxCnpj);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(10, 250, 575, 253);
		contentPanel.add(tabbedPane);

		JPanel aba_endereco = new JPanel();
		tabbedPane.addTab("Endere\u00E7o", null, aba_endereco, null);
		aba_endereco.setLayout(null);
		JLabel JLEnd = new JLabel("Endere\u00E7o");
		JLEnd.setBounds(0, 24, 95, 15);
		aba_endereco.add(JLEnd);
		JLEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		JLEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));

		endereco = new JTextField();
		endereco.setBounds(99, 21, 391, 20);
		aba_endereco.add(endereco);
		endereco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		endereco.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(0, 54, 95, 15);
		aba_endereco.add(lblCep);
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cep = new JTextField();
		cep.setBounds(100, 48, 146, 20);
		aba_endereco.add(cep);
		cep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cep.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(261, 51, 62, 15);
		aba_endereco.add(lblBairro);
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));

		bairro = new JTextField();
		bairro.setBounds(334, 49, 156, 20);
		aba_endereco.add(bairro);
		bairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bairro.setColumns(10);

		cidade = new JTextField();
		cidade.setBounds(99, 79, 146, 20);
		aba_endereco.add(cidade);
		cidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cidade.setColumns(10);

		JLabel lblN = new JLabel("Cidade");
		lblN.setBounds(0, 81, 95, 15);
		aba_endereco.add(lblN);
		lblN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 13));

		complemento = new JTextField();
		complemento.setBounds(101, 106, 391, 20);
		aba_endereco.add(complemento);
		complemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		complemento.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(0, 111, 95, 15);
		aba_endereco.add(lblComplemento);
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));

		msg_erro_endereco = new JLabel("");
		msg_erro_endereco.setForeground(Color.RED);
		msg_erro_endereco.setBounds(95, 181, 428, 14);
		aba_endereco.add(msg_erro_endereco);

		aba_telefones = new JPanel();
		tabbedPane.addTab("Telefones", null, aba_telefones, null);
		aba_telefones.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 534, 165);
		aba_telefones.add(scrollPane);

		table = new JTable(modeltelefone);
		scrollPane.setViewportView(table);

		add_telefone = new JButton("");
		add_telefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditFormTelefone ef = new EditFormTelefone(-1,
						new TelefoneCliente(), modeltelefone);
				ef.setModal(true);
				ef.setLocationRelativeTo(null);
				ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ef.setVisible(true);
			}
		});
		add_telefone.setIcon(new ImageIcon(EditFormCliente.class
				.getResource("/Img/plus.png")));
		add_telefone.setToolTipText("Adicionar telefone");
		add_telefone.setBounds(10, 191, 23, 23);
		aba_telefones.add(add_telefone);

		edit_telefone = new JButton("");
		edit_telefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();
				if (linha > -1) {
					Telefone t = modeltelefone.find(linha);
					EditFormTelefone ef = new EditFormTelefone(linha, t,
							modeltelefone);
					ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ef.setVisible(true);
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um telefone");
				}
			}
		});
		edit_telefone.setIcon(new ImageIcon(EditFormCliente.class
				.getResource("/Img/edit.png")));
		edit_telefone.setToolTipText("Alterar telefone");
		edit_telefone.setBounds(35, 191, 23, 23);
		aba_telefones.add(edit_telefone);

		remove_telefone = new JButton("");
		remove_telefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					modeltelefone.remove(linha);
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um telefone");
				}
			}
		});
		remove_telefone.setIcon(new ImageIcon(EditFormCliente.class
				.getResource("/Img/trash.png")));
		remove_telefone.setToolTipText("Remover telefone");
		remove_telefone.setBounds(60, 191, 23, 23);
		aba_telefones.add(remove_telefone);

		msg_erro_telefones = new JLabel("");
		msg_erro_telefones.setForeground(Color.RED);
		msg_erro_telefones.setBounds(116, 200, 428, 14);
		aba_telefones.add(msg_erro_telefones);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salvarSair = new JButton("Salvar e sair");
				salvarSair.addActionListener(this);
				salvar = new JButton("Salvar");
				salvar.addActionListener(this);
				buttonPane.add(salvar);
				salvarSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				buttonPane.add(salvarSair);
				salvarSair.setMnemonic(KeyEvent.VK_S);
				getRootPane().setDefaultButton(salvarSair);
			}

			sair = new JButton("Sair");
			sair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			sair.addActionListener(this);
			sair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(sair);
		}
	}

	private void visualizando() {

		boolean enabled = editando;
		nomeCompleto.setEditable(enabled);
		nomeResponsavel.setEditable(enabled);
		nomeguerra.setEditable(enabled);
		rg.setEditable(enabled);
		cpfcnpj.setEditable(enabled);
		chckbxCnpj.setEnabled(false);
		email.setEditable(enabled);
		datanascimento.setEditable(enabled);
		endereco.setEditable(enabled);
		cep.setEditable(enabled);
		bairro.setEditable(enabled);
		cidade.setEditable(enabled);
		complemento.setEditable(enabled);

		salvarSair.setVisible(enabled);
		add_telefone.setVisible(enabled);
		edit_telefone.setVisible(enabled);
		remove_telefone.setVisible(enabled);

		getRootPane().setDefaultButton(sair);

	}

	private void carregarCampos() {

		nomeCompleto.setText(cliente.getNomeCompleto());
		nomeResponsavel.setText(cliente.getResponsavel());
		rg.setText(cliente.getRg());
		cpfcnpj.setText(cliente.getCpfCnpj());
		email.setText(cliente.getEmail());
		datanascimento.setValue(new SimpleDateFormat("dd/MM/yyyy")
				.format(cliente.getDatanascimento()));

		chckbxCnpj.setSelected(cliente.getCpfCnpj().length() == 11);
		Endereco e = cliente.getEndereco();
		endereco.setText(e.getEndereco());
		cep.setText(e.getCep() + "");
		bairro.setText(e.getBairro());
		cidade.setText(e.getCidade());
		complemento.setText(e.getComplemento());

	}

	private void getDados() {
		cliente.setNomeCompleto(nomeCompleto.getText());
		cliente.setResponsavel(nomeResponsavel.getText());
		cliente.setRg(rg.getText());
		cliente.setEmail(email.getText());
		cliente.setCpfCnpj(Extras.FormatCnpjCpf(cpfcnpj.getValue().toString()));
		cliente.setNomeGuerraMilitar(nomeguerra.getText());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = formatter.parse(datanascimento.getText());
			cliente.setDatanascimento(new Date(date.getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Endereco e = cliente.getEndereco();
		e.setEndereco(endereco.getText());
		e.setCep(cep.getText());
		e.setBairro(bairro.getText());
		e.setCidade(cidade.getText());
		e.setComplemento(complemento.getText());
		cliente.setEndereco(e);
		cliente.setTelefones(modeltelefone.getLinhas());
	}

	private void limparTela() {
		cliente = new Cliente();
		carregarCampos();

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == salvarSair) {
			getDados();
			if (controller.cadastrar(cliente)) {
				if (_modelCliente != null)
					_modelCliente.add(cliente);

				this.dispose();
			}

		}// final do botão cadastrar usuário

		if (acao.getSource() == salvar) {
			getDados();
			if (controller.cadastrar(cliente)) {
				if (_modelCliente != null)
					_modelCliente.add(cliente);

				this.dispose();
				PesquisarNovoCliente cdtcc = new PesquisarNovoCliente();
				cdtcc.setLocationRelativeTo(null);
				cdtcc.setVisible(true);
			}
		}

		if (acao.getSource() == sair) {
			this.dispose();
		}
	}// final da ação do botão
}
