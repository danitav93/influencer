package model;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import views.InfluencerModel;
import views.ListOfInfluencerJDialog;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblDa;
	private JLabel lblNumeroPost;
	private JTextField textField_5;
	private JLabel lblA;
	private JTextField textField_6;
	private JTextField textField_7;
	private JFrame frame=this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblParolaChiave = new JLabel("Search");
		lblParolaChiave.setBounds(22, 62, 83, 16);
		contentPane.add(lblParolaChiave);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(80, 59, 278, 22);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Peso Like");
		lblNewLabel_1.setBounds(22, 110, 56, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblPesoCommenti = new JLabel("Peso commenti");
		lblPesoCommenti.setBounds(151, 110, 105, 16);
		contentPane.add(lblPesoCommenti);

		JLabel lblPesoCondivisioni = new JLabel("Peso Condivisioni");
		lblPesoCondivisioni.setBounds(292, 110, 128, 16);
		contentPane.add(lblPesoCondivisioni);

		textField_2 = new JTextField();
		textField_2.setBounds(32, 128, 25, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(178, 128, 25, 22);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(333, 128, 25, 22);
		contentPane.add(textField_4);




		lblDa = new JLabel("Da (gg/mm/aaaa)");
		lblDa.setBounds(141, 184, 104, 16);
		contentPane.add(lblDa);

		lblNumeroPost = new JLabel("peso post");
		lblNumeroPost.setBounds(22, 184, 83, 16);
		contentPane.add(lblNumeroPost);

		textField_5 = new JTextField();
		textField_5.setBounds(32, 213, 25, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		lblA = new JLabel("a (gg/mm/aaaa)");
		lblA.setBounds(292, 184, 105, 16);
		contentPane.add(lblA);




		JButton btnEseguiRicerca = new JButton("Esegui ricerca");
		btnEseguiRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog dlg = new JDialog(frame, "Progress Dialog", false);
				JProgressBar progressBar=new JProgressBar();
				progressBar.setIndeterminate(true);
				progressBar.setSize(100, 10);
			    dlg.getContentPane().add(BorderLayout.CENTER, progressBar);
			    dlg.getContentPane().add(BorderLayout.NORTH, new JLabel("Progress..."));
			    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			    dlg.setSize(300, 75);
			    dlg.setLocationRelativeTo(frame);
			    progressBar.setVisible(true);
			    dlg.setVisible(true);
				
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						try {
						/*List<InfluencerModel> list= FacebookLogic.getListOfInfluencers(textFieldSearch.getText());
						ListOfInfluencerJDialog dialog = new ListOfInfluencerJDialog(list); 
						dialog.setVisible(true);*/
						} catch (Exception e) {
							e.printStackTrace();
						}
						dlg.dispose();

					}
				});
				t.start();
						
				
				
			}
		});
		btnEseguiRicerca.setBounds(135, 324, 152, 25);
		contentPane.add(btnEseguiRicerca);

		textField_6 = new JTextField();
		textField_6.setBounds(151, 213, 84, 22);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(304, 213, 84, 22);
		contentPane.add(textField_7);

	}
}
