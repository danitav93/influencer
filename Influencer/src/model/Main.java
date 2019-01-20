package model;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Utility.ConfigFileNotFoundException;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;
import logic.SeleniumLogic;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JFrame frame=this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//carico il file di properties
		try {
			PropertiesService.init();
		} catch (Exception e1) {
			e1.printStackTrace();
			 int dialogButton = JOptionPane.YES_OPTION;
			 JOptionPane.showConfirmDialog (null, "Errore con il file di configurazione. Controlla la presenza del file config.properties","Errore!",dialogButton);
		    System.exit(0);
		}

		
		
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
		setBounds(100, 100, 503, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblParolaChiave = new JLabel("Search");
		lblParolaChiave.setBounds(22, 62, 83, 16);
		contentPane.add(lblParolaChiave);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(80, 59, 222, 22);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);




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
						new SeleniumLogic(textFieldSearch.getText(),dlg).startSeleniumLogic();;
						} catch (PropertiesNotFoundException e) {
							e.printStackTrace();
							 int dialogButton = JOptionPane.YES_OPTION;
							 JOptionPane.showConfirmDialog (null, "Errore con il file di configurazione. Properties non trovata","Errore!",dialogButton);
						    System.exit(0);
						} catch (ConfigFileNotFoundException h) {
							h.printStackTrace();
							 int dialogButton = JOptionPane.YES_OPTION;
							 JOptionPane.showConfirmDialog (null, "Errore con il file di configurazione. Controlla la presenza del file config.properties","Errore!",dialogButton);
						    System.exit(0);
						}

					}
				});
				t.start();
						
				
				
			}
		});
		btnEseguiRicerca.setBounds(314, 58, 130, 25);
		contentPane.add(btnEseguiRicerca);
		
		JButton btnNewButton = new JButton("Impostazioni");
		btnNewButton.setBounds(16, 122, 110, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblFindYourInflucer = new JLabel("Find your influcer!");
		lblFindYourInflucer.setBounds(182, 11, 120, 14);
		contentPane.add(lblFindYourInflucer);
		
		JLabel lblPoweredByNodelab = new JLabel("Powered By NodeLab");
		lblPoweredByNodelab.setBounds(334, 147, 130, 14);
		contentPane.add(lblPoweredByNodelab);
		
		JLabel lblDevelopementTeam = new JLabel("Developement Team");
		lblDevelopementTeam.setBounds(334, 163, 130, 14);
		contentPane.add(lblDevelopementTeam);

	}
}
