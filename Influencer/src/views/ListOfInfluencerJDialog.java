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

public class ListOfInfluencerJDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JList<String> list;
	private DefaultListModel<String> listModel;

	//private List<InfluencerModel> listInfluenzer;

	

	/**
	 * Create the dialog.
	 */
	public ListOfInfluencerJDialog(List<InfluencerModel> listInfluenzer) {

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
				
				for (InfluencerModel influencer:listInfluenzer) {
					listModel.addElement(influencer.toString());
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

			textField = new JTextField();
			textField.setBounds(80, 142, 301, 22);
			contentPanel.add(textField);
			textField.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(80, 174, 301, 22);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(80, 208, 301, 22);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);




			

			


			MouseListener mouseListener = new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			        if (e.getClickCount() == 1) {

			        	
			        	
			        	textField.setText(listInfluenzer.get(list.getSelectedIndex()).getNome());
			        	textField_2.setText(listInfluenzer.get(list.getSelectedIndex()).getLinkProfilo());
			        	textField_3.setText(Float.toString(listInfluenzer.get(list.getSelectedIndex()).getScore()));
			         }
			    }
			};
			list.addMouseListener(mouseListener);





		}
	}
}
