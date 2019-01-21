package Utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class MyFloatFilter extends KeyAdapter {

	private JTextField jtextField;

	public  MyFloatFilter(JTextField jtextField) {
		this.jtextField=jtextField;
	}

	@Override
	public void keyTyped(KeyEvent arg0)  {
		char vchar = arg0.getKeyChar();
		if (!(Character.isDigit(vchar) || (vchar=='.' && !jtextField.getText().contains(".")))   ) {
			arg0.consume();
		}
	}
}
