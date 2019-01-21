package model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import Utility.LoginFaildException;
import Utility.NessunaPaginaTrovataException;
import Utility.NoDriverFounfException;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;
import logic.SeleniumLogic;
import views.ImpostazioniJFrame;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JFrame frame=this;
	private ImpostazioniJFrame impostazioniJFrame;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		//crea la cartella per il file di properties se già non esiste
		try {
			
			File theDir = new File(System.getProperty("user.home")+"/FindYourInfluencer");
			File theFile = new File(System.getProperty("user.home")+"/FindYourInfluencer/config.properties");
			
			if (!theDir.exists()) {
				theDir.mkdir();
				PropertiesService.createPropertiesFile(theDir+"/config.properties");
			} else if (!theFile.exists()) {
				PropertiesService.createPropertiesFile(theDir+"/config.properties");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Non riesco a creare a cartella.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
			System.exit(0);
		}
		
		//used by Selenium
		try {
			PropertiesService.init();
		} catch (Exception e1) {
			e1.printStackTrace();
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
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

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


		JButton btnEseguiRicerca = new JButton("Esegui ricerca");
		btnEseguiRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog dlg = new JDialog(frame, "Progress Dialog", false);
				JProgressBar progressBar=new JProgressBar();
				progressBar.setIndeterminate(true);
				progressBar.setSize(200, 10);
				dlg.getContentPane().add(BorderLayout.CENTER, progressBar);
				dlg.getContentPane().add(BorderLayout.NORTH, new JLabel("Il driver pricipale sta processando la ricerca\n, non chiudere i browser ed attendere..."));
				dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				dlg.setSize(500, 80);
				dlg.setLocationRelativeTo(frame);
				progressBar.setVisible(true);
				dlg.setVisible(true);

				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						btnEseguiRicerca.setEnabled(false);
						btnNewButton.setEnabled(false);
						try {
							new SeleniumLogic(textFieldSearch.getText(),dlg,btnEseguiRicerca).startSeleniumLogic();;
						} catch (PropertiesNotFoundException e) {
							e.printStackTrace();
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
						} catch (ConfigFileNotFoundException h) {
							h.printStackTrace();
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
						} catch (NessunaPaginaTrovataException l) {
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Nessuna pagina trovata.","Attenzione!",JOptionPane.PLAIN_MESSAGE,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
						} catch (LoginFaildException p) {
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Non sono riusito ad effettuare il login, controlla le credenziali","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
						} catch (NoDriverFounfException k){
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Non ho trovato il driver di Chrome, ricontrolla il path!","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
						}finally {
							btnEseguiRicerca.setEnabled(true);
							btnNewButton.setEnabled(true);
							dlg.setVisible(false);
						}

					}
				});
				t.start();



			}
		});
		btnEseguiRicerca.setBounds(314, 58, 130, 25);
		contentPane.add(btnEseguiRicerca);

		frame.getRootPane().setDefaultButton(btnEseguiRicerca);

		btnNewButton = new JButton("Impostazioni");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (impostazioniJFrame==null) {
					impostazioniJFrame=new ImpostazioniJFrame();
				}
				impostazioniJFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(16, 122, 110, 23);
		contentPane.add(btnNewButton);

		JLabel lblFindYourInflucer = new JLabel("DRIVE OUT THE INFLUENCER!");
		lblFindYourInflucer.setBounds(161, 11, 213, 14);
		contentPane.add(lblFindYourInflucer);

		JLabel lblPoweredByNodelab = new JLabel("Powered By NodeLab");
		lblPoweredByNodelab.setBounds(334, 147, 130, 14);
		contentPane.add(lblPoweredByNodelab);

		JLabel lblDevelopementTeam = new JLabel("Developement Team");
		lblDevelopementTeam.setBounds(334, 163, 130, 14);
		contentPane.add(lblDevelopementTeam);

	}
}
