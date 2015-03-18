package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class JDTelaCadLemb extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBSalvLemb;
	private JButton JBNovLemb;
	private JLabel lblDataContato;
	
	private	UtilDateModel model;
	private JDatePanelImpl 	datePanel;
	private JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaCadLemb dialog = new JDTelaCadLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaCadLemb() {
		setBounds(100, 100, 450, 300);
		setTitle("SIGA - cadastro de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblDataContato = new JLabel("Data contato");
			lblDataContato.setBounds(10, 11, 74, 14);
			contentPanel.add(lblDataContato);
			

			model = new UtilDateModel();
			datePanel = new JDatePanelImpl(model);
			datePanel.setPreferredSize(new java.awt.Dimension(202, 182));
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.setBounds(80, 11, 202, 30);
			contentPanel.add(datePicker);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBSalvLemb = new JButton("Salvar");
				JBSalvLemb.addActionListener(this);
				buttonPane.add(JBSalvLemb);
				getRootPane().setDefaultButton(JBSalvLemb);
			}
			{
				JBNovLemb = new JButton("Novo");
				JBNovLemb.addActionListener(this);
				buttonPane.add(JBNovLemb);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {
		
		if(acao.getSource() == JBSalvLemb){
			
		}// final do botão salvar lembrete
		
		if(acao.getSource() == JBNovLemb){
			
		}// final do botão novo lembrete
		
	}

}
