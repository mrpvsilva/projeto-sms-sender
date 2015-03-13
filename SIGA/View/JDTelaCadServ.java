package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Extra.Extras;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import jmoneyfield.JMoneyField;

import javax.swing.JComboBox;

public class JDTelaCadServ extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField JTFItem;
	private JTextField JMFVlrCusto;
	private JComboBox<String> JCBStatus;
	private Extras ext = new Extras();
	private JButton JBSalvServ;
	private JButton JBNovoServ;

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
			JTFItem.setBounds(49, 8, 375, 20);
			contentPanel.add(JTFItem);
			JTFItem.setColumns(10);
		}
		
		JTextArea JTADescItem = new JTextArea();
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
		
		JMoneyField JMFVlrCom = new JMoneyField();
		JMFVlrCom.setBounds(101, 200, 150, 20);
		contentPanel.add(JMFVlrCom);
		{
			JLabel JLAtivo = new JLabel("Ativo");
			JLAtivo.setBounds(261, 162, 46, 14);
			contentPanel.add(JLAtivo);
		}
		{
			JCBStatus = new JComboBox<String>();
			for(String item: ext.Status()){
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

		if(acao.getSource() == JBSalvServ){
			
		}// final do botão salvar
		
		if(acao.getSource() == JBNovoServ){
			
		}// final do botão novo
	}
}
