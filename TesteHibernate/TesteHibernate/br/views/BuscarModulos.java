package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Dominio.Modulo;
import Interfaces.IRepositoryBase;
import Repositories.ModuloRepository;
import TableModels.DefaultTableModel;
import TableModels.ModuloTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarModulos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel<Modulo> model;
	private IRepositoryBase<Modulo> repository;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarModulos frame = new BuscarModulos();
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
	public BuscarModulos() {
		repository = new ModuloRepository();
		model = new ModuloTableModel(repository.findAll());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 324, 359);
		contentPane.add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setLinhas(repository.findAll());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(126, 28, 89, 23);
		contentPane.add(btnNewButton);
	}
}
