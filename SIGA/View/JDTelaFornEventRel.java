package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class JDTelaFornEventRel extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBGerarRel;
	private JLabel JLDtInicio;
	private UtilDateModel model;
	private JDatePanelImpl datePanelDtIni;
	private JDatePickerImpl datePickerDtIni;
	private JLabel lblDataFim;
	private UtilDateModel modelDtF;
	private JDatePanelImpl datePanelDtF;
	private JDatePickerImpl datePickerDtF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaFornEventRel dialog = new JDTelaFornEventRel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaFornEventRel() {
		setTitle("SIGA - relat\u00F3rio eventos por fornecedores");
		setBounds(100, 100, 532, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDtInicio = new JLabel("Data in\u00EDcio");
			JLDtInicio.setBounds(10, 56, 66, 14);
			contentPanel.add(JLDtInicio);
		}
		{
			
			model = new UtilDateModel();
			datePanelDtIni = new JDatePanelImpl(model);
			datePanelDtIni.setPreferredSize(new java.awt.Dimension(202, 182));
			datePickerDtIni = new JDatePickerImpl(datePanelDtIni);
			datePickerDtIni.setBounds(74, 51, 152, 30);
			contentPanel.add(datePickerDtIni);
			
		}
		
		{
			
			modelDtF = new UtilDateModel();
			datePanelDtF = new JDatePanelImpl(modelDtF);
			datePanelDtF.setPreferredSize(new java.awt.Dimension(202, 182));
			datePickerDtF = new JDatePickerImpl(datePanelDtF);
			datePickerDtF.setBounds(323, 51, 152, 30);
			contentPanel.add(datePickerDtF);
			
		}
		
		{
			lblDataFim = new JLabel("Data fim");
			lblDataFim.setBounds(254, 56, 59, 14);
			contentPanel.add(lblDataFim);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBGerarRel = new JButton("Gerar");
				JBGerarRel.setActionCommand("OK");
				buttonPane.add(JBGerarRel);
				getRootPane().setDefaultButton(JBGerarRel);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBGerarRel){
			
			if(datePickerDtIni.getJFormattedTextField().getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Data início em branco.","Erro ao gerar",JOptionPane.ERROR_MESSAGE);
			else if(datePickerDtF.getJFormattedTextField().getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Data fim em branco.","Erro ao gerar",JOptionPane.ERROR_MESSAGE);
			else{
				
			}
			
		}// final do botão cadastrar cliente
				
	}// final da ação do botão
}
