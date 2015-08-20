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

public class EditFormClienteOrcamento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton salvar;
	private JTextField nomeCompleto;
	private JButton sair;
	private JPanel aba_telefones;
	private JTable table;
	private DefaultTableModel<Telefone> modeltelefone;
	private JTextField email;
	private Cliente cliente;
	private ClientesControl controller;
	private boolean editando;
	private JLabel msg_erro_dado_pessoais;
	private JLabel msg_erro_telefones;
	private JButton add_telefone;
	private JTabbedPane tabbedPane;
	private JButton remove_telefone;
	private JButton edit_telefone;
	private DefaultTableModel<Cliente> _modelCliente;
	private DefaultTableModel<ClienteEvento> _modelClienteEvento;
	private Evento _evento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormClienteOrcamento dialog = new EditFormClienteOrcamento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Contrutor chamada para o cadastro do cliente */
	public EditFormClienteOrcamento() throws ParseException {
		cliente = new Cliente();
		modeltelefone = new TelefoneTableModel();
		start();
	}

	/** Contrutor usado pela tela de principal e de busca de clientes */
	public EditFormClienteOrcamento(String cpfcnpjcliente, boolean isCNPJ)
			throws ParseException {
		cliente = new Cliente();
		start();		
	}

	/** Contrutor usado pela tela de adicionar cliente ao evento */
	public EditFormClienteOrcamento(Evento evento,
			DefaultTableModel<ClienteEvento> modelCliente)
			throws ParseException {
		cliente = new Cliente();
		_modelClienteEvento = modelCliente;
		_evento = evento;
		modeltelefone = new TelefoneTableModel();
		start();
	}

	/** Construtor chamado para edição e visualização do cliente */
	public EditFormClienteOrcamento(boolean editando, Cliente cliente)
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
				EditFormClienteOrcamento.class
						.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 616, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel dadospessoais_pane = new JPanel();
		dadospessoais_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		dadospessoais_pane.setBounds(10, 11, 575, 95);
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

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(44, 54, 62, 15);
		dadospessoais_pane.add(lblEmail);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setColumns(10);
		email.setBounds(116, 55, 260, 20);
		dadospessoais_pane.add(email);

		msg_erro_dado_pessoais = new JLabel("");
		msg_erro_dado_pessoais.setForeground(Color.RED);
		msg_erro_dado_pessoais.setBounds(116, 173, 428, 14);
		dadospessoais_pane.add(msg_erro_dado_pessoais);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(10, 117, 575, 204);
		contentPanel.add(tabbedPane);

		aba_telefones = new JPanel();
		tabbedPane.addTab("Telefones", null, aba_telefones, null);
		aba_telefones.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 534, 122);
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
		add_telefone.setIcon(new ImageIcon(EditFormClienteOrcamento.class
				.getResource("/Img/plus.png")));
		add_telefone.setToolTipText("Adicionar telefone");
		add_telefone.setBounds(10, 149, 23, 23);
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
		edit_telefone.setIcon(new ImageIcon(EditFormClienteOrcamento.class
				.getResource("/Img/edit.png")));
		edit_telefone.setToolTipText("Alterar telefone");
		edit_telefone.setBounds(35, 149, 23, 23);
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
		remove_telefone.setIcon(new ImageIcon(EditFormClienteOrcamento.class
				.getResource("/Img/trash.png")));
		remove_telefone.setToolTipText("Remover telefone");
		remove_telefone.setBounds(60, 149, 23, 23);
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
				salvar.setIcon(new ImageIcon(EditFormClienteOrcamento.class
						.getResource("/Img/Confirmar.png")));
				buttonPane.add(salvar);
				salvar.setMnemonic(KeyEvent.VK_S);
				getRootPane().setDefaultButton(salvar);
			}

			sair = new JButton("Sair");
			sair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			sair.setIcon(new ImageIcon(EditFormClienteOrcamento.class
					.getResource("/Img/exit16.png")));
			sair.addActionListener(this);
			sair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(sair);
		}
	}

	private void visualizando() {
		boolean enabled = editando;
		nomeCompleto.setEditable(enabled);
		email.setEditable(enabled);
		salvar.setVisible(enabled);
		add_telefone.setVisible(enabled);
		edit_telefone.setVisible(enabled);
		remove_telefone.setVisible(enabled);

		getRootPane().setDefaultButton(sair);

	}

	private void carregarCampos() {
		nomeCompleto.setText(cliente.getNomeCompleto());
		email.setText(cliente.getEmail());
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == salvar) {

			if (nomeCompleto.getText().length() < 1) {
				Validate.validarJTextField(nomeCompleto,
						msg_erro_dado_pessoais,
						"Campo nome completo é obrigatório.");
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

				cliente.setTelefones(modeltelefone.getLinhas());

				boolean sucesso = false;

				if (!editando) {
					sucesso = controller.cadastrar(cliente);
					if (_modelCliente != null) {
						_modelCliente.add(cliente);
					}
					if (_evento != null) {
						_modelClienteEvento.add(new ClienteEvento(_evento,
								cliente));
					}

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
