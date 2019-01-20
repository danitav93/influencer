package views;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

public class ListOfPaginaJDialog extends JDialog {

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
	
	//private List<InfluencerModel> listInfluenzer;

	

	/**
	 * Create the dialog.
	 */
	public ListOfPaginaJDialog(List<PaginaModel> listPagine) {

		setTitle("Risultati");
		setBounds(100, 100, 450, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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




			

			


			MouseListener mouseListener = new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			        if (e.getClickCount() == 1) {

			        	
			        	
			        	nomeTextField.setText(listPagine.get(list.getSelectedIndex()).getNomePagina());
			        	urlTextField.setText(listPagine.get(list.getSelectedIndex()).getUrl());
			        	ScoreTextField.setText(Float.toString(listPagine.get(list.getSelectedIndex()).getFinalScore()));
			         }
			    }
			};
			list.addMouseListener(mouseListener);





		}
	}
}
