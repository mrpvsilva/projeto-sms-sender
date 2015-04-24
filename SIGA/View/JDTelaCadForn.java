package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import Control.FornecedoresControl;
import Dominio.Endereco;
import Dominio.EnderecoFornecedor;
import Dominio.Fornecedor;
import Dominio.Telefone;
import Dominio.TelefoneFornecedor;
import Extra.Extras;
import Extra.Mascaras;
import Extra.Validacoes;
import TableModels.AbstractDefaultTableModel;
import TableModels.TelefoneTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class JDTelaCadForn extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFNome;
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private JTextField JTFSite;
	private JButton JBNovForn;
	private JButton JBSalvForn;
	private JTextField JFFRg;
	private JFormattedTextField JFFCnpj;
	private JComboBox JCBTpServ;
	private MaskFormatter maskCnpj;
	private JLabel JLBairro;
	private JTextField JTFBairro;
	private JLabel lblCep;
	private JFormattedTextField JFFCep;
	private JCheckBox JCBCpfMask;
	private MaskFormatter maskCep;
	private FornecedoresControl _fornecedorControl;
	private AbstractDefaultTableModel<TelefoneFornecedor> telmodel;
	private AbstractDefaultTableModel<Fornecedor> forModel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnaddtel;
	private JButton btnedittel;
	private JButton btnremovetel;
	private int id;
	private Fornecedor fornecedor;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadForn dialog = new JDTelaCadForn(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public JDTelaCadForn(Fornecedor fornecedor,
			AbstractDefaultTableModel<Fornecedor> model) throws ParseException {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaCadForn.class.getResource("/Img/CNPJ G200.png")));
		// this.id = id;
		this.forModel = model;
		this.fornecedor = fornecedor;
		_fornecedorControl = new FornecedoresControl();
		setBounds(0, -20, 512, 379);
		setTitle("SIGA  - Cadastrar fornecedor");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setHorizontalAlignment(SwingConstants.RIGHT);
		JLNome.setBounds(0, 11, 73, 14);
		contentPanel.add(JLNome);

		JLabel JLCnpj = new JLabel("Cnpj");
		JLCnpj.setHorizontalAlignment(SwingConstants.RIGHT);
		JLCnpj.setBounds(0, 36, 73, 14);
		contentPanel.add(JLCnpj);

		JLabel JLRg = new JLabel("Rg");
		JLRg.setBounds(293, 39, 35, 14);
		contentPanel.add(JLRg);

		JLabel JLTelefone1 = new JLabel("Telefones");
		JLTelefone1.setHorizontalAlignment(SwingConstants.RIGHT);
		JLTelefone1.setBounds(0, 104, 73, 14);
		contentPanel.add(JLTelefone1);

		JLabel JLEmail = new JLabel("Email");
		JLEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		JLEmail.setBounds(0, 198, 73, 14);
		contentPanel.add(JLEmail);

		JLabel JLEnd = new JLabel("Ender.");
		JLEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		JLEnd.setBounds(0, 229, 73, 14);
		contentPanel.add(JLEnd);

		JTFNome = new JTextField();
		JTFNome.setBounds(83, 11, 400, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		maskCnpj = new MaskFormatter(Mascaras.maskCnpj);
		JFFCnpj = new JFormattedTextField(maskCnpj);
		JFFCnpj.setBounds(83, 36, 118, 20);
		contentPanel.add(JFFCnpj);

		JFFRg = new JTextField();

		JFFRg.setBounds(320, 36, 163, 20);
		contentPanel.add(JFFRg);

		JLabel JLSite = new JLabel("Site");
		JLSite.setHorizontalAlignment(SwingConstants.RIGHT);
		JLSite.setBounds(0, 281, 73, 14);
		contentPanel.add(JLSite);

		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setHorizontalAlignment(SwingConstants.RIGHT);
		JLTpServ.setBounds(0, 64, 73, 14);
		contentPanel.add(JLTpServ);

		JTFEmail = new JTextField();
		JTFEmail.setBounds(83, 201, 400, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);

		JTFEnd = new JTextField();
		JTFEnd.setBounds(83, 229, 400, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);

		JTFSite = new JTextField();
		JTFSite.setBounds(83, 281, 400, 20);
		contentPanel.add(JTFSite);
		JTFSite.setColumns(10);

		JCBTpServ = new JComboBox(_fornecedorControl.DDLTipoServico());
		JCBTpServ.setBounds(83, 64, 400, 20);

		contentPanel.add(JCBTpServ);

		JLBairro = new JLabel("Bairro");
		JLBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		JLBairro.setBounds(0, 253, 73, 14);
		contentPanel.add(JLBairro);

		JTFBairro = new JTextField();
		JTFBairro.setBounds(83, 253, 118, 20);
		contentPanel.add(JTFBairro);
		JTFBairro.setColumns(10);

		lblCep = new JLabel("CEP");
		lblCep.setBounds(293, 259, 46, 14);
		contentPanel.add(lblCep);

		maskCep = new MaskFormatter(Mascaras.maskCep);
		JFFCep = new JFormattedTextField(maskCep);
		JFFCep.setBounds(365, 256, 118, 20);
		contentPanel.add(JFFCep);
		JFFCep.setColumns(10);

		JCBCpfMask = new JCheckBox("CPF");
		JCBCpfMask.setBounds(208, 35, 56, 23);
		JCBCpfMask.addActionListener(this);
		contentPanel.add(JCBCpfMask);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 103, 370, 87);
		contentPanel.add(scrollPane);

		telmodel = new TelefoneTableModel();
		table = new JTable(telmodel);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		btnaddtel = new JButton("");
		btnaddtel.setIcon(new ImageIcon(JDTelaCadForn.class
				.getResource("/Img/plus.png")));
		btnaddtel.setToolTipText("Adicionar telefone");
		btnaddtel.setBounds(463, 104, 23, 23);
		btnaddtel.addActionListener(this);
		contentPanel.add(btnaddtel);

		btnedittel = new JButton("");
		btnedittel.setToolTipText("Alterar telefone");
		btnedittel.setIcon(new ImageIcon(JDTelaCadForn.class
				.getResource("/Img/edit.png")));
		btnedittel.setBounds(463, 129, 23, 23);
		btnedittel.addActionListener(this);
		contentPanel.add(btnedittel);

		btnremovetel = new JButton("");
		btnremovetel.setToolTipText("Remover telefone");
		btnremovetel.setIcon(new ImageIcon(JDTelaCadForn.class
				.getResource("/Img/trash.png")));
		btnremovetel.setBounds(462, 153, 23, 23);
		btnremovetel.addActionListener(this);
		contentPanel.add(btnremovetel);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvForn = new JButton("Salvar");
				JBSalvForn.setIcon(new ImageIcon(JDTelaCadForn.class
						.getResource("/Img/Confirmar.png")));
				JBSalvForn.addActionListener(this);
				JBSalvForn.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvForn);
				getRootPane().setDefaultButton(JBSalvForn);
			}
			{
				JBNovForn = new JButton("Novo");
				JBNovForn.setIcon(new ImageIcon(JDTelaCadForn.class
						.getResource("/Img/window_new16.png")));
				JBNovForn.addActionListener(this);
				JBNovForn.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovForn);
			}

			JBSair = new JButton("Sair");
			JBSair.setIcon(new ImageIcon(JDTelaCadForn.class
					.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}

		preencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == btnaddtel) {
			JDTelaEditTelefone ef = new JDTelaEditTelefone(-1, null, telmodel);
			ef.setModal(true);
			ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ef.setVisible(true);
		}

		if (acao.getSource() == btnedittel) {
			int linha = table.getSelectedRow();

			if (linha > -1) {
				TelefoneFornecedor t = telmodel.find(linha);
				JDTelaEditTelefone ef = new JDTelaEditTelefone(linha, t,
						telmodel);
				ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ef.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um telefone");
			}

		}

		if (acao.getSource() == btnremovetel) {
			int linha = table.getSelectedRow();
			if (linha > -1) {
				telmodel.remove(linha);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um telefone");
			}
		}

		if (acao.getSource() == JBSalvForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja salvar o fornecedor?")) {

				if (JTFNome.getText().isEmpty()) // Valida Nome
					JOptionPane.showMessageDialog(null, "Nome em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaCpfCnpj(JFFCnpj.getText())) // Valida
																		// CpfCnpj
					JOptionPane.showMessageDialog(null, "Cpf/Cnpj inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JFFRg.getText().trim().isEmpty()) // Valida Rg
					JOptionPane.showMessageDialog(null, "Rg em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (telmodel.getRowCount() < 1)

					JOptionPane.showMessageDialog(null,
							"Adicione ao menos um telefone",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JCBTpServ.getSelectedItem().toString()
						.equals("Selecione"))
					JOptionPane.showMessageDialog(null,
							"Selecione um tipo de serviço.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFEmail.getText().trim().isEmpty()) // Valida Email
					JOptionPane.showMessageDialog(null, "Email em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFEnd.getText().trim().isEmpty()) // Valida Endereço
					JOptionPane.showMessageDialog(null, "Endereço em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFBairro.getText().trim().isEmpty()) // Valida Bairro
					JOptionPane.showMessageDialog(null, "Bairro em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaCep(JFFCep.getText())) // Valida Cep
					JOptionPane.showMessageDialog(null, "Cep inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFSite.getText().trim().isEmpty()) // Valida Site
					JOptionPane.showMessageDialog(null, "Site em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					if (fornecedor == null) {
						cadastrar();
					} else {
						atualizar();
					}

					// this.id = (int) fornecedor.getId();
					preencherCampos();

					/* Acredito que esteja todas as validações possíveis */

				}// Final da validação

			}// final da pergunta se o deseja cadastrar o fornecedor

		}// final do botão salvar

		if (acao.getSource() == JBNovForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo fornecedor?")) {

				JTFNome.setText("");
				JFFCnpj.setText("");
				JFFRg.setText("");
				JTFEmail.setText("");
				JTFEnd.setText("");
				JTFBairro.setText("");
				JFFCep.setText("");
				JTFSite.setText("");

			}// Final da pergunta se deseja cadastrar um novo fornecedor

		}// final do botão novo

		if (acao.getSource() == JCBCpfMask) {
			JFFCnpj.setValue(null);
			formaterCPFCNPJ(JCBCpfMask.isSelected());
		}

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}

	private void atualizar() {

		fornecedor.setNome(JTFNome.getText());
		fornecedor.setCpfcnpj(Extras.FormatCnpjCpf(JFFCnpj.getText()));
		fornecedor.setValorServico(new BigDecimal(5.00));

		fornecedor.setRg(JFFRg.getText());
		fornecedor.setEmail(JTFEmail.getText());
		fornecedor.setSite(JTFSite.getText());

		Endereco endereco = fornecedor.getEndereco();

		endereco.setEndereco(JTFEnd.getText());
		endereco.setBairro(JTFBairro.getText());
		endereco.setCep((Integer.parseInt(Extras.FormatCep(JFFCep.getText()))));

		fornecedor.setEndereco(endereco);

		fornecedor.setTipoServico(_fornecedorControl
				.buscarTipoServico(JCBTpServ.getSelectedItem().toString()));

		fornecedor.setTelefones(telmodel.getLinhas());

		String out = _fornecedorControl.atualizar(fornecedor);
		if (out == null) {
			carregarGrid();
			JOptionPane.showMessageDialog(null, "Fornecedor atualizado");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void cadastrar() {
		fornecedor = new Fornecedor();

		fornecedor.setNome(JTFNome.getText());
		fornecedor.setCpfcnpj(Extras.FormatCnpjCpf(JFFCnpj.getText()));
		fornecedor.setValorServico(new BigDecimal(5.00));

		fornecedor.setRg(JFFRg.getText());
		fornecedor.setEmail(JTFEmail.getText());
		fornecedor.setSite(JTFSite.getText());

		EnderecoFornecedor endereco = new EnderecoFornecedor();

		endereco.setEndereco(JTFEnd.getText());
		endereco.setBairro(JTFBairro.getText());
		endereco.setCep((Integer.parseInt(Extras.FormatCep(JFFCep.getText()))));

		fornecedor.setEndereco(endereco);

		fornecedor.setTipoServico(_fornecedorControl
				.buscarTipoServico(JCBTpServ.getSelectedItem().toString()));

		fornecedor.setTelefones(telmodel.getLinhas());

		String out = _fornecedorControl.cadastrar(fornecedor);
		if (out == null) {
			carregarGrid();
			JOptionPane.showMessageDialog(null, "Fornecedor cadastrado");
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void carregarGrid() {
		if (forModel != null)
			forModel.setLinhas(_fornecedorControl.listarTodos());
	}

	private void formaterCPFCNPJ(boolean isccpf) {
		if (isccpf) {
			JCBCpfMask.setSelected(true);
			try {
				MaskFormatter cpfm = new MaskFormatter(Mascaras.maskCpf);
				JFFCnpj.setFormatterFactory(new DefaultFormatterFactory(cpfm));

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			try {
				MaskFormatter cnpjm = new MaskFormatter(Mascaras.maskCnpj);
				JFFCnpj.setFormatterFactory(new DefaultFormatterFactory(cnpjm));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private void preencherCampos() {
		if (fornecedor == null)
			return;

		this.setTitle("SIGA - Alterar fornecedor");
		JTFNome.setText(fornecedor.getNome());
		String cpf = fornecedor.getCpfcnpj();
		formaterCPFCNPJ(cpf.length() == 11 ? true : false);
		JFFCnpj.setText(cpf);
		JFFRg.setText(fornecedor.getRg());
		JTFEmail.setText(fornecedor.getEmail());
		JTFEnd.setText(fornecedor.getEndereco().getEndereco());
		JTFBairro.setText(fornecedor.getEndereco().getBairro());
		JFFCep.setText(fornecedor.getEndereco().getCep() + "");
		JTFSite.setText(fornecedor.getSite());
		telmodel.setLinhas(fornecedor.getTelefones());
		JCBTpServ.setSelectedItem(fornecedor.getTipoServico().getNome());

	}
}
