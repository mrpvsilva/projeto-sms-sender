package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.hibernate.HibernateException;

import Dominio.Modulo;
import Interfaces.IRepositoryBase;
import Repositories.ModuloRepository;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarModulo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private IRepositoryBase<Modulo> repository;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarModulo frame = new CadastrarModulo();
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
	public CadastrarModulo() {
		repository = new ModuloRepository();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(52, 41, 322, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Modulo m = new Modulo();
					m.setNome(textField.getText());
					repository.add(m);
					System.out.println(m.getNome() + " Cadastrado");
					textField.setText("");
				} catch (HibernateException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(151, 136, 158, 23);
		contentPane.add(btnNewButton);
	}
}
