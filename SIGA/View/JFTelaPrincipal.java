package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Model.UsuarioBean;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class JFTelaPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UsuarioBean usuario;
	private JMenuItem JMIFornCad;
	private JMenuItem JMIFornBuscar;
	private JMenuItem JMIFornExcluir;
	private JMenuItem JMIServCad;
	private JMenuItem JMIServBuscar;
	private JMenuItem JMIServExcluir;
	private JMenuItem JMICliCad;
	private JMenuItem JMICliBuscar;
	private JMenuItem JMICliExcluir;
	private JMenuItem JMILembCad;
	private JMenuItem JMILembBuscar;
	private JMenuItem JMILembExcluir;
	private JMenuItem JMIFinSincInf;
	private JMenuItem JMIFinFormPag;
	private JMenuItem JMIRelContFormComp;
	private JMenuItem JMIRelContFormCusto;
	private JMenuItem JMIRelEventSerRea;
	private JMenuItem JMIRelEventForn;
	private JMenuItem JMIRelImpOrc;
	private JMenuItem JMIRelImpCont;
	private JMenuItem JMIUsuCad;
	private JMenuItem JMIUsuBuscar;
	private JMenuItem JMIUsuExcluir;
	private JMenu JMIRelCont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFTelaPrincipal frame = new JFTelaPrincipal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFTelaPrincipal(UsuarioBean usuario) {

		/* Atribuição dos valores recebidos do BD */
		this.usuario = usuario;

		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Centralização da tela e maximização */
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);

		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu JMForn = new JMenu("Fornecedores");
		menuBar.add(JMForn);

		JMIFornCad = new JMenuItem("Cadastrar");
		JMIFornCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		JMIFornCad.addActionListener(this);
		JMForn.add(JMIFornCad);

		JMIFornBuscar = new JMenuItem("Buscar");
		JMIFornBuscar.addActionListener(this);
		JMForn.add(JMIFornBuscar);

		JMIFornExcluir = new JMenuItem("Excluir");
		JMIFornExcluir.addActionListener(this);
		JMForn.add(JMIFornExcluir);

		JMenu JMServ = new JMenu("Servi\u00E7os");
		menuBar.add(JMServ);

		JMIServCad = new JMenuItem("Cadastrar");
		JMIServCad.addActionListener(this);
		JMServ.add(JMIServCad);

		JMIServBuscar = new JMenuItem("Buscar");
		JMIServBuscar.addActionListener(this);
		JMServ.add(JMIServBuscar);

		JMIServExcluir = new JMenuItem("Excluir");
		JMIServExcluir.addActionListener(this);
		JMServ.add(JMIServExcluir);

		JMenu JMCli = new JMenu("Clientes");
		menuBar.add(JMCli);

		JMICliCad = new JMenuItem("Cadastrar");
		JMICliCad.addActionListener(this);
		JMCli.add(JMICliCad);

		JMICliBuscar = new JMenuItem("Buscar");
		JMICliBuscar.addActionListener(this);
		JMCli.add(JMICliBuscar);

		JMICliExcluir = new JMenuItem("Excluir");
		JMICliExcluir.addActionListener(this);
		JMCli.add(JMICliExcluir);

		JMenu JMLemb = new JMenu("Lembretes");
		menuBar.add(JMLemb);

		JMILembCad = new JMenuItem("Cadastrar");
		JMILembCad.addActionListener(this);
		JMLemb.add(JMILembCad);

		JMILembBuscar = new JMenuItem("Buscar");
		JMILembBuscar.addActionListener(this);
		JMLemb.add(JMILembBuscar);

		JMILembExcluir = new JMenuItem("Excluir");
		JMILembExcluir.addActionListener(this);
		JMLemb.add(JMILembExcluir);

		JMenu JMFin = new JMenu("Financeiro");
		menuBar.add(JMFin);

		JMIFinSincInf = new JMenuItem("Sincronizar info.");
		JMIFinSincInf.addActionListener(this);
		JMFin.add(JMIFinSincInf);

		JMIFinFormPag = new JMenuItem("Formas de pag.");
		JMIFinFormPag.addActionListener(this);
		JMFin.add(JMIFinFormPag);

		JMenu JMRel = new JMenu("Relat\u00F3rios");
		menuBar.add(JMRel);

		JMIRelCont = new JMenu("Contrato");
		JMRel.add(JMIRelCont);

		JMIRelContFormComp = new JMenuItem("Formatura completa");
		JMIRelContFormComp.addActionListener(this);
		JMIRelCont.add(JMIRelContFormComp);

		JMIRelContFormCusto = new JMenuItem("Formatura customizada");
		JMIRelContFormCusto.addActionListener(this);
		JMIRelCont.add(JMIRelContFormCusto);

		JMenu JMIRelEvent = new JMenu("Eventos");
		JMRel.add(JMIRelEvent);

		JMIRelEventSerRea = new JMenuItem("A ser realizado");
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventSerRea);

		JMIRelEventForn = new JMenuItem("Fornecedores");
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventForn);

		JMenu JMIRelImp = new JMenu("Impress\u00E3o");
		JMRel.add(JMIRelImp);

		JMIRelImpOrc = new JMenuItem("Or\u00E7amento");
		JMIRelImpOrc.addActionListener(this);
		JMIRelImp.add(JMIRelImpOrc);

		JMIRelImpCont = new JMenuItem("Contrato");
		JMIRelImpCont.addActionListener(this);
		JMIRelImp.add(JMIRelImpCont);

		JMenu JMUsu = new JMenu("Usu\u00E1rio");
		menuBar.add(JMUsu);

		JMIUsuCad = new JMenuItem("Cadastrar");
		JMIUsuCad.addActionListener(this);
		JMUsu.add(JMIUsuCad);

		JMIUsuBuscar = new JMenuItem("Buscar");
		JMIUsuBuscar.addActionListener(this);
		JMUsu.add(JMIUsuBuscar);

		JMIUsuExcluir = new JMenuItem("Excluir");
		JMIUsuExcluir.addActionListener(this);
		JMUsu.add(JMIUsuExcluir);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		/* JMenu Fornecedores */
		if (acao.getSource() == JMIFornCad) {
			JDTelaCadForn jdtcf;

			try {
				jdtcf = new JDTelaCadForn(0);
				jdtcf.setVisible(true);
				jdtcf.setLocationRelativeTo(null);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemFornecedores Cadastro

		if (acao.getSource() == JMIFornBuscar) {
			JDTelaBuscarForn jdtbf = new JDTelaBuscarForn();
			jdtbf.setVisible(true);
		}// final do JMenuItemFornecedores Buscar

		if (acao.getSource() == JMIFornExcluir) {
			JOptionPane
					.showInputDialog("Digite o código do fornecedor para exclusão");
		}// final do JMenuItemFornecedores Excluir

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Serviços */
		if (acao.getSource() == JMIServCad) {
			JDTelaCadServ jdtcs = new JDTelaCadServ();
			jdtcs.setVisible(true);
		}// final do JMenuItemServiços Cadastro

		if (acao.getSource() == JMIServBuscar) {
			JDTelaBuscarServ jdtbs = new JDTelaBuscarServ();
			jdtbs.setVisible(true);
		}// final do JMenuItemServiços Buscar

		if (acao.getSource() == JMIServExcluir) {
			JOptionPane
					.showInputDialog("Digite o código do serviço para exclusão");
		}// final do JMenuItemServiços Excluir

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Clientes */
		if (acao.getSource() == JMICliCad) {
			JDTelaCadCli jdtcc;

			try {
				jdtcc = new JDTelaCadCli();
				jdtcc.setVisible(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemClientes Cadastrar

		if (acao.getSource() == JMICliBuscar) {
			JDTelaBuscarCli jdtbc = new JDTelaBuscarCli();
			jdtbc.setVisible(true);
		}// final do JMenuItemClientes Buscar

		if (acao.getSource() == JMICliExcluir) {
			JOptionPane
					.showInputDialog("Digite o código do cliente para exclusão");
		}// final do JMenuItemClientes Excluir

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Lembretes */
		if (acao.getSource() == JMILembCad) {
			JDTelaCadLemb jdtcl = new JDTelaCadLemb();
			jdtcl.setVisible(true);
		}// final do JMenuItemLembretes Cadastrar

		if (acao.getSource() == JMILembBuscar) {
			JDTelaBuscarLemb jdtbl = new JDTelaBuscarLemb();
			jdtbl.setVisible(true);
		}// final do JMenuItemLembretes Buscar

		if (acao.getSource() == JMILembExcluir) {
			JOptionPane
					.showInputDialog("Digite o código do lembrete para exclusão");
		}// final do JMenuItemLembretes Excluir

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Financeiro */
		if (acao.getSource() == JMIFinSincInf) {

		}// final do JMenuItemFinanceiro Sincronização de informações

		if (acao.getSource() == JMIFinFormPag) {

		}// final do JMenuItemFinanceiro Formas de pagamentos

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Relatórios */
		if (acao.getSource() == JMIRelContFormComp) {

		}// final do JMenuItemRelatórios -> Contratos -> Formatura completo

		if (acao.getSource() == JMIRelContFormCusto) {

		}// final do JMenuItemRelatórios -> Contratos -> Formatura customizado

		if (acao.getSource() == JMIRelEventSerRea) {

		}// final do JMenuItemRelatórios -> Eventos -> A ser realizado

		if (acao.getSource() == JMIRelEventForn) {

		}// final do JMenuItemRelatórios -> Eventos -> Fornecedores

		if (acao.getSource() == JMIRelImpOrc) {

		}// final do JMenuItemRelatórios -> Impressão -> Orçamentos

		if (acao.getSource() == JMIRelImpCont) {

		}// final do JMenuItemRelatórios -> Impressão -> Contratos

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Usuário */
		if (acao.getSource() == JMIUsuCad) {

			try {
				JDTelaCadUsu jdtcu = new JDTelaCadUsu(0);
				jdtcu.setVisible(true);
				jdtcu.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscara", JOptionPane.ERROR_MESSAGE);
			}

		}// final do JMenuItemUsuário Cadastrar

		if (acao.getSource() == JMIUsuBuscar) {
			JDTelaBuscarUsu jdtbu = new JDTelaBuscarUsu();
			jdtbu.setVisible(true);
		}// final do JMenuItemUsuário Buscar

		if (acao.getSource() == JMIUsuExcluir) {
			JOptionPane
					.showInputDialog("Digite o login do usuário para exclusão");
		}// final do JMenuItemUsuário Excluir

	}// final da ação do menu

}
