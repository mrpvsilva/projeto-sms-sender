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
import javax.swing.text.MaskFormatter;

import Control.ClientesControl;
import Dominio.Cliente;
import Dominio.Endereco;
import Dominio.Telefone;
import Dominio.TelefoneCliente;
import Extra.Extras;
import Extra.Mascaras;
import Extra.Validacoes;
import TableModels.DefaultTableModel;
import TableModels.TelefoneTableModel;
import Util.Validate;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class JDTelaFormCliente extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton salvar;
	private JButton JBNovoCad;
	private JTextField nomeCompleto;
	private JTextField nomeResponsavel;
	private JTextField rg;
	private MaskFormatter maskCpf;
	private JTextField endereco;
	private JFormattedTextField cpf;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaFormCliente dialog = new JDTelaFormCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Contrutor chamada para o cadastro do cliente */
	public JDTelaFormCliente() throws ParseException {
		cliente = new Cliente();
		modeltelefone = new TelefoneTableModel();
		start();
	}

	/** Construtor chamado para edição e visualização do cliente */
	public JDTelaFormCliente(boolean editando, Cliente cliente)
			throws ParseException {
		this.editando = editando;
		this.cliente = cliente;
		modeltelefone = new TelefoneTableModel(cliente.getTelefones());
		start();
		carregarCampos();
		if (!editando)
			visualizando();
	}

	public void start() throws ParseException {
		controller = new ClientesControl();
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaFormCliente.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 616, 574);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		maskCpf = new MaskFormatter(Mascaras.maskCpf);
		new MaskFormatter(Mascaras.mask9digi);

		JPanel dadospessoais_pane = new JPanel();
		dadospessoais_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		dadospessoais_pane.setBounds(10, 26, 575, 198);
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

		JLabel JLResponsavel = new JLabel("Resp. financeiro");
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
		rg.setBounds(116, 116, 184, 20);
		dadospessoais_pane.add(rg);
		rg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rg.setColumns(10);

		JLabel JLRg = new JLabel("RG");
		JLRg.setHorizontalAlignment(SwingConstants.RIGHT);
		JLRg.setBounds(10, 116, 96, 15);
		dadospessoais_pane.add(JLRg);
		JLRg.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setBounds(299, 119, 46, 15);
		dadospessoais_pane.add(JLCpf);
		JLCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cpf = new JFormattedTextField(maskCpf);
		cpf.setBounds(360, 117, 184, 20);
		dadospessoais_pane.add(cpf);
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(254, 147, 96, 15);
		dadospessoais_pane.add(lblEmail);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setColumns(10);
		email.setBounds(360, 148, 184, 20);
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
		data_nasc.setBounds(10, 147, 96, 15);
		dadospessoais_pane.add(data_nasc);

		datanascimento = new JFormattedTextField(
				new MaskFormatter("##/##/####"));
		datanascimento.setValue(null);
		datanascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		datanascimento.setColumns(10);
		datanascimento.setBounds(116, 147, 184, 20);
		dadospessoais_pane.add(datanascimento);

		msg_erro_dado_pessoais = new JLabel("");
		msg_erro_dado_pessoais.setForeground(Color.RED);
		msg_erro_dado_pessoais.setBounds(116, 173, 428, 14);
		dadospessoais_pane.add(msg_erro_dado_pessoais);

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
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		add_telefone = new JButton("");
		add_telefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDTelaEditTelefone ef = new JDTelaEditTelefone(-1,
						new TelefoneCliente(), modeltelefone);
				ef.setModal(true);
				ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ef.setVisible(true);
			}
		});
		add_telefone.setIcon(new ImageIcon(JDTelaFormCliente.class
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
					JDTelaEditTelefone ef = new JDTelaEditTelefone(linha, t,
							modeltelefone);
					ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ef.setVisible(true);
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um telefone");
				}
			}
		});
		edit_telefone.setIcon(new ImageIcon(JDTelaFormCliente.class
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
		remove_telefone.setIcon(new ImageIcon(JDTelaFormCliente.class
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
				salvar = new JButton("Salvar");
				salvar.addActionListener(this);
				salvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				salvar.setIcon(new ImageIcon(JDTelaFormCliente.class
						.getResource("/Img/Confirmar.png")));
				buttonPane.add(salvar);
				salvar.setMnemonic(KeyEvent.VK_S);
				getRootPane().setDefaultButton(salvar);
			}
			{
				JBNovoCad = new JButton("Novo");
				JBNovoCad.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovoCad.setIcon(new ImageIcon(JDTelaFormCliente.class
						.getResource("/Img/window_new16.png")));
				JBNovoCad.setMnemonic(KeyEvent.VK_N);
				JBNovoCad.addActionListener(this);
				buttonPane.add(JBNovoCad);
			}

			sair = new JButton("Sair");
			sair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			sair.setIcon(new ImageIcon(JDTelaFormCliente.class
					.getResource("/Img/exit16.png")));
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
		cpf.setEditable(enabled);
		email.setEditable(enabled);
		datanascimento.setEditable(enabled);
		endereco.setEditable(enabled);
		cep.setEditable(enabled);
		bairro.setEditable(enabled);
		cidade.setEditable(enabled);
		complemento.setEditable(enabled);

		salvar.setVisible(enabled);
		JBNovoCad.setVisible(enabled);
		add_telefone.setVisible(enabled);
		edit_telefone.setVisible(enabled);
		remove_telefone.setVisible(enabled);

		getRootPane().setDefaultButton(sair);

	}

	private void carregarCampos() {

		nomeCompleto.setText(cliente.getNomeCompleto());
		nomeResponsavel.setText(cliente.getResponsavel());
		rg.setText(cliente.getRg());	
		cpf.setText(cliente.getCpfCnpj());
		email.setText(cliente.getEmail());
		datanascimento.setValue(new SimpleDateFormat("dd/MM/yyyy")
				.format(cliente.getDatanascimento()));
		Endereco e = cliente.getEndereco();
		endereco.setText(e.getEndereco());
		cep.setText(e.getCep() + "");
		bairro.setText(e.getBairro());
		cidade.setText(e.getCidade());
		complemento.setText(e.getComplemento());

	}

	@Override
	public void actionPerformed(ActionEvent acao) {
		System.out.println();

		if (acao.getSource() == salvar) {

			if (nomeCompleto.getText().length() < 1) {
				Validate.validarJTextField(nomeCompleto,
						msg_erro_dado_pessoais,
						"Campo nome completo é obrigatório.");
				return;
			} else if (rg.getText().trim().length() < 1) {
				Validate.validarJTextField(rg, msg_erro_dado_pessoais,
						"Campo RG é obrigatório.");
				return;
			} else if (cpf.getValue() == null) {

				Validate.validarJFormatTextField(cpf, msg_erro_dado_pessoais,
						"Campo CPF é obrigatório.");
				return;
			} else if (!Validacoes.ValidaCpfCnpj(cpf.getValue().toString())) {

				Validate.validarJFormatTextField(cpf, msg_erro_dado_pessoais,
						"Campo CPF é inválido.");
				return;
			} else if (datanascimento.getValue() == null) {

				Validate.validarJFormatTextField(datanascimento,
						msg_erro_dado_pessoais,
						"Campo Data de nascimento é obrigatório.");
				return;
			} else if (endereco.getText().length() < 1) {

				Validate.validarJTextField(endereco, msg_erro_endereco,
						"Campo Endereço é obrigatório.");
				return;
			} else if (cep.getText().length() < 1) {

				Validate.validarJTextField(cep, msg_erro_endereco,
						"Campo CEP é obrigatório.");
				return;
			} else if (bairro.getText().length() < 1) {

				Validate.validarJTextField(bairro, msg_erro_endereco,
						"Campo Bairro é obrigatório.");
				return;
			} else if (cidade.getText().length() < 1) {

				Validate.validarJTextField(cidade, msg_erro_endereco,
						"Campo Cidade é obrigatório.");
				return;
			} else if (modeltelefone.getLinhas().isEmpty()) {
				tabbedPane.setSelectedIndex(1);
				Validate.validarJTable(table, msg_erro_telefones,
						"Telefone é obrigatório.");
				return;
			}

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja salvar o cliente?")) {

				cliente.setNomeCompleto(nomeCompleto.getText());
				cliente.setResponsavel(nomeResponsavel.getText());
				cliente.setRg(rg.getText());
				cliente.setEmail(email.getText());
				cliente.setCpfCnpj(Extras.FormatCnpjCpf(cpf.getValue()
						.toString()));
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
				e.setCep(Integer.parseInt(cep.getText()));
				e.setBairro(bairro.getText());
				e.setCidade(cidade.getText());
				e.setComplemento(complemento.getText());
				cliente.setEndereco(e);
				cliente.setTelefones(modeltelefone.getLinhas());

				boolean sucesso = false;

				if (!editando) {
					sucesso = controller.cadastrar(cliente);
				} else {
					sucesso = controller.atualizar(cliente);
				}

				String txt = sucesso ? "Cliente salvo com sucesso"
						: "Falha ao salvar o cliente";

				JOptionPane.showMessageDialog(null, txt, "",
						sucesso ? JOptionPane.INFORMATION_MESSAGE
								: JOptionPane.WARNING_MESSAGE);

			}// final da confirmação

		}// final do botão cadastrar usuário

		if (acao.getSource() == sair) {
			this.dispose();
		}
	}// final da ação do botão
}
