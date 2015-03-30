package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Control.ServicosControl;
import Dominio.Item;
import Extra.Extras;
import Extra.Validacoes;
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

public class JDTelaCadServ extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JTextArea JTADescItem;
	private JMoneyField JMFVlrCusto;
	private JMoneyField JMFVlrCom;
	private JComboBox<String> JCBStatus;
	private Extras ext = new Extras();
	private JButton JBSalvServ;
	private JButton JBNovoServ;
	private ServicosControl sc;
	private JLabel lblTipoDeServio;
	private JComboBox ddltiposervico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadServ dialog = new JDTelaCadServ();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadServ() {
		sc = new ServicosControl();
		setBounds(100, 100, 450, 349);
		setTitle("SIGA - cadastro de servi\u00E7os");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel JLItem = new JLabel("Item");
			JLItem.setBounds(10, 11, 46, 14);
			contentPanel.add(JLItem);
		}
		{
			JLabel JLDesItem = new JLabel("Descri\u00E7\u00E3o");
			JLDesItem.setBounds(10, 43, 106, 14);
			contentPanel.add(JLDesItem);
		}
		{
			JTFItem = new JTextField();
			JTFItem.setBounds(101, 8, 323, 20);
			contentPanel.add(JTFItem);
			JTFItem.setColumns(10);
		}

		JTADescItem = new JTextArea();
		JTADescItem.setLineWrap(true);
		JTADescItem.setWrapStyleWord(true);
		JTADescItem.setBounds(101, 43, 323, 108);
		contentPanel.add(JTADescItem);

		JLabel JLValorCusto = new JLabel("Valor custo");
		JLValorCusto.setBounds(10, 163, 72, 14);
		contentPanel.add(JLValorCusto);

		JMFVlrCusto = new JMoneyField();
		JMFVlrCusto.setBounds(101, 160, 170, 20);
		contentPanel.add(JMFVlrCusto);
		{
			JLabel lblValorComercial = new JLabel("Valor comercial");
			lblValorComercial.setBounds(10, 191, 90, 14);
			contentPanel.add(lblValorComercial);
		}

		JMFVlrCom = new JMoneyField();
		JMFVlrCom.setBounds(101, 188, 170, 20);
		contentPanel.add(JMFVlrCom);
		{
			JLabel JLAtivo = new JLabel("Ativo");
			JLAtivo.setBounds(281, 163, 37, 14);
			contentPanel.add(JLAtivo);
		}
		{
			JCBStatus = new JComboBox<String>();
			for (String item : ext.Status()) {
				JCBStatus.addItem(item);
			}
			JCBStatus.setBounds(317, 160, 107, 20);
			contentPanel.add(JCBStatus);
		}
		{
			lblTipoDeServio = new JLabel("Tipo de servi\u00E7o");
			lblTipoDeServio.setBounds(10, 222, 90, 14);
			contentPanel.add(lblTipoDeServio);
		}
		{
			ddltiposervico = new JComboBox(sc.DDLTipoServico());
			ddltiposervico.setBounds(101, 219, 170, 20);
			contentPanel.add(ddltiposervico);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvServ = new JButton("Salvar");
				JBSalvServ.setIcon(new ImageIcon(JDTelaCadServ.class.getResource("/Img/Confirmar.png")));
				JBSalvServ.addActionListener(this);
				buttonPane.add(JBSalvServ);
				getRootPane().setDefaultButton(JBSalvServ);
			}
			{
				JBNovoServ = new JButton("Novo");
				buttonPane.add(JBNovoServ);
			}
		}
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
				else if (ddltiposervico.getSelectedItem().toString()
						.equals("Selecione"))
					JOptionPane.showMessageDialog(null,
							"Selecione um tipo de serviço.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else {

					Item item = new Item();

					item.setNome(JTFItem.getText());
					item.setDescricao(JTADescItem.getText());
					item.setAtivo(JCBStatus.getSelectedIndex() == 0 ? true
							: false);
					item.setTipoitem(sc.buscarTipoItem(ddltiposervico
							.getSelectedItem().toString()));

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
						JOptionPane.showMessageDialog(null,
								"Serviço cadastrado com sucesso", "Sucesso",
								JOptionPane.WARNING_MESSAGE);
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
				JCBStatus.setSelectedItem("ATIVO");

			}// final da confirmação

		}// final do botão novo
	}
}
