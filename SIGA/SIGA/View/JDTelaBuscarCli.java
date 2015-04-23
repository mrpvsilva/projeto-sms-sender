package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Control.ClientesControl;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class JDTelaBuscarCli extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	private JButton JBEditCli;
	private JLabel JLFiltro;
	private JButton JBBuscar;
	private JTextField JTFBuscar;
	private JComboBox<String> JCBFiltro;
	private ClientesControl cliCont = new ClientesControl();
	private JScrollPane scroll;
	private JTable tabela;
	private DefaultTableModel model;
	protected String valor;
	private JButton JBSair;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscarCli dialog = new JDTelaBuscarCli();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscarCli() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDTelaBuscarCli.class.getResource("/Img/CNPJ G200.png")));
		setTitle("SIGA - buscar clientes");
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
			JBBuscar.setIcon(new ImageIcon(JDTelaBuscarCli.class.getResource("/Img/Procurar.png")));
			JBBuscar.setMnemonic(KeyEvent.VK_F);
			JBBuscar.addActionListener(this);
			JBBuscar.setBounds(325, 11, 99, 23);
			contentPanel.add(JBBuscar);
		}
		{
			JTFBuscar = new JTextField();
			JTFBuscar.setBounds(149, 11, 172, 20);
			contentPanel.add(JTFBuscar);
			JTFBuscar.setColumns(10);
		}
		{
			JCBFiltro = new JComboBox<String>();
			for (String item : cliCont.Filtros()) {
				JCBFiltro.addItem(item);
			}
			JCBFiltro.setBounds(39, 11, 103, 20);
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
                                                    new String[] {  "Nome", 
                                                    				"CPF",
                                                    				"Tipo evento"}){
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
                    	model.addRow(new Object[]{new Integer(123),"Teste1","(91)12345678"});
                    	model.addRow(new Object[]{new Integer(456),"Teste2","(91)12345679"});
                    	model.addRow(new Object[]{new Integer(789),"Teste3","(91)12345670"});
                    	
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
				JBCadCli = new JButton("Cadastrar");
				JBCadCli.setIcon(new ImageIcon(JDTelaBuscarCli.class.getResource("/Img/save16.png")));
				JBCadCli.addActionListener(this);
				JBCadCli.setMnemonic(KeyEvent.VK_C);
				buttonPane.add(JBCadCli);
				getRootPane().setDefaultButton(JBCadCli);
			}
			{
				JBEditCli = new JButton("Editar");
				JBEditCli.setIcon(new ImageIcon(JDTelaBuscarCli.class.getResource("/Img/edit_add16.png")));
				JBEditCli.setMnemonic(KeyEvent.VK_E);
				JBEditCli.addActionListener(this);
				buttonPane.add(JBEditCli);
			}
			{
				JBSair = new JButton("Sair");
				JBSair.setIcon(new ImageIcon(JDTelaBuscarCli.class.getResource("/Img/exit16.png")));
				JBSair.addActionListener(this);
				JBSair.setMnemonic(KeyEvent.VK_Q);
				buttonPane.add(JBSair);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		if(acao.getSource() == JBCadCli){
			
			try {
				JDTelaCadCli cdtcc = new JDTelaCadCli();
				cdtcc.setVisible(true);
				cdtcc.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro ao carregar máscaras","Error",JOptionPane.ERROR_MESSAGE);
			}// final do try e catch
			
		}// final do botão cadastrar cliente
		
		if(acao.getSource() == JBEditCli){
			
			try {
				JDTelaEditCli jdtec = new JDTelaEditCli();
				jdtec.setVisible(true);
				jdtec.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro ao carregar máscaras","Error",JOptionPane.ERROR_MESSAGE);
			}// final do try e catch
			
		}// final do botão atualizar cliente
		
		if(acao.getSource() == JBBuscar){
			
		}// final do botão buscar cliente
		
		if(acao.getSource() == JBSair){
			this.dispose();
		}
		
	}// final da ação do botão

}
