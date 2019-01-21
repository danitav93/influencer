package Utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MyIntFilter extends KeyAdapter {
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		char vchar = arg0.getKeyChar();
		if (!Character.isDigit(vchar) ) {
			arg0.consume();
		}
	}

}
