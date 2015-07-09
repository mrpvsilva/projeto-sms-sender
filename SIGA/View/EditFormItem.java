package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import jmoneyfield.JMoneyField;
import Control.ServicosControl;
import Dominio.Item;
import Dominio.TipoCobranca;
import Extra.Extras;
import Extra.Validacoes;
import TableModels.DefaultTableModel;
import javax.swing.JScrollPane;

public class EditFormItem extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JTextArea JTADescItem;
	private JMoneyField JMFVlrCusto;
	private JMoneyField JMFVlrCom;
	private JButton JBSalvServ;
	private JButton JBNovoServ;
	private ServicosControl sc;
	private JLabel lblTipoDeServio;
	private JComboBox ddlTipoItem;
	private JButton JBSair;
	private Item item;
	private DefaultTableModel<Item> model;
	private JCheckBox chckbxAtivo;
	private JComboBox tipocobranca;
	private JLabel lblItemAtivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditFormItem dialog = new EditFormItem(0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditFormItem(long l, DefaultTableModel<Item> model) {
		setResizable(false);
		setModal(true);
		sc = new ServicosControl();
		this.item = sc.buscarItem(l);
		this.model = model;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EditFormItem.class.getResource("/Img/LOGO_LOGIN_GDA.png")));

		setBounds(100, 100, 510, 373);
		setTitle("SIGA - cadastrar item");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel JLItem = new JLabel("Item");
			JLItem.setHorizontalAlignment(SwingConstants.RIGHT);
			JLItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLItem.setBounds(0, 11, 110, 14);
			contentPanel.add(JLItem);
		}
		{
			JLabel JLDesItem = new JLabel("Descri\u00E7\u00E3o");
			JLDesItem.setHorizontalAlignment(SwingConstants.RIGHT);
			JLDesItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
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

		JLabel JLValorCusto = new JLabel("Valor custo");
		JLValorCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		JLValorCusto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLValorCusto.setBounds(0, 163, 110, 14);
		contentPanel.add(JLValorCusto);

		JMFVlrCusto = new JMoneyField();
		JMFVlrCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		JMFVlrCusto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JMFVlrCusto.setBounds(120, 163, 261, 20);
		contentPanel.add(JMFVlrCusto);
		{
			JLabel lblValorComercial = new JLabel("Valor comercial");
			lblValorComercial.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorComercial.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblValorComercial.setBounds(0, 191, 110, 14);
			contentPanel.add(lblValorComercial);
		}

		JMFVlrCom = new JMoneyField();
		JMFVlrCom.setHorizontalAlignment(SwingConstants.RIGHT);
		JMFVlrCom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JMFVlrCom.setBounds(120, 191, 261, 20);
		contentPanel.add(JMFVlrCom);
		{
			lblTipoDeServio = new JLabel("Tipo de item");
			lblTipoDeServio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoDeServio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTipoDeServio.setBounds(0, 222, 110, 14);
			contentPanel.add(lblTipoDeServio);
		}
		{
			ddlTipoItem = new JComboBox(sc.DDLTipoServico());
			ddlTipoItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ddlTipoItem.setBounds(120, 222, 261, 20);
			contentPanel.add(ddlTipoItem);
		}

		chckbxAtivo = new JCheckBox("");
		chckbxAtivo.setSelected(true);
		chckbxAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxAtivo.setBounds(120, 281, 17, 23);
		contentPanel.add(chckbxAtivo);

		JLabel lblNewLabel = new JLabel("Tipo de cobran\u00E7a");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 255, 100, 14);
		contentPanel.add(lblNewLabel);

		tipocobranca = new JComboBox(TipoCobranca.values());
		tipocobranca.removeItem(TipoCobranca.TODOS);
		tipocobranca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tipocobranca.setBounds(120, 253, 261, 20);
		contentPanel.add(tipocobranca);
		{
			lblItemAtivo = new JLabel("Item ativo");
			lblItemAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblItemAtivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblItemAtivo.setBounds(33, 285, 77, 14);
			contentPanel.add(lblItemAtivo);
		}
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditFormTipoItem efti = new EditFormTipoItem(ddlTipoItem);
				efti.setLocationRelativeTo(null);
				efti.setVisible(true);
			}
		});
		button.setToolTipText("Adicionar tipo de item");
		button.setIcon(new ImageIcon(EditFormItem.class.getResource("/Img/plus.png")));
		button.setBounds(384, 222, 20, 20);
		contentPanel.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 43, 349, 105);
		contentPanel.add(scrollPane);
		
				JTADescItem = new JTextArea();
				scrollPane.setViewportView(JTADescItem);
				JTADescItem.setLineWrap(true);
				JTADescItem.setFont(new Font("Monospaced", Font.PLAIN, 13));
				JTADescItem.setWrapStyleWord(true);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvServ = new JButton("Salvar");
				JBSalvServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSalvServ.setIcon(new ImageIcon(EditFormItem.class
						.getResource("/Img/Confirmar.png")));
				JBSalvServ.setMnemonic(KeyEvent.VK_S);
				JBSalvServ.addActionListener(this);
				buttonPane.add(JBSalvServ);
				getRootPane().setDefaultButton(JBSalvServ);
			}
			{
				JBNovoServ = new JButton("Novo");
				JBNovoServ.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBNovoServ.setIcon(new ImageIcon(EditFormItem.class
						.getResource("/Img/window_new16.png")));
				JBNovoServ.addActionListener(this);
				JBNovoServ.setMnemonic(KeyEvent.VK_N);
				buttonPane.add(JBNovoServ);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
				JBSair.setIcon(new ImageIcon(EditFormItem.class
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

			if (JTFItem.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Nome do item em branco.",
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			else if (JTADescItem.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null,
						"Descrição do item em branco.", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
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
						"Selecione um tipo de item.", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
			else if(tipocobranca.getSelectedItem()==TipoCobranca.SELECIONE)
				JOptionPane.showMessageDialog(null,
						"Selecione um tipo de cobrança.", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
			else {

				if (item == null) {
					cadastrar();
				} else {
					atualizar();
				}

			}// final das validações

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
		item.setTipocobranca((TipoCobranca) tipocobranca.getSelectedItem());

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
		item.setTipocobranca((TipoCobranca) tipocobranca.getSelectedItem());
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
		tipocobranca.setSelectedItem(item.getTipocobranca());
		chckbxAtivo.setSelected(item.isAtivo());

	}
}
