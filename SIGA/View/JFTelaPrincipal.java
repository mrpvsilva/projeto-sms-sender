package View;

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
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class JFTelaPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	private JMenu JMEventos;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmBuscar;
	private JMenuItem mntmExcluir;

	private JMenu JMTipoServio;
	private JMenuItem JMITSCad;
	private JMenuItem JMITSBuscar;
	private JLabel label;
	private JMenu mnTipoServio_1;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JFTelaPrincipal.class.getResource("/Img/CNPJ G200.png")));

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
		JMIFornBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		JMIFornBuscar.addActionListener(this);
		JMForn.add(JMIFornBuscar);

		JMIFornExcluir = new JMenuItem("Excluir");
		JMIFornExcluir
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		JMIFornExcluir.addActionListener(this);
		JMForn.add(JMIFornExcluir);

		mnTipoServio_1 = new JMenu("Tipo Servi\u00E7o");
		menuBar.add(mnTipoServio_1);

		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(null, 0);
				tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tscad.setVisible(true);
			}
		});
		mnTipoServio_1.add(mntmCadastrar_1);

		JMenuItem mntmBuscar_1 = new JMenuItem("Buscar");
		mntmBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDTelaBuscaTipoServico tsb = new JDTelaBuscaTipoServico();
				tsb.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tsb.setVisible(true);
			}
		});
		mnTipoServio_1.add(mntmBuscar_1);

		JMenu JMServ = new JMenu("Itens");
		menuBar.add(JMServ);

		JMIServCad = new JMenuItem("Cadastrar");
		JMIServCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		JMIServCad.addActionListener(this);
		JMServ.add(JMIServCad);

		JMIServBuscar = new JMenuItem("Buscar");
		JMIServBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		JMIServBuscar.addActionListener(this);
		JMServ.add(JMIServBuscar);

		JMIServExcluir = new JMenuItem("Excluir");
		JMIServExcluir
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		JMIServExcluir.addActionListener(this);
		JMServ.add(JMIServExcluir);

		JMTipoServio = new JMenu("Tipo itens");
		JMServ.add(JMTipoServio);

		JMITSCad = new JMenuItem("Cadastrar");
		JMITSCad.addActionListener(this);
		JMTipoServio.add(JMITSCad);

		JMITSBuscar = new JMenuItem("Buscar");
		JMITSBuscar.addActionListener(this);
		JMTipoServio.add(JMITSBuscar);

		JMenu JMCli = new JMenu("Clientes");
		JMCli.setEnabled(false);
		menuBar.add(JMCli);

		JMICliCad = new JMenuItem("Cadastrar");
		JMICliCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		JMICliCad.addActionListener(this);
		JMCli.add(JMICliCad);

		JMICliBuscar = new JMenuItem("Buscar");
		JMICliBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		JMICliBuscar.addActionListener(this);
		JMCli.add(JMICliBuscar);

		JMICliExcluir = new JMenuItem("Excluir");
		JMICliExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		JMICliExcluir.addActionListener(this);
		JMCli.add(JMICliExcluir);

		JMEventos = new JMenu("Eventos");
		JMEventos.setEnabled(false);
		menuBar.add(JMEventos);

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		JMEventos.add(mntmCadastrar);

		mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		JMEventos.add(mntmBuscar);

		mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
		JMEventos.add(mntmExcluir);

		JMenu JMLemb = new JMenu("Lembretes");
		menuBar.add(JMLemb);

		JMILembCad = new JMenuItem("Cadastrar");
		JMILembCad.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_PAGE_UP, 0));
		JMILembCad.addActionListener(this);
		JMLemb.add(JMILembCad);

		JMILembBuscar = new JMenuItem("Buscar");
		JMILembBuscar.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_PAGE_DOWN, 0));
		JMILembBuscar.addActionListener(this);
		JMLemb.add(JMILembBuscar);

		JMILembExcluir = new JMenuItem("Excluir");
		JMILembExcluir.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_END, 0));
		JMILembExcluir.addActionListener(this);
		JMLemb.add(JMILembExcluir);

		JMenu JMFin = new JMenu("Financeiro");
		JMFin.setEnabled(false);
		menuBar.add(JMFin);

		JMIFinSincInf = new JMenuItem("Sincronizar info.");
		JMIFinSincInf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				InputEvent.CTRL_MASK));
		JMIFinSincInf.addActionListener(this);
		JMFin.add(JMIFinSincInf);

		JMIFinFormPag = new JMenuItem("Formas de pag.");
		JMIFinFormPag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				InputEvent.CTRL_MASK));
		JMIFinFormPag.addActionListener(this);
		JMFin.add(JMIFinFormPag);

		JMenu JMTipoDePagamento = new JMenu("Tipo de pagamento");
		JMFin.add(JMTipoDePagamento);

		JMenuItem JMITpPagCad = new JMenuItem("Cadastrar");
		JMITpPagCad.addActionListener(this);
		JMTipoDePagamento.add(JMITpPagCad);

		JMenuItem JMITpPagBuscar = new JMenuItem("Buscar");
		JMITpPagBuscar.addActionListener(this);
		JMTipoDePagamento.add(JMITpPagBuscar);

		JMenuItem JMITpPagExcluir = new JMenuItem("Excluir");
		JMITpPagExcluir.addActionListener(this);
		JMTipoDePagamento.add(JMITpPagExcluir);

		JMenu JMFormasDePagamento = new JMenu("Formas de pagamento");
		JMFin.add(JMFormasDePagamento);

		JMenuItem JMIFormPagCad = new JMenuItem("Cadastrar");
		JMIFormPagCad.addActionListener(this);
		JMFormasDePagamento.add(JMIFormPagCad);

		JMenuItem JMIFormPagBuscar = new JMenuItem("Buscar");
		JMIFormPagBuscar.addActionListener(this);
		JMFormasDePagamento.add(JMIFormPagBuscar);

		JMenuItem JMIFormPagExcluir = new JMenuItem("Excluir");
		JMIFormPagExcluir.addActionListener(this);
		JMFormasDePagamento.add(JMIFormPagExcluir);

		JMenu JMRel = new JMenu("Relat\u00F3rios");
		JMRel.setEnabled(false);
		menuBar.add(JMRel);

		JMIRelCont = new JMenu("Contrato");
		JMRel.add(JMIRelCont);

		JMIRelContFormComp = new JMenuItem("Formatura completa");
		JMIRelContFormComp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				InputEvent.CTRL_MASK));
		JMIRelContFormComp.addActionListener(this);
		JMIRelCont.add(JMIRelContFormComp);

		JMIRelContFormCusto = new JMenuItem("Formatura customizada");
		JMIRelContFormCusto.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_4, InputEvent.CTRL_MASK));
		JMIRelContFormCusto.addActionListener(this);
		JMIRelCont.add(JMIRelContFormCusto);

		JMenu JMIRelEvent = new JMenu("Eventos");
		JMRel.add(JMIRelEvent);

		JMIRelEventSerRea = new JMenuItem("A ser realizado");
		JMIRelEventSerRea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
				InputEvent.CTRL_MASK));
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventSerRea);

		JMIRelEventForn = new JMenuItem("Fornecedores");
		JMIRelEventForn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,
				InputEvent.CTRL_MASK));
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventForn);

		JMenu JMIRelImp = new JMenu("Impress\u00E3o");
		JMRel.add(JMIRelImp);

		JMIRelImpOrc = new JMenuItem("Or\u00E7amento");
		JMIRelImpOrc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,
				InputEvent.CTRL_MASK));
		JMIRelImpOrc.addActionListener(this);
		JMIRelImp.add(JMIRelImpOrc);

		JMIRelImpCont = new JMenuItem("Contrato");
		JMIRelImpCont.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8,
				InputEvent.CTRL_MASK));
		JMIRelImpCont.addActionListener(this);
		JMIRelImp.add(JMIRelImpCont);

		JMenu JMUsu = new JMenu("Usu\u00E1rio");
		menuBar.add(JMUsu);

		JMIUsuCad = new JMenuItem("Cadastrar");
		JMIUsuCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
		JMIUsuCad.addActionListener(this);
		JMUsu.add(JMIUsuCad);

		JMIUsuBuscar = new JMenuItem("Buscar");
		JMIUsuBuscar
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0));
		JMIUsuBuscar.addActionListener(this);
		JMUsu.add(JMIUsuBuscar);

		JMIUsuExcluir = new JMenuItem("Excluir");
		JMIUsuExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
				0));
		JMIUsuExcluir.addActionListener(this);
		JMUsu.add(JMIUsuExcluir);

		JMenu mnPerfil = new JMenu("Perfil");
		JMUsu.add(mnPerfil);

		JMenuItem mntmCadastrar_3 = new JMenuItem("Cadastrar");
		mnPerfil.add(mntmCadastrar_3);

		JMenuItem mntmBuscar_3 = new JMenuItem("Buscar");
		mnPerfil.add(mntmBuscar_3);

		JMenuItem mntmExcluir_3 = new JMenuItem("Excluir");
		mnPerfil.add(mntmExcluir_3);

		JMenu mnPermisso = new JMenu("Permiss\u00E3o");
		JMUsu.add(mnPermisso);

		JMenuItem mntmCadastrar_4 = new JMenuItem("Cadastrar");
		mnPermisso.add(mntmCadastrar_4);

		JMenuItem mntmBuscar_4 = new JMenuItem("Buscar");
		mnPermisso.add(mntmBuscar_4);

		JMenuItem mntmExcluir_4 = new JMenuItem("Excluir");
		mnPermisso.add(mntmExcluir_4);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("");
		label.setBounds(165, 68, 1168, 531);
		label.setIcon(new ImageIcon(JFTelaPrincipal.class
				.getResource("/Img/CNPJ G800.png")));
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		/* JMenu Fornecedores */
		if (acao.getSource() == JMIFornCad) {
			JDTelaCadForn jdtcf;

			try {
				jdtcf = new JDTelaCadForn(0);
				jdtcf.setResizable(false);
				jdtcf.setVisible(true);
				jdtcf.setLocationRelativeTo(null);
			} catch (ParseException e) {

				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemFornecedores Cadastro

		if (acao.getSource() == JMIFornBuscar) {
			JDTelaBuscarForn jdtbf = new JDTelaBuscarForn();
			jdtbf.setResizable(false);
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
			jdtcs.setResizable(false);
			jdtcs.setVisible(true);
		}// final do JMenuItemServiços Cadastro

		if (acao.getSource() == JMIServBuscar) {
			JDTelaBuscarServ jdtbs = new JDTelaBuscarServ();
			jdtbs.setResizable(false);
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
				jdtcc.setResizable(false);
				jdtcc.setVisible(true);
			} catch (ParseException e) {

				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemClientes Cadastrar

		if (acao.getSource() == JMICliBuscar) {
			JDTelaBuscarCli jdtbc = new JDTelaBuscarCli();
			jdtbc.setResizable(false);
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
			jdtcl.setResizable(false);
			jdtcl.setVisible(true);
		}// final do JMenuItemLembretes Cadastrar

		if (acao.getSource() == JMILembBuscar) {
			JDTelaBuscarLemb jdtbl = new JDTelaBuscarLemb();
			jdtbl.setResizable(false);
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

			JDTelaFormPagFin jdtfpf = new JDTelaFormPagFin();
			jdtfpf.setVisible(true);
			jdtfpf.setResizable(false);
			jdtfpf.setLocationRelativeTo(null);

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
				jdtcu.setResizable(false);
				jdtcu.setVisible(true);
				jdtcu.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscara", JOptionPane.ERROR_MESSAGE);
			}

		}// final do JMenuItemUsuário Cadastrar

		if (acao.getSource() == JMITSCad) {
			JDTelaEditFormTS ef = new JDTelaEditFormTS(0, null);
			ef.setResizable(false);
			ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ef.setVisible(true);
		}

		if (acao.getSource() == JMITSBuscar) {
			JDTelaBuscarTS bts = new JDTelaBuscarTS();
			bts.setResizable(false);
			bts.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			bts.setVisible(true);
		}

		if (acao.getSource() == JMIUsuBuscar) {
			JDTelaBuscarUsu jdtbu = new JDTelaBuscarUsu();
			jdtbu.setResizable(false);
			jdtbu.setVisible(true);
		}// final do JMenuItemUsuário Buscar

		if (acao.getSource() == JMIUsuExcluir) {
			JOptionPane
					.showInputDialog("Digite o login do usuário para exclusão");
		}

	}// final da ação do menu
}
