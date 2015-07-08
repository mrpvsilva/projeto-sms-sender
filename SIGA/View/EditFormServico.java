package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import Control.TipoServicoControl;
import Dominio.Servico;
import Dominio.TipoCobranca;
import TableModels.DefaultTableModel;

import java.awt.Font;

import jmoneyfield.JMoneyField;

import javax.swing.JComboBox;

public class EditFormServico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JCheckBox chkAtivo;
	private JButton JBSalvar;
	private JButton JBSair;
	private long id;
	private Servico _servico;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private DefaultTableModel<Servico> _model;
	private JButton JBNovo;
	private JMoneyField valor;
	private JComboBox tipocobranca;

	/**
	 * Construtor utilizado pela tela principal para cadastro do serviço
	 * 
	 */

	public EditFormServico() {
		start();
	}

	/**
	 * Construtor utilizado pela tela buscarservicos para cadastro do serviço
	 * 
	 * @param model
	 */

	public EditFormServico(DefaultTableModel<Servico> model) {
		_model = model;
		start();
	}

	/**
	 * Construtor utilizado pela tela buscarservicos para edição do serviço
	 * 
	 * @param model
	 * @param servico
	 */

	public EditFormServico(DefaultTableModel<Servico> model, Servico servico) {
		_model = model;
		_servico = servico;
		start();
	}

	public void start() {
		setResizable(false);
		setModal(true);
		setTitle("SIGA - cadastro de servi\u00E7o");
		setBounds(100, 100, 451, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(0, 21, 105, 14);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNome.setBounds(115, 18, 221, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);

		chkAtivo = new JCheckBox("Ativo");
		chkAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chkAtivo.setSelected(true);
		chkAtivo.setBounds(342, 17, 97, 23);
		contentPanel.add(chkAtivo);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setBounds(0, 78, 105, 14);
		contentPanel.add(lblValor);

		valor = new JMoneyField();
		valor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		valor.setBounds(115, 75, 221, 20);
		contentPanel.add(valor);
		valor.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tipo de cobran\u00E7a");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(0, 52, 105, 15);
		contentPanel.add(lblNewLabel);

		tipocobranca = new JComboBox(TipoCobranca.values());
		tipocobranca.removeItem(TipoCobranca.TODOS);
		tipocobranca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tipocobranca.setBounds(115, 46, 221, 20);
		contentPanel.add(tipocobranca);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvar = new JButton("Salvar");
				JBSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvar.setIcon(new ImageIcon(EditFormServico.class
						.getResource("/Img/Confirmar.png")));
				JBSalvar.setMnemonic(KeyEvent.VK_S);
				buttonPane.add(JBSalvar);
				JBSalvar.addActionListener(this);
				getRootPane().setDefaultButton(JBSalvar);
			}
			{

				JBNovo = new JButton("Novo");
				JBNovo.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovo.setIcon(new ImageIcon(EditFormServico.class
						.getResource("/Img/window_new16.png")));
				JBNovo.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovo);

			}
			JBSair = new JButton("Sair");
			JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JBSair.setIcon(new ImageIcon(EditFormServico.class
					.getResource("/Img/exit16.png")));
			JBSair.setMnemonic(KeyEvent.VK_Q);
			JBSair.addActionListener(this);
			buttonPane.add(JBSair);
		}

		preencherCampos();
	}

	private void preencherCampos() {
		if (_servico == null)
			return;

		// _servico = tipoServicoControl.buscarTipoServico(id);
		tfNome.setText(_servico.getNome());
		valor.setValor(_servico.getValorservico());
		chkAtivo.setSelected(_servico.isAtivo());
		tipocobranca.setSelectedItem(_servico.getTipocobranca());

	}

	private void cadastrar() throws BadLocationException {

		_servico = new Servico();
		_servico.setNome(tfNome.getText());
		_servico.setAtivo(chkAtivo.isSelected());
		_servico.setValorservico(valor.getValor());
		_servico.setTipocobranca((TipoCobranca) tipocobranca.getSelectedItem());

		String out = tipoServicoControl.cadastrar(_servico);

		if (out == null) {
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Serviço cadastrado com sucesso");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, out);
		}

	}

	private void atualizar() throws BadLocationException {

		_servico.setNome(tfNome.getText());
		_servico.setAtivo(chkAtivo.isSelected());
		_servico.setValorservico(valor.getValor());
		_servico.setTipocobranca((TipoCobranca) tipocobranca.getSelectedItem());

		String out = tipoServicoControl.atualizar(_servico);

		if (out == null) {
			carregarGrid();
			JOptionPane.showMessageDialog(null,
					"Serviço atualizado com sucesso");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, out);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSalvar) {
			if (tfNome.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo nome obrigatório.");
				return;
			}
			if(tipocobranca.getSelectedItem()==TipoCobranca.SELECIONE){
				JOptionPane.showMessageDialog(null, "Selecione um tipo de cobrança");
				return;
			}

			if (_servico == null) {
				try {
					cadastrar();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					atualizar();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource() == JBSair) {
			this.dispose();
		}
	}

	private void carregarGrid() {
		if (_model != null)
			_model.setLinhas(tipoServicoControl.listarTodos());

	}

	public static void main(String[] args) {
		try {
			EditFormServico dialog = new EditFormServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
