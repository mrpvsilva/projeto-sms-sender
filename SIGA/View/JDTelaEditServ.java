package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import Control.ServicosControl;
import Dominio.Item;
import Extra.Extras;
import Extra.Validacoes;
import Model.ServicosBean;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import jmoneyfield.JMoneyField;
import javax.swing.ImageIcon;

public class JDTelaEditServ extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JComboBox<String> JCBStatus;
	private Extras ext = new Extras();
	private JButton JBAtuServ;
	private JButton JBExcServ;
	private JMoneyField JMFVlrCom;
	private JMoneyField JMFVlrCusto;
	private JTextArea JTADescItem;
	private ServicosControl _servicoControl = new ServicosControl();
	private Item _item;
	private int _ID;
	private JButton JBSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditServ dialog = new JDTelaEditServ(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditServ(int ID) {
		_ID = ID;
		setBounds(100, 100, 450, 300);
		setTitle("SIGA - edição de servi\u00E7os");
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

		JMFVlrCusto = new JMoneyField(); // new JTextField();
		JMFVlrCusto.setBounds(101, 160, 159, 20);
		contentPanel.add(JMFVlrCusto);
		{
			JLabel lblValorComercial = new JLabel("Valor comercial");
			lblValorComercial.setBounds(10, 203, 90, 14);
			contentPanel.add(lblValorComercial);
		}

		JMFVlrCom = new JMoneyField();
		JMFVlrCom.setBounds(101, 200, 159, 20);
		contentPanel.add(JMFVlrCom);
		{
			JLabel JLAtivo = new JLabel("Ativo");
			JLAtivo.setBounds(270, 164, 34, 14);
			contentPanel.add(JLAtivo);
		}
		{
			JCBStatus = new JComboBox<String>();
			for (String item : ext.Status()) {
				JCBStatus.addItem(item);
			}
			JCBStatus.setBounds(301, 160, 107, 20);
			contentPanel.add(JCBStatus);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuServ = new JButton("Atualizar");
				JBAtuServ.setIcon(new ImageIcon(JDTelaEditServ.class.getResource("/Img/reload16.png")));
				JBAtuServ.addActionListener(this);
				JBAtuServ.setMnemonic(KeyEvent.VK_A);
				buttonPane.add(JBAtuServ);

				getRootPane().setDefaultButton(JBAtuServ);
			}
			{
				JBExcServ = new JButton("Excluir");
				JBExcServ.setIcon(new ImageIcon(JDTelaEditServ.class.getResource("/Img/close16.png")));
				JBExcServ.setMnemonic(KeyEvent.VK_X);
				buttonPane.add(JBExcServ);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setIcon(new ImageIcon(JDTelaEditServ.class.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}

		PreencherCampos();
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBAtuServ) {

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

				// COlocar aqui a atualização do serviço

				_item.setAtivo(JCBStatus.getSelectedIndex() == 0 ? true : false);
				_item.setDescricao(JTADescItem.getText());
				_item.setNome(JTFItem.getText());

				try {
					_item.setValorCusto(JMFVlrCusto.getValor());
					_item.setValorComercial(JMFVlrCom.getValor());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}

				String out = _servicoControl.atualizar(_item);

				if (out == null) {
					PreencherCampos();
					JOptionPane.showMessageDialog(null,
							"Serviço atualizado com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, out);
				}

			}// final das validações

		}// final do botão atualizar

		if (acao.getSource() == JBExcServ) {

		}// final do botão excluir
		
		if(acao.getSource() == JBSair){
			this.dispose();
		}
	}

	private void PreencherCampos() {
		_item = _servicoControl.buscarItem(_ID);
		JTFItem.setText(_item.getNome());
		JTADescItem.setText(_item.getDescricao());
		JMFVlrCusto.setValor(_item.getValorCusto());
		JMFVlrCom.setValor(_item.getValorComercial());

		if (_item.isAtivo())
			JCBStatus.setSelectedIndex(0);
		else
			JCBStatus.setSelectedIndex(1);
	}
}
