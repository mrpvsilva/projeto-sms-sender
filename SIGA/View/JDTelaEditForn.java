package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import Control.FornecedoresControl;
import Dominio.Fornecedor;
import Dominio.Telefone;
import Extra.Extras;
import Extra.Validacoes;
import Model.FornecedoresBean;

public class JDTelaEditForn extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFNome;
	private JTextField JTFEmail;
	private JTextField JTFEnd;
	private JTextField JTFSite;
	private JButton JBExclForn;
	private JButton JBAtuForn;
	private JFormattedTextField JFFFone1;
	private JFormattedTextField JFFFone;
	private JFormattedTextField JFFRg;
	private JFormattedTextField JFFCnpj;
	private JComboBox<String> JCBTpServ;
	private JFormattedTextField JFFFone2;
	private JLabel JLBairro;
	private JTextField JTFBairro;
	private JLabel lblCep;
	private JTextField JFFCep;
	private JComboBox<String> JCBOperadora1;
	private JComboBox<String> JCBOperadora;
	private JComboBox<String> JCBOperadora2;
	private Extras ext = new Extras();
	private Fornecedor fornecedor;
	private int _ID;
	private FornecedoresControl _fornecedorControl = new FornecedoresControl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditForn dialog = new JDTelaEditForn(2);
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
	public JDTelaEditForn(int id) throws ParseException {

		_ID = id;

		setBounds(100, 100, 581, 300);
		setTitle("SIGA  - edi\u00E7\u00E3o de fornecedores");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel JLNome = new JLabel("Nome");
		JLNome.setBounds(10, 11, 46, 14);
		contentPanel.add(JLNome);

		JLabel JLCnpj = new JLabel("Cnpj");
		JLCnpj.setBounds(10, 36, 35, 14);
		contentPanel.add(JLCnpj);

		JLabel JLRg = new JLabel("Rg");
		JLRg.setBounds(279, 42, 35, 14);
		contentPanel.add(JLRg);

		JLabel JLTelefone1 = new JLabel("Fone");
		JLTelefone1.setBounds(10, 67, 46, 14);
		contentPanel.add(JLTelefone1);

		JLabel JLTelefone2 = new JLabel("Fone 1");
		JLTelefone2.setBounds(279, 67, 46, 14);
		contentPanel.add(JLTelefone2);

		JLabel JLTelefone3 = new JLabel("Fone 2");
		JLTelefone3.setBounds(10, 95, 46, 14);
		contentPanel.add(JLTelefone3);

		JLabel JLEmail = new JLabel("Email");
		JLEmail.setBounds(10, 120, 46, 14);
		contentPanel.add(JLEmail);

		JLabel JLEnd = new JLabel("Ender.");
		JLEnd.setBounds(10, 151, 59, 14);
		contentPanel.add(JLEnd);

		JTFNome = new JTextField();
		JTFNome.setBounds(48, 8, 438, 20);
		contentPanel.add(JTFNome);
		JTFNome.setColumns(10);

		JFFCnpj = new JFormattedTextField();
		JFFCnpj.setBounds(48, 36, 118, 20);
		contentPanel.add(JFFCnpj);

		JFFRg = new JFormattedTextField();
		JFFRg.setBounds(368, 36, 118, 20);
		contentPanel.add(JFFRg);

		JLabel JLSite = new JLabel("Site");
		JLSite.setBounds(10, 203, 46, 14);
		contentPanel.add(JLSite);

		JLabel JLTpServ = new JLabel("Tipo Servi\u00E7o");
		JLTpServ.setBounds(279, 95, 79, 14);
		contentPanel.add(JLTpServ);

		JFFFone = new JFormattedTextField();
		JFFFone.setBounds(48, 64, 118, 20);
		contentPanel.add(JFFFone);

		JFFFone1 = new JFormattedTextField();
		JFFFone1.setBounds(368, 67, 118, 20);
		contentPanel.add(JFFFone1);

		JFFFone2 = new JFormattedTextField();
		JFFFone2.setBounds(48, 92, 118, 20);
		contentPanel.add(JFFFone2);

		JTFEmail = new JTextField();
		JTFEmail.setBounds(48, 120, 438, 20);
		contentPanel.add(JTFEmail);
		JTFEmail.setColumns(10);

		JTFEnd = new JTextField();
		JTFEnd.setBounds(48, 148, 438, 20);
		contentPanel.add(JTFEnd);
		JTFEnd.setColumns(10);

		JTFSite = new JTextField();
		JTFSite.setBounds(48, 200, 438, 20);
		contentPanel.add(JTFSite);
		JTFSite.setColumns(10);

		JCBTpServ = new JComboBox<String>(_fornecedorControl.DDLTipoServico());
		JCBTpServ.setBounds(368, 92, 118, 20);

		contentPanel.add(JCBTpServ);

		JLBairro = new JLabel("Bairro");
		JLBairro.setBounds(10, 175, 46, 14);
		contentPanel.add(JLBairro);

		JTFBairro = new JTextField();
		JTFBairro.setBounds(48, 172, 118, 20);
		contentPanel.add(JTFBairro);
		JTFBairro.setColumns(10);

		lblCep = new JLabel("CEP");
		lblCep.setBounds(279, 178, 46, 14);
		contentPanel.add(lblCep);

		JFFCep = new JTextField();
		JFFCep.setBounds(368, 175, 118, 20);
		contentPanel.add(JFFCep);
		JFFCep.setColumns(10);

		JCBOperadora1 = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora1.addItem(item);
		}
		JCBOperadora1.setBounds(496, 67, 59, 20);
		contentPanel.add(JCBOperadora1);

		JCBOperadora = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora.addItem(item);
		}
		JCBOperadora.setBounds(176, 64, 59, 20);
		contentPanel.add(JCBOperadora);

		JCBOperadora2 = new JComboBox<String>();
		for (String item : ext.getOperadora()) {
			JCBOperadora2.addItem(item);
		}
		JCBOperadora2.setBounds(176, 92, 59, 20);
		contentPanel.add(JCBOperadora2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuForn = new JButton("Atualizar");
				JBAtuForn.addActionListener(this);
				buttonPane.add(JBAtuForn);
				getRootPane().setDefaultButton(JBAtuForn);
			}
			{
				JBExclForn = new JButton("Excluir");
				JBExclForn.addActionListener(this);
				buttonPane.add(JBExclForn);
			}
		}

		PreencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBAtuForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja atualizar o fornecedor?")) {

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
				else if (!Validacoes.ValidaTelefone(JFFFone.getText())) // Valida
																		// Telefone
					JOptionPane.showMessageDialog(null, "Telefone inválido.",
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
				else if (Validacoes.ValidaCep(JFFCep.getText())) // Valida Cep
					JOptionPane.showMessageDialog(null, "Cep inválido.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (JTFSite.getText().trim().isEmpty()) // Valida Site
					JOptionPane.showMessageDialog(null, "Site em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					FornecedoresBean fornBean = new FornecedoresBean();

					fornecedor.setNome(JTFNome.getText());
					fornecedor.setCpfcnpj(Extras.FormatCnpjCpf(JFFCnpj
							.getText()));

					fornecedor.setRg(JFFRg.getText());

					fornecedor
							.getTelefones()
							.get(0)
							.setDdd(Extras.FormatDDD(Extras.FormatFone(JFFFone
									.getText())));

					fornecedor
							.getTelefones()
							.get(0)
							.setNumero(
									Extras.FormatFoneBD(Extras
											.FormatFone(JFFFone.getText())));

					fornecedor
							.getTelefones()
							.get(0)
							.setOperadora(
									JCBOperadora.getSelectedItem().toString());

					fornecedor.setEmail(JTFEmail.getText());

					fornecedor.setEndereco(JTFEnd.getText());

					fornecedor.setSite(JTFSite.getText());

					fornecedor.setTiposervico(_fornecedorControl
							.BuscarTipoServico(JCBTpServ.getSelectedItem()
									.toString()));

					/* Colocar o método de cadastrar */
					String out = _fornecedorControl.Atualizar(fornecedor);
					if (out == null) {
						PreencherCampos();
						JOptionPane.showMessageDialog(null,
								"Fornecedor atualizado com sucesso");

					} else {
						JOptionPane.showMessageDialog(null, out);
					}
					/* Acredito que esteja todas as validações possíveis */

				}// final da validação

			}// final da confirmação

		}// final do botão atualizar

		if (acao.getSource() == JBExclForn) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja excluir o fornecedor?")) {

			}// final da confirmação

		}// final do botão excluir
	}

	private void PreencherCampos() {
		fornecedor = _fornecedorControl.BuscarFornecedor(_ID);
		JTFNome.setText(fornecedor.getNome());
		JFFCnpj.setText(fornecedor.getCpfcnpj());
		JFFRg.setText(fornecedor.getRg());
		JTFEmail.setText(fornecedor.getEmail());
		JTFEnd.setText(fornecedor.getEndereco());
		JTFSite.setText(fornecedor.getSite());
		List<Telefone> telefones = fornecedor.getTelefones();

		if (telefones.size() > 0) {
			JFFFone.setText("(" + telefones.get(0).getDdd() + ")"
					+ telefones.get(0).getNumero());
			for (int i = 0; i < JCBOperadora.getItemCount(); i++) {
				if (JCBOperadora.getItemAt(i).equals(
						telefones.get(0).getOperadora())) {
					JCBOperadora.setSelectedIndex(i);
				}
			}
		}

		if (telefones.size() > 1) {
			JFFFone1.setText("(" + telefones.get(1).getDdd() + ")"
					+ telefones.get(1).getNumero());
			for (int i = 0; i < JCBOperadora1.getItemCount(); i++) {
				if (JCBOperadora1.getItemAt(i).equals(
						telefones.get(2).getOperadora())) {
					JCBOperadora1.setSelectedIndex(i);
				}
			}
		}

		if (telefones.size() > 2) {
			JFFFone2.setText("(" + telefones.get(2).getDdd() + ")"
					+ telefones.get(2).getNumero());
			for (int i = 0; i < JCBOperadora2.getItemCount(); i++) {
				if (JCBOperadora2.getItemAt(i).equals(
						telefones.get(2).getOperadora())) {
					JCBOperadora2.setSelectedIndex(i);
				}
			}
		}

		for (int i = 0; i < JCBTpServ.getItemCount(); i++) {
			if (JCBTpServ.getItemAt(i).equals(
					fornecedor.getTiposervico().getNome())) {
				JCBTpServ.setSelectedIndex(i);
			}
		}

	}
}
