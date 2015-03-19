package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.LembretesControl;

public class JDTelaBuscarLemb extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadLemb;
	private JButton JBEditLemb;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private LembretesControl lemCont = new LembretesControl();
	private JScrollPane scroll;
	private JTable tabela;
	private DefaultTableModel model;
	protected String valor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarLemb dialog = new JDTelaBuscarLemb();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarLemb() {
		setTitle("SIGA - buscar lembretes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLFiltro = new JLabel("Filtro");
			JLFiltro.setBounds(10, 11, 46, 14);
			contentPanel.add(JLFiltro);
		}
		{
			JBBuscar = new JButton("Buscar");
			JBBuscar.addActionListener(this);
			JBBuscar.setBounds(335, 11, 89, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setColumns(10);
			JTFBuscar.setBounds(153, 11, 172, 20);
			contentPanel.add(JTFBuscar);
		}
		{
			JCBFiltro = new JComboBox<String>();
			for (String item : lemCont.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(40, 11, 103, 20);
			contentPanel.add(JCBFiltro);
		}
		{
			// Criação da Jtable
			scroll = new JScrollPane();
			contentPanel.add(scroll);
            scroll.setBounds(12, 59, 412, 158);
            {
                    final TableModel tabelaModel = 
                                    new DefaultTableModel(
                                                    new String[][] { },
                                                    new String[] {  "Data agendada", 
                                                    				"Assunto",
                                                    				"Usuário"}){
                    	/**
																		 * 
																		 */
																		private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int col) {  
                    		return false;  
                         } };
                    
                    tabela = new JTable();
                    scroll.setViewportView(tabela);
                    tabela.setModel(tabelaModel);
                    model = (DefaultTableModel) tabela.getModel();
                    model.fireTableDataChanged();
                    
                    //for (LancamentoBean lanc : lancControl.getLancamentos()) { Laço necessário para incluir registro na tabela
						//model.addRow(new Object[]{new Integer(lanc.getIdLancamento()),formatas.format(lanc.getDtCompra()),lanc.getNAutorizacao(),lanc.getSelectedConveniada()});
                    	model.addRow(new Object[]{"01/02/2015","Retornar para o cliente","Guido"});
                    	model.addRow(new Object[]{"02/03/2015","Ligar para o Guido","Flavio"});
                    	model.addRow(new Object[]{"03/04/2015","Pegar datashow","Junior"});
                    	
					//}
                    
                    tabela.addMouseListener(new MouseAdapter() {
                            

							public void mouseClicked(MouseEvent evt) {
								if(evt.getClickCount() == 1){
                                    int linha = tabela.getSelectedRow();
                                    valor = String.valueOf(tabela.getValueAt(linha, 0));       
                                    
								}
								
                            }
                    });
                    
            }
    }
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadLemb = new JButton("Cadastrar");
				JBCadLemb.addActionListener(this);
				buttonPane.add(JBCadLemb);
				getRootPane().setDefaultButton(JBCadLemb);
			}
			{
				JBEditLemb = new JButton("Alterar");
				JBEditLemb.addActionListener(this);
				buttonPane.add(JBEditLemb);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {


		if(acao.getSource() == JBCadLemb){
			JDTelaCadLemb jdtcl = new JDTelaCadLemb();
			jdtcl.setVisible(true);
			jdtcl.setLocationRelativeTo(null);
		}// final do botão cadastrar lembretes
		
		if(acao.getSource() == JBEditLemb){
			JDTelaEditLemb jdtel = new JDTelaEditLemb();
			jdtel.setVisible(true);
			jdtel.setLocationRelativeTo(null);
		}// final do botão atualizar lembretes
		
		if(acao.getSource() == JBBuscar){
			
		}// final do botão buscar lembretes

	}// final da ação do botão

}
