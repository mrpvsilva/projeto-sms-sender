package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.LembretesControl;
import Dominio.Lembrete;
import Dominio.Permissao;
import Extra.Extras;
import Util.Modulos;
import Util.PermissoesManager;

import java.awt.Font;

public class TelaPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem JMIFornCad;
	private JMenuItem JMIFornBuscar;
	private JMenuItem JMIServCad;
	private JMenuItem JMIServBuscar;
	private JMenuItem JMICliCad;
	private JMenuItem JMICliBuscar;
	private JMenuItem JMILembCad;
	private JMenuItem JMILembBuscar;
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
	private JMenu JMIRelCont;
	private JMenu JMEventos;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmBuscar;

	private JMenu JMTipoServio;
	private JMenuItem JMITSCad;
	private JMenuItem JMITSBuscar;
	private JLabel label;
	private JMenu mnTipoServio_1;
	private Permissao usuarios;
	private Permissao lembretes;
	private Permissao tipoServicos;
	private Permissao fornecedores;
	private Permissao itens;
	private Permissao tipoItens;
	private Permissao perfis;
	private Permissao modulos;
	private Permissao clientes;
	private Permissao eventos;
	private Permissao financeiro;
	private Permissao orcamento;
	private JMenu user;
	private JMenuItem mntmSair;
	private JMenuItem mntmTrocarSenha;
	private JMenu mnOramentos;
	private JMenuItem mntmNovo;
	private JMenuItem mntmBuscar_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		// buscar as permissões

		usuarios = PermissoesManager.buscarPermissao(Modulos.Usuarios);
		lembretes = PermissoesManager.buscarPermissao(Modulos.Lembretes);
		tipoServicos = PermissoesManager.buscarPermissao(Modulos.Servicos);
		fornecedores = PermissoesManager.buscarPermissao(Modulos.Fornecedores);
		itens = PermissoesManager.buscarPermissao(Modulos.Itens);
		tipoItens = PermissoesManager.buscarPermissao(Modulos.Tipo_itens);
		perfis = PermissoesManager.buscarPermissao(Modulos.Perfis);
		modulos = PermissoesManager.buscarPermissao(Modulos.Modulos);
		clientes = PermissoesManager.buscarPermissao(Modulos.Clientes);
		eventos = PermissoesManager.buscarPermissao(Modulos.Eventos);
		financeiro = PermissoesManager.buscarPermissao(Modulos.Financeiro);
		orcamento = PermissoesManager.buscarPermissao(Modulos.Orcamentos);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaPrincipal.class.getResource("/Img/LOGO_LOGIN_GDA.png")));

		setTitle("SIGA - Sistema de informa\u00E7\u00E3o G&A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Centralização da tela e maximização */
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);

		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// MENU FORNECEDORES
		JMenu JMForn = new JMenu("Fornecedores");
		JMForn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMForn.setVisible(fornecedores.isVisualizar());
		menuBar.add(JMForn);

		JMIFornCad = new JMenuItem("Novo");
		JMIFornCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFornCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		JMIFornCad.addActionListener(this);
		JMIFornCad.setVisible(fornecedores.isCadastrar());
		JMForn.add(JMIFornCad);

		JMIFornBuscar = new JMenuItem("Buscar");
		JMIFornBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFornBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		JMIFornBuscar.addActionListener(this);
		JMForn.add(JMIFornBuscar);

		// MENU TIPO SERVICOS
		mnTipoServio_1 = new JMenu("Servi\u00E7os");
		mnTipoServio_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnTipoServio_1.setVisible(tipoServicos.isVisualizar());
		JMForn.add(mnTipoServio_1);

		JMenuItem mntmCadastrar_1 = new JMenuItem("Novo");
		mntmCadastrar_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditFormServico tscad = new EditFormServico();
				tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tscad.setLocationRelativeTo(null);
				tscad.setVisible(true);
			}
		});
		mntmCadastrar_1.setVisible(tipoServicos.isCadastrar());
		mnTipoServio_1.add(mntmCadastrar_1);

		JMenuItem mntmBuscar_1 = new JMenuItem("Buscar");
		mntmBuscar_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarServicos tsb = new BuscarServicos();
				tsb.setLocationRelativeTo(null);
				tsb.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tsb.setVisible(true);
			}
		});
		mnTipoServio_1.add(mntmBuscar_1);

		// MENU ITENS
		JMenu JMServ = new JMenu("Itens");
		JMServ.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMServ.setVisible(itens.isVisualizar());
		menuBar.add(JMServ);

		JMIServCad = new JMenuItem("Novo");
		JMIServCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIServCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		JMIServCad.addActionListener(this);
		JMIServCad.setVisible(itens.isCadastrar());
		JMServ.add(JMIServCad);

		JMIServBuscar = new JMenuItem("Buscar");
		JMIServBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIServBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		JMIServBuscar.addActionListener(this);
		JMServ.add(JMIServBuscar);

		// MENUT TIPO ITENS
		JMTipoServio = new JMenu("Tipo itens");
		JMTipoServio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMTipoServio.setVisible(tipoItens.isVisualizar());
		JMServ.add(JMTipoServio);

		JMITSCad = new JMenuItem("Novo");
		JMITSCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMITSCad.addActionListener(this);
		JMITSCad.setVisible(tipoItens.isCadastrar());
		JMTipoServio.add(JMITSCad);

		JMITSBuscar = new JMenuItem("Buscar");
		JMITSBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMITSBuscar.addActionListener(this);
		JMTipoServio.add(JMITSBuscar);

		// MENU CLIENTES
		JMenu JMCli = new JMenu("Clientes");
		JMCli.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMCli.setVisible(clientes.isVisualizar());
		menuBar.add(JMCli);

		JMICliCad = new JMenuItem("Novo");
		JMICliCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMICliCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		JMICliCad.addActionListener(this);
		JMICliCad.setVisible(clientes.isCadastrar());
		JMCli.add(JMICliCad);

		JMICliBuscar = new JMenuItem("Buscar");
		JMICliBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMICliBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		JMICliBuscar.addActionListener(this);
		JMCli.add(JMICliBuscar);

		// MENU EVENTOS
		JMEventos = new JMenu("Eventos");
		JMEventos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMEventos.setEnabled(false);
		JMEventos.setVisible(eventos.isVisualizar());

		mnOramentos = new JMenu("Or\u00E7amentos");
		mnOramentos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnOramentos.setVisible(orcamento.isVisualizar());
		menuBar.add(mnOramentos);

		mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EditFormOrcamento ef = new EditFormOrcamento();
				ef.setLocationRelativeTo(null);
				ef.setVisible(true);

			}
		});
		mntmNovo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNovo.setVisible(orcamento.isCadastrar());
		mnOramentos.add(mntmNovo);

		mntmBuscar_2 = new JMenuItem("Buscar");
		mntmBuscar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarOrcamentos bo = new BuscarOrcamentos();
				bo.setVisible(true);
			}
		});
		mntmBuscar_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnOramentos.add(mntmBuscar_2);
		menuBar.add(JMEventos);

		mntmCadastrar = new JMenuItem("Novo");
		mntmCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmCadastrar
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mntmCadastrar.setVisible(eventos.isCadastrar());
		JMEventos.add(mntmCadastrar);

		mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		JMEventos.add(mntmBuscar);

		// MENU LEMBRETES
		JMenu JMLemb = new JMenu("Lembretes");
		JMLemb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		// permissão de visualização dos lembretes
		JMLemb.setVisible(lembretes.isVisualizar());
		menuBar.add(JMLemb);

		JMILembCad = new JMenuItem("Novo");
		JMILembCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMILembCad.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_PAGE_UP, 0));
		JMILembCad.addActionListener(this);
		// permissão de cadastrdo dos lembretes
		JMILembCad.setVisible(lembretes.isCadastrar());
		JMLemb.add(JMILembCad);

		JMILembBuscar = new JMenuItem("Buscar");
		JMILembBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMILembBuscar.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_PAGE_DOWN, 0));
		JMILembBuscar.addActionListener(this);
		JMLemb.add(JMILembBuscar);

		// MENU FINANCEIRO
		JMenu JMFin = new JMenu("Financeiro");
		JMFin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMFin.setEnabled(false);
		JMFin.setVisible(financeiro.isVisualizar());
		menuBar.add(JMFin);

		JMIFinSincInf = new JMenuItem("Sincronizar info.");
		JMIFinSincInf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFinSincInf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				InputEvent.CTRL_MASK));
		JMIFinSincInf.addActionListener(this);
		JMFin.add(JMIFinSincInf);

		JMIFinFormPag = new JMenuItem("Formas de pag.");
		JMIFinFormPag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFinFormPag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				InputEvent.CTRL_MASK));
		JMIFinFormPag.addActionListener(this);
		JMFin.add(JMIFinFormPag);

		// MENU TIPO PAGAMENTO
		JMenu JMTipoDePagamento = new JMenu("Tipo de pagamento");
		JMTipoDePagamento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMFin.add(JMTipoDePagamento);

		JMenuItem JMITpPagCad = new JMenuItem("Cadastrar");
		JMITpPagCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMITpPagCad.addActionListener(this);
		JMTipoDePagamento.add(JMITpPagCad);

		JMenuItem JMITpPagBuscar = new JMenuItem("Buscar");
		JMITpPagBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMITpPagBuscar.addActionListener(this);
		JMTipoDePagamento.add(JMITpPagBuscar);

		// MENU FORMAS DE PAGAMENTO
		JMenu JMFormasDePagamento = new JMenu("Formas de pagamento");
		JMFormasDePagamento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMFin.add(JMFormasDePagamento);

		JMenuItem JMIFormPagCad = new JMenuItem("Cadastrar");
		JMIFormPagCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFormPagCad.addActionListener(this);
		JMFormasDePagamento.add(JMIFormPagCad);

		JMenuItem JMIFormPagBuscar = new JMenuItem("Buscar");
		JMIFormPagBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIFormPagBuscar.addActionListener(this);
		JMFormasDePagamento.add(JMIFormPagBuscar);

		// MENU RELATORIOS
		JMenu JMRel = new JMenu("Relat\u00F3rios");
		JMRel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMRel.setEnabled(false);
		menuBar.add(JMRel);

		JMIRelCont = new JMenu("Contrato");
		JMIRelCont.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMRel.add(JMIRelCont);

		JMIRelContFormComp = new JMenuItem("Formatura completa");
		JMIRelContFormComp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelContFormComp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				InputEvent.CTRL_MASK));
		JMIRelContFormComp.addActionListener(this);
		JMIRelCont.add(JMIRelContFormComp);

		JMIRelContFormCusto = new JMenuItem("Formatura customizada");
		JMIRelContFormCusto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelContFormCusto.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_4, InputEvent.CTRL_MASK));
		JMIRelContFormCusto.addActionListener(this);
		JMIRelCont.add(JMIRelContFormCusto);

		JMenu JMIRelEvent = new JMenu("Eventos");
		JMIRelEvent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMRel.add(JMIRelEvent);

		JMIRelEventSerRea = new JMenuItem("A ser realizado");
		JMIRelEventSerRea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelEventSerRea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
				InputEvent.CTRL_MASK));
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventSerRea);

		JMIRelEventForn = new JMenuItem("Fornecedores");
		JMIRelEventForn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelEventForn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,
				InputEvent.CTRL_MASK));
		JMIRelEventSerRea.addActionListener(this);
		JMIRelEvent.add(JMIRelEventForn);

		JMenu JMIRelImp = new JMenu("Impress\u00E3o");
		JMIRelImp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMRel.add(JMIRelImp);

		JMIRelImpOrc = new JMenuItem("Or\u00E7amento");
		JMIRelImpOrc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelImpOrc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,
				InputEvent.CTRL_MASK));
		JMIRelImpOrc.addActionListener(this);
		JMIRelImp.add(JMIRelImpOrc);

		JMIRelImpCont = new JMenuItem("Contrato");
		JMIRelImpCont.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIRelImpCont.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8,
				InputEvent.CTRL_MASK));
		JMIRelImpCont.addActionListener(this);
		JMIRelImp.add(JMIRelImpCont);

		// MENU USUARIOS
		JMenu JMUsu = new JMenu("Usu\u00E1rios");
		JMUsu.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		// permissão de visualização do menu usuários
		JMUsu.setVisible(usuarios.isVisualizar());
		menuBar.add(JMUsu);

		JMIUsuCad = new JMenuItem("Novo");
		JMIUsuCad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIUsuCad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
		JMIUsuCad.addActionListener(this);
		JMUsu.setVisible(usuarios.isCadastrar());
		JMUsu.add(JMIUsuCad);

		JMIUsuBuscar = new JMenuItem("Buscar");
		JMIUsuBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMIUsuBuscar
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0));
		JMIUsuBuscar.addActionListener(this);
		JMUsu.add(JMIUsuBuscar);

		// MENU PERFIL
		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnPerfil.setVisible(perfis.isVisualizar());
		JMUsu.add(mnPerfil);

		JMenuItem mntmCadastrar_3 = new JMenuItem("Novo");
		mntmCadastrar_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmCadastrar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditFormPerfil efp = new EditFormPerfil(null, null);
				efp.setVisible(true);
			}
		});
		mntmCadastrar_3.setVisible(perfis.isCadastrar());
		mnPerfil.add(mntmCadastrar_3);

		JMenuItem mntmBuscar_3 = new JMenuItem("Buscar");
		mntmBuscar_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmBuscar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarPerfis bp = new BuscarPerfis();
				bp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				bp.setVisible(true);
			}
		});
		mnPerfil.add(mntmBuscar_3);

		// MENU MODULOS
		JMenu mnPermisso = new JMenu("M\u00F3dulos");
		mnPermisso.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMUsu.add(mnPermisso);

		JMenuItem mntmCadastrar_4 = new JMenuItem("Novo");
		mntmCadastrar_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmCadastrar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditFormModulo efm = new EditFormModulo(null, null);
				efm.setVisible(true);
			}
		});
		mntmCadastrar_4.setVisible(modulos.isCadastrar());
		mnPermisso.add(mntmCadastrar_4);

		JMenuItem mntmBuscar_4 = new JMenuItem("Buscar");
		mntmBuscar_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmBuscar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarModulos bm = new BuscarModulos();
				bm.setVisible(true);
			}
		});

		mnPermisso.add(mntmBuscar_4);

		user = new JMenu(Extras.getUsuarioLogado().getUsuario());
		user.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(user);

		mntmTrocarSenha = new JMenuItem("Trocar senha");
		mntmTrocarSenha.addActionListener(this);
		mntmTrocarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		user.add(mntmTrocarSenha);

		mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmSair.addActionListener(this);
		user.add(mntmSair);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/Img/DESKTOP_GDA.png")));
		label.setOpaque(true);
		contentPane.add(label);

		buscarLembretes();

	}

	@Override
	public void actionPerformed(ActionEvent acao) {

		/* JMenu Fornecedores */
		if (acao.getSource() == JMIFornCad) {
			EditFormFornecedor jdtcf;

			try {
				jdtcf = new EditFormFornecedor(null, null);
				jdtcf.setLocationRelativeTo(null);
				jdtcf.setResizable(false);
				jdtcf.setVisible(true);

			} catch (ParseException e) {

				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemFornecedores Cadastro

		if (acao.getSource() == JMIFornBuscar) {
			BuscarFornecedores jdtbf = new BuscarFornecedores();
			jdtbf.setLocationRelativeTo(null);
			jdtbf.setResizable(false);
			jdtbf.setVisible(true);
		}

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Serviços */
		if (acao.getSource() == JMIServCad) {
			EditFormItem jdtcs = new EditFormItem(0, null);
			jdtcs.setLocationRelativeTo(null);
			jdtcs.setResizable(false);
			jdtcs.setVisible(true);
		}// final do JMenuItemServiços Cadastro

		if (acao.getSource() == JMIServBuscar) {
			BuscarItens jdtbs = new BuscarItens();
			jdtbs.setLocationRelativeTo(null);
			jdtbs.setResizable(false);
			jdtbs.setVisible(true);
		}

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Clientes */
		if (acao.getSource() == JMICliCad) {

			try {
				EditFormCliente jdtcc = new EditFormCliente();
				jdtcc.setLocationRelativeTo(null);
				jdtcc.setResizable(false);
				jdtcc.setVisible(true);
			} catch (ParseException e) {

				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscaras", JOptionPane.ERROR_MESSAGE);
			}// necessário devido a máscara e não ter que repetir várias
				// instruções try e Catch

		}// final do JMenuItemClientes Cadastrar

		if (acao.getSource() == JMICliBuscar) {
			BuscarClientes jdtbc = new BuscarClientes();
			jdtbc.setLocationRelativeTo(null);
			jdtbc.setResizable(false);
			jdtbc.setVisible(true);
		}

		/*
		 * ======================================================================
		 * ==
		 * ====================================================================
		 * ==
		 */

		/* JMenu Lembretes */
		if (acao.getSource() == JMILembCad) {
			EditFormLembrete jdtcl = new EditFormLembrete();
			jdtcl.setLocationRelativeTo(null);
			jdtcl.setResizable(false);
			jdtcl.setVisible(true);
		}// final do JMenuItemLembretes Cadastrar

		if (acao.getSource() == JMILembBuscar) {
			BuscarLembretes jdtbl = new BuscarLembretes();
			jdtbl.setResizable(false);
			jdtbl.setLocationRelativeTo(null);
			jdtbl.setVisible(true);

		}

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
				EditFormUsuario jdtcu = new EditFormUsuario(null, null);
				jdtcu.setResizable(false);
				jdtcu.setVisible(true);
				jdtcu.setLocationRelativeTo(null);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Erro ao carregar máscara", JOptionPane.ERROR_MESSAGE);
			}

		}// final do JMenuItemUsuário Cadastrar

		if (acao.getSource() == JMITSCad) {
			EditFormTipoItem ef = new EditFormTipoItem();
			ef.setResizable(false);
			ef.setLocationRelativeTo(null);
			ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ef.setVisible(true);
		}

		if (acao.getSource() == JMITSBuscar) {
			BuscarTipoItens bts = new BuscarTipoItens();
			bts.setResizable(false);
			bts.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			bts.setLocationRelativeTo(null);
			bts.setVisible(true);
		}

		if (acao.getSource() == JMIUsuBuscar) {
			BuscarUsuarios jdtbu = new BuscarUsuarios();
			jdtbu.setLocationRelativeTo(null);
			jdtbu.setResizable(false);
			jdtbu.setVisible(true);
		}

		if (acao.getSource() == mntmSair) {
			TelaLogin login = new TelaLogin();
			login.setVisible(true);
			this.dispose();
		}
		if (acao.getSource() == mntmTrocarSenha) {
			TrocarSenha ts = new TrocarSenha(Extras.getUsuarioLogado());
			ts.setLocationRelativeTo(null);
			ts.setVisible(true);
		}

	}// final da ação do menu

	private void buscarLembretes() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					int c = new LembretesControl().buscarLembretesUsuario();
					System.out.println(c);

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}).start();
	}
}
