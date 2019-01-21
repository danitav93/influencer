package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utility.MyFloatFilter;
import Utility.MyIntFilter;
import Utility.PropertiesService;
import logic.Constants;

public class ImpostazioniJFrame extends JFrame {

	private static final long serialVersionUID = -8949582788957421959L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_driver;
	private JTextField textField_pgscroll;
	private JTextField textField_postscroll;
	private JFileChooser fc;
	private JTextField textField_email;
	private JTextField textField_password;
	private JTextField textField_mediaLike;
	private JTextField textField_mediaCommenti;
	private JTextField textField_mediaPostGiornalieri;
	private JTextField textField_mediaCondivisioni;
	private JTextField textField_mediaVisualizzazioni;
	private JTextField textField_MiPiace;
	private JTextField textField_followers;
	private JTextField textField_arcotemporale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImpostazioniJFrame frame = new ImpostazioniJFrame();
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
	public ImpostazioniJFrame() {
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Parametri Influencer", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblPesoMediaLike = new JLabel("Peso media Like:");
		lblPesoMediaLike.setBounds(10, 11, 175, 14);
		panel_3.add(lblPesoMediaLike);
		
		textField_mediaLike = new JTextField();
		textField_mediaLike.addKeyListener(new MyFloatFilter(textField_mediaLike));
		textField_mediaLike.setText("0");
		textField_mediaLike.setColumns(10);
		textField_mediaLike.setBounds(195, 11, 53, 20);
		panel_3.add(textField_mediaLike);
		try {
			textField_mediaLike.setText(PropertiesService.getStringProperty("mediaLikeScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		JLabel lblPesoMediaCommenti = new JLabel("Peso media commenti:");
		lblPesoMediaCommenti.setBounds(10, 36, 175, 14);
		panel_3.add(lblPesoMediaCommenti);
		
		textField_mediaCommenti = new JTextField();
		textField_mediaCommenti.setText("0");
		textField_mediaCommenti.setColumns(10);
		textField_mediaCommenti.setBounds(195, 36, 53, 20);
		textField_mediaCommenti.addKeyListener(new MyFloatFilter(textField_mediaCommenti));
		try {
			textField_mediaCommenti.setText(PropertiesService.getStringProperty("mediaCommentiScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		panel_3.add(textField_mediaCommenti);
		
		JLabel lblPesoMediaPost = new JLabel("Peso media post girnalieri:");
		lblPesoMediaPost.setBounds(10, 61, 175, 14);
		panel_3.add(lblPesoMediaPost);
		
		textField_mediaPostGiornalieri = new JTextField();
		textField_mediaPostGiornalieri.setText("0");
		textField_mediaPostGiornalieri.setColumns(10);
		textField_mediaPostGiornalieri.setBounds(195, 61, 53, 20);
		panel_3.add(textField_mediaPostGiornalieri);
		textField_mediaPostGiornalieri.addKeyListener(new MyFloatFilter(textField_mediaPostGiornalieri));
		try {
			textField_mediaPostGiornalieri.setText(PropertiesService.getStringProperty("mediaPostGiornalieraScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		JLabel lblPesoMediaCondivisioni = new JLabel("Peso media condivisioni:");
		lblPesoMediaCondivisioni.setBounds(10, 86, 175, 14);
		panel_3.add(lblPesoMediaCondivisioni);
		
		textField_mediaCondivisioni = new JTextField();
		textField_mediaCondivisioni.setText("0");
		textField_mediaCondivisioni.setColumns(10);
		textField_mediaCondivisioni.setBounds(195, 86, 53, 20);
		panel_3.add(textField_mediaCondivisioni);
		textField_mediaCondivisioni.addKeyListener(new MyFloatFilter(textField_mediaCondivisioni));
		try {
			textField_mediaCondivisioni.setText(PropertiesService.getStringProperty("mediaCondivisioniScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		
		JLabel lblPesoMediaVisualizzazioni = new JLabel("Peso media visualizzazioni:");
		lblPesoMediaVisualizzazioni.setBounds(10, 111, 175, 14);
		panel_3.add(lblPesoMediaVisualizzazioni);
		
		textField_mediaVisualizzazioni = new JTextField();
		textField_mediaVisualizzazioni.setText("0");
		textField_mediaVisualizzazioni.setColumns(10);
		textField_mediaVisualizzazioni.setBounds(195, 111, 53, 20);
		panel_3.add(textField_mediaVisualizzazioni);
		textField_mediaVisualizzazioni.addKeyListener(new MyFloatFilter(textField_mediaVisualizzazioni));
		try {
			textField_mediaVisualizzazioni.setText(PropertiesService.getStringProperty("mediaVisualizzazioniScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		JLabel lblPesoMiPiace = new JLabel("Peso mi piace alla pagina:");
		lblPesoMiPiace.setBounds(10, 136, 175, 14);
		panel_3.add(lblPesoMiPiace);
		
		textField_MiPiace = new JTextField();
		textField_MiPiace.setText("0");
		textField_MiPiace.setColumns(10);
		textField_MiPiace.setBounds(195, 136, 53, 20);
		panel_3.add(textField_MiPiace);
		textField_MiPiace.addKeyListener(new MyFloatFilter(textField_MiPiace));
		try {
			textField_MiPiace.setText(PropertiesService.getStringProperty("miPiacePaginaScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		
		JLabel lblPesoFollowersDella = new JLabel("Peso followers della pagina:");
		lblPesoFollowersDella.setBounds(10, 161, 175, 14);
		panel_3.add(lblPesoFollowersDella);
		
		textField_followers = new JTextField();
		textField_followers.setText("0");
		textField_followers.setColumns(10);
		textField_followers.setBounds(195, 161, 53, 20);
		panel_3.add(textField_followers);
		textField_followers.addKeyListener(new MyFloatFilter(textField_followers));
		try {
			textField_followers.setText(PropertiesService.getStringProperty("followersPaginaScore"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		
		JLabel lblArcoTemporalePer = new JLabel("Arco temporale per la media");
		lblArcoTemporalePer.setBounds(10, 186, 175, 14);
		panel_3.add(lblArcoTemporalePer);
		
		textField_arcotemporale = new JTextField();
		textField_arcotemporale.addKeyListener(new MyIntFilter());
		textField_arcotemporale.setText("0");
		textField_arcotemporale.setColumns(10);
		textField_arcotemporale.setBounds(195, 186, 53, 20);
		panel_3.add(textField_arcotemporale);
		try {
			textField_arcotemporale.setText(PropertiesService.getStringProperty("daysCheck"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		
		JButton button_3 = new JButton("Default");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_mediaLike.setText(Float.toString(Constants.mediaLikeScore));
				textField_mediaCommenti.setText(Float.toString(Constants.mediaCommentiScore));
				textField_mediaPostGiornalieri.setText(Float.toString(Constants.mediaPostGiornalieriScore));
				textField_mediaCondivisioni.setText(Float.toString(Constants.mediaCondivisioniScore));
				textField_mediaVisualizzazioni.setText(Float.toString(Constants.mediaVisualizzazioniScore));
				textField_MiPiace.setText(Float.toString(Constants.miPiacePaginaScore));
				textField_followers.setText(Float.toString(Constants.followersPaginaScore));
				textField_arcotemporale.setText(Integer.toString(Constants.daysCheck));
			}
		});
		button_3.setBounds(169, 240, 89, 23);
		panel_3.add(button_3);
		
		JButton button_4 = new JButton("Salva");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] keys= new String[] {"mediaLikeScore","mediaCommentiScore","mediaPostGiornalieraScore","mediaCondivisioniScore","mediaVisualizzazioniScore","miPiacePaginaScore","followersPaginaScore","daysCheck"};
				String[] values= new String[] {textField_mediaLike.getText(),textField_mediaCommenti.getText(),textField_mediaPostGiornalieri.getText(),textField_mediaCondivisioni.getText(),textField_mediaVisualizzazioni.getText(),textField_MiPiace.getText(),textField_followers.getText(),textField_arcotemporale.getText()};
				try {
					PropertiesService.saveParamsChanges(keys,values);
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Salvataggio avvenuto con successo","Bene!",JOptionPane.PLAIN_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				} catch (Exception f) {
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				    System.exit(0);
				}
			}
		});
		button_4.setBounds(320, 240, 89, 23);
		panel_3.add(button_4);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Facebook", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(28, 21, 59, 14);
		panel_2.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 57, 71, 14);
		panel_2.add(lblPassword);
		
		textField_email = new JTextField();
		textField_email.setBounds(156, 18, 158, 20);
		panel_2.add(textField_email);
		textField_email.setColumns(10);
		try {
			textField_email.setText(PropertiesService.getStringProperty("facebookEmail"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(156, 54, 158, 20);
		panel_2.add(textField_password);
		try {
			textField_password.setText(PropertiesService.getStringProperty("facebookPassword"));
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		
		JButton button_2 = new JButton("Salva");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PropertiesService.saveParamsChanges(new String[] {"facebookEmail","facebookPassword"}, new String[] {textField_email.getText(),textField_password.getText()});
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Salvataggio avvenuto con successo","Bene!",JOptionPane.PLAIN_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				} catch (Exception f) {
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				    System.exit(0);
				
			}
			}
		});
		button_2.setBounds(320, 240, 89, 23);
		panel_2.add(button_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Prestazioni", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNumberOfDrivers = new JLabel("Numero di driver:");
		lblNumberOfDrivers.setBounds(10, 11, 175, 14);
		panel_1.add(lblNumberOfDrivers);
		
		textField_driver = new JTextField();
		textField_driver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!((Character.isDigit(vchar)) && Integer.parseInt(textField_driver.getText()+vchar)<=Constants.MAX_DRIVER_PROCESSING_PAGE_NUMBER) ) {
					arg0.consume();
				}
			}
		});
		textField_driver.setBounds(195, 11, 53, 20);
		panel_1.add(textField_driver);
		textField_driver.setColumns(10);
		try {
			textField_driver.setText(new Integer(PropertiesService.getIntProperty("numberOfDrivers")).toString());
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
	

		
		JLabel lblNumberOfPage = new JLabel("Numero di pagina scroll:");
		lblNumberOfPage.setBounds(10, 39, 184, 14);
		panel_1.add(lblNumberOfPage);
		
		textField_pgscroll = new JTextField();
		textField_pgscroll.setColumns(10);
		textField_pgscroll.setBounds(195, 39, 53, 20);
		panel_1.add(textField_pgscroll);
		try {
			textField_pgscroll.setText(new Integer(PropertiesService.getIntProperty("numberOfPagesScroll")).toString());
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		textField_pgscroll.addKeyListener(new MyIntFilter());
		
		JLabel lblNumeroDiPost = new JLabel("Numero di post scroll:");
		lblNumeroDiPost.setBounds(10, 67, 148, 14);
		panel_1.add(lblNumeroDiPost);
		
		textField_postscroll = new JTextField();
		textField_postscroll.setColumns(10);
		textField_postscroll.setBounds(195, 67, 53, 20);
		panel_1.add(textField_postscroll);
		textField_postscroll.addKeyListener(new MyIntFilter());

		
		try {
			textField_postscroll.setText(new Integer(PropertiesService.getIntProperty("numberOfPostScroll")).toString());
		} catch (Exception f) {
			Object[] options = {"OK"};
			 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
		    System.exit(0);
		}
		
		JButton button = new JButton("Salva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PropertiesService.saveParamsChanges(new String[] {"numberOfDrivers","numberOfPagesScroll","numberOfPostScroll"}, new String[] {textField_driver.getText(),textField_pgscroll.getText(),textField_postscroll.getText()});
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Salvataggio avvenuto con successo","Bene!",JOptionPane.PLAIN_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				} catch (Exception f) {
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				    System.exit(0);
				}
			}
		});
		button.setBounds(320, 240, 89, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Default");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_driver.setText(Integer.toString(Constants.numberOfDrivers));
				textField_pgscroll.setText(Integer.toString(Constants.numberOfPagesScroll));
				textField_postscroll.setText(Integer.toString(Constants.numberOfPostScroll));
			}
		});
		button_1.setBounds(169, 240, 89, 23);
		panel_1.add(button_1);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Selenium", null, panel, null);
		panel.setLayout(null);
		
				
				JButton btnNewButton = new JButton("scegli");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
							
							int returnVal=fc.showOpenDialog(ImpostazioniJFrame.this);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								textField.setText(fc.getSelectedFile().getPath());
							} 
						
					}
				});
				btnNewButton.setBounds(331, 24, 78, 21);
				panel.add(btnNewButton);
				
				JLabel lblDriverPath = new JLabel("driver path:");
				lblDriverPath.setBounds(10, 27, 89, 14);
				panel.add(lblDriverPath);
				
				textField = new JTextField();
				textField.setBounds(76, 24, 239, 20);
				panel.add(textField);
				textField.setColumns(10);
				try {
					textField.setText(PropertiesService.getStringProperty("driverPath"));
				} catch (Exception f) {
					Object[] options = {"OK"};
					 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				    System.exit(0);
				}
				
				JButton btnSalva = new JButton("Salva");
				btnSalva.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							PropertiesService.saveParamChanges("driverPath", textField.getText());
							Object[] options = {"OK"};
							 JOptionPane.showOptionDialog(null, "Salvataggio avvenuto con successo","Bene!",JOptionPane.PLAIN_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
						} catch (Exception f) {
							Object[] options = {"OK"};
							 JOptionPane.showOptionDialog(null, "Errore con il file di configurazione. Controlla la presenza del file config.properties.","Errore!",JOptionPane.PLAIN_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
						    System.exit(0);
						}
					}
				});
				btnSalva.setBounds(320, 240, 89, 23);
				panel.add(btnSalva);
				
				JButton btnDefault = new JButton("Default");
				btnDefault.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText(Constants.driverPath);
					}
				});
				btnDefault.setBounds(169, 240, 89, 23);
				panel.add(btnDefault);
		
		 fc = new JFileChooser(System.getProperty("user.home")+ "/Desktop");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc.setFileFilter(new FileNameExtensionFilter("exe file", "exe"));
		
	}

}
