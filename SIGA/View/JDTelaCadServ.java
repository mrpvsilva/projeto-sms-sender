package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import Control.ServicosControl;
import Extra.Extras;
import Extra.Validacoes;
import Model.ServicosBean;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import jmoneyfield.JMoneyField;

import javax.swing.JComboBox;

public class JDTelaCadServ extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JTextArea JTADescItem;
	private JTextField JMFVlrCusto;
	private JMoneyField JMFVlrCom;
	private JComboBox<String> JCBStatus;
	private Extras ext = new Extras();
	private JButton JBSalvServ;
	private JButton JBNovoServ;
	private ServicosControl sc;

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
		setBounds(100, 100, 450, 300);
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
		JMFVlrCusto.setBounds(101, 160, 150, 20);
		contentPanel.add(JMFVlrCusto);
		{
			JLabel lblValorComercial = new JLabel("Valor comercial");
			lblValorComercial.setBounds(10, 203, 90, 14);
			contentPanel.add(lblValorComercial);
		}

		JMFVlrCom = new JMoneyField();
		JMFVlrCom.setBounds(101, 200, 150, 20);
		contentPanel.add(JMFVlrCom);
		{
			JLabel JLAtivo = new JLabel("Ativo");
			JLAtivo.setBounds(261, 162, 46, 14);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvServ = new JButton("Salvar");
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
					"Deseja cadastrar o servi�o?")) {

				if (JTFItem.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Nome do item em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (JTADescItem.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Descri��o do item em branco.",
							"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaVlrMoney(JMFVlrCusto.getText()))
					JOptionPane.showMessageDialog(null,
							"Valor de custo em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else if (!Validacoes.ValidaVlrMoney(JMFVlrCom.getText()))
					JOptionPane.showMessageDialog(null,
							"Valor comercial em branco.", "Erro ao cadastrar",
							JOptionPane.ERROR_MESSAGE);
				else {
					ServicosBean servBean = new ServicosBean();

					servBean.setNomeitem(JTFItem.getText());
					servBean.setDescricaoitem(JTADescItem.getText());
					servBean.setVlrcustoitem(new BigDecimal(Extras
							.FormatVlrMoneyBD(JMFVlrCusto.getText())));
					servBean.setVlrcomercialitem(new BigDecimal(Extras
							.FormatVlrMoneyBD(JMFVlrCom.getText())));
					servBean.setAtivoitem(JCBStatus.getSelectedIndex() == 0 ? true
							: false);

					String out = sc.Cadastrar(servBean);

					if (out != null) {
						JOptionPane.showMessageDialog(null, out, "Alerta",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Servi�o cadastrado com sucesso", "Sucesso",
								JOptionPane.WARNING_MESSAGE);
					}

				}// final das valida��es

			}// final da confirma��o

			/**
			 * Inser��o de valores feito do Paulo ServicosBean sb = new
			 * ServicosBean(); sb.setAtivoitem(JCBStatus.getSelectedIndex() == 0
			 * ? true : false); sb.setNomeitem(JTFItem.getText());
			 * sb.setDescricaoitem(JTADescItem.getText()); // tratar os
			 * caracteres presentes na mascara monetaria //
			 * sb.setVlrcustoitem(Double.parseDouble(JMFVlrCusto.getText())); //
			 * sb.setVlrcomercialitem(Double.parseDouble(JMFVlrCom.getText()));
			 * sb.setVlrcustoitem(500.0); sb.setVlrcomercialitem(1000.0);
			 * 
			 * String out = sc.Cadastrar(sb);
			 * 
			 * if (out != null) { JOptionPane.showMessageDialog(null, out,
			 * "Alerta", JOptionPane.ERROR_MESSAGE); } else {
			 * JOptionPane.showMessageDialog(null,
			 * "Servi�o cadastrado com sucesso", "Sucesso",
			 * JOptionPane.ERROR_MESSAGE); }
			 **/

		}// final do bot�o salvar

		if (acao.getSource() == JBNovoServ) {

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
					"Deseja cadastrar um novo servi�o?")) {

				JTFItem.setText("");
				JTADescItem.setText("");
				JMFVlrCusto.setText("");
				JMFVlrCom.setText("");
				JCBStatus.setSelectedItem("ATIVO");

			}// final da confirma��o

		}// final do bot�o novo
	}
}
