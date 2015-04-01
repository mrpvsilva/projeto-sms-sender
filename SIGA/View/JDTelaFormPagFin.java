package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.EventoControl;
import Control.FinanceiroControl;
import Dominio.FormaPagamento;
import Dominio.TipoPagamento;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JDTelaFormPagFin extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private JComboBox JCBTpPagamento;
	private FinanceiroControl finControl = new FinanceiroControl();
	private JLabel lblQtdParcelas;
	private JComboBox JCBFormPag;

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
		setTitle("SIGA - formas de pagamento");
		setBounds(100, 100, 450, 300);
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
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(374, 8, 50, 20);
		contentPanel.add(spinner);
		
		JLabel lblFormaDePagamento = new JLabel("Forma pagamento");
		lblFormaDePagamento.setBounds(10, 34, 121, 14);
		contentPanel.add(lblFormaDePagamento);
		
		JCBFormPag = new JComboBox();
		for(FormaPagamento item : finControl.allFormaPagamento()){
			JCBFormPag.addItem(item.getForma());
		}
		JCBFormPag.setBounds(120, 31, 156, 20);
		contentPanel.add(JCBFormPag);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadCli = new JButton("Cadastrar");
				JBCadCli.setActionCommand("OK");
				buttonPane.add(JBCadCli);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBEditCli = new JButton("Alterar");
				JBEditCli.setActionCommand("Cancel");
				buttonPane.add(JBEditCli);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadCli){
			
		}// final do botão cadastrar cliente
		
		if(acao.getSource() == JBEditCli){
			
		}// final do botão atualizar cliente
		
	}// final da ação do botão
}
