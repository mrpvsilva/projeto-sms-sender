package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import Control.FinanceiroControl;
import Dominio.FormaPagamento;
import Dominio.TipoPagamento;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.ImageIcon;
import jmoneyfield.JMoneyField;

public class JDTelaFormPagFin extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private JComboBox<String> JCBTpPagamento;
	private FinanceiroControl finControl = new FinanceiroControl();
	private JLabel lblQtdParcelas;
	private JComboBox JCBFormPag;
	private JLabel lblNDoc;
	private JLabel lblDataPagamenot;
	private JLabel lblDataEmisso;
	private UtilDateModel modelDtVenc;
	private UtilDateModel modelDtEmi;
	private JDatePanelImpl datePanelDtVenc;
	private JDatePanelImpl datePanelDtEmi;
	private JDatePickerImpl datePickerDtEmi;
	private JDatePickerImpl datePickerDtVenc;
	private JButton btnSair;
	private JLabel lblValorPagamento;
	private JTextField JMFVlrPag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaFormPagFin dialog = new JDTelaFormPagFin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaFormPagFin() {
		setResizable(false);
		setModal(true);
		setTitle("SIGA - formas de pagamento");
		setBounds(100, 100, 493, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel JLTipoPagamento = new JLabel("Tipo pagamento");
		JLTipoPagamento.setBounds(10, 11, 97, 14);
		contentPanel.add(JLTipoPagamento);
		
		JCBTpPagamento = new JComboBox();
		for (TipoPagamento item : finControl.allTpPagamentos()) {
			JCBTpPagamento.addItem(item.getDescricao());
		}
		JCBTpPagamento.setBounds(120, 8, 156, 20);
		contentPanel.add(JCBTpPagamento);
		{
			lblQtdParcelas = new JLabel("Qtd. Parcelas");
			lblQtdParcelas.setBounds(289, 11, 86, 14);
			contentPanel.add(lblQtdParcelas);
		}
		
		JSpinner SpQtdParc = new JSpinner();
		SpQtdParc.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		SpQtdParc.setBounds(374, 8, 50, 20);
		contentPanel.add(SpQtdParc);
		
		JLabel lblFormaDePagamento = new JLabel("Forma pagamento");
		lblFormaDePagamento.setBounds(10, 34, 121, 14);
		contentPanel.add(lblFormaDePagamento);
		
		JCBFormPag = new JComboBox();
		for(FormaPagamento item : finControl.allFormaPagamento()){
			JCBFormPag.addItem(item.getForma());
		}
		JCBFormPag.setBounds(120, 34, 156, 20);
		contentPanel.add(JCBFormPag);
		
		lblNDoc = new JLabel("N\u00BA doc");
		lblNDoc.setBounds(289, 34, 50, 14);
		contentPanel.add(lblNDoc);
		
		JFormattedTextField JFFNDoc = new JFormattedTextField();
		JFFNDoc.setBounds(330, 34, 94, 20);
		contentPanel.add(JFFNDoc);
		
		lblDataPagamenot = new JLabel("Data vencimento");
		lblDataPagamenot.setBounds(10, 65, 97, 14);
		
		modelDtVenc = new UtilDateModel();
		datePanelDtVenc = new JDatePanelImpl(modelDtVenc);
		datePanelDtVenc.setPreferredSize(new java.awt.Dimension(202, 182));
		datePickerDtVenc = new JDatePickerImpl(datePanelDtVenc);
		datePickerDtVenc.setBounds(120, 60, 154, 30);
		contentPanel.add(datePickerDtVenc);
		
		contentPanel.add(lblDataPagamenot);
		
		lblDataEmisso = new JLabel("Data emiss\u00E3o");
		lblDataEmisso.setBounds(10, 101, 97, 14);
		
		modelDtEmi = new UtilDateModel();
		datePanelDtEmi = new JDatePanelImpl(modelDtEmi);
		datePanelDtEmi.setPreferredSize(new java.awt.Dimension(202, 182));
		datePickerDtEmi = new JDatePickerImpl(datePanelDtEmi);
		datePickerDtEmi.setBounds(120, 97, 154, 30);
		contentPanel.add(datePickerDtEmi);
		
		contentPanel.add(lblDataEmisso);
		
		lblValorPagamento = new JLabel("Valor pagamento");
		lblValorPagamento.setBounds(10, 143, 97, 14);
		contentPanel.add(lblValorPagamento);
		
		JMFVlrPag = new JMoneyField();
		JMFVlrPag.setBounds(120, 138, 167, 20);
		contentPanel.add(JMFVlrPag);
		JMFVlrPag.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadCli = new JButton("Salvar");
				JBCadCli.setIcon(new ImageIcon(JDTelaFormPagFin.class.getResource("/Img/Confirmar.png")));
				JBCadCli.setMnemonic(KeyEvent.VK_S);
				JBCadCli.addActionListener(this);
				buttonPane.add(JBCadCli);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBEditCli = new JButton("Novo");
				JBEditCli.setIcon(new ImageIcon(JDTelaFormPagFin.class.getResource("/Img/window_new16.png")));
				JBEditCli.setMnemonic(KeyEvent.VK_N);
				JBEditCli.addActionListener(this);
				buttonPane.add(JBEditCli);
			}
			
			btnSair = new JButton("Sair");
			btnSair.setIcon(new ImageIcon(JDTelaFormPagFin.class.getResource("/Img/exit16.png")));
			btnSair.setMnemonic(KeyEvent.VK_Q);
			btnSair.addActionListener(this);
			buttonPane.add(btnSair);
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadCli){
			
		}// final do botão cadastrar cliente
		
		if(acao.getSource() == JBEditCli){
			
		}// final do botão atualizar cliente
		
		if(acao.getSource() == btnSair){
			this.dispose();
		}
		
	}// final da ação do botão
}
