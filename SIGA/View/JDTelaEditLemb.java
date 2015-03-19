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

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import Model.LembretesBean;

public class JDTelaEditLemb extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBAtuLemb;
	private JButton JBExcLemb;
	private JLabel JLDataContato;
	
	private	UtilDateModel model;
	private JDatePanelImpl 	datePanel;
	private JDatePickerImpl datePicker;
	private JComboBox<String> JCBUsuario;
	private JTextArea JTALemb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaEditLemb dialog = new JDTelaEditLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaEditLemb() {
		setBounds(100, 100, 450, 300);
		setTitle("SIGA - edi\u00E7\u00E3o de lembretes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLDataContato = new JLabel("Data contato");
			JLDataContato.setBounds(10, 11, 74, 14);
			contentPanel.add(JLDataContato);
			

			model = new UtilDateModel();
			datePanel = new JDatePanelImpl(model);
			datePanel.setPreferredSize(new java.awt.Dimension(202, 182));
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.setBounds(99, 11, 224, 30);
			contentPanel.add(datePicker);
			
		}
		
		JLabel JLUsuario = new JLabel("Usu\u00E1rio");
		JLUsuario.setBounds(10, 53, 46, 14);
		contentPanel.add(JLUsuario);
		
		JCBUsuario = new JComboBox<String>();
		JCBUsuario.setBounds(99, 50, 224, 20);
		contentPanel.add(JCBUsuario);
		
		JLabel lblLembrete = new JLabel("Lembrete");
		lblLembrete.setBounds(10, 89, 63, 14);
		contentPanel.add(lblLembrete);
		
		JTALemb = new JTextArea();
		JTALemb.setLineWrap(true);
		JTALemb.setBounds(99, 84, 325, 133);
		contentPanel.add(JTALemb);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBAtuLemb = new JButton("Atualizar");
				JBAtuLemb.addActionListener(this);
				buttonPane.add(JBAtuLemb);
				getRootPane().setDefaultButton(JBAtuLemb);
			}
			{
				JBExcLemb = new JButton("Excluir");
				JBExcLemb.addActionListener(this);
				buttonPane.add(JBExcLemb);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {
		
		if(acao.getSource() == JBAtuLemb){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja atualizar o lembrete?")){
			
			if(datePicker.getJFormattedTextField().getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Data do lembrete em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JCBUsuario.getSelectedItem().toString().equals("Selecionar"))
				JOptionPane.showMessageDialog(null, "Usuário do lembrete em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else if(JTALemb.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Anotações do lembrete em branco.","Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
			else{
				
				LembretesBean lembBean = new LembretesBean();
				
//				lembBean.setDtcontatolemb(dtcontatolemb);  o que seria aqui? Não lembro //
				//lembBean.setDtagendadalemb(); Converter para DateTime
				lembBean.setUsuario(JCBUsuario.getSelectedItem().toString());
				lembBean.setAssunto(JTALemb.getText());
				
			}// final da validação
			
			}// final da confirmação
			
		}// final do botão salvar lembrete
		
		if(acao.getSource() == JBExcLemb){
			
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Deseja excluir o lembrete?")){
						

			}// final da confirmação
			
		}// final do botão novo lembrete
		
	}
}
