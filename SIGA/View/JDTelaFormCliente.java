package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

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
import Extra.Mascaras;
import TableModels.AbstractDefaultTableModel;
import TableModels.TelefoneTableModel;

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
	private MaskFormatter maskRG;
	private MaskFormatter maskCpf;
	private JTextField endereco;
	private MaskFormatter maskFone;
	private JFormattedTextField cpf;
	private JButton sair;
	private JPanel endereco_pane;
	private JTextField cep;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField complemento;
	private JPanel telefones_pane;
	private JTable table;
	private AbstractDefaultTableModel<Telefone> telefones;
	private JTextField email;
	private Cliente cliente;
	private AbstractDefaultTableModel<Cliente> modelCliente;
	private ClientesControl controller;
	private boolean edit;

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

	// CONSTRUTOR CHAMADO PELA TELA PRINCIPAL DO SISTEMA
	public JDTelaFormCliente() throws ParseException {
		edit = false;
		cliente = new Cliente();
		telefones = new TelefoneTableModel();
		start();
	}

	// CONTRUTOR CHAMADO PELA TELA DE BUSCA DE CLIENTE PARA CADASTRO DO CLIENTE
	public JDTelaFormCliente(AbstractDefaultTableModel<Cliente> modelCliente)
			throws ParseException {
		edit = false;
		this.modelCliente = modelCliente;
		cliente = new Cliente();
		telefones = new TelefoneTableModel();
		start();
	}

	// CONTRUTOR CHAMADO PELA TELA DE BUSCA DE CLIENTE PARA EDIÇÃO DO CLIENTE
	public JDTelaFormCliente(AbstractDefaultTableModel<Cliente> modelCliente,
			Cliente cliente) throws ParseException {
		edit = true;
		this.modelCliente = modelCliente;
		this.cliente = cliente;
		telefones = new TelefoneTableModel(cliente.getTelefones());
		start();
		carregarCampos();
	}

	// CONTRUTOR CHAMADO PELA TELA DE BUSCA DE CLIENTE PARA VISUALIZACAO DO
	// CLIENTE
	public JDTelaFormCliente(Cliente cliente) throws ParseException {
		edit = false;
		this.cliente = cliente;
		telefones = new TelefoneTableModel(cliente.getTelefones());
		start();
		carregarCampos();
	}

	public void start() throws ParseException {
		controller = new ClientesControl();
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaFormCliente.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - cadastro de cliente");
		setBounds(100, 100, 616, 617);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		maskRG = new MaskFormatter(Mascaras.maskRg);

		maskCpf = new MaskFormatter(Mascaras.maskCpf);

		maskFone = new MaskFormatter(Mascaras.mask9digi);

		JPanel dadospessoais_pane = new JPanel();
		dadospessoais_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		dadospessoais_pane.setBounds(10, 26, 554, 157);
		contentPanel.add(dadospessoais_pane);
		dadospessoais_pane.setLayout(null);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 28, 46, 14);
		dadospessoais_pane.add(JLNome);
		JLNome.setHorizontalAlignment(SwingConstants.RIGHT);
		JLNome.setFont(new Font("Tahoma", Font.PLAIN, 13));

		nomeCompleto = new JTextField();
		nomeCompleto.setBounds(68, 25, 428, 20);
		dadospessoais_pane.add(nomeCompleto);
		nomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeCompleto.setColumns(10);

		JLabel JLResponsavel = new JLabel("Resp.");
		JLResponsavel.setBounds(10, 56, 46, 14);
		dadospessoais_pane.add(JLResponsavel);
		JLResponsavel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		nomeResponsavel = new JTextField();
		nomeResponsavel.setBounds(68, 53, 428, 20);
		dadospessoais_pane.add(nomeResponsavel);
		nomeResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeResponsavel.setColumns(10);
		rg = new JFormattedTextField(maskRG);
		rg.setBounds(68, 83, 184, 20);
		dadospessoais_pane.add(rg);
		rg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rg.setColumns(10);

		JLabel JLRg = new JLabel("RG");
		JLRg.setBounds(10, 86, 46, 14);
		dadospessoais_pane.add(JLRg);
		JLRg.setHorizontalAlignment(SwingConstants.RIGHT);
		JLRg.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel JLCpf = new JLabel("CPF");
		JLCpf.setBounds(251, 86, 46, 14);
		dadospessoais_pane.add(JLCpf);
		JLCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cpf = new JFormattedTextField(maskCpf);
		cpf.setBounds(312, 84, 184, 20);
		dadospessoais_pane.add(cpf);
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 117, 46, 14);
		dadospessoais_pane.add(lblEmail);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setColumns(10);
		email.setBounds(68, 115, 184, 20);
		dadospessoais_pane.add(email);

		// JFormattedTextField formattedTextField = new JFormattedTextField(
		// (AbstractFormatter) null);
		// formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// formattedTextField.setColumns(10);
		// formattedTextField.setBounds(68, 115, 184, 20);
		// dadospessoais.add(formattedTextField);

		// JFormattedTextField formattedTextField = new
		// JFormattedTextField((AbstractFormatter) null);
		// formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// formattedTextField.setColumns(10);
		// formattedTextField.setBounds(68, 114, 162, 20);
		// dadospessoais.add(formattedTextField);

		endereco_pane = new JPanel();
		endereco_pane.setLayout(null);
		endereco_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		endereco_pane.setBounds(10, 194, 554, 157);
		contentPanel.add(endereco_pane);

		JLabel JLEnd = new JLabel("Endere\u00E7o");
		JLEnd.setBounds(0, 27, 95, 15);
		endereco_pane.add(JLEnd);
		JLEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		JLEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));

		endereco = new JTextField();
		endereco.setBounds(105, 24, 391, 20);
		endereco_pane.add(endereco);
		endereco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		endereco.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCep.setBounds(0, 56, 95, 15);
		endereco_pane.add(lblCep);

		cep = new JTextField();
		cep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cep.setColumns(10);
		cep.setBounds(105, 53, 146, 20);
		endereco_pane.add(cep);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBairro.setBounds(265, 55, 62, 15);
		endereco_pane.add(lblBairro);

		bairro = new JTextField();
		bairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bairro.setColumns(10);
		bairro.setBounds(340, 55, 156, 20);
		endereco_pane.add(bairro);

		cidade = new JTextField();
		cidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cidade.setColumns(10);
		cidade.setBounds(105, 82, 146, 20);
		endereco_pane.add(cidade);

		JLabel lblN = new JLabel("Cidade");
		lblN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblN.setBounds(0, 85, 95, 15);
		endereco_pane.add(lblN);

		complemento = new JTextField();
		complemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		complemento.setColumns(10);
		complemento.setBounds(105, 113, 391, 20);
		endereco_pane.add(complemento);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComplemento.setBounds(0, 116, 95, 15);
		endereco_pane.add(lblComplemento);

		telefones_pane = new JPanel();
		telefones_pane.setLayout(null);
		telefones_pane.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Telefones",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		telefones_pane.setBounds(10, 362, 554, 157);
		contentPanel.add(telefones_pane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 534, 87);
		telefones_pane.add(scrollPane);

		table = new JTable(telefones);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDTelaEditTelefone ef = new JDTelaEditTelefone(-1,
						new TelefoneCliente(), telefones);
				ef.setModal(true);
				ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ef.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(JDTelaFormCliente.class
				.getResource("/Img/plus.png")));
		button.setToolTipText("Adicionar telefone");
		button.setBounds(10, 115, 23, 23);
		telefones_pane.add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();
				if (linha > -1) {
					Telefone t = telefones.find(linha);
					JDTelaEditTelefone ef = new JDTelaEditTelefone(linha, t,
							telefones);
					ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ef.setVisible(true);
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um telefone");
				}
			}
		});
		button_1.setIcon(new ImageIcon(JDTelaFormCliente.class
				.getResource("/Img/edit.png")));
		button_1.setToolTipText("Alterar telefone");
		button_1.setBounds(35, 115, 23, 23);
		telefones_pane.add(button_1);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					telefones.remove(linha);
				} else {
					JOptionPane
							.showMessageDialog(null, "Selecione um telefone");
				}
			}
		});
		button_2.setIcon(new ImageIcon(JDTelaFormCliente.class
				.getResource("/Img/trash.png")));
		button_2.setToolTipText("Remover telefone");
		button_2.setBounds(61, 115, 23, 23);
		telefones_pane.add(button_2);

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

	private void carregarCampos() {

		nomeCompleto.setText(cliente.getNomeCompleto());
		nomeResponsavel.setText(cliente.getResponsavel());
		rg.setText(cliente.getRg());
		cpf.setValue(cliente.getCpfCnpj());
		email.setText(cliente.getEmail());

		Endereco e = cliente.getEndereco();
		endereco.setText(e.getEndereco());
		cep.setText(e.getCep() + "");
		bairro.setText(e.getBairro());
		cidade.setText(e.getCidade());
		complemento.setText(e.getComplemento());

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == salvar) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja salvar o cliente?")) {

				cliente.setNomeCompleto(nomeCompleto.getText());
				cliente.setResponsavel(nomeResponsavel.getText());
				cliente.setRg(rg.getText());
				cliente.setEmail(email.getText());
				cliente.setCpfCnpj(cpf.getText());

				Endereco e = cliente.getEndereco();
				e.setEndereco(endereco.getText());
				e.setCep(Integer.parseInt(cep.getText()));
				e.setBairro(bairro.getText());
				e.setCidade(cidade.getText());
				e.setComplemento(complemento.getText());
				cliente.setEndereco(e);
				cliente.setTelefones(telefones.getLinhas());

				if (!edit)
					controller.cadastrar(cliente);
				else
					controller.atualizar(cliente);

			}// final da confirmação

		}// final do botão cadastrar usuário

		if (acao.getSource() == sair) {
			this.dispose();
		}
	}// final da ação do botão
}
