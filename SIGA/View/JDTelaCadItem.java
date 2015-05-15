package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Control.ServicosControl;
import Dominio.Item;
import Extra.Extras;
import Extra.Validacoes;
import TableModels.AbstractDefaultTableModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import jmoneyfield.JMoneyField;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class JDTelaCadItem extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JTextArea JTADescItem;
	private JMoneyField JMFVlrCusto;
	private JMoneyField JMFVlrCom;
	private Extras ext = new Extras();
	private JButton JBSalvServ;
	private JButton JBNovoServ;
	private ServicosControl sc;
	private JLabel lblTipoDeServio;
	private JComboBox ddlTipoItem;
	private JButton JBSair;
	private Item item;
	private AbstractDefaultTableModel<Item> model;
	private JCheckBox chckbxAtivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadItem dialog = new JDTelaCadItem(0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadItem(int id, AbstractDefaultTableModel<Item> model) {
		setResizable(false);
		setModal(true);
		sc = new ServicosControl();
		this.item = sc.buscarItem(id);
		this.model = model;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JDTelaCadItem.class.getResource("/Img/CNPJ G200.png")));

		setBounds(100, 100, 510, 373);
		setTitle("SIGA - cadastrar item");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel JLItem = new JLabel("Item");
			JLItem.setHorizontalAlignment(SwingConstants.RIGHT);
			JLItem.setFont(new Font("Tahoma", Font.BOLD, 13));
			JLItem.setBounds(0, 11, 110, 14);
			contentPanel.add(JLItem);
		}
		{
			JLabel JLDesItem = new JLabel("Descri\u00E7\u00E3o");
			JLDesItem.setHorizontalAlignment(SwingConstants.RIGHT);
			JLDesItem.setFont(new Font("Tahoma", Font.BOLD, 13));
			JLDesItem.setBounds(0, 43, 110, 14);
			contentPanel.add(JLDesItem);
		}
		{
			JTFItem = new JTextField();
			JTFItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JTFItem.setBounds(120, 11, 349, 20);
			contentPanel.add(JTFItem);
			JTFItem.setColumns(10);
		}

		JTADescItem = new JTextArea();
		JTADescItem.setFont(new Font("Monospaced", Font.PLAIN, 13));
		JTADescItem.setLineWrap(true);
		JTADescItem.setWrapStyleWord(true);
		JTADescItem.setBounds(120, 46, 349, 108);
		contentPanel.add(JTADescItem);

		JLabel JLValorCusto = new JLabel("Valor custo");
		JLValorCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		JLValorCusto.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLValorCusto.setBounds(0, 163, 110, 14);
		contentPanel.add(JLValorCusto);

		JMFVlrCusto = new JMoneyField();
		JMFVlrCusto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JMFVlrCusto.setBounds(120, 163, 170, 20);
		contentPanel.add(JMFVlrCusto);
		{
			JLabel lblValorComercial = new JLabel("Valor comercial");
			lblValorComercial.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorComercial.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblValorComercial.setBounds(0, 191, 110, 14);
			contentPanel.add(lblValorComercial);
		}

		JMFVlrCom = new JMoneyField();
		JMFVlrCom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JMFVlrCom.setBounds(120, 191, 170, 20);
		contentPanel.add(JMFVlrCom);
		{
			lblTipoDeServio = new JLabel("Tipo de servi\u00E7o");
			lblTipoDeServio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoDeServio.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTipoDeServio.setBounds(0, 222, 110, 14);
			contentPanel.add(lblTipoDeServio);
		}
		{
			ddlTipoItem = new JComboBox(sc.DDLTipoServico());
			ddlTipoItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ddlTipoItem.setBounds(120, 222, 170, 20);
			contentPanel.add(ddlTipoItem);
		}

		chckbxAtivo = new JCheckBox("Ativo");
		chckbxAtivo.setSelected(true);
		chckbxAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxAtivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxAtivo.setBounds(339, 163, 130, 23);
		contentPanel.add(chckbxAtivo);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvServ = new JButton("Salvar");
				JBSalvServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvServ.setIcon(new ImageIcon(JDTelaCadItem.class
						.getResource("/Img/Confirmar.png")));
				JBSalvServ.setMnemonic(KeyEvent.VK_S);
				JBSalvServ.addActionListener(this);
				buttonPane.add(JBSalvServ);
				getRootPane().setDefaultButton(JBSalvServ);
			}
			{
				JBNovoServ = new JButton("Novo");
				JBNovoServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovoServ.setIcon(new ImageIcon(JDTelaCadItem.class
						.getResource("/Img/window_new16.png")));
				JBNovoServ.addActionListener(this);
				JBNovoServ.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovoServ);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(JDTelaCadItem.class
						.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}

		preencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBSalvServ) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar o serviço?")) {

				if (JTFItem.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Nome do item em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (JTADescItem.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Descrição do item em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaVlrMoney(JMFVlrCusto.getText()))
					JOptionPane.showMessageDialog(null,
							"Valor de custo em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaVlrMoney(JMFVlrCom.getText()))
					JOptionPane.showMessageDialog(null,
							"Valor comercial em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (ddlTipoItem.getSelectedItem().toString()
						.equals("Selecione"))
					JOptionPane.showMessageDialog(null,
							"Selecione um tipo de serviço.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					if (item == null) {
						cadastrar();
					} else {
						atualizar();
					}

				}// final das validações

			}// final da confirmação

		}// final do botão salvar

		if (acao.getSource() == JBNovoServ) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo serviço?")) {

				JTFItem.setText("");
				JTADescItem.setText("");
				JMFVlrCusto.setText("");
				JMFVlrCom.setText("");
				chckbxAtivo.setSelected(true);

			}// final da confirmação

		}// final do botão novo

		if (acao.getSource() == JBSair) {
			this.dispose();
		}
	}

	private void atualizar() {

		item.setNome(JTFItem.getText());
		item.setDescricao(JTADescItem.getText());
		item.setAtivo(chckbxAtivo.isSelected());
		item.setTipoitem(sc.buscarTipoItem(ddlTipoItem.getSelectedItem()
				.toString()));

		try {
			item.setValorComercial(JMFVlrCom.getValor());
			item.setValorCusto(JMFVlrCusto.getValor());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		String out = sc.atualizar(item);

		if (out != null) {

			JOptionPane.showMessageDialog(null, out, "Alerta",
					JOptionPane.ERROR_MESSAGE);
		} else {
			carregarGrid();
			JOptionPane.showMessageDialog(null, "Item atualizado com sucesso",
					"Sucesso", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void cadastrar() {
		item = new Item();
		item.setNome(JTFItem.getText());
		item.setDescricao(JTADescItem.getText());
		item.setAtivo(chckbxAtivo.isSelected());
		item.setTipoitem(sc.buscarTipoItem(ddlTipoItem.getSelectedItem()
				.toString()));

		try {
			item.setValorComercial(JMFVlrCom.getValor());
			item.setValorCusto(JMFVlrCusto.getValor());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		String out = sc.cadastrar(item);

		if (out != null) {

			JOptionPane.showMessageDialog(null, out, "Alerta",
					JOptionPane.ERROR_MESSAGE);
		} else {
			carregarGrid();
			JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso",
					"Sucesso", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void carregarGrid() {
		if (model != null)
			model.setLinhas(sc.listarTodos());
	}

	private void preencherCampos() {
		if (item == null)
			return;
		setTitle("SIGA - atualizar item");
		JTFItem.setText(item.getNome());
		JTADescItem.setText(item.getDescricao());
		JMFVlrCusto.setValor(item.getValorCusto());
		JMFVlrCom.setValor(item.getValorComercial());
		ddlTipoItem.setSelectedItem(item.getTipoitem().getNome());
		chckbxAtivo.setSelected(item.isAtivo());

	}
}
