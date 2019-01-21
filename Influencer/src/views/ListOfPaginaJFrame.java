package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utility.ConfigFileNotFoundException;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;

public class ListOfPaginaJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTextField;
	private JTextField urlTextField;
	private JTextField ScoreTextField;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JTextField textFieldMediaCommenti;
	private JTextField textFieldMediaCondivisioni;
	private JTextField textFieldMediaLike;
	private JTextField textFieldMediaVisualizzazioni;
	private JTextField textFieldMmiPiace;
	private JTextField textFieldFollowers;
	private JTextField textFieldTempoDIEsecuzione;
	private JTextField textFieldImpiegati;
	private JTextField textFieldPagineProcessate;
	private JTextField mediaPostAlGiornoTextField;

	private JFileChooser fc;


	/**
	 * Create the dialog.
	 */
	public ListOfPaginaJFrame(List<PaginaModel> listPagine, int tempodiEsecuzione) {

		
		
		setTitle("Risultati");
		setBounds(100, 100, 450, 681);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 13, 408, 99);
			contentPanel.add(scrollPane);
			{

				listModel = new DefaultListModel<>();

				for (PaginaModel pagina: listPagine) {
					listModel.addElement(pagina.toString());
				}

				list=new JList<>(listModel);
				scrollPane.setViewportView(list);
			}
			{
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setBounds(12, 145, 56, 16);
				contentPanel.add(lblNome);
			}
			{
				JLabel lblUrl = new JLabel("Url:");
				lblUrl.setBounds(12, 174, 56, 16);
				contentPanel.add(lblUrl);
			}
			{
				JLabel lblScore = new JLabel("Score:");
				lblScore.setBounds(12, 211, 56, 16);
				contentPanel.add(lblScore);
			}

			nomeTextField = new JTextField();
			nomeTextField.setBounds(80, 142, 301, 22);
			contentPanel.add(nomeTextField);
			nomeTextField.setColumns(10);

			urlTextField = new JTextField();
			urlTextField.setBounds(80, 174, 301, 22);
			contentPanel.add(urlTextField);
			urlTextField.setColumns(10);

			ScoreTextField = new JTextField();
			ScoreTextField.setBounds(80, 208, 301, 22);
			contentPanel.add(ScoreTextField);
			ScoreTextField.setColumns(10);
			{
				JLabel lblDettagliRisultato = new JLabel("Dettagli risultato (selezina dalla lista)");
				lblDettagliRisultato.setBounds(10, 120, 371, 14);
				contentPanel.add(lblDettagliRisultato);
			}

			JLabel lblMediaCommenti = new JLabel("Media Commenti");
			lblMediaCommenti.setBounds(12, 245, 153, 16);
			contentPanel.add(lblMediaCommenti);

			textFieldMediaCommenti = new JTextField();
			textFieldMediaCommenti.setColumns(10);
			textFieldMediaCommenti.setBounds(228, 242, 153, 22);
			contentPanel.add(textFieldMediaCommenti);

			JLabel lblMediaCondivisioni = new JLabel("Media Condivisioni");
			lblMediaCondivisioni.setBounds(12, 274, 153, 16);
			contentPanel.add(lblMediaCondivisioni);

			textFieldMediaCondivisioni = new JTextField();
			textFieldMediaCondivisioni.setColumns(10);
			textFieldMediaCondivisioni.setBounds(228, 271, 153, 22);
			contentPanel.add(textFieldMediaCondivisioni);

			JLabel lblMediaLike = new JLabel("Media Like");
			lblMediaLike.setBounds(12, 306, 153, 16);
			contentPanel.add(lblMediaLike);

			textFieldMediaLike = new JTextField();
			textFieldMediaLike.setColumns(10);
			textFieldMediaLike.setBounds(228, 303, 153, 22);
			contentPanel.add(textFieldMediaLike);

			JLabel lblMediaVisualizzazioni = new JLabel("Media Visualizzazioni");
			lblMediaVisualizzazioni.setBounds(12, 341, 153, 16);
			contentPanel.add(lblMediaVisualizzazioni);

			textFieldMediaVisualizzazioni = new JTextField();
			textFieldMediaVisualizzazioni.setColumns(10);
			textFieldMediaVisualizzazioni.setBounds(228, 338, 153, 22);
			contentPanel.add(textFieldMediaVisualizzazioni);

			JLabel lblMiPiace = new JLabel("Mi piace");
			lblMiPiace.setBounds(12, 371, 153, 16);
			contentPanel.add(lblMiPiace);

			textFieldMmiPiace = new JTextField();
			textFieldMmiPiace.setColumns(10);
			textFieldMmiPiace.setBounds(228, 368, 153, 22);
			contentPanel.add(textFieldMmiPiace);

			textFieldFollowers = new JTextField();
			textFieldFollowers.setColumns(10);
			textFieldFollowers.setBounds(228, 401, 153, 22);
			contentPanel.add(textFieldFollowers);

			JLabel lblFollowers = new JLabel("Followers");
			lblFollowers.setBounds(12, 404, 153, 16);
			contentPanel.add(lblFollowers);

			JLabel lblDettagliEsecuzione = new JLabel("Dettagli processing");
			lblDettagliEsecuzione.setBounds(12, 507, 153, 14);
			contentPanel.add(lblDettagliEsecuzione);

			JLabel lblTempoDiEsecuzione = new JLabel("Tempo di esecuzione");
			lblTempoDiEsecuzione.setBounds(12, 535, 153, 16);
			contentPanel.add(lblTempoDiEsecuzione);

			textFieldTempoDIEsecuzione = new JTextField();
			textFieldTempoDIEsecuzione.setColumns(10);
			textFieldTempoDIEsecuzione.setBounds(228, 532, 153, 22);
			contentPanel.add(textFieldTempoDIEsecuzione);
			textFieldTempoDIEsecuzione.setText(new Integer(tempodiEsecuzione).toString()+"s");

			JLabel lblDriversImpiegati = new JLabel("Drivers impiegati");
			lblDriversImpiegati.setBounds(12, 565, 153, 16);
			contentPanel.add(lblDriversImpiegati);

			textFieldImpiegati = new JTextField();
			textFieldImpiegati.setColumns(10);
			textFieldImpiegati.setBounds(228, 562, 153, 22);
			contentPanel.add(textFieldImpiegati);
			try {
				textFieldImpiegati.setText(PropertiesService.getStringProperty("numberOfDrivers"));
			} catch (PropertiesNotFoundException e1) {
				e1.printStackTrace();
			} catch (ConfigFileNotFoundException e1) {
				e1.printStackTrace();
			}

			JLabel lblPagineElaborate = new JLabel("Pagine processate");
			lblPagineElaborate.setBounds(12, 595, 153, 16);
			contentPanel.add(lblPagineElaborate);


			textFieldPagineProcessate = new JTextField();
			textFieldPagineProcessate.setColumns(10);
			textFieldPagineProcessate.setBounds(228, 592, 153, 22);
			contentPanel.add(textFieldPagineProcessate);
			textFieldPagineProcessate.setText(new Integer(listPagine.size()).toString());


			JLabel lblMediaPostAl = new JLabel("Media post al giorno");
			lblMediaPostAl.setBounds(12, 434, 153, 16);
			contentPanel.add(lblMediaPostAl);

			mediaPostAlGiornoTextField = new JTextField();
			mediaPostAlGiornoTextField.setColumns(10);
			mediaPostAlGiornoTextField.setBounds(228, 431, 153, 22);
			contentPanel.add(mediaPostAlGiornoTextField);

			fc = new JFileChooser(System.getProperty("user.home")+ "/Desktop");
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));

			JButton btnEsporta = new JButton("esporta");
			btnEsporta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (list.isSelectionEmpty())  {
						return;
					}
					try {
						
						int returnVal=fc.showSaveDialog(ListOfPaginaJFrame.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							//File file = fc.getSelectedFile();
							String filename = fc.getSelectedFile().toString();
							   if (!filename .endsWith(".txt"))
							        filename += ".txt";
							 
							Path path = Paths.get(filename);
							byte[] strToBytes = ((PaginaModel)listPagine.get(list.getSelectedIndex())).toExportString().getBytes();
							Files.write(path, strToBytes);
						} 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnEsporta.setBounds(292, 474, 89, 23);
			contentPanel.add(btnEsporta);








			MouseListener mouseListener = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {



						nomeTextField.setText(listPagine.get(list.getSelectedIndex()).getNomePagina());
						urlTextField.setText(listPagine.get(list.getSelectedIndex()).getUrl());
						ScoreTextField.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getFinalScore()));
						textFieldMediaCommenti.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMediaCommenti()));
						textFieldMediaCondivisioni.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMediaCondivisioni()));
						textFieldMediaLike.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMediaLike()));
						textFieldMediaVisualizzazioni.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMediaVisualizzazioni()));
						textFieldMmiPiace.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMiPiacePagina()));
						textFieldFollowers.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getFollowersPagina()));
						mediaPostAlGiornoTextField.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getMediaPostGiornaliera()));
					}
				}
			};
			list.addMouseListener(mouseListener);





		}
	}
}
