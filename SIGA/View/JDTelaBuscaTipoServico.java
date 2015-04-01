package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.TipoServicoControl;
import TableModels.TipoServicoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class JDTelaBuscaTipoServico extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TipoServicoTableModel model;
	private TipoServicoControl tipoServicoControl = new TipoServicoControl();
	private JTextField tfNome;
	private JComboBox cbAtivo;
	private JButton JBPesquisar;
	private JButton JBCadastrar;
	private JButton JBEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDTelaBuscaTipoServico dialog = new JDTelaBuscaTipoServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDTelaBuscaTipoServico() {
		setTitle("SIGA - buscar tipo de servi\u00E7o");
		setBounds(100, 100, 450, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 414, 280);
		contentPanel.add(scrollPane);

		model = new TipoServicoTableModel(tipoServicoControl.listarTodos());
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		scrollPane.setViewportView(table);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(32, 11, 46, 14);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(93, 8, 285, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblAtivo = new JLabel("Ativo");
		lblAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtivo.setBounds(32, 34, 46, 14);
		contentPanel.add(lblAtivo);

		cbAtivo = new JComboBox(new String[] { "Todos", "Ativo", "Inativo" });
		cbAtivo.setBounds(93, 31, 141, 20);
		contentPanel.add(cbAtivo);

		JBPesquisar = new JButton("Pesquisar");
		JBPesquisar.setMnemonic(KeyEvent.VK_F);
		JBPesquisar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class.getResource("/Img/Procurar.png")));
		JBPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisar();
			}
		});
		JBPesquisar.setBounds(178, 65, 120, 23);
		contentPanel.add(JBPesquisar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JBCadastrar = new JButton("Cadastrar");
				JBCadastrar.setMnemonic(KeyEvent.VK_C);
				JBCadastrar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class.getResource("/Img/save16.png")));
				JBCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(
								model, 0);

						tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						tscad.setVisible(true);
					}
				});
				buttonPane.add(JBCadastrar);
				getRootPane().setDefaultButton(JBCadastrar);
			}
			{
				JBEditar = new JButton("Edit");
				JBEditar.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class.getResource("/Img/edit_add16.png")));
				JBEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int linha = table.getSelectedRow();
						if (linha > -1) {
							int id = model.getId(linha);
							JDTelaCadTipoServico tscad = new JDTelaCadTipoServico(
									model, id);

							tscad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							tscad.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null,
									"Selecione uma linha");
						}
					}
				});
				JBEditar.setMnemonic(KeyEvent.VK_A);
				buttonPane.add(JBEditar);
			}
			
			JButton JBSair = new JButton("Sair");
			JBSair.setIcon(new ImageIcon(JDTelaBuscaTipoServico.class.getResource("/Img/exit16.png")));
			JBSair.addActionListener(this);
			JBSair.setMnemonic(KeyEvent.VK_Q);
			buttonPane.add(JBSair);
		}
	}

	private void pesquisar() {

		String nome = tfNome.getText();
		String ativo = cbAtivo.getSelectedItem().toString();

		model.setTipoServico(tipoServicoControl.listarTodos(nome, ativo));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
