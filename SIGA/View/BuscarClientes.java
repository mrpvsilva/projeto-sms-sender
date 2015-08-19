package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Control.ClientesControl;
import Dominio.Cliente;
import Dominio.GridRecords;
import Dominio.Permissao;
import TableModels.ClienteTableModel;
import TableModels.DefaultTableModel;
import Util.Modulos;
import Util.PermissoesManager;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BuscarClientes extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JBCadCli;
	// private JButton JBEditCli;
	protected String valor;
	private JButton JBSair;
	private Permissao Clientes;
	private JPanel panel;
	private JLabel lblPesquisa;
	private JTextField pesquisa;
	private JRadioButton radiorg;
	private DefaultTableModel<Cliente> modelClientes;
	private ClientesControl controller;
	private JTable table;
	private JButton btnAlterar;
	private JRadioButton radiocpf, radionome;
	private JLabel emptyList;
	private JTextField pageIndex;
	private GridRecords<Cliente> gridRecord;
	private JComboBox recordCount;
	private JLabel totalRecord;
	private JLabel lblPg;
	private JLabel lblDe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarClientes dialog = new BuscarClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarClientes() {
		controller = new ClientesControl();
		modelClientes = new ClienteTableModel();
		Clientes = PermissoesManager.buscarPermissao(Modulos.Clientes);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BuscarClientes.class.getResource("/Img/LOGO_LOGIN_GDA.png")));
		setTitle("SIGA - buscar clientes");
		setBounds(100, 100, 797, 729);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pesquisar",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 11, 765, 107);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPesquisa.setBounds(57, 36, 84, 15);
		panel.add(lblPesquisa);

		pesquisa = new JTextField();
		pesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == 10)
					pesquisarClientes();
				else
					modelClientes.clear();
			}

		});
		pesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pesquisa.setBounds(151, 34, 323, 20);
		panel.add(pesquisa);
		pesquisa.setColumns(10);

		radionome = new JRadioButton("Nome");
		radionome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelClientes.clear();
			}
		});

		radionome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radionome.setSelected(true);
		radionome.setBounds(151, 56, 58, 23);

		radiocpf = new JRadioButton("CPF");
		radiocpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelClientes.clear();
			}
		});
		radiocpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radiocpf.setBounds(211, 56, 47, 23);

		radiorg = new JRadioButton("RG");
		radiorg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radiorg.setBounds(260, 56, 47, 23);
		radiorg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelClientes.clear();
			}
		});

		ButtonGroup radiogroup = new ButtonGroup();
		radiogroup.add(radionome);
		radiogroup.add(radiocpf);
		radiogroup.add(radiorg);

		panel.add(radionome);
		panel.add(radiocpf);
		panel.add(radiorg);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					pesquisarClientes();
			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisarClientes();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPesquisar.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/Procurar.png")));
		btnPesquisar.setBounds(484, 33, 118, 23);
		panel.add(btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setToolTipText("Clique duas vezes para visualizar o cliente");
		scrollPane.setBounds(10, 129, 765, 483);
		contentPanel.add(scrollPane);

		table = new JTable(modelClientes);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					try {
						int linha = table.getSelectedRow();
						Cliente c = modelClientes.find(linha);
						EditFormCliente jf = new EditFormCliente(false, c);
						jf.setVisible(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		scrollPane.setViewportView(table);

		emptyList = new JLabel("Nenhum registro encontrado");
		emptyList.setVisible(false);
		emptyList.setHorizontalAlignment(SwingConstants.RIGHT);
		emptyList.setFont(new Font("Tahoma", Font.BOLD, 13));
		emptyList.setBounds(561, 615, 214, 15);
		contentPanel.add(emptyList);

		JPanel pagination = new JPanel();
		pagination.setBounds(73, 615, 503, 40);
		contentPanel.add(pagination);

		JButton btnIn = new JButton("");
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = Integer.parseInt(pageIndex.getText());

				if (index == 1)
					return;

				pageIndex.setText("1");
				pesquisarClientes();
			}
		});
		btnIn.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/back.png")));
		btnIn.setSize(10, 10);
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(btnIn);

		JButton btnBackc = new JButton("");
		btnBackc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(pageIndex.getText());

				if (index == 1)
					return;
				index--;
				System.out.println(index);
				pageIndex.setText(index < 1 ? "1" : index + "");
				pesquisarClientes();

			}
		});
		btnBackc.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/rewind.png")));
		btnBackc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(btnBackc);

		lblPg = new JLabel("P\u00E1g.");
		pagination.add(lblPg);

		pageIndex = new JTextField();
		pageIndex.setText("1");
		pageIndex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(pageIndex);
		pageIndex.setColumns(1);

		lblDe = new JLabel("de");
		pagination.add(lblDe);

		totalRecord = new JLabel("de 2");
		totalRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(totalRecord);

		JButton btnForward = new JButton("");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(pageIndex.getText());

				if (index == gridRecord.getPageSize())
					return;

				index++;

				pageIndex.setText(index > gridRecord.getPageSize() ? gridRecord
						.getPageSize() + "" : index + "");
				pesquisarClientes();
			}
		});
		btnForward.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/forwind.png")));
		btnForward.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(btnForward);

		JButton btnFim = new JButton("");
		btnFim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(pageIndex.getText());

				if (index == gridRecord.getPageSize())
					return;

				pageIndex.setText(gridRecord.getPageSize() + "");
				pesquisarClientes();
			}
		});
		btnFim.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/forward.png")));
		btnFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pagination.add(btnFim);

		recordCount = new JComboBox();
		recordCount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				pesquisarClientes();
			}
		});
		recordCount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		recordCount.setModel(new DefaultComboBoxModel(new String[] { "1", "10",
				"20", "30", "50", "100" }));
		pagination.add(recordCount);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JBCadCli = new JButton("Cadastrar");
		JBCadCli.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/save16.png")));
		JBCadCli.addActionListener(this);
		JBCadCli.setMnemonic(KeyEvent.VK_C);
		JBCadCli.setVisible(Clientes.isCadastrar());
		buttonPane.add(JBCadCli);

		JBSair = new JButton("Sair");
		JBSair.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/exit16.png")));
		JBSair.addActionListener(this);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = table.getSelectedRow();
				if (linha < 0) {
					JOptionPane.showMessageDialog(null,
							"Selecione uma cliente", "Atenção",
							JOptionPane.WARNING_MESSAGE);

					return;
				}

				Cliente c = modelClientes.find(linha);

				try {
					EditFormCliente cdtcc = new EditFormCliente(true, c);
					cdtcc.setVisible(true);
					cdtcc.setLocationRelativeTo(null);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao a tela de edição do cliente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}// final do try e catch
			}
		});
		btnAlterar.setIcon(new ImageIcon(BuscarClientes.class
				.getResource("/Img/edit_add16.png")));
		buttonPane.add(btnAlterar);
		btnAlterar.setVisible(Clientes.isAlterar());
		JBSair.setMnemonic(KeyEvent.VK_Q);
		buttonPane.add(JBSair);
		pesquisarClientes();

	}

	private void pesquisarClientes() {
		if (gridRecord == null)
			gridRecord = new GridRecords<Cliente>();

		String txt = pesquisa.getText();
		String campo = radiocpf.isSelected() ? "cpfcnpj"
				: radiorg.isSelected() ? "rg"
						: radionome.isSelected() ? "nomecompleto" : "";

		gridRecord.setPageIndex(Integer.parseInt(pageIndex.getText()));
		gridRecord.setRecordsCount(Integer.parseInt(recordCount
				.getSelectedItem().toString()));
		controller._listarTodos(txt, campo, gridRecord);
		modelClientes.setLinhas(gridRecord.getLista());
		pageIndex.setText(gridRecord.getPageIndex() + "");
		totalRecord.setText(gridRecord.getPageSize() + "");
		emptyList.setVisible(gridRecord.isEmptyRecords());

	}

	// private void pesquisar() {
	//
	// String txt = pesquisa.getText();
	// String campo = radiocpf.isSelected() ? "cpfcnpj"
	// : radiorg.isSelected() ? "rg"
	// : radionome.isSelected() ? "nomecompleto" : "";
	// if (txt.length() > 0) {
	// modelClientes.setLinhas(controller.pesquisar(txt, campo));
	// System.out.println(modelClientes.getRowCount());
	//
	// if (modelClientes.getRowCount() == 0)
	// emptyList.setVisible(true);
	// else
	// emptyList.setVisible(false);
	//
	// } else {
	// modelClientes.clear();
	// emptyList.setVisible(false);
	// }
	//
	// }

	@Override
	public void actionPerformed(ActionEvent acao) {

		if (acao.getSource() == JBCadCli) {
			PesquisarNovoCliente cdtcc = new PesquisarNovoCliente();
			cdtcc.setLocationRelativeTo(null);
			cdtcc.setVisible(true);

		}// final do botão cadastrar cliente

		if (acao.getSource() == JBSair) {
			this.dispose();
		}

	}// final da ação do botão
}
