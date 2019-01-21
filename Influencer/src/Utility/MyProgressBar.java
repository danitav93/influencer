package Utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class MyProgressBar extends JFrame {

	
	private static final long serialVersionUID = -903675736333065944L;
	
	private JProgressBar pb;
	
	private JTextArea textArea;
	
	private int driverAttivi=0;
	
	private JLabel driversAttiviLabel;
	
	private int secondiTrascorsi=0;
	
	public MyProgressBar(int maxValue) {
		
		
		
		pb= new JProgressBar();
		pb.setBounds(46,41,250,30);
		pb.setValue(0);
		pb.setStringPainted(true);
		pb.setMaximum(maxValue*4);
		getContentPane().add(pb);
		this.setSize(367,450);
		getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		textArea = new JTextArea();
		textArea.setBounds(20, 179, 309, 210);
		getContentPane().add(textArea);
		
		JLabel lblProgresso = new JLabel("Progresso");
		lblProgresso.setBounds(146, 11, 89, 14);
		getContentPane().add(lblProgresso);
		
		JLabel lblStorico = new JLabel("Storico");
		lblStorico.setBounds(20, 162, 101, 14);
		getContentPane().add(lblStorico);
		
		JLabel lblPagineDaProcessare = new JLabel("Pagine da processare:");
		lblPagineDaProcessare.setBounds(20, 82, 162, 14);
		getContentPane().add(lblPagineDaProcessare);
		
		JLabel lblNewLabel = new JLabel("Drivers attivi:");
		lblNewLabel.setBounds(20, 107, 143, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel pagToProcess = new JLabel("");
		pagToProcess.setBounds(212, 82, 58, 14);
		getContentPane().add(pagToProcess);
		pagToProcess.setText(new Integer(maxValue).toString());
		
		driversAttiviLabel = new JLabel("");
		driversAttiviLabel.setBounds(212, 107, 46, 14);
		getContentPane().add(driversAttiviLabel);
		
		JLabel labltmp = new JLabel("Tempo trascorso:");
		labltmp.setBounds(20, 132, 162, 14);
		getContentPane().add(labltmp);
		
		JLabel tempoTrascorso = new JLabel("");
		tempoTrascorso.setBounds(212, 132, 46, 14);
		getContentPane().add(tempoTrascorso);
		
		Timer t = new Timer( );
		t.scheduleAtFixedRate(new TimerTask() {

			
		    @Override
		    public void run() {
		    	try {
		    	secondiTrascorsi++;
		    	tempoTrascorso.setText(new Integer(secondiTrascorsi).toString()+"s");
		    	} catch (Exception e) {
		    		t.cancel();
		    	}

		    }
		}, 0,1000);
	}
	
	//update the pb
	public void update(String string, Integer value) {
		if (string!=null) {
			textArea.append(string+"\n");
		}
		
		if (value!=null) {
			pb.setValue(value);
		}
		
	}
	
	public void updateDriver(int menoPiu) {
		if (menoPiu==0) {
			driverAttivi--;
		} else {
			driverAttivi++;
		}
		driversAttiviLabel.setText(new Integer(driverAttivi).toString());
	}
	
	public int getValue() {
		return pb.getValue();
	}

	public int getTempoDiEsecuzione() {
		return secondiTrascorsi;
	}
}
